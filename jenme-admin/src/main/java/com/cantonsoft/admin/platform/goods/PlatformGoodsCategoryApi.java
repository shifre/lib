package com.cantonsoft.admin.platform.goods;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.common.goods.category.GoodsCategoryService;
import com.cantonsoft.core.common.goods.category.model.GoodsCategory;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@JsonApi
@Lazy
@RequestMapping(value = "/platform/goods/category")
@Access(value = "fn.main.goods.category")
public class PlatformGoodsCategoryApi extends BaseEntityApi<GoodsCategory, Long, GoodsCategory, GoodsCategory>{
	@Autowired
	GoodsCategoryService service;
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}

	@Override
	protected BaseEntityService<GoodsCategory,Long> getEntityService() {
		return service;
	}	
	
}
