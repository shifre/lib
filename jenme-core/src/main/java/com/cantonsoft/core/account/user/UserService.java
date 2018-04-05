package com.cantonsoft.core.account.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.account.user.dao.UserAuthDao;
import com.cantonsoft.core.account.user.dao.UserDao;
import com.cantonsoft.core.account.user.model.User;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class UserService extends BaseEntityService<User, Long> {
	@Autowired
	private UserAuthDao userAuthDao;
	@Autowired
	private AuthService authService;
	@Autowired
	private UserDao userDao;
	
	@Override
	protected UserDao getDao() {
		return userDao;
	}

	@Override
	public void delete(Long key) {
		UserAuth userAuth =	userAuthDao.findByDomainAndUserId(UserAuth.AUTH_TYPE_MAIN, Long.parseLong(key.toString()));
		userAuthDao.delete(userAuth.getId());
		
		super.delete(key);
	}
	
	public void addWithPassword(User user, String username, String password) {
		this.validateAdd(user);
		super.add(user);
		
		authService.createAuthInfo(UserAuth.AUTH_TYPE_MAIN, user.getId(), username, password);
	}
	
	public void updateWithPassword(User user, String password) {
		this.update(user);
		authService.updatePassword(UserAuth.AUTH_TYPE_MAIN, user.getId(), password);
	}
}