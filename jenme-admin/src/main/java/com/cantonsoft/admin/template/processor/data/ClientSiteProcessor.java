package com.cantonsoft.admin.template.processor.data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.cloud.site.SiteService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class ClientSiteProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private SiteService siteService;
	
	
	public ClientSiteProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		String clientId = element.getAttributeValue("clientId");
		Object result = null;
		if (StringUtils.isNumeric(clientId))
		{
			result = siteService.findByClientId(Long.valueOf(clientId));
		}
		addToModel(arguments, element, var, result);
	}
}
