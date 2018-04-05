package com.cantonsoft.admin.template.processor.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.common.info.vendor.VendorService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class VendorListProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private VendorService service;
	
	public VendorListProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		Object result = service.findAll();
		addToModel(arguments, element, var, result);
	}
}
