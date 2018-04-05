package com.cantonsoft.admin.partner.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cantonsoft.core.account.user.PartnerUserService;
import com.cantonsoft.core.account.user.model.PartnerUser;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.mvc.service.EntityAccessControl;

@Component
@Lazy
public class PartnerUserAccessControl extends EntityAccessControl<Long> {

	@Autowired
	private PartnerUserService service;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	@Override
	protected boolean canRead(Long id) {
		PartnerUser user = service.find(id);
		return sessionHelper.getSession().getPartner().getId().equals(user.getPartnerId());
	}
	@Override
	protected boolean canWrite(Long id) {

		PartnerUser user = service.find(id);
		return sessionHelper.getSession().getPartner().getId().equals(user.getPartnerId());
	}
}
