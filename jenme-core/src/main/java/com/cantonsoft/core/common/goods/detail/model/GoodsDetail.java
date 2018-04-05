package com.cantonsoft.core.common.goods.detail.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;

@Entity
@Table(name = "T_GOODS_DETAIL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GoodsDetail extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8752176158225806871L;
	private Long goodsId;
	private Long refGoodsId;
	private Long quantity;

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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
