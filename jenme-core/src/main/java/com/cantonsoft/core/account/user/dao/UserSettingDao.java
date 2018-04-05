package com.cantonsoft.core.account.user.dao;

import java.util.List;

import com.cantonsoft.core.account.user.model.UserSetting;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

public interface UserSettingDao extends BaseEntityDao<UserSetting, Long> {
	List<UserSetting> findByDomainAndUserId(String domain, Long userId);
	UserSetting findByDomainAndUserIdAndType(String domain, Long userId, String type);
}
