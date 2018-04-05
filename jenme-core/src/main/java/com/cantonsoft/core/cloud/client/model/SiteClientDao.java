package com.cantonsoft.core.cloud.client.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.model.EntityDao;

@Repository
@Lazy
public class SiteClientDao extends EntityDao<SiteClient> implements ISiteClientDao {

	private final static String FIND_BY_HOTEL = "SELECT a FROM SiteClient a WHERE a.hotelId = :hotelId";
	
	public SiteClient queryByHotelId(Long hotelId){
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("hotelId", hotelId);
		return (SiteClient) query.queryOne(FIND_BY_HOTEL, respMap, false);
	}
}
