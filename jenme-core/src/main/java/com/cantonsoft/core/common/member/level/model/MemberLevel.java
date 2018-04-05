package com.cantonsoft.core.common.member.level.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cantonsoft.framework.mvc.model.IdEntity;
import com.cantonsoft.framework.mvc.model.filter.meta.Indexable;
import com.cantonsoft.framework.validation.form.meta.Check;
@Entity
@Table(name = "T_MEMBER_LEVEL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Check(value = "memberLevel")
public class MemberLevel extends IdEntity{
	private static final long serialVersionUID = 1L;
	private Long clientId;
	@Indexable
	private String title;
	@Indexable
	private Long level;
	private Long position;
	private String status;
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public Long getPosition() {
		return position;
	}
	public void setPosition(Long position) {
		this.position = position;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
