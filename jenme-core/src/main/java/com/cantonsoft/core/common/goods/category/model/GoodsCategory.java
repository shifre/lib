package com.cantonsoft.core.common.goods.category.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;

@Entity
@Table(name = "T_GOODS_CATEGORY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GoodsCategory extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3952952928406417721L;
	private String code;
	private String name;

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
}
