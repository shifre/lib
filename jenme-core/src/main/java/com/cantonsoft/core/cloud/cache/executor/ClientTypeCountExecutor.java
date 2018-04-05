package com.cantonsoft.core.cloud.cache.executor;

import org.springframework.beans.factory.annotation.Autowired;

import com.cantonsoft.core.cloud.client.ClientService;
import com.cantonsoft.framework.cache.CacheDataExecutor;

public class ClientTypeCountExecutor extends CacheDataExecutor<Long> {
	@Autowired
	private ClientService clientService;
	
	@Override
	protected Object createData(Long key) {
		return clientService.findTypeCount(key);
	}
}
