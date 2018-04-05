package com.cantonsoft.admin.template.processor.data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.common.thumbs.ThumbsService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class ThumbsListProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private ThumbsService thumbsService;
	
	public ThumbsListProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		String refIdStr = element.getAttributeValue("refId");
		String refType=element.getAttributeValue("refType");
				
		Object result = null;
		if (StringUtils.isNumeric(refIdStr))
		{
			result = thumbsService.findByRefIdAndRefType(Long.valueOf(refIdStr),refType);
		}
		addToModel(arguments, element, var, result);
	}
}
