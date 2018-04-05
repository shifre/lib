package com.cantonsoft.core.cloud.cache;

import java.io.Serializable;

public class VisitKey implements Serializable {

	private static final long serialVersionUID = -3134465936624741810L;
	private Object id;
	private Long siteId;
	private String remoteAddress;
	private String userAgent;
	private String visitUrl;
	
	
	public VisitKey() {
		super();
	}
	public VisitKey(Object id, Long siteId, String remoteAddress,
			String userAgent) {
		super();
		this.id = id;
		this.siteId = siteId;
		this.remoteAddress = remoteAddress;
		this.userAgent = userAgent;
	}
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getVisitUrl() {
		return visitUrl;
	}
	public void setVisitUrl(String visitUrl) {
		this.visitUrl = visitUrl;
	}
	
	
}
