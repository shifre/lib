package com.cantonsoft.core.common.asset;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.asset.model.Asset;
import com.cantonsoft.core.common.asset.model.AssetDao;
import com.cantonsoft.core.common.asset.string.AssetStringService;
import com.cantonsoft.core.common.asset.string.model.AssetString;
import com.cantonsoft.core.common.asset.text.AssetTextService;
import com.cantonsoft.core.common.asset.text.model.AssetText;
import com.cantonsoft.framework.event.Event;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
@Service
@Lazy
public class AssetService extends BaseEntityService<Asset, Long>{
	@Autowired
	private AssetDao dao;
	
	@Autowired
	private AssetTextService assettextservice;
	
	@Autowired
	private AssetStringService assetstringservice;

	@Override
	protected BaseEntityDao<Asset, Long> getDao() {
		return dao;
	}

	public Asset findBySiteIdAndTitle(Long siteId, String title){
		return dao.findBySiteIdAndTitle(siteId, title);
	}
	
	public Asset findBySiteIdAndName(Long siteId, String name) {
		return dao.findBySiteIdAndName(siteId, name);
	}
	
	public Integer findByIdMaxCount(Long siteId, String type){
		Integer idmax = dao.countById(siteId, type);
		if (idmax == 0) {
			idmax = 1;
		}else{
			idmax += 1;
		}
		 return idmax;
	}
	
	public void importService(Long siteId, InputStream stream) throws Exception
	{
		ObjectMapper objMapper = new ObjectMapper();
		HashMap map = objMapper.readValue(stream, HashMap.class);
		checkData(siteId, map, objMapper);
	}
	
	public void checkData(Long siteId, Map map, ObjectMapper objMapper) throws Exception {
		
		 List<Asset> assetList = null;
		 List<AssetText> assetTextList = null;
		 List<AssetString> assetStringList = null;
		 
		 try {
			Object value = map.get("assets");
			JavaType jt = objMapper.getTypeFactory().constructParametricType(ArrayList.class, Asset.class);
			assetList = objMapper.convertValue(value, jt);
			
			value = map.get("assettexts");
			jt = objMapper.getTypeFactory().constructParametricType(ArrayList.class, AssetText.class);
			assetTextList = objMapper.convertValue(value, jt);
			
			value = map.get("assetstrings");
			jt = objMapper.getTypeFactory().constructParametricType(ArrayList.class, AssetString.class);
			assetStringList = objMapper.convertValue(value, jt);
			
		} catch (Exception e) {
			throw new ServiceException("error.structure.import.format");
		}
		 
		importData(siteId, assetList, assetTextList, assetStringList);
	}
	
	public void importData(Long siteId, List<Asset> assetList, List<AssetText> assetTextList, List<AssetString> assetStringList) throws Exception {
		delData(siteId);
		
		Long sId = null;
		
		Map<Long, Long> assetIdMap = new HashMap<Long, Long>();
		
		if(assetList != null){
			for (Asset asset : assetList) {
				sId = asset.getId();
				asset.setId(null);
				asset.setSiteId(siteId);
				asset.setTemplateField(true);
				asset.setSourceType("");
				add(asset);
				assetIdMap.put(sId,asset.getId());
			}
		}
		
		
		if (assetTextList != null) {
			for (AssetText assetText : assetTextList) {
				assetText.setId(null);
				assetText.setAssetId(assetIdMap.get(assetText.getAssetId()));
				assettextservice.add(assetText);
			}
		}
		
		if(assetTextList != null){
			for (AssetString assetString : assetStringList) {
				assetString.setId(null);
				assetString.setAssetId(assetIdMap.get(assetString.getAssetId()));
				assetstringservice.add(assetString);
			}
		}
		
		eventDispatcher.dispatch(new Event("site-refresh", null, siteId));
	}
	
	public List<Long> getAssetIds(List<Asset> assetList) {
		List<Long> assetIds = new ArrayList<>();
		for (Asset asset : assetList) {
			assetIds.add(asset.getId());
		}
		return assetIds;
	}
	
	public Map<String, Object> getDataMap(Long siteId)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Asset> assetList = dao.findBySiteId(siteId);
		if (null == assetList || assetList.isEmpty())
		{
			jsonMap.put("assets", new ArrayList<>());
			jsonMap.put("assettexts", new ArrayList<>());
			jsonMap.put("assetstrings", new ArrayList<>());
			
			return jsonMap;
		}
		
		List<Long> assetIds = getAssetIds(assetList);
		List<AssetText> assetTextList = assettextservice.findByAssetIdIn(assetIds);
		List<AssetString> assetStringList = assetstringservice.findByAssetIdIn(assetIds);
		
		jsonMap.put("assets", assetList);
		jsonMap.put("assettexts", assetTextList);
		jsonMap.put("assetstrings", assetStringList);
		
		return jsonMap;
		
	}
	
	public void delData(Long siteId) {
		if (siteId != null) {
			List<Asset> assetList = dao.findBySiteId(siteId);
			List<Long> assetIds = getAssetIds(assetList);
			if(assetIds.isEmpty())
			{
				return;
			}
			assetstringservice.delByAssetIds(assetIds);
			assettextservice.delByAssetIds(assetIds);
			dao.delBySiteId(siteId);
		}
	}
}
