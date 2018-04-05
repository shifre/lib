package com.cantonsoft.core.account.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.account.user.dao.ClientUserDao;
import com.cantonsoft.core.account.user.dao.UserAuthDao;
import com.cantonsoft.core.account.user.model.ClientUser;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class ClientUserService extends BaseEntityService<ClientUser, Long> {

	@Autowired
	private ClientUserDao clientUserDao;
	@Autowired
	private UserAuthDao userAuthDao;
	@Autowired
	private AuthService authService;
	
	@Override
	protected BaseEntityDao<ClientUser, Long> getDao() {
		return clientUserDao;
	}
	@Override
	public void delete(Long id) {
		UserAuth userAuth =	userAuthDao.findByDomainAndUserId(UserAuth.AUTH_TYPE_CLIENT, id);
		userAuthDao.delete(userAuth);
		super.delete(id);
	}
	@Override
	public void deleteEntity(ClientUser entity) {
		UserAuth userAuth =	userAuthDao.findByDomainAndUserId(UserAuth.AUTH_TYPE_CLIENT, entity.getId());
		userAuthDao.delete(userAuth);
		super.deleteEntity(entity);
	}
	
	public void addWithPassword(ClientUser user, String username, String password) {
		this.add(user);
		authService.createAuthInfo(UserAuth.AUTH_TYPE_CLIENT, user.getId(), username, password);
	}
	
	public void updateWithPassword(ClientUser user, String password) {
		this.update(user);
		authService.updatePassword(UserAuth.AUTH_TYPE_CLIENT, user.getId(), password);
	}
}