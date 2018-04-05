package com.cantonsoft.vo;

import java.math.BigDecimal;
import java.util.Date;

public class StockInFormVo  {
    	
	private Long id;
	private String stockInCode;
	private Long vendorId;
	private String vendorOrderCd;
	private Long credentialId;
	private int indicator;
	private Long originFormId;
	private String remark;
	private String createBy;
	private Date createDate;
	private BigDecimal costPrice;
	
	private String updateBy;
	private Date updateZDate;
	public String getStockInCode() {
		return stockInCode;
	}
	public void setStockInCode(String stockInCode) {
		this.stockInCode = stockInCode;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorOrderCd() {
		return vendorOrderCd;
	}
	public void setVendorOrderCd(String vendorOrderCd) {
		this.vendorOrderCd = vendorOrderCd;
	}
	public Long getCredentialId() {
		return credentialId;
	}
	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}
	public int getIndicator() {
		return indicator;
	}
	public void setIndicator(int indicator) {
		this.indicator = indicator;
	}
	public Long getOriginFormId() {
		return originFormId;
	}
	public void setOriginFormId(Long originFormId) {
		this.originFormId = originFormId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateZDate() {
		return updateZDate;
	}
	public void setUpdateZDate(Date updateZDate) {
		this.updateZDate = updateZDate;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
		
}
