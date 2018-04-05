package com.cantonsoft.admin.template.processor.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.account.user.AuthService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class UsernameProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private AuthService authService;
	
	public UsernameProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		String domain =  element.getAttributeValue("domain");
		String idStr = element.getAttributeValue("id");

		String username = null;
		if (StringUtils.isNumeric(idStr))
		{
			username = authService.findUsername(domain, Long.valueOf(idStr));
		}
		Map<String, String> result = new HashMap<>();
		result.put("username", username);
		addToModel(arguments, element, var, result);
	}
}
