package com.cantonsoft.core.common.thumbs.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_THUMBS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Check(value = "thumbs")
public class Thumbs extends IdEntity {
	
	private static final long serialVersionUID = 1L;

	private String refType;
	private Long refId;
	private String image;
	private Integer sort;
	
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public Long getRefId() {
		return refId;
	}
	public void setRefId(Long refId) {
		this.refId = refId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
