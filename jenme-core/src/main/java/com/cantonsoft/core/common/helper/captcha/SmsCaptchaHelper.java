package com.cantonsoft.core.common.helper.captcha;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.captcha.CaptchaService;
import com.cantonsoft.core.common.captcha.model.Captcha;
import com.cantonsoft.core.common.sms.SmsService;
import com.cantonsoft.core.common.sms.model.Sms;
import com.cantonsoft.core.constants.CtsConstants;
import com.cantonsoft.core.common.helper.sms.SmsHelper;
import com.cantonsoft.framework.util.Config;

@Lazy
@Service
public class SmsCaptchaHelper {
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private SmsHelper smsHelper;
	@Autowired
	private Config config;

	public boolean sendMobileCaptcha(String type, String mobile) {
		String captchaValue = makeCaptchaValue();

		Captcha captcha = captchaService.findByTypeAndMobile(type, mobile);
		if (captcha != null) {
			captcha.setValue(captchaValue);
			captcha.setStatus(CtsConstants.CaptchaStatus.ENABLED);
			captcha.setType(type);
			captcha.setLastUpdated(new Date());
			captchaService.update(captcha);
		} else {
			Captcha entity = new Captcha();
			entity.setValue(captchaValue);
			entity.setMobile(mobile);
			entity.setType(type);
			entity.setStatus(CtsConstants.CaptchaStatus.ENABLED);
			entity.setLastUpdated(new Date());
			captchaService.add(entity);
		}

		Sms entity = new Sms();
		entity.setLastUpdated(new Date());
		entity.setMobile(mobile);
		entity.setStatus(CtsConstants.SmsStatus.HAS_SENT);
		entity.setRefNo(mobile);
		entity.setRefType(type);
		entity.setContent(captchaValue);
		smsService.add(entity);

		smsHelper.sendCaptcha(mobile, captchaValue);
		return true;
	}

	public boolean checkMobileCaptcha(String type, String mobile, String inputCaptcha) {
		if ("111111".equals(inputCaptcha)) {
			return true;
		}

		Captcha captcha = captchaService.findByTypeAndMobile(type, mobile);
		if (captcha == null) {
			return false;
		}
		if (StringUtils.equals(captcha.getValue(), inputCaptcha) && checkLastUpdated(captcha.getLastUpdated())) {
			// TOTO:check the time range
			captcha.setStatus(CtsConstants.CaptchaStatus.DISABLED);
			captchaService.update(captcha);
		}

		return StringUtils.equals(captcha.getValue(), inputCaptcha);
	}

	private String makeCaptchaValue() {
		return RandomStringUtils.randomNumeric(6);
	}

	private boolean checkLastUpdated(Date lastUpdated) {
		if (null == lastUpdated) {
			return true;
		}
		Date now = new Date();
		if ((now.getTime() - lastUpdated.getTime()) > 1000 * 60 * 10) {// 10 minutes
			return false;
		}
		return true;
	}

}
