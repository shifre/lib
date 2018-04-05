package com.cantonsoft.core.common.asset.text.model;

import java.io.Serializable;


public class AssetTextUpdate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private String value;
	private String url;


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
