package com.cantonsoft.vo;

public class GoodsDetailVo {
	private Long goodsDetialId;
	private Long goodsId;
	private Long refGoodsId;
	private String goodsName;
	private Long quantity;

	public Long getGoodsDetialId() {
		return goodsDetialId;
	}

	public void setGoodsDetialId(Long goodsDetialId) {
		this.goodsDetialId = goodsDetialId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getRefGoodsId() {
		return refGoodsId;
	}

	public void setRefGoodsId(Long refGoodsId) {
		this.refGoodsId = refGoodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
