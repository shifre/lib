package com.cantonsoft.core.cloud.cache;

import com.cantonsoft.framework.cache.CacheKey;

public class UniqueUserKey extends CacheKey {
	private String domain;
	private Long userId;
	
	public UniqueUserKey(String domain, Long userId) {
		this.domain = domain;
		this.userId = userId;
	}

	public String getDomain() {
		return domain;
	}

	public Long getUserId() {
		return userId;
	}
}
