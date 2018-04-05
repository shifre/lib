package com.cantonsoft.core.cloud.client.model;

import com.cantonsoft.framework.mvc.model.IEntityDao;

public interface ISiteClientDao extends IEntityDao<SiteClient> {
	
	public SiteClient queryByHotelId(Long hotelId);
	
}