package com.cantonsoft.core.common.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.member.model.Member;
import com.cantonsoft.core.common.member.model.MemberDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
@Service
@Lazy
public class MemberService extends BaseEntityService<Member, Long>{
	@Autowired
	private MemberDao dao;

	@Override
	protected BaseEntityDao<Member, Long> getDao() {
		return dao;
	}
	
	public  Member findByMobileAndPassword(String mobile, String password){
		return dao.findByMobileAndPassword(mobile, password);
	}

	public Member findByOpenId(String openId){
		return dao.findTopByOpenId(openId);
	}
}
