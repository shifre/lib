package com.cantonsoft.core.cloud.client.service.constract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.cloud.client.service.constract.model.ServiceContract;
import com.cantonsoft.core.cloud.client.service.constract.model.ServiceContractDao;
import com.cantonsoft.framework.mvc.model.IEntityDao;
import com.cantonsoft.framework.mvc.service.CrudService;

@Service
@Lazy
public class ServiceContractService extends CrudService<ServiceContract> {
	@Autowired
	private ServiceContractDao dao;

	@Override
	protected IEntityDao<ServiceContract> getEntityDao() {
		return dao;
	}
}
