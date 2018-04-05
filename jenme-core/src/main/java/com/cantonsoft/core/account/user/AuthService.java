package com.cantonsoft.core.account.user;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.account.user.dao.UserAuthDao;
import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.util.StringTool;

@Service
public class AuthService {
	@Autowired
	private UserAuthDao userAuthDao;

	public void updatePassword(String domain, Long userId, String oldPassword, String newPassword) {
		
		UserAuth userAuth = userAuthDao.findByDomainAndUserId(domain, userId);
		if (null != userAuth)
		{
			String encriptedOld = StringTool.getMd5AndBase64EncodeString(oldPassword);
			String encriptedNew = StringTool.getMd5AndBase64EncodeString(newPassword);
			if (userAuth.getPassword().equals(StringUtils.defaultIfBlank(encriptedOld, ""))) {
				userAuthDao.updatePassword(StringUtils.defaultIfBlank(encriptedNew, ""), userAuth.getId());
				return;
			}
		}
		throw new ServiceException("error.update.password");
	}
	
	public void updatePassword(String domain, Long userId, String newPassword) {

		String encriptedNew = StringTool.getMd5AndBase64EncodeString(newPassword);
		userAuthDao.updatePassword(domain, userId, StringUtils.defaultIfBlank(encriptedNew, ""));
	}

	public void updateLoginStatus(String domain, String username, boolean success, String ip)
	{
		UserAuth userAuth = userAuthDao.findByDomainAndUsername(domain, username);
		userAuth.setLastLoginIp(ip);
		if (success)
		{
			userAuth.setErrorCount(0);
		}
		else
		{
			userAuth.setErrorCount(userAuth.getErrorCount() + 1);
		}
		userAuth.setLastLoginTime(new Date());
		userAuthDao.save(userAuth);
	}

	public String findUsername(String domain, Long id)
	{
		UserAuth auth = userAuthDao.findByDomainAndUserId(domain, id);
		return auth.getUniqueUsername();
	}
	
	public String getUsername(GenericUser user)
	{
		UserAuth auth = userAuthDao.findByDomainAndUserId(user.getDomain(), user.getId());
		return auth.getUniqueUsername();
	}
	
	public void checkUniqueUsername(String domain ,String userName) throws ServiceException
	{
		UserAuth auth =	userAuthDao.findByDomainAndUsername(domain, userName);
		if(auth != null)
		{
			throw new ServiceException("error.user.name.employ");
		}
	}
	
	public void checkUpdateUserName(Long id, String domain ,String userName) throws ServiceException
	{
		UserAuth auth =	userAuthDao.findByDomainAndUsername(domain, userName);
		if(auth != null && !auth.getUserId().equals(id))
		{
			throw new ServiceException("error.user.name.employ");
		}
	}
	
	public void createAuthInfo(String domain, Long id, String username, String password) {
		checkUniqueUsername(domain, username);
		UserAuth auth = new UserAuth();
		auth.setDomain(domain);
		auth.setUserId(id);
		auth.setUsername(username);
		auth.setPassword(StringTool.getMd5AndBase64EncodeString(password));
		auth.setUpdatedPasswordTime(new Date());
		userAuthDao.save(auth);
	}
}
