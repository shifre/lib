package com.cantonsoft.vo;

import com.cantonsoft.framework.mvc.model.IdEntity;

public class UserVo extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String title;
	private String phone;
	private String email;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}