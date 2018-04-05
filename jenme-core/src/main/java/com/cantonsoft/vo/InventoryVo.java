package com.cantonsoft.vo;

import java.math.BigDecimal;

public class InventoryVo {

	private Long inventoryId;
	private Long deptId;
	private Long goodsId;
	private Long availableNum;
	private Long sampleNum;
	private Long exhibitNum;
	private Long borkenNum;
	private Long withdrawNum;
	private Long salingNum;
	private Long sampleSaleNum;
	private Long forSaleNum;
	private Long purchaseNum;
	private Long moveInNum;
	private Long moveOutNum;
	private Long minAlertNum;
	private Long maxAlertNum;
	private String remark;

	// goods
	private String goodsCode;
	private String goodsName;
	private String color;
	private String spec;
	private String texture;
	private String weight;
	private String volume;
	private BigDecimal purPrice;
	private BigDecimal salePrice;

	// total
	private BigDecimal salingPurPriceSum;
	private BigDecimal salingSalePriceSum;
	private BigDecimal exhibitPurPriceSum;
	private BigDecimal availablePurPriceSum;
	private BigDecimal samplePurPriceSum;
	private BigDecimal borkenPurPriceSum;
	private BigDecimal withdrawPurPriceSum;

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getAvailableNum() {
		return availableNum;
	}

	public void setAvailableNum(Long availableNum) {
		this.availableNum = availableNum;
	}

	public Long getSampleNum() {
		return sampleNum;
	}

	public void setSampleNum(Long sampleNum) {
		this.sampleNum = sampleNum;
	}

	public Long getExhibitNum() {
		return exhibitNum;
	}

	public void setExhibitNum(Long exhibitNum) {
		this.exhibitNum = exhibitNum;
	}

	public Long getBorkenNum() {
		return borkenNum;
	}

	public void setBorkenNum(Long borkenNum) {
		this.borkenNum = borkenNum;
	}

	public Long getWithdrawNum() {
		return withdrawNum;
	}

	public void setWithdrawNum(Long withdrawNum) {
		this.withdrawNum = withdrawNum;
	}

	public Long getSalingNum() {
		return salingNum;
	}

	public void setSalingNum(Long salingNum) {
		this.salingNum = salingNum;
	}

	public Long getSampleSaleNum() {
		return sampleSaleNum;
	}

	public void setSampleSaleNum(Long sampleSaleNum) {
		this.sampleSaleNum = sampleSaleNum;
	}

	public Long getForSaleNum() {
		return forSaleNum;
	}

	public void setForSaleNum(Long forSaleNum) {
		this.forSaleNum = forSaleNum;
	}

	public Long getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(Long purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public Long getMoveInNum() {
		return moveInNum;
	}

	public void setMoveInNum(Long moveInNum) {
		this.moveInNum = moveInNum;
	}

	public Long getMoveOutNum() {
		return moveOutNum;
	}

	public void setMoveOutNum(Long moveOutNum) {
		this.moveOutNum = moveOutNum;
	}

	public Long getMinAlertNum() {
		return minAlertNum;
	}

	public void setMinAlertNum(Long minAlertNum) {
		this.minAlertNum = minAlertNum;
	}

	public Long getMaxAlertNum() {
		return maxAlertNum;
	}

	public void setMaxAlertNum(Long maxAlertNum) {
		this.maxAlertNum = maxAlertNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public BigDecimal getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(BigDecimal purPrice) {
		this.purPrice = purPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getSalingPurPriceSum() {
		return salingPurPriceSum;
	}

	public void setSalingPurPriceSum(BigDecimal salingPurPriceSum) {
		this.salingPurPriceSum = salingPurPriceSum;
	}

	public BigDecimal getSalingSalePriceSum() {
		return salingSalePriceSum;
	}

	public void setSalingSalePriceSum(BigDecimal salingSalePriceSum) {
		this.salingSalePriceSum = salingSalePriceSum;
	}

	public BigDecimal getExhibitPurPriceSum() {
		return exhibitPurPriceSum;
	}

	public void setExhibitPurPriceSum(BigDecimal exhibitPurPriceSum) {
		this.exhibitPurPriceSum = exhibitPurPriceSum;
	}

	public BigDecimal getAvailablePurPriceSum() {
		return availablePurPriceSum;
	}

	public void setAvailablePurPriceSum(BigDecimal availablePurPriceSum) {
		this.availablePurPriceSum = availablePurPriceSum;
	}

	public BigDecimal getSamplePurPriceSum() {
		return samplePurPriceSum;
	}

	public void setSamplePurPriceSum(BigDecimal samplePurPriceSum) {
		this.samplePurPriceSum = samplePurPriceSum;
	}

	public BigDecimal getBorkenPurPriceSum() {
		return borkenPurPriceSum;
	}

	public void setBorkenPurPriceSum(BigDecimal borkenPurPriceSum) {
		this.borkenPurPriceSum = borkenPurPriceSum;
	}

	public BigDecimal getWithdrawPurPriceSum() {
		return withdrawPurPriceSum;
	}

	public void setWithdrawPurPriceSum(BigDecimal withdrawPurPriceSum) {
		this.withdrawPurPriceSum = withdrawPurPriceSum;
	}
	
	public void calculateInventory() {
		if (salingNum != null) {
			salingPurPriceSum = new BigDecimal(salingNum).multiply(purPrice);
			salingSalePriceSum = new BigDecimal(salingNum).multiply(salePrice);
		}
		
		if (exhibitNum != null) {
			exhibitPurPriceSum = new BigDecimal(exhibitNum).multiply(purPrice);
		}
		
		if (availableNum != null) {
			availablePurPriceSum = new BigDecimal(availableNum).multiply(purPrice);
		}
		
		if (sampleNum != null) {
			samplePurPriceSum = new BigDecimal(sampleNum).multiply(purPrice);
		}
		
		if (borkenNum != null) {
			borkenPurPriceSum = new BigDecimal(borkenNum).multiply(purPrice);
		}
		
		if (withdrawNum != null) {
			withdrawPurPriceSum = new BigDecimal(withdrawNum).multiply(purPrice);
		}
	}

}
