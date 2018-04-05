package com.cantonsoft.admin.template.processor.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.common.department.DepartmentService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class DeptmentListProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private DepartmentService service;
	
	public DeptmentListProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		String storeInd = element.getAttributeValue("storeInd");
		Object result = service.findAllByStoreInd(storeInd);
		addToModel(arguments, element, var, result);
	}
}
