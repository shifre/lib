package com.cantonsoft.core.common.member.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_MEMBER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "memberRegister")
public class MemberAdd extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String mobile;
	private String password;
	private Date createdTime = new Date();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}
