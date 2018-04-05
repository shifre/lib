package com.cantonsoft.core.account;

import java.util.Date;

import com.cantonsoft.core.account.user.model.GenericUser;

public class UserSession {
	private GenericUser user;
	private Date loginTime = new Date();
	
	public UserSession(GenericUser user) {
		this.user = user;
	}

	public GenericUser getUser() {
		return user;
	}

	public Date getLoginTime() {
		return loginTime;
	}

}
