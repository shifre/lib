package com.cantonsoft.core.common.helper.sms;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import com.cantonsoft.framework.cache.CacheKey;

public class In4SendSms extends CacheKey {

	private String uid;
	private String pwd;
	private String time;
	private String mid;
	private String encode;
	private String mobile;
	private String mobileids;
	private String content;

	public Map<String, String> toParameters() {
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isEmpty(uid)) {
			map.put("uid", uid);
		}
		if (!StringUtils.isEmpty(pwd)) {
			map.put("pwd", pwd);
		}
		if (!StringUtils.isEmpty(time)) {
			map.put("time", time);
		}
		if (!StringUtils.isEmpty(mid)) {
			map.put("mid", mid);
		}
		if (!StringUtils.isEmpty(encode)) {
			map.put("encode", encode);
		}
		if (!StringUtils.isEmpty(mobile)) {
			map.put("mobile", mobile);
		}
		if (!StringUtils.isEmpty(mobileids)) {
			map.put("mobileids", mobileids);
		}
		if (!StringUtils.isEmpty(content)) {
			map.put("content", content);
		}
		return map;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobileids() {
		return mobileids;
	}

	public void setMobileids(String mobileids) {
		this.mobileids = mobileids;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
