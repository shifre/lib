package com.cantonsoft.admin.platform.inventory;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.core.common.inventory.InventoryService;
import com.cantonsoft.core.common.inventory.model.Inventory;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;
import com.cantonsoft.vo.InventoryVo;

@JsonApi
@Lazy
@RequestMapping(value = "/platform/inventory/info")
@Access(value = "fn.main.inventory.info")
public class PlatformInventoryApi extends BaseEntityApi<Inventory, Long, InventoryVo, InventoryVo>{
	@Autowired
	InventoryService service;
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}

	@Override
	protected BaseEntityService<Inventory,Long> getEntityService() {
		return null;
	}
	
	@Override
	public @ResponseBody Object search(@RequestBody PageFilter filter, HttpServletRequest request, HttpServletResponse response) {
		PageData<InventoryVo> pageData = service.buildPageData(filter);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), pageData);
		
	}
}
