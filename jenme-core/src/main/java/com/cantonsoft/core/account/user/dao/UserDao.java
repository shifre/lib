package com.cantonsoft.core.account.user.dao;

import org.springframework.stereotype.Repository;

import com.cantonsoft.core.account.user.model.User;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
public interface UserDao extends BaseEntityDao<User, Long> {
	
}
