package com.cantonsoft.admin.template.processor.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.cloud.site.setting.SiteSettingService;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class SiteSettingProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private SiteSettingService siteSettingService;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	
	public SiteSettingProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		Long siteId = sessionHelper.getSession().getSite().getId();
		Object result = siteSettingService.findBySiteId(siteId);
		
		addToModel(arguments, element, var, result);
	}
}
