package com.cantonsoft.core.account.user.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.mvc.model.filter.meta.Indexable;
import com.cantonsoft.framework.validation.form.meta.Check;

@MappedSuperclass
@Check(value = "user")
public abstract class GenericUser extends IdEntity {
	private static final long serialVersionUID = 1L;
	
	@Indexable
	private String title;
	private String phone;
	private String email;
	private String image;
	@Indexable
	private String type;
	@Indexable
	private Date createdTime = new Date();
	private boolean enabled;
	private String description;
	public static String TYPE_ADMIN="ADMIN";
	
	@Transient
	public abstract String getDomain();

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
		return StringUtils.lowerCase(email);
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return StringUtils.upperCase(type);
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}