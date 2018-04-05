package com.cantonsoft.core.cloud.cache;

import java.io.Serializable;

import com.cantonsoft.framework.cache.CacheKey;


public class SiteDataKey<T> extends CacheKey implements Serializable{
	
	private static final long serialVersionUID = 8678426731639635656L;
	
	private Long siteId;
	private T data;
	
	public SiteDataKey(Long siteId, T data) {
		this.siteId = siteId;
		this.data = data;
	}
	public Long getSiteId() {
		return siteId;
	}
	public T getData() {
		return data;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public void setData(T data) {
		this.data = data;
	}
}
