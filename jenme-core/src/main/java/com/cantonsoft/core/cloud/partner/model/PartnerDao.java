package com.cantonsoft.core.cloud.partner.model;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

public interface PartnerDao extends BaseEntityDao<Partner, Long> {
	Partner findByTitle(String title);
}