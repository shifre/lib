package com.cantonsoft.admin.account.auth;

import java.io.Serializable;
import java.util.Map;

import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.core.cloud.partner.model.Partner;
import com.cantonsoft.core.cloud.site.model.Site;
import com.cantonsoft.framework.security.Menu;

public class SessionStorage implements Serializable {

	private static final long serialVersionUID = 1L;
	private GenericUser user;
	private Menu userMenu;
	private Menu siteMenu;
	private Map<String, Menu> accessMenu;
	private Partner partner;
	private Client client;
	private Site site;

	public SessionStorage(GenericUser innerUser) {
		this.user = innerUser;
	}
	
	public GenericUser getUser() {
		return user;
	}
	
	public Menu getSiteMenu() {
		return siteMenu;
	}

	public void setSiteMenu(Menu siteMenu) {
		this.siteMenu = siteMenu;
	}

	public Menu getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(Menu userMenu) {
		this.userMenu = userMenu;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Map<String, Menu> getAccessMenu() {
		return accessMenu;
	}

	public void setAccessMenu(Map<String, Menu> accessMenu) {
		this.accessMenu = accessMenu;
	}
	
}