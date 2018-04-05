package com.cantonsoft.admin.account.tag;

import java.util.Map;

import com.cantonsoft.core.account.user.AuthService;
import com.cantonsoft.core.account.user.GenericUserService;
import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.core.cloud.cache.CacheRegister;
import com.cantonsoft.core.cloud.cache.UniqueUserKey;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.cache.TwoLevelCacheManager;
import com.cantonsoft.framework.mvc.util.ApplicationUtil;
import com.cantonsoft.framework.security.Menu;

public class UserDataHelper {
	public static <T> T getBean(Class<T> clazz)
	{
		return ApplicationUtil.WEBAPP_CONTEXT.getBean(clazz);
	}
	
	public static Menu getUserMenu()
	{
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getUserMenu();
	}

	public static Menu getSiteMenu()
	{
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSiteMenu();
	}

	public static Object getUserSetting(String key)
	{
		GenericUser user = getCurrentUser();
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		Map<String, String> setting = (Map<String, String>)cacheManager.getData(CacheRegister.USER_SETTING, new UniqueUserKey(user.getDomain(), user.getId()));
		
		return null == setting? null : setting.get(key);
	}
	
	public static GenericUser getCurrentUser()
	{
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getCurrentUser();
	}
	
	public static Object getUsername(Object user)
	{
		if (null == user)
		{
			return "";
		}
		AuthService authService = getBean(AuthService.class);
		return authService.getUsername((GenericUser)user);
	}
	
	public static Menu getMenuByAccess(String access)
	{
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSession().getAccessMenu().get(access);
	}
	
	public static Object findUser(String domain, Long id)
	{
		GenericUserService us = getBean(GenericUserService.class);
		return us.findUser(domain, id);
	}
	
	public static Object getCurrentUserUpdateInfo()
	{
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		GenericUser user = sessionHelper.getCurrentUser();
		
		GenericUserService us = getBean(GenericUserService.class);
		return us.findUser(user.getDomain(), user.getId());
	}
	
	public static Object partner()
	{
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSession().getPartner();
	}
	
	public static Object client()
	{
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSession().getClient();
	}
}
