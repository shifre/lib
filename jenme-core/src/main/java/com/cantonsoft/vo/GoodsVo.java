package com.cantonsoft.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GoodsVo {
	private Long goodsId;
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
	private boolean hidden;
	private BigDecimal lcPrice;
	private BigDecimal weight;
	private BigDecimal volume;
	private String color;
	private String spec;
	private boolean customizeAudit;
	private boolean customize;
	private boolean accessories;
	private String updateBy;
	private Date updateDate;
	private String expiredInd;
	private Date expiredDate;
	private Long count;
	
//	private List<GoodsDetailVo> details;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

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

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
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

	public boolean isCustomizeAudit() {
		return customizeAudit;
	}

	public void setCustomizeAudit(boolean customizeAudit) {
		this.customizeAudit = customizeAudit;
	}

	public boolean isCustomize() {
		return customize;
	}

	public void setCustomize(boolean customize) {
		this.customize = customize;
	}

	public boolean isAccessories() {
		return accessories;
	}

	public void setAccessories(boolean accessories) {
		this.accessories = accessories;
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

//	public List<GoodsDetailVo> getDetails() {
//		return details;
//	}
//
//	public void setDetails(List<GoodsDetailVo> details) {
//		this.details = details;
//	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
