package com.cantonsoft.admin.template.processor.current;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class MenuProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private ShiroSessionHelper sessionHelper;
	
	public MenuProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		Object result = sessionHelper.getUserMenu();
		addToModel(arguments, element, var, result);
	}
}
