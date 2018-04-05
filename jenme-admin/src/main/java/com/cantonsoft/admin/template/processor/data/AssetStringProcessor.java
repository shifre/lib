package com.cantonsoft.admin.template.processor.data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.common.asset.string.AssetStringService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class AssetStringProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private AssetStringService assetStringService;
	
	public AssetStringProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		String idStr = element.getAttributeValue("id");
		Object result = null;
		if (StringUtils.isNumeric(idStr))
		{
			result = assetStringService.find(Long.valueOf(idStr));
		}
		addToModel(arguments, element, var, result);
	}
}
