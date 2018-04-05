package com.cantonsoft.admin.account;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.core.account.ClientUserIdentity;
import com.cantonsoft.core.account.LoginSuccessProcessor;
import com.cantonsoft.core.account.PartnerUserIdentity;
import com.cantonsoft.core.account.PasswordUpdation;
import com.cantonsoft.core.account.UserIdentity;
import com.cantonsoft.core.account.UserSession;
import com.cantonsoft.core.account.user.AuthService;
import com.cantonsoft.core.account.user.GenericUserService;
import com.cantonsoft.core.account.user.UserProfile;
import com.cantonsoft.core.account.user.UserSettingService;
import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.core.account.user.model.UserSetting;
import com.cantonsoft.core.cloud.client.ClientService;
import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.core.cloud.partner.PartnerService;
import com.cantonsoft.core.cloud.partner.model.Partner;
import com.cantonsoft.core.cloud.site.SiteService;
import com.cantonsoft.admin.account.auth.MultiDomainUserToken;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.captcha.CaptchaHelper;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.service2.ApiSupport;
import com.cantonsoft.framework.mvc.util.SessionUtil;
import com.cantonsoft.framework.security.SessionHelper;
import com.cantonsoft.framework.validation.IValidationDirector;

@JsonApi
@Lazy
public class AccountApi extends ApiSupport {
	@Autowired
	private SessionHelper<GenericUser> sessionHelper;
	@Autowired
	private AuthService authService;
	@Autowired
	private GenericUserService genericUserService;
	@Autowired
	private UserSettingService userSettingService;
	@Autowired
	private PartnerService partnerService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private CaptchaHelper captchaHelper;
	@Autowired
	protected IValidationDirector vDirector;
	@Autowired
	private SiteService siteService;
	@Autowired
	private ShiroSessionHelper shiroSessionHelper;
	
	@Autowired(required = false)
	private LoginSuccessProcessor loginSuccessProcessor;
	
	@RequestMapping(value = "platform/login.json", method = RequestMethod.POST)
	public @ResponseBody Object login(@RequestBody UserIdentity identity, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		vDirector.findValidator("login").validate(identity);

		Map<String, Object> map = new HashMap<String, Object>();
		return commonLoginLogic(UserAuth.AUTH_TYPE_MAIN, identity, map, request, response);
	}
	
	@RequestMapping(value = "partner/login.json", method = RequestMethod.POST)
	public @ResponseBody Object loginPartner(@RequestBody PartnerUserIdentity identity, HttpServletRequest request, HttpServletResponse response)
	{
		vDirector.findValidator("loginPartner").validate(identity);
		Map<String, Object> map = new HashMap<String, Object>();
		Partner partner = partnerService.findByTitle(identity.getPartner());
		
		if (null == partner)
		{
			throw new ServiceException("error.login.partner.notexists");
		}
		else
		{
			identity.setUsername(identity.getUsername() + "@" + partner.getId());
		}
		return commonLoginLogic(UserAuth.AUTH_TYPE_PARTNER, identity, map, request, response);
	}
	
	@RequestMapping(value = "client/login.json", method = RequestMethod.POST)
	public @ResponseBody Object loginClient(@RequestBody ClientUserIdentity identity, HttpServletRequest request, HttpServletResponse response)
	{
		vDirector.findValidator("loginClient").validate(identity);
		Map<String, Object> map = new HashMap<String, Object>();
		Client client = clientService.findByTitle(identity.getClient());
		
		if (null == client)
		{
			throw new ServiceException("error.login.client.notexists");
		}
		else
		{
			identity.setUsername(identity.getUsername() + "@" + client.getId());
		}
		return commonLoginLogic(UserAuth.AUTH_TYPE_CLIENT, identity, map, request, response);
	}
	
