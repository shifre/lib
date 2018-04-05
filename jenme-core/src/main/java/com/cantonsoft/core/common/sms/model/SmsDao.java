package com.cantonsoft.core.common.sms.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Lazy
@Repository
public interface SmsDao extends BaseEntityDao<Sms, Long> {
	public List<Sms> findByStatus(Integer status);
}
