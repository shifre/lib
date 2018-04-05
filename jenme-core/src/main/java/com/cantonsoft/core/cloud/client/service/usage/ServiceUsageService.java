package com.cantonsoft.core.cloud.client.service.usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.cloud.client.service.usage.model.ServiceUsageDao;
import com.cantonsoft.core.cloud.client.service.usage.model.ServiceUsageView;
import com.cantonsoft.framework.mvc.model.IEntityDao;
import com.cantonsoft.framework.mvc.service.CrudService;

@Service
@Lazy
public class ServiceUsageService extends CrudService<ServiceUsageView> {
	@Autowired
	private ServiceUsageDao dao;

	@Override
	protected IEntityDao<ServiceUsageView> getEntityDao() {
		return dao;
	}
}