	private Object commonLoginLogic(String domain, UserIdentity identity, Map<String, Object> map, HttpServletRequest request, HttpServletResponse response)
	{
		boolean isSecureCheck = false;
		if(!StringUtils.isEmpty(identity.getVerifyCode()))
		{
			captchaHelper.checkCaptcha(request, identity.getVerifyCode());
			isSecureCheck = true;
		}
		
		String ip = request.getRemoteAddr();
		try
		{
			checkLogin(domain, identity, isSecureCheck);
		}
		catch (Exception ex)
		{
			authService.updateLoginStatus(domain, identity.getUsername(), false, ip);
			throw ex;
		}

		GenericUser user = sessionHelper.getCurrentUser();
	
		UserSession acc = new UserSession(user);
		authService.updateLoginStatus(user.getDomain(), identity.getUsername(), true, ip);
		if (loginSuccessProcessor != null)
		{
			loginSuccessProcessor.process(user);
		}
		map.put("session", acc);
		
		Map<String, String> setting = userSettingService.getUserSettings(user.getDomain(), user.getId());
		String lang = setting.get(UserSetting.TYPE_LANGUAGE);
		
		if (!StringUtils.isEmpty(lang))
		{
			SessionUtil.setLocale(new Locale(lang));
		}
			
		return ApiResponse.make(map);
	}

	@Access(value = "fn.common.profile")
	@RequestMapping(value = "account/profile/update.json", method = RequestMethod.POST)
	public @ResponseBody Object updateProfile(@RequestBody UserProfile profile, HttpServletRequest request, HttpServletResponse response)
	{	
		GenericUser user = sessionHelper.getCurrentUser();
		genericUserService.saveUser(user.getDomain(), user.getId(), profile);
		sessionHelper.getCurrentUser().setImage(profile.getImage());
		sessionHelper.getCurrentUser().setTitle(user.getTitle());
		
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
	}

	@Access(value = "fn.common.password")
	@RequestMapping(value = "account/password/update.json", method = RequestMethod.POST)
	public @ResponseBody Object updatePassword(@RequestBody PasswordUpdation data, HttpServletRequest request, HttpServletResponse response)
	{
		vDirector.findValidator("changePassword").validate(data);
		GenericUser user = sessionHelper.getCurrentUser();
		authService.updatePassword(user.getDomain(), user.getId(), data.getOldPassword(), data.getPassword());
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
	}

	@Access(value = "fn.common.setting")
	@RequestMapping(value = "account/setting/theme.json", method = RequestMethod.POST)
	public @ResponseBody Object theme(@RequestParam String value, HttpServletRequest request, HttpServletResponse response) {
		GenericUser user = sessionHelper.getCurrentUser();
		userSettingService.save(user.getDomain(), user.getId(), UserSetting.TYPE_THEME, value);
		return ApiResponse.make(null);
	}

	@Access(value = "fn.common.setting")
	@RequestMapping(value = "account/setting/language.json", method = RequestMethod.POST)
	public @ResponseBody Object language(@RequestParam String value, HttpServletRequest request, HttpServletResponse response) {
		GenericUser user = sessionHelper.getCurrentUser();
		userSettingService.save(user.getDomain(), user.getId(), UserSetting.TYPE_LANGUAGE, value);
		if (UserSetting.LANGUAGE_EN.equals(value)) {
			request.getSession().setAttribute(SessionUtil.KEY_LOCALE, new Locale(UserSetting.LANGUAGE_EN));
		} else {
			request.getSession().setAttribute(SessionUtil.KEY_LOCALE, new Locale(UserSetting.LANGUAGE_ZH));
		}
		return ApiResponse.make(null);
	}
	
	private void checkLogin(String domain, UserIdentity identity, boolean secureCheck)
	{
		try
		{
			MultiDomainUserToken token = new MultiDomainUserToken(domain, identity.getUsername(), identity.getPassword(), identity.isRemember());
			token.setSecure(secureCheck);
			sessionHelper.login(token);
		}
		catch (AuthenticationException authEx)
		{
			throw new ServiceException(authEx.getMessage());
		}
	}
}
