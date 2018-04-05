package com.cantonsoft.core.common.helper.sms;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cantonsoft.framework.http.SyncHttpClient;
import com.cantonsoft.framework.util.Config;

@Component
public class SmsHelper {
	@Autowired
	private SyncHttpClient httpclient;
	@Autowired
	private Config config;

	public String sendCaptcha(String mobile, String captcha) {
		String content = "您的验证码是" + captcha + "。请在页面中提交验证码完成验证。【哥特软件】";
		return sendSms(mobile, content);
	}

	public String sendSms(String mobile, String content) {
		In4SendSms input = new In4SendSms();
		input.setUid("stonecai");
		input.setPwd("ee1e9598ae77cf1f76333716df5b1718");// test123456
		input.setMobile(mobile);
		input.setEncode("utf8");
		input.setContent(content);

		Out4SendSms output = new Out4SendSms();
		httpclient.post(config.getProperty("server.sms.cn"), config.getProperty("sms.send.path"), false, input.toParameters(), output);

		System.out.println("Sent sms: \"" + content + "\" to mobile: " + mobile + " at: " + new Date() + " \nresult==" + output.getStatusCode());
		return output.getData().getSuccess();
	}
}
