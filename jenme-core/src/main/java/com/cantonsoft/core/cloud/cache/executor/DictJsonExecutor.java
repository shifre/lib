package com.cantonsoft.core.cloud.cache.executor;

import org.springframework.beans.factory.annotation.Autowired;

import com.cantonsoft.framework.cache.CacheDataExecutor;
import com.cantonsoft.framework.tree.Entry;
import com.cantonsoft.framework.util.DictionaryManager;
import com.cantonsoft.framework.util.JsonUtil;

public class DictJsonExecutor extends CacheDataExecutor<String> {
	@Autowired
	private DictionaryManager dictionaryManager;
	
	@Override
	protected Object createData(String key) {
		try
		{
			Entry entry = dictionaryManager.getDictByUniqueKey(key);
			return entry;
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
}
