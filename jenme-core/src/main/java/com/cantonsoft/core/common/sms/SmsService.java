package com.cantonsoft.core.common.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.cantonsoft.core.common.sms.model.Sms;
import com.cantonsoft.core.common.sms.model.SmsDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Lazy
@Service
public class SmsService extends BaseEntityService<Sms, Long> {

	@Autowired
	private SmsDao smsDao;

	@Override
	protected SmsDao getDao() {
		return smsDao;
	}

}
