package com.cantonsoft.core.common.asset.string;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.asset.string.model.AssetString;
import com.cantonsoft.core.common.asset.string.model.AssetStringDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
@Service
@Lazy
public class AssetStringService extends BaseEntityService<AssetString, Long>{
	@Autowired
	private AssetStringDao dao;

	@Override
	protected BaseEntityDao<AssetString, Long> getDao() {
		return dao;
	}
	
	public List<AssetString> findByAssetId(Long assetId) {
		return dao.findByAssetId(assetId);
	}
	
	public Integer findMinPositionByAssetId(Long assetId) {
		
		Integer min = dao.findMinPositionByAssetId(assetId);
		
		if (null == min) 
		{
			min = 1000;
		}
		else
		{
			min -= 1;
		}
		
		return min;
	}
	
	public List<AssetString> findByAssetIdIn(List<Long> assetIds){
		return dao.findByAssetIdIn(assetIds);
	}
	
	public void delByAssetIds(List<Long> assetIds) {
		dao.delByAssetIds(assetIds);
	}
	
	public void delByAssetId(Long assetId) {
		dao.delByAssetId(assetId);
	}
	
}
