package com.cantonsoft.core.cloud.site.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
@Lazy
public interface SiteDao extends BaseEntityDao<Site,Long>{

	Site findByTitle(String title);
	Site findByDomain(String domain);
	List<Site> findByClientId(Long clientId);
}
