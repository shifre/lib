package com.cantonsoft.core.common.member.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
@Repository
@Lazy
public interface MemberRegisterDao extends BaseEntityDao<MemberAdd, Long>{
		
	 MemberAdd findByMobile(String mobile);
}
