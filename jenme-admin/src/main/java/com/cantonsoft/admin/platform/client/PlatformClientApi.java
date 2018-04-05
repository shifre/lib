package com.cantonsoft.admin.platform.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.cloud.client.ClientService;
import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.core.cloud.client.model.ClientUpdate;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@JsonApi
@Lazy
@RequestMapping(value = "platform/client")
@Access(value = "fn.main.client")
public class PlatformClientApi extends BaseEntityApi<Client, Long, Client, ClientUpdate>{
	@Autowired
	private ClientService service;
	
	@Override
	protected BaseEntityService<Client, Long> getEntityService() {
		return service;
	}

	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title").addField("type");
	}
}
