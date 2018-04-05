package com.cantonsoft.admin.client.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cantonsoft.core.account.user.ClientUserService;
import com.cantonsoft.core.account.user.model.ClientUser;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.mvc.service.EntityAccessControl;

@Component
@Lazy
public class ClientUserAccessControl extends EntityAccessControl<Long> {

	@Autowired
	private ClientUserService service;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	@Override
	protected boolean canRead(Long id) {
		ClientUser user = service.find(id);
		return sessionHelper.getSession().getClient().getId().equals(user.getClientId());
	}
	@Override
	protected boolean canWrite(Long id) {

		ClientUser user = service.find(id);
		return sessionHelper.getSession().getClient().getId().equals(user.getClientId());
	}
}
