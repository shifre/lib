package com.cantonsoft.core.cloud.site.view.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
@Lazy
public interface SiteViewDao extends BaseEntityDao<SiteView, Long>{
	
	 List<SiteView> findByPartnerId(Long partnerId);
}
