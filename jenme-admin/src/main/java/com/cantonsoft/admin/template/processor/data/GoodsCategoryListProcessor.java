package com.cantonsoft.admin.template.processor.data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.common.goods.category.GoodsCategoryService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class GoodsCategoryListProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private GoodsCategoryService service;
	
	public GoodsCategoryListProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		Object result = service.findAll();
		addToModel(arguments, element, var, result);
	}
}
