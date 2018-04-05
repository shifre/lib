package com.cantonsoft.admin.partner.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cantonsoft.core.cloud.client.ClientService;
import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.mvc.service.EntityAccessControl;

@Component
@Lazy
public class ClientAccessControl extends EntityAccessControl<Long> {

	@Autowired
	private ClientService service;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	@Override
	protected boolean canRead(Long id) {
		Client client = service.find(id);
		return sessionHelper.getSession().getPartner().getId().equals(client.getPartnerId());
	}
	
	@Override
	protected boolean canWrite(Long id) {
		Client client = service.find(id);
		return sessionHelper.getSession().getPartner().getId().equals(client.getPartnerId());
	}
}
