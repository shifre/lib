package com.cantonsoft.core.account;

public class PartnerUserIdentity extends UserIdentity{
	private String partner;

	public String getPartner() {
		return null == partner? null : partner.trim();
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}
}
