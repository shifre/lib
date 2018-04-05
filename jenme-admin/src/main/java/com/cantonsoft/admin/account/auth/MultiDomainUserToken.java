package com.cantonsoft.admin.account.auth;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MultiDomainUserToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;
	private String domain;
	private boolean secure;
	public MultiDomainUserToken(final String domain, final String username, final String password, final boolean isRememberMe) {
		super(username, password.toCharArray(), isRememberMe);
		this.domain = domain;
	}

	public String getDomain() {
		return domain;
	}

	public boolean isSecure() {
		return secure;
	}

	public void setSecure(boolean secure) {
		this.secure = secure;
	}
}
