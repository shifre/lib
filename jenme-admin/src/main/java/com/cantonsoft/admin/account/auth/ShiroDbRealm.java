package com.cantonsoft.admin.account.auth;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cantonsoft.core.account.user.GenericUserService;
import com.cantonsoft.core.account.user.dao.UserAuthDao;
import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.framework.security.shiro.StringPermissionResolver;
import com.cantonsoft.framework.util.StringTool;

public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger LOG = LogFactory.getLogger(ShiroDbRealm.class);

	private Map<String, String> defaultPermissions;
	@Autowired
	private UserAuthDao userAuthDao;
	@Autowired
	private GenericUserService genericUserService;
	
	public ShiroDbRealm() {
		super();
		this.setPermissionResolver(new StringPermissionResolver());
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		MultiDomainUserToken token = (MultiDomainUserToken) authToken;	
		UserAuth auth = userAuthDao.findByDomainAndUsername(token.getDomain(), token.getUsername());
		
		if (null == auth)
		{
	 		throw new AuthenticationException("error.login.failed");
		}
		if (!token.isSecure() && auth.isWarning())
		{
			throw new AuthenticationException("error.login.warn");
		}
		if (!StringTool.getMd5AndBase64EncodeString(String.valueOf(token.getPassword())).equals(auth.getPassword()))
		{
	 		throw new AuthenticationException("error.login.failed");
		}

		GenericUser user = genericUserService.findUser(token.getDomain(), auth.getUserId());
		if(!user.isEnabled())
 		{
	 		throw new AuthenticationException("error.login.disabled");
 		}
 		SessionStorage shiroUser = new SessionStorage(user);
 		return new SimpleAuthenticationInfo(shiroUser, token.getPassword(), getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SessionStorage shiroUser = (SessionStorage) principals.fromRealm(getName()).iterator().next();
		if (null == shiroUser)
		{
			return null;
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		if (null != defaultPermissions)
		{
			String permissions = defaultPermissions.get(shiroUser.getUser().getType() + "@" + shiroUser.getUser().getDomain());
			if (null != permissions)
			{
				String[] permissionArray = permissions.split(",", -1);
				info.addStringPermissions(Arrays.asList(permissionArray));
			}
		}
		return info;
	}
	
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
	
	public Map<String, String> getDefaultPermissions() {
		return defaultPermissions;
	}

	public void setDefaultPermissions(Map<String, String> defaultPermissions) {
		this.defaultPermissions = defaultPermissions;
	}
}
