package com.cantonsoft.core.account.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;


@Entity
@Table(name = "T_USER_SETTING")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserSetting extends IdEntity {
	private static final long serialVersionUID = 1L;
	public final static String TYPE_THEME = "theme";
	public final static String TYPE_LANGUAGE = "language";
	public final static String LANGUAGE_ZH = "zh";
	public final static String LANGUAGE_EN = "en";

	private String domain;
	private Long userId;
	private String type;
	private String value;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
