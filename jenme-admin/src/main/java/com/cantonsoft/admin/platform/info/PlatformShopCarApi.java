package com.cantonsoft.admin.platform.info;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.core.common.info.car.ShopCarService;
import com.cantonsoft.core.common.info.car.model.ShopCar;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@JsonApi
@Lazy
@RequestMapping(value = "/platform/info/car")
@Access(value = "fn.main.info.car")
public class PlatformShopCarApi extends BaseEntityApi<ShopCar, Long, ShopCar, ShopCar>{
	@Autowired
	ShopCarService service;
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}

	@Override
	protected BaseEntityService<ShopCar,Long> getEntityService() {
		return service;
	}
	
	@Override
	public @ResponseBody Object add(@RequestBody ShopCar data, HttpServletRequest request, HttpServletResponse response) {
		ShopCar partner = service.findByCarNo(data.getCarNo());
		if (partner != null) {
			return ApiResponse.make(true, null, "已存在该车牌号车辆！", partner);
		}
		return super.add(data, request, response);
	}
}
