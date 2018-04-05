package com.cantonsoft.core.cloud.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.cloud.client.model.SiteClient;
import com.cantonsoft.core.cloud.client.model.SiteClientDao;
import com.cantonsoft.framework.mvc.model.IEntityDao;
import com.cantonsoft.framework.mvc.service.CrudService;

@Service
@Lazy
public class ClientSiteService extends CrudService<SiteClient> {
	@Autowired
	private SiteClientDao siteClientDao;

	
	@Override
	protected IEntityDao<SiteClient> getEntityDao() {
		return siteClientDao;
	}

	public SiteClient queryByHotelId(Long hotelId){
		return siteClientDao.queryByHotelId(hotelId);
	}
	
}
