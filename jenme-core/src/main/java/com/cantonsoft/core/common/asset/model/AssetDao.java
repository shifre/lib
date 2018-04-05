package com.cantonsoft.core.common.asset.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
@Repository
@Lazy
public interface AssetDao extends BaseEntityDao<Asset, Long>{

	List<Asset> findBySiteId(Long siteId);
	Asset findBySiteIdAndName(Long siteId, String name);
	Asset findBySiteIdAndTitle(Long siteId, String title);
	@Query("SELECT count(id) FROM Asset a Where a.siteId = :siteId AND a.type= :type")
	Integer countById(@Param("siteId") Long siteId, @Param("type") String type);
	@Query("DELETE  Asset a WHERE a.siteId = :siteId")
	void delBySiteId(@Param("siteId") Long siteId);
}
