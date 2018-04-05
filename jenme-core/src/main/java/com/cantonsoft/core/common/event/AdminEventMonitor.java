package com.cantonsoft.core.common.event;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cantonsoft.core.account.user.model.UserSetting;
import com.cantonsoft.core.cloud.cache.CacheRegister;
import com.cantonsoft.core.cloud.cache.SiteDataKey;
import com.cantonsoft.core.cloud.cache.UniqueUserKey;
import com.cantonsoft.framework.cache.TwoLevelCacheManager;
import com.cantonsoft.framework.event.Event;
import com.cantonsoft.framework.event.IEventDispatcher;
import com.cantonsoft.framework.event.IEventListener;

@Component
public class AdminEventMonitor implements IEventListener {
	@Autowired
	private IEventDispatcher vDispatcher;
	
	@Autowired
	private TwoLevelCacheManager cacheManager;
	
	@PostConstruct
	public void init()
	{
		vDispatcher.addListener(EventRegister.USERSETTING_CHANGE, this);
		vDispatcher.addListener(EventRegister.CHANNEL_ATTRIBUTE_CHANGE, this);
		vDispatcher.addListener(EventRegister.PRODUCT_ATTRIBUTE_CHANGE, this);
		vDispatcher.addListener(EventRegister.SITE_NAV_CHANGE, this);
		vDispatcher.addListener(EventRegister.EFORM_ATTRIBUTE_CHANGE, this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void check(Event event) {
		String key = (String) event.getCode();
		if (key.equals(EventRegister.USERSETTING_CHANGE))
		{
			UserSetting setting = (UserSetting)event.getData();
			UniqueUserKey user = new UniqueUserKey(setting.getDomain(), setting.getUserId());
			cacheManager.clearOne(CacheRegister.USER_SETTING, user);
		}
		else if (key.equals(EventRegister.CHANNEL_ATTRIBUTE_CHANGE))
		{
			SiteDataKey<Object> channelKey = (SiteDataKey<Object>)event.getData();
			cacheManager.clearOne(CacheRegister.CHANNEL_ATTRIBUTE_FORM, channelKey);
		}
		else if (key.equals(EventRegister.PRODUCT_ATTRIBUTE_CHANGE))
		{
			SiteDataKey<Object> typeKey = (SiteDataKey<Object>)event.getData();
			cacheManager.clearOne(CacheRegister.PRODUCT_ATTRIBUTE_FORM, typeKey);
		}
		else if (key.equals(EventRegister.SITE_NAV_CHANGE))
		{
			Long siteId = (Long)event.getData();
			cacheManager.clearOne(CacheRegister.SITE_NAV_JSON, siteId);
		} else if (EventRegister.EFORM_ATTRIBUTE_CHANGE.equals(key)){
			SiteDataKey<Object> eformkey = (SiteDataKey<Object>)event.getData();
			cacheManager.clearOne(CacheRegister.EFORM_ATTRIBUTE_FORM, eformkey);
		}
	}
}
