package com.cantonsoft.core.common.thumbs.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
@Lazy
public interface ThumbsDao extends BaseEntityDao<Thumbs, Long> {
		List<Thumbs> findByRefIdAndRefType(Long refId, String refType);
		
		@Query("SELECT MAX(sort) FROM Thumbs a where refId=:refId and refType=:refType")
		Integer findMaxSort(@Param("refId")Long refId,@Param("refType")String refType);
}
