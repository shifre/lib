package com.cantonsoft.core.common.info.car.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;

@Entity
@Table(name = "T_SHOPCAR")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShopCar extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5364688496941662745L;
	private String carNo;
	private String models;
	private BigDecimal maxVolume;
	private BigDecimal maxWeight;
	private String price;
	private Double mutiple;

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	public BigDecimal getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(BigDecimal maxVolume) {
		this.maxVolume = maxVolume;
	}

	public BigDecimal getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(BigDecimal maxWeight) {
		this.maxWeight = maxWeight;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Double getMutiple() {
		return mutiple;
	}

	public void setMutiple(Double mutiple) {
		this.mutiple = mutiple;
	}
}
