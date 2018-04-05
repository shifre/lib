package com.cantonsoft.admin.template.processor.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.common.template.TemplateService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class TemplateListProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private TemplateService templateService;
	
	public TemplateListProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		Object result = templateService.findAll();
		addToModel(arguments, element, var, result);
	}
}
