package com.cantonsoft.core.common.department.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;

@Entity
@Table(name = "T_DEPARTMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8245386939789169448L;
	private String deptCode;
	private String deptName;
	private String phone;
	private String address;
	private String storeInd = "N";
	private String openInd = "N";
	private String expiredInd = "N";

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStoreInd() {
		return storeInd;
	}

	public void setStoreInd(String storeInd) {
		this.storeInd = storeInd;
	}

	public String getOpenInd() {
		return openInd;
	}

	public void setOpenInd(String openInd) {
		this.openInd = openInd;
	}

	public String getExpiredInd() {
		return expiredInd;
	}

	public void setExpiredInd(String expiredInd) {
		this.expiredInd = expiredInd;
	}

}
