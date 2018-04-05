package com.cantonsoft.core.common.goods.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.goods.category.model.GoodsCategory;
import com.cantonsoft.core.common.goods.category.model.GoodsCategoryDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class GoodsCategoryService extends BaseEntityService<GoodsCategory, Long> {

	@Autowired
	private GoodsCategoryDao goodsCategoryDao;
	
	@Override
	protected GoodsCategoryDao getDao() {
		return goodsCategoryDao;
	}

}
