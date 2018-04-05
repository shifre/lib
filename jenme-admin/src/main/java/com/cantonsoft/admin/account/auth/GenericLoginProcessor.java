package com.cantonsoft.admin.account.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cantonsoft.core.account.LoginSuccessProcessor;
import com.cantonsoft.core.account.user.model.ClientUser;
import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.core.account.user.model.PartnerUser;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.core.cloud.client.ClientService;
import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.core.cloud.partner.PartnerService;
import com.cantonsoft.core.cloud.partner.model.Partner;
import com.cantonsoft.core.cloud.site.SiteService;
import com.cantonsoft.core.cloud.site.model.Site;
import com.cantonsoft.framework.exception.ServiceException;

@Component
@Lazy
public class GenericLoginProcessor implements LoginSuccessProcessor {

	@Autowired
	private PartnerService partnerService;
	@Autowired
	private ClientService clientService;
	@Autowired 
	private ShiroSessionHelper sessionHelper;
	@Autowired
	private SiteService siteService;
	
	@Override
	public void process(GenericUser user) {
		if (UserAuth.AUTH_TYPE_PARTNER.equals(user.getDomain()))
		{
			Partner partner = partnerService.find(((PartnerUser)user).getPartnerId());
			sessionHelper.getSession().setPartner(partner);
		}
		else if (UserAuth.AUTH_TYPE_CLIENT.equals(user.getDomain()))
		{
			Client client = clientService.find(((ClientUser)user).getClientId());
			List<Site> sites = siteService.findByClientId(client.getId());
			if(sites == null || sites.isEmpty())
			{
				throw new ServiceException("error.login.client.not.create.site");
			}
			sessionHelper.getSession().setSite(sites.get(0));
			sessionHelper.getSession().setClient(client);
		}
	}
}
