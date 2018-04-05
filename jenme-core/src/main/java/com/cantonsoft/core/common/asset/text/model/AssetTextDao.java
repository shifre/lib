package com.cantonsoft.core.common.asset.text.model;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
@Repository
@Lazy
public interface AssetTextDao extends BaseEntityDao<AssetText, Long>{

	List<AssetText> findByAssetId(Long assetId);
	List<AssetText> findByAssetIdIn(List<Long> assetIds);
	@Query("SELECT MIN(position) FROM AssetText a WHERE a.assetId = :assetId")
	Integer findMinPositionByAssetId(@Param("assetId") Long assetId); 
	@Query("DELETE  AssetText a WHERE a.assetId IN :assetIds")
	void delByAssetIds(@Param("assetIds") List<Long> assetIds);
	@Query("DELETE  AssetText a WHERE a.assetId = :assetId")
	void delByAssetId(@Param("assetId") Long assetId);
}
