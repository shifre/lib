package com.cantonsoft.admin.platform.info;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.common.info.area.AreaService;
import com.cantonsoft.core.common.info.area.model.Area;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@JsonApi
@Lazy
@RequestMapping(value = "/platform/info/area")
@Access(value = "fn.main.info.area")
public class PlatformAreaApi extends BaseEntityApi<Area, Long, Area, Area>{
	@Autowired
	AreaService service;
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}

	@Override
	protected BaseEntityService<Area,Long> getEntityService() {
		return service;
	}
}
