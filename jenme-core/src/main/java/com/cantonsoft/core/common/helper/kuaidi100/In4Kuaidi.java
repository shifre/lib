package com.cantonsoft.core.common.helper.kuaidi100;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import com.cantonsoft.framework.cache.CacheKey;

public class In4Kuaidi extends CacheKey {

	private String id;
	private String com;
	private String nu;
	private String valicode;
	private String show;// 0：返回json字符串， 1：返回xml对象， 2：返回html对象， 3：返回text文本。
	private String muti;// 1:返回多行完整的信息， 0:只返回一行信息。
	private String order;// desc;asc;

	public Map<String, String> toParameters() {
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isEmpty(id)) {
			map.put("id", id);
		}
		if (!StringUtils.isEmpty(com)) {
			map.put("com", com);
		}
		if (!StringUtils.isEmpty(nu)) {
			map.put("nu", nu);
		}
		if (!StringUtils.isEmpty(valicode)) {
			map.put("valicode", valicode);
		}
		if (!StringUtils.isEmpty(show)) {
			map.put("show", show);
		}
		if (!StringUtils.isEmpty(muti)) {
			map.put("muti", muti);
		}
		if (!StringUtils.isEmpty(order)) {
			map.put("order", order);
		}
		return map;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public String getValicode() {
		return valicode;
	}

	public void setValicode(String valicode) {
		this.valicode = valicode;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getMuti() {
		return muti;
	}

	public void setMuti(String muti) {
		this.muti = muti;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
