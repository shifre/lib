package com.cantonsoft.core.cloud.site.model;

import java.io.Serializable;

public class SiteTemplate implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long siteId;
	private Long templateId;
	
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	
}
