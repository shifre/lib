package com.cantonsoft.core.common.notice.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
@Lazy
public interface NoticeDao extends BaseEntityDao<Notice,Long>{
	@Query("SELECT a FROM Notice a WHERE a.status=1 and (a.type = 1 or a.type=2) ORDER BY createdTime DESC")
	List<Notice> findPatnerNotice();
	@Query("SELECT a FROM Notice a WHERE a.status=1 and a.type = 2 ORDER BY createdTime DESC")
	List<Notice> findClientNotice();
	
	@Query("SELECT a FROM Notice a WHERE a.status=1 and a.type = :type ORDER BY createdTime DESC")
	List<Notice> findTop5ByTypeByCreatedTimeDesc(@Param("type")Integer type);
	
}
