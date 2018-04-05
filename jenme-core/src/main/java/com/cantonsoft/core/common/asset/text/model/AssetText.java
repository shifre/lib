package com.cantonsoft.core.common.asset.text.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.validation.form.meta.Check;
@Entity
@Table(name = "T_ASSET_TEXT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "assetText")
public class AssetText extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long assetId;
	private String title;
	private String value;
	private String url;
	private Long position;
	public Long getAssetId() {
		return assetId;
	}
	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}
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
	public Long getPosition() {
		return position;
	}
	public void setPosition(Long position) {
		this.position = position;
	}

}
