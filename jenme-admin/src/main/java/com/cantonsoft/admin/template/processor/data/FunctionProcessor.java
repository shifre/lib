package com.cantonsoft.admin.template.processor.data;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class FunctionProcessor extends AbstractModelVariableModifierProcessor {
	
	public FunctionProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		String idStr = element.getAttributeValue("functionId");
		Object result = null;
		if (StringUtils.isNumeric(idStr))
		{
			result = true;
		}
		addToModel(arguments, element, var, result);
	}
}
