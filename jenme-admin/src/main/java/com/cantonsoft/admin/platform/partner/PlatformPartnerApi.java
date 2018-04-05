package com.cantonsoft.admin.platform.partner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.cloud.partner.PartnerService;
import com.cantonsoft.core.cloud.partner.model.Partner;
import com.cantonsoft.core.cloud.partner.model.PartnerUpdate;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@JsonApi
@Lazy
@RequestMapping(value = "platform/partner")
@Access(value = "fn.main.partner")
public class PlatformPartnerApi extends BaseEntityApi<Partner, Long, Partner, PartnerUpdate>{

	@Autowired
	private PartnerService service;

	@Override
	protected BaseEntityService<Partner, Long> getEntityService() {
		return service;
	}
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}
}
