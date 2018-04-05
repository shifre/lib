package com.cantonsoft.core.cloud.site.model;

import java.util.Date;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.mvc.model.filter.meta.Indexable;

public class SiteUpdate extends IdEntity {
	private static final long serialVersionUID = 1L;


	@Indexable
	private String title;
	private String description;
	@Indexable
	private Date createdTime = new Date();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}