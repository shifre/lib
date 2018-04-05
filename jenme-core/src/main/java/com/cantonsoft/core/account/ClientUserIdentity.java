package com.cantonsoft.core.account;

public class ClientUserIdentity extends UserIdentity{
	private String client;

	public String getClient() {
		return null == client? null : client.trim();
	}

	public void setClient(String client) {
		this.client = client;
	}
}
