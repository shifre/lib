package com.cantonsoft.core.common.goods.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;

@Entity
@Table(name = "T_GOODS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Goods extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4771231420078372861L;
	private String code;
	private String name;
	private Long categoryId;
	private String type;
	private String texture;
	private BigDecimal salePrice;
	private BigDecimal purPrice;
	private String unit;
	private Long vendorId;
	private Integer saleDeliverTime;
	private Integer purDeliverTime;
	private String orderMemo;
	private String hidenInd;
	private BigDecimal lcPrice;
	private BigDecimal weight;
	private BigDecimal volume;
	private String color;
	private String spec;
	private String customizeAudit;
	private String customizeInd;
	private String accessoriesInd;
	private String updateBy;
	private Date updateDate;
	private String expiredInd = "N";
	private Date expiredDate;
	private Long count;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(BigDecimal purPrice) {
		this.purPrice = purPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Integer getSaleDeliverTime() {
		return saleDeliverTime;
	}

	public void setSaleDeliverTime(Integer saleDeliverTime) {
		this.saleDeliverTime = saleDeliverTime;
	}

	public Integer getPurDeliverTime() {
		return purDeliverTime;
	}

	public void setPurDeliverTime(Integer purDeliverTime) {
		this.purDeliverTime = purDeliverTime;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public String getHidenInd() {
		return hidenInd;
	}

	public void setHidenInd(String hidenInd) {
		this.hidenInd = hidenInd;
	}

	public BigDecimal getLcPrice() {
		return lcPrice;
	}

	public void setLcPrice(BigDecimal lcPrice) {
		this.lcPrice = lcPrice;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getCustomizeAudit() {
		return customizeAudit;
	}

	public void setCustomizeAudit(String customizeAudit) {
		this.customizeAudit = customizeAudit;
	}

	public String getCustomizeInd() {
		return customizeInd;
	}

	public void setCustomizeInd(String customizeInd) {
		this.customizeInd = customizeInd;
	}

	public String getAccessoriesInd() {
		return accessoriesInd;
	}

	public void setAccessoriesInd(String accessoriesInd) {
		this.accessoriesInd = accessoriesInd;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getExpiredInd() {
		return expiredInd;
	}

	public void setExpiredInd(String expiredInd) {
		this.expiredInd = expiredInd;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
