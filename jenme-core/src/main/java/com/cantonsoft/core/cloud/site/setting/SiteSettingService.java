package com.cantonsoft.core.cloud.site.setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.cloud.site.setting.model.SiteSetting;
import com.cantonsoft.core.cloud.site.setting.model.SiteSettingDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class SiteSettingService extends BaseEntityService<SiteSetting,Long> {
	@Autowired
	private SiteSettingDao dao;
	
	@Override
	protected BaseEntityDao<SiteSetting, Long> getDao() {
		return dao;
	}
	
	public SiteSetting findTopBySiteIdAndName(Long siteId, String name){
		return dao.findTopBySiteIdAndName(siteId, name);
	}
	
	public SiteSetting findBySiteId(Long siteId){
		return dao.findBySiteId(siteId);
	}
}