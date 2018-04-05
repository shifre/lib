package com.cantonsoft.core.common.info.car.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
@Lazy
public interface ShopCarDao extends BaseEntityDao<ShopCar, Long>{
	
	ShopCar findByCarNo(String carNo);

}
