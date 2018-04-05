package com.cantonsoft.core.common.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.member.model.MemberAdd;
import com.cantonsoft.core.common.member.model.MemberRegisterDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
@Service
@Lazy
public class MemberRegisterService extends BaseEntityService<MemberAdd, Long>{
	@Autowired
	private MemberRegisterDao dao;

	@Override
	protected BaseEntityDao<MemberAdd, Long> getDao() {
		return dao;
	}
	

	public MemberAdd findByMobile(String mobile){
		return dao.findByMobile(mobile);
	}
}
