package com.cantonsoft.core.common.info.area.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;

@Entity
@Table(name = "T_AREA")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Area extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521770363530457909L;
	private String city;
	private String area;
	private String expiredInd = "N";
	private Date expiredTime;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getExpiredInd() {
		return expiredInd;
	}

	public void setExpiredInd(String expiredInd) {
		this.expiredInd = expiredInd;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

}
