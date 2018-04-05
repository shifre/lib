package com.cantonsoft.admin.security.cache;

import org.springframework.beans.factory.annotation.Autowired;

import com.cantonsoft.admin.security.model.AcClientPartner;
import com.cantonsoft.core.cloud.client.ClientService;
import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.framework.cache.CacheDataExecutor;

public class AcClientCacheExecutor extends CacheDataExecutor<Long> {



	
	@Autowired
	private ClientService clientService;

	@Override
	protected Object createData(Long key)
	{
		return createAcClient(key);
	}

	private Object createAcClient(Long clientId)
	{
		AcClientPartner obj = new AcClientPartner();
		Client client=clientService.find(clientId);
		if(client!=null){
			obj.setClientId(client.getId());
			obj.setPartnerId(client.getPartnerId());
		}
		return obj;
	}

	
}
