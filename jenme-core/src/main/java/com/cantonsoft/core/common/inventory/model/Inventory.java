package com.cantonsoft.core.common.inventory.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;

@Entity
@Table(name = "T_INVENTORY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Inventory extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 78797024902741350L;
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

}
