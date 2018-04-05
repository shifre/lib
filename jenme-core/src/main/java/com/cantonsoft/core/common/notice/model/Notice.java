package com.cantonsoft.core.common.notice.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.mvc.model.filter.meta.Indexable;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_NOTICE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "notice")
public class Notice extends IdEntity {
	private static final long serialVersionUID = 3479465599940600512L;

	@Indexable
	private String title;
	private String detail;
	private Integer type;//公告范围：1:渠道商；2:渠道商和全部酒店
	private Date createdTime = new Date();
	private Boolean status;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
