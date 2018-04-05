package com.cantonsoft.core.account.user.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
public interface UserAuthDao extends BaseEntityDao<UserAuth, Long> {

	UserAuth findByDomainAndUserId(String domain, Long userId);
	UserAuth findByDomainAndUsername(String domain, String username);
	@Modifying
	@Query("UPDATE UserAuth a SET a.password = :password WHERE a.id = :id")
	void updatePassword(@Param("password") String password, @Param("id")Long id);

	@Modifying
	@Query("UPDATE UserAuth a SET a.password = :password WHERE a.userId = :userId AND a.domain = :domain")
	void updatePassword(@Param("domain")String domain, @Param("userId")Long userId, @Param("password")String password);
	
}
