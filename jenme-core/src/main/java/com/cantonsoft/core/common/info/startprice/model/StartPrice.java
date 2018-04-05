package com.cantonsoft.core.common.info.startprice.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;

@Entity
@Table(name = "T_START_PRICE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StartPrice extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8073796171677019814L;
	private String price;
	private String area;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
