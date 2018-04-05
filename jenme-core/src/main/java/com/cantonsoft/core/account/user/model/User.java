package com.cantonsoft.core.account.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_USER")
@Check(value = "user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends GenericUser {
	private static final long serialVersionUID = 1L;

	@Override
	@Transient
	public String getDomain() {
		return UserAuth.AUTH_TYPE_MAIN;
	}
}
