package com.cantonsoft.core.common.member.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.member.address.model.MemberAddress;
import com.cantonsoft.core.common.member.address.model.MemberAddressDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
@Service
@Lazy
public class MemberAddressService extends BaseEntityService<MemberAddress, Long>{
	@Autowired
	private MemberAddressDao dao;

	@Override
	protected BaseEntityDao<MemberAddress, Long> getDao() {
		return dao;
	}
	
	public List<MemberAddress> findByMemberId(Long memberId) {
		return dao.findByMemberId(memberId);
	}

	public MemberAddress findDefaultAddress(Long memberId) {
		return dao.findTopByMemberIdAndDefaultFlag(memberId, Boolean.TRUE);
	}
}
