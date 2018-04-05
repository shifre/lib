package com.cantonsoft.core.cloud.client.service.constract.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_SERVICE_CONTRACT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "serviceContract")
public class ServiceContract extends IdEntity {
	private static final long serialVersionUID = 1L;

	public static final String STATUS_ACTIVE = "ACTIVE";
	public static final String STATUS_DISABLED = " DISABLED";

	private Long siteId;
	private String status;
	private Date createdTime = new Date();
	private Date expiryTime;
	private String description;

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
