package com.cantonsoft.core.common.info.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.info.car.model.ShopCar;
import com.cantonsoft.core.common.info.car.model.ShopCarDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class ShopCarService extends BaseEntityService<ShopCar, Long> {

	@Autowired
	private ShopCarDao shopCarDao;
	
	@Override
	protected ShopCarDao getDao() {
		return shopCarDao;
	}
	
	public ShopCar findByCarNo(String carNo) {
		return shopCarDao.findByCarNo(carNo);
	}

}
