package com.cantonsoft.core.cloud.site.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.mvc.model.filter.meta.Indexable;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_SITE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "site")
public class Site extends IdEntity {
	private static final long serialVersionUID = 1L;

	public static final String SITE_STATUS_NEW = "NEW";
	public static final String SITE_STATUS_OPEN = "OPEN";
	public static final String SITE_STATUS_CLOSE = "CLOSE";

	private Long clientId;
	@Indexable
	private String title;
	private String domain;
	private String description;
	@Indexable
	private Date createdTime = new Date();
	private String status = SITE_STATUS_NEW;
	private Long templateId;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
}