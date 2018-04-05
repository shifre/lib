package com.cantonsoft.core.common.info.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.info.area.model.Area;
import com.cantonsoft.core.common.info.area.model.AreaDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class AreaService extends BaseEntityService<Area, Long> {

	@Autowired
	private AreaDao areaDao;
	
	@Override
	protected AreaDao getDao() {
		return areaDao;
	}

}
