package com.cantonsoft.core.cloud.client.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.VDeleteIdEntity;
import com.cantonsoft.framework.mvc.model.filter.meta.Indexable;
import com.cantonsoft.framework.validation.form.meta.Check;

@Entity
@Table(name = "T_CLIENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "client")
public class Client extends VDeleteIdEntity {
	private static final long serialVersionUID = 1L;

	public static final String TYPE_VIP1 = "VIP1";
	public static final String TYPE_VIP2 = "VIP2";
	public static final String TYPE_VIP3 = "VIP3";

	private Long partnerId;
	private String type;
	@Indexable
	private String title;
	private String image;
	private String phone;
	private String email;
	private boolean enabled;
	private boolean chain;
	private String contact;
	private Date createdTime = new Date();

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isChain() {
		return chain;
	}

	public void setChain(boolean chain) {
		this.chain = chain;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
