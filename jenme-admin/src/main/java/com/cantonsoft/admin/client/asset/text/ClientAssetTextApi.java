package com.cantonsoft.admin.client.asset.text;

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

import com.cantonsoft.core.common.asset.text.AssetTextService;
import com.cantonsoft.core.common.asset.text.model.AssetText;
import com.cantonsoft.core.common.asset.text.model.AssetTextUpdate;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@JsonApi
@Lazy
@RequestMapping(value = "client/asset/text")
@Access(value = "fn.client.asset.text")
public class ClientAssetTextApi extends BaseEntityApi<AssetText, Long, AssetText, AssetTextUpdate> {

	@Autowired
	private AssetTextService service;
	
	
	@Override
	protected BaseEntityService<AssetText, Long> getEntityService() {
		return service;
	}

	@Override
	protected AssetText doAdd(Map<String, Object> respMap, AssetText data, HttpServletRequest request,
			HttpServletResponse response) {
		data.setPosition(Long.valueOf(service.findMinPositionByAssetId(data.getAssetId())));
		return super.doAdd(respMap, data, request, response);
	}

	@Access(value = "update")
	@RequestMapping(value = "move.json", method = RequestMethod.POST)
	public @ResponseBody Object add(@RequestParam Long id1, @RequestParam Long id2, HttpServletRequest request, HttpServletResponse response)
	{
		AssetText text1 = service.find(id1);
		AssetText text2 = service.find(id2);
		
		Long position = text1.getPosition();
		text1.setPosition(text2.getPosition());
		text2.setPosition(position);
		
		service.update(text1);
		service.update(text2);
		
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
	}
}
