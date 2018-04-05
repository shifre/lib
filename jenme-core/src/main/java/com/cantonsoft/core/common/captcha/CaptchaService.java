package com.cantonsoft.core.common.captcha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.captcha.model.Captcha;
import com.cantonsoft.core.common.captcha.model.CaptchaDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Lazy
@Service
public class CaptchaService extends BaseEntityService<Captcha, Long> {
	@Autowired
	private CaptchaDao captchaDao;

	@Override
	protected CaptchaDao getDao() {
		return captchaDao;
	}

	public List<Captcha> findByStatus(Integer status) {
		return captchaDao.findByStatus(status);
	}

	public Captcha findByTypeAndMobile(String type, String mobile) {
		return captchaDao.findByTypeAndMobile(type, mobile);
	}

}
