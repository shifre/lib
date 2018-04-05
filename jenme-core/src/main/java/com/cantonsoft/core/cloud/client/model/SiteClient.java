package com.cantonsoft.core.cloud.client.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.validation.form.meta.Check;

// Generated 2015-7-28 16:28:02 by Hibernate Tools 3.4.0.CR1

@Entity
@Table(name = "VC_SITE_CLIENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "siteClient")

public class SiteClient extends IdEntity {


	private Long partnerId;
	private Long hotelId;


	

	public long getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public Long getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	

}
