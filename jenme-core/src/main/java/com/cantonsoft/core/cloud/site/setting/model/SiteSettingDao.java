package com.cantonsoft.core.cloud.site.setting.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
@Lazy
public interface SiteSettingDao extends BaseEntityDao<SiteSetting,Long>{

	SiteSetting findTopBySiteIdAndName(Long siteId, String name);
	SiteSetting findBySiteId(Long siteId);
}
