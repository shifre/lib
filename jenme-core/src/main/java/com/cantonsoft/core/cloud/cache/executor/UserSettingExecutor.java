package com.cantonsoft.core.cloud.cache.executor;

import org.springframework.beans.factory.annotation.Autowired;

import com.cantonsoft.core.account.user.UserSettingService;
import com.cantonsoft.core.cloud.cache.UniqueUserKey;
import com.cantonsoft.framework.cache.CacheDataExecutor;

public class UserSettingExecutor extends CacheDataExecutor<UniqueUserKey> {
	@Autowired
	private UserSettingService userSettingService;
	
	@Override
	protected Object createData(UniqueUserKey key) {
		return userSettingService.getUserSettings(key.getDomain(), key.getUserId());
	}
}
