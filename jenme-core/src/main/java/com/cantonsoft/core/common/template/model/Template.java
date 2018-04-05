package com.cantonsoft.core.common.template.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.validation.form.meta.Check;
@Entity
@Table(name = "T_TEMPLATE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "template")
public class Template extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String image;
	private String description;
	private Date createdTime = new Date();
	private Long version;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

}
