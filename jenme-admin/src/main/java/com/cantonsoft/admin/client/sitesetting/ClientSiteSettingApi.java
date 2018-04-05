package com.cantonsoft.admin.client.sitesetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.core.cloud.site.setting.SiteSettingService;
import com.cantonsoft.core.cloud.site.setting.model.SiteSetting;
import com.cantonsoft.core.cloud.site.setting.model.SiteSettingUpdate;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@JsonApi
@Lazy
@RequestMapping(value = "client/sitesetting")
@Access(value = "fn.cient.sitesetting")
public class ClientSiteSettingApi extends BaseEntityApi<SiteSetting, Long, SiteSetting, SiteSettingUpdate> {
	@Autowired
	private SiteSettingService service;
	@Autowired
	private ShiroSessionHelper sessionHelper;

	@Override
	protected BaseEntityService<SiteSetting, Long> getEntityService() {
		return service;
	}
	
	private Long getSiteId()
	{
		return sessionHelper.getSession().getSite().getId();
	}


}
