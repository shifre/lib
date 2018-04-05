package com.cantonsoft.admin.template.processor.current;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.core.cloud.cache.CacheRegister;
import com.cantonsoft.core.cloud.cache.UniqueUserKey;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.cache.TwoLevelCacheManager;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class SettingProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private TwoLevelCacheManager cacheManager;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	
	public SettingProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		GenericUser user = sessionHelper.getCurrentUser();
		Map<String, String> result = (Map<String, String>)cacheManager.getData(CacheRegister.USER_SETTING, new UniqueUserKey(user.getDomain(), user.getId()));
		if (null == result)
		{
			result = Collections.EMPTY_MAP;
		}
		addToModel(arguments, element, var, result);
	}
}
