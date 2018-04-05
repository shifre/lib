package com.cantonsoft.core.account.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.account.user.dao.PartnerUserDao;
import com.cantonsoft.core.account.user.dao.UserAuthDao;
import com.cantonsoft.core.account.user.model.PartnerUser;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class PartnerUserService extends BaseEntityService<PartnerUser, Long> {

	@Autowired
	private PartnerUserDao partnerUserDao;
	@Autowired
	private UserAuthDao userAuthDao;
	@Autowired
	private AuthService authService;

	@Override
	protected BaseEntityDao<PartnerUser, Long> getDao() {
		return partnerUserDao;
	}

	@Override
	public void delete(Long id) {
		UserAuth userAuth =	userAuthDao.findByDomainAndUserId(UserAuth.AUTH_TYPE_PARTNER, id);
		userAuthDao.delete(userAuth);
		super.delete(id);
	}

	@Override
	public void deleteEntity(PartnerUser entity) {
		UserAuth userAuth =	userAuthDao.findByDomainAndUserId(UserAuth.AUTH_TYPE_PARTNER, entity.getId());
		userAuthDao.delete(userAuth);
		super.deleteEntity(entity);
	}
	
	public void addWithPassword(PartnerUser user, String username, String password) {
		super.add(user);
		authService.createAuthInfo(UserAuth.AUTH_TYPE_PARTNER, user.getId(), username, password);
	}
	
	public void updateWithPassword(PartnerUser user, String password) {
		this.update(user);
		authService.updatePassword(UserAuth.AUTH_TYPE_PARTNER, user.getId(), password);
	}
}