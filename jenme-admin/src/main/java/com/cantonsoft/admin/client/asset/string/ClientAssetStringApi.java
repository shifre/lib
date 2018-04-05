package com.cantonsoft.admin.client.asset.string;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.core.common.asset.string.AssetStringService;
import com.cantonsoft.core.common.asset.string.model.AssetString;
import com.cantonsoft.core.common.asset.string.model.AssetStringUpdate;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;

@JsonApi
@Lazy
@RequestMapping(value = "client/asset/string")
@Access(value = "fn.client.asset.string")
public class ClientAssetStringApi extends BaseEntityApi<AssetString, Long, AssetString, AssetStringUpdate> {

	@Autowired
	private AssetStringService service;
	
	@PostConstruct
	public void init()
	{
		searchDef = new SearchDef().addField("title").addField("assetId");
	}
	
	@Override
	protected BaseEntityService<AssetString, Long> getEntityService() {
		return service;
	}

	@Override
	protected AssetString doAdd(Map<String, Object> respMap, AssetString data, HttpServletRequest request,
			HttpServletResponse response) {
		data.setPosition(service.findMinPositionByAssetId(data.getAssetId()));
		return super.doAdd(respMap, data, request, response);
	}
	

	@Override
	protected PageData<AssetString> doSearch(PageFilter filter, HttpServletRequest request,
			HttpServletResponse response) {
		
		return super.doSearch(filter, request, response);
	}

	@Access(value = "update")
	@RequestMapping(value = "move.json", method = RequestMethod.POST)
	public @ResponseBody Object add(@RequestParam Long id1, @RequestParam Long id2, HttpServletRequest request, HttpServletResponse response)
	{
		AssetString string1 = service.find(id1);
		AssetString string2 = service.find(id2);
		
		Integer position = string1.getPosition();
		string1.setPosition(string2.getPosition());
		string2.setPosition(position);
		
		service.update(string1);
		service.update(string2);
		
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
	}
}
