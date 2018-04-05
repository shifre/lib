package com.cantonsoft.core.cloud.client.service.usage.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.mvc.model.filter.meta.Indexable;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "V_SERVICE_USAGE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "serviceUsageView")
public class ServiceUsageView extends IdEntity {
	private static final long serialVersionUID = 1L;

	@Indexable
	private String status;
	@Indexable
	private Date createdTime;
	@Indexable
	private Date expiryTime;
	private Long partnerId;
	@Indexable
	private String clientName;
	private String clientImage;
	private String clientPhone;
	private String clientEmail;
	@Indexable
	private String siteName;
	private String siteStatus;

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

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientImage() {
		return clientImage;
	}

	public void setClientImage(String clientImage) {
		this.clientImage = clientImage;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteStatus() {
		return siteStatus;
	}

	public void setSiteStatus(String siteStatus) {
		this.siteStatus = siteStatus;
	}

}
