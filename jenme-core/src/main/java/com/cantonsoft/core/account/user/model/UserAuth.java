package com.cantonsoft.core.account.user.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;

@Entity
@Table(name = "T_USER_AUTH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAuth extends IdEntity {
	private static final long serialVersionUID = 1L;
	public static final String AUTH_TYPE_MAIN = "MAIN";
	public static final String AUTH_TYPE_PARTNER = "PARTNER";
	public static final String AUTH_TYPE_CLIENT = "CLIENT";
	private String domain;
	private Long userId;
	private String username;
	private String password;
	private int errorCount;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Date updatedPasswordTime;

	@Transient
	public boolean isWarning(){
		if (this.getErrorCount() >= 3 && null != this.getLastLoginTime())
		{
			return System.currentTimeMillis() - this.getLastLoginTime().getTime() < 10 * 60 * 1000;
		}
		return false;
	}
	
	@Transient
	public String getUniqueUsername()
	{
		String username = this.getUsername();
		int idx = username.indexOf('@');
		if (idx > 0)
		{
			return username.substring(0, idx);
		}
		else
		{
			return username;
		}
	}
	
	public String getUsername() {
		return StringUtils.lowerCase(username);
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return null == password ? "" : password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
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
}
