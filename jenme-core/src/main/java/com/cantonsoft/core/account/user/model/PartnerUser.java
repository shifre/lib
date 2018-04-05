package com.cantonsoft.core.account.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.filter.meta.Indexable;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_PARTNER_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "partnerUser")
public class PartnerUser extends GenericUser {
	private static final long serialVersionUID = 1L;
	
	public static final String TYPE_ADMIN = "ADMIN";
	public static final String TYPE_DEVELOPER = "DEVELOPER";
	public static final String TYPE_GENERIC = "GENERIC";
	
	@Indexable
	private Long partnerId;

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	@Transient
	public String getDomain() {
		return UserAuth.AUTH_TYPE_PARTNER;
	}
}
