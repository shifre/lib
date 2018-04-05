package com.cantonsoft.core.common.member.address.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
@Repository
@Lazy
public interface MemberAddressDao extends BaseEntityDao<MemberAddress, Long>{
	
	List<MemberAddress> findByMemberId(Long memberId);

	MemberAddress findTopByMemberIdAndDefaultFlag(Long memberId, Boolean defaultFlag);

}
