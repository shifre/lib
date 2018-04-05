package com.cantonsoft.core.common.info.startprice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.info.startprice.model.StartPrice;
import com.cantonsoft.core.common.info.startprice.model.StartPriceDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class StartPriceService extends BaseEntityService<StartPrice, Long> {

	@Autowired
	private StartPriceDao startPriceDao;
	
	@Override
	protected StartPriceDao getDao() {
		return startPriceDao;
	}

}
