package com.cantonsoft.core.cloud.partner.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.VDeleteIdEntity;
import com.cantonsoft.framework.mvc.model.filter.meta.Indexable;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_PARTNER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "partner")
public class Partner extends VDeleteIdEntity {
	private static final long serialVersionUID = 1L;

	private String type = "GENERIC";
	@Indexable
	private String title;
	private String logoUrl;
	private String phone;
	private String email;
	private String division;
	private String address;
	private boolean enabled;
	private String contactName;
	private String description;
	private String portal;
	private Date createdTime = new Date();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

}
