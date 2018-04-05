package com.cantonsoft.admin.template.processor.data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.account.user.GenericUserService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class UserProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private GenericUserService userService;
	
	public UserProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		String domain = element.getAttributeValue("domain");
		String idStr = element.getAttributeValue("id");
		Object result = null;
		if (StringUtils.isNumeric(idStr))
		{
			result = userService.findUser(domain, Long.valueOf(idStr));
		}
		addToModel(arguments, element, var, result);
	}
}
