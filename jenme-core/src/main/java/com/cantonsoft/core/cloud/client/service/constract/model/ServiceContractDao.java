package com.cantonsoft.core.cloud.client.service.constract.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.model.EntityDao;

@Repository
@Lazy
public class ServiceContractDao extends EntityDao<ServiceContract> implements IServiceContractDao {

}
