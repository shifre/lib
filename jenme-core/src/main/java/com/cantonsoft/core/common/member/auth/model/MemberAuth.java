package com.cantonsoft.core.common.member.auth.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;

@Entity
@Table(name = "T_MEMBER_AUTH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
// @Check(value = "memberAuth")
public class MemberAuth extends IdEntity {
	private static final long serialVersionUID = 1L;

	private Long memberId;
	private String account;
	private String password;
	private Integer errorCount = 0;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Date updatedPasswordTime;
	private String token;
	private String imToken;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getUpdatedPasswordTime() {
		return updatedPasswordTime;
	}

	public void setUpdatedPasswordTime(Date updatedPasswordTime) {
		this.updatedPasswordTime = updatedPasswordTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getImToken() {
		return imToken;
	}

	public void setImToken(String imToken) {
		this.imToken = imToken;
	}

}
