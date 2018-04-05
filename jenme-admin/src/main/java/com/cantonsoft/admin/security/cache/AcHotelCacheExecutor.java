package com.cantonsoft.admin.security.cache;

import org.springframework.beans.factory.annotation.Autowired;

import com.cantonsoft.admin.security.model.AcClientPartner;
import com.cantonsoft.core.cloud.client.ClientSiteService;
import com.cantonsoft.core.cloud.client.model.SiteClient;
import com.cantonsoft.framework.cache.CacheDataExecutor;

public class AcHotelCacheExecutor extends CacheDataExecutor<Long> {



	
	@Autowired
	private ClientSiteService clientSiteService;

	@Override
	protected Object createData(Long key)
	{
		return createAcHotel(key);
	}

	private Object createAcHotel(Long hotelId)
	{
		AcClientPartner obj = new AcClientPartner();
		SiteClient siteClient=clientSiteService.queryByHotelId(hotelId);
		if(siteClient!=null){
			obj.setHotelId(hotelId);
			obj.setClientId(siteClient.getId());
			obj.setPartnerId(siteClient.getPartnerId());
			
			System.out.println("hotelId=" + hotelId + ", createAcPark=" + obj);
		}
		return obj;
	}

	
}
