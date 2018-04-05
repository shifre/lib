package com.cantonsoft.core.cloud.site.setting.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_SITE_SETTING")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "siteSetting")
public class SiteSetting extends IdEntity {
	private static final long serialVersionUID = 1L;
	
	private Long siteId;
	private String name;
	private String value;
	
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}