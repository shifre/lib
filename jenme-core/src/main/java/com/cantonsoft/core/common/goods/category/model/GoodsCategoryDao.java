package com.cantonsoft.core.common.goods.category.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
@Lazy
public interface GoodsCategoryDao extends BaseEntityDao<GoodsCategory, Long>{

}
