package com.cantonsoft.core.common.captcha.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Lazy
@Repository
public interface CaptchaDao extends BaseEntityDao<Captcha, Long> {
	
	List<Captcha> findByStatus(Integer status);

	Captcha findByTypeAndMobile(String type, String mobile);

}
