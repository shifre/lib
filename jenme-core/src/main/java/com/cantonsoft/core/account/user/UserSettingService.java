package com.cantonsoft.core.account.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.account.user.dao.UserSettingDao;
import com.cantonsoft.core.account.user.model.UserSetting;
import com.cantonsoft.core.common.event.EventRegister;
import com.cantonsoft.framework.event.Event;
import com.cantonsoft.framework.event.IEventDispatcher;
import com.cantonsoft.framework.validation.IValidationDirector;

@Service
@Lazy
public class UserSettingService {
	@Autowired
	private IEventDispatcher eventDispatcher;
	@Autowired
	private UserSettingDao userSettingDao;
	@Autowired
	private IValidationDirector vDirector;
	
	public void save(String domain, Long userId, String type, String value)
	{
		UserSetting setting = new UserSetting();
		setting.setDomain(domain);
		setting.setUserId(userId);
		setting.setType(type);
		setting.setValue(value);
		vDirector.findValidator("user.setting").validate(setting);
		
		UserSetting entity = userSettingDao.findByDomainAndUserIdAndType(domain, userId, type);
		if (null == entity)
		{
			entity = setting;
			userSettingDao.save(entity);
		}
		else
		{
			entity.setValue(value);
			userSettingDao.save(entity);
		}
		eventDispatcher.dispatch(new Event(EventRegister.USERSETTING_CHANGE, entity, entity));
	}
	
	public Map<String, String> getUserSettings(String domain, Long userId)
	{
		List<UserSetting> list = userSettingDao.findByDomainAndUserId(domain, userId);
		Map<String, String> ret = new HashMap<String, String>();
		
		for (UserSetting setting : list)
		{
			ret.put(setting.getType(), setting.getValue());
		}
		return ret;
	}
}
