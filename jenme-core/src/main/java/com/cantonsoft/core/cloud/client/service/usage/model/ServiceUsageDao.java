package com.cantonsoft.core.cloud.client.service.usage.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.model.EntityDao;

@Repository
@Lazy
public class ServiceUsageDao extends EntityDao<ServiceUsageView> implements IServiceUsageDao {
}
