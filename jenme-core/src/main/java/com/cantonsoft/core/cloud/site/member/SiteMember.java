package com.cantonsoft.core.cloud.site.member;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_SITE_MEMBER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "sitemember")
public class SiteMember extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	private Long memberId;
	private String openId;
	private Long siteId;
	private Date createdTime;
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
