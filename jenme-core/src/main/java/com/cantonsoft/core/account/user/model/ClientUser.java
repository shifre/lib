package com.cantonsoft.core.account.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_CLIENT_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "clientUser")
public class ClientUser extends GenericUser {
	private static final long serialVersionUID = 1L;

	public static final String TYPE_ADMIN = "ADMIN";
	public static final String TYPE_GENERIC = "GENERIC";

	private Long clientId;

	@Override
	@Transient
	public String getDomain() {
		return UserAuth.AUTH_TYPE_CLIENT;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
}
