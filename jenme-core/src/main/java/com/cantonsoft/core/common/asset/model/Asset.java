package com.cantonsoft.core.common.asset.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.validation.form.meta.Check;
@Entity
@Table(name = "T_ASSET")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "asset")
public class Asset extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TYPE_HTML = "HTML";
	public static final String TYPE_IMAGE = "IMAGE";
	public static final String TYPE_FILE = "FILE";
	public static final String TYPE_TEXT = "TEXT";
	private Long siteId;
	private String title;
	private String name;
	private String type;
	private boolean templateField;
	private String description;
	private String sourceType;
	
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isTemplateField() {
		return templateField;
	}
	public void setTemplateField(boolean templateField) {
		this.templateField = templateField;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	

}
