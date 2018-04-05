package com.cantonsoft.admin.view;

import com.cantonsoft.core.cloud.cache.CacheRegister;
import com.cantonsoft.core.cloud.cache.SiteDataKey;
import com.cantonsoft.core.cloud.partner.model.Partner;
import com.cantonsoft.core.cloud.site.SiteFieldConstent;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.cache.TwoLevelCacheManager;
import com.cantonsoft.framework.mvc.util.ApplicationUtil;
import com.cantonsoft.framework.util.Config;

public class CacheDataHelper {
	public static <T> T getBean(Class<T> clazz)
	{
		return ApplicationUtil.WEBAPP_CONTEXT.getBean(clazz);
	}

	public static Object getSiteNavJson()
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return cacheManager.getData(CacheRegister.SITE_NAV_JSON, sessionHelper.getSession().getSite().getId());
	}
	
	public static Object getCityJson()
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		return cacheManager.getData(CacheRegister.DICT_JSON, "city");
	}
	
	public static Object getNewestClient()
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Partner partner = sessionHelper.getSession().getPartner();
		if (null == partner) {
			return null;
		}
		return cacheManager.getData(CacheRegister.PARTNER_CLIENT_NEWEST, partner.getId());
	}
	
	public static Object getClientTypeCount()
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Partner partner = sessionHelper.getSession().getPartner();
		if (null == partner) {
			return null;
		}
		return cacheManager.getData(CacheRegister.PARTNER_CLIENT_TYPECOUNT, partner.getId());
	}
	
	public static Object getAttributeForm(Object channelId)
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Long siteId = sessionHelper.getSession().getSite().getId();
		SiteDataKey<Object> key = new SiteDataKey<Object>(siteId, channelId);
		if (channelId == null ||"".equals(channelId)||"undefined".equals(channelId))
		{
			return cacheManager.getData(CacheRegister.CHANNEL_ATTRIBUTE_FORM, null);
		}
		else
		{
			return cacheManager.getData(CacheRegister.CHANNEL_ATTRIBUTE_FORM, key);
		}
	}
	
	public static Object getProductTypeAttributeForm(Object typeId)
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Long siteId = sessionHelper.getSession().getSite().getId();
		SiteDataKey<Object> key = new SiteDataKey<Object>(siteId, typeId);
		if (typeId == null ||"".equals(typeId)||"undefined".equals(typeId))
		{
			return cacheManager.getData(CacheRegister.PRODUCT_ATTRIBUTE_FORM, key);
		}
		else
		{
			return cacheManager.getData(CacheRegister.PRODUCT_ATTRIBUTE_FORM, key);
		}
	}
	
	public static Object getEformAttributeForm(Object eformId)
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Long siteId = sessionHelper.getSession().getSite().getId();
		SiteDataKey<Object> key = new SiteDataKey<Object>(siteId, eformId);
		return cacheManager
				.getData(CacheRegister.EFORM_ATTRIBUTE_FORM, key);
	}
	
	
	public static Object getSiteVisitData(){
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Config config = getBean(Config.class);
		String dateRang= config.getProperty(SiteFieldConstent.PRO_SITEVISIT_RANG, SiteFieldConstent.PRO_SITEVISIT_RANG_DEFAULT);
		Long siteId = sessionHelper.getSession().getSite().getId();
		SiteDataKey<String> key = new SiteDataKey<String>(siteId, dateRang);
		
		return cacheManager
				.getData(CacheRegister.SITE_VISIT, key);
	}
	
	public static Object getSite(Long id)
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		return cacheManager.getData(CacheRegister.SITE, id);
	}
	
	public static Object pupularArticle(){
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Long siteId = sessionHelper.getSession().getSite().getId();
		return cacheManager.getData(CacheRegister.ARTICLE_POPULAR_LIST, siteId);
	}
	
	public static Object pupularProduct(){
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Long siteId = sessionHelper.getSession().getSite().getId();
		return cacheManager.getData(CacheRegister.PRODUCT_POPULAR_LIST, siteId);
	}
	
	public static Object getOpenAreas()
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		return cacheManager.getData(CacheRegister.OPEN_AREA_LIST, "area");
	}
	
	public static Object getHotelTags()
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		return cacheManager.getData(CacheRegister.HOTEL_TAG_LIST, "tag");
	}
	
	public static Object getHotelParams(Long classId)
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		return cacheManager.getData(CacheRegister.HOTEL_PARAM_LIST, classId);
	}
	
	public static Object getHotelParamClass()
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		return cacheManager.getData(CacheRegister.HOTEL_PARAM_CLASS_LIST, "paramclass");
	}
	
	public static Object getHotelAllParams()
	{
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		return cacheManager.getData(CacheRegister.HOTEL_ALL_PARAM_LIST, "hotelallparams");
	}
}
