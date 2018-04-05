package com.cantonsoft.admin.platform.info;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.common.info.vendor.VendorService;
import com.cantonsoft.core.common.info.vendor.model.Vendor;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@JsonApi
@Lazy
@RequestMapping(value = "/platform/info/vendor")
@Access(value = "fn.main.info.vendor")
public class PlatformVendorApi extends BaseEntityApi<Vendor, Long, Vendor, Vendor>{
	@Autowired
	VendorService service;
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}

	@Override
	protected BaseEntityService<Vendor,Long> getEntityService() {
		return service;
	}	
}
