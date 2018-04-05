package com.cantonsoft.core.common.member.level.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
@Repository
@Lazy
public interface MemberLevelDao extends BaseEntityDao<MemberLevel, Long>{
	public List<MemberLevel> findByClientId(Long clientId);
	
	@Query("SELECT MAX(a.position) FROM MemberLevel a")
	Long findMaxPosition();

	MemberLevel findTopByClientId(Long clientId,Sort sort);
}
