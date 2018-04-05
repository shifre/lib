package com.cantonsoft.core.common.asset.model;

import java.io.Serializable;

public class AssetUpdate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private String name;
	private String type;
	private boolean templateField;
	private String description;


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

}
