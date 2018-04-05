package com.cantonsoft.core.common.member.auth.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
@Lazy
public interface MemberAuthDao extends BaseEntityDao<MemberAuth, Long> {

	MemberAuth findByMemberId(Long memberId);

	MemberAuth findByAccount(String account);
	
	MemberAuth findByToken(String token);

	MemberAuth findByAccountAndPassword(String account, String password);

	@Modifying
	@Query("UPDATE MemberAuth a SET a.password = :password WHERE a.id = :id")
	void updatePassword(@Param("password") String password, @Param("id") Long id);

	@Modifying
	@Query("UPDATE MemberAuth a SET a.password = :password WHERE a.account = :account")
	void updatePassword(@Param("password") String password, @Param("account") String account);
}
