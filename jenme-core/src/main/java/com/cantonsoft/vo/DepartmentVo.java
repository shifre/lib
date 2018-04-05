package com.cantonsoft.vo;

import com.cantonsoft.framework.mvc.model.filter.meta.Indexable;

public class DepartmentVo {
	private Long deptId;
	@Indexable
	private String deptCode;
	@Indexable
	private String deptName;
	@Indexable
	private String phone;
	private String address;
	private boolean store;
	private boolean open;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

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

	public boolean isStore() {
		return store;
	}

	public void setStore(boolean store) {
		this.store = store;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
