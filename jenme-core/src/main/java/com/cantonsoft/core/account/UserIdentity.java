package com.cantonsoft.core.account;

import org.apache.commons.lang3.StringUtils;

public class UserIdentity {
	private String username;
	private String password;
	private boolean remember;
	private String verifyCode;

	public String getUsername() {
		return StringUtils.lowerCase(username);
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
