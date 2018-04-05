package com.cantonsoft.core.common.asset.string.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
@Repository
@Lazy
public interface AssetStringDao extends BaseEntityDao<AssetString, Long>{

	List<AssetString> findByAssetId(Long assetId);
	@Query("SELECT MIN(position) FROM AssetString a WHERE a.assetId = :assetId")
	Integer findMinPositionByAssetId(@Param("assetId") Long assetId);
	List<AssetString> findByAssetIdIn(List<Long> assetIds);
	@Query("DELETE  AssetString a WHERE a.assetId IN :assetIds")
	void delByAssetIds(@Param("assetIds") List<Long> assetIds);
	@Query("DELETE  AssetString a WHERE a.assetId = :assetId")
	void delByAssetId(@Param("assetId")  Long assetId);
}
