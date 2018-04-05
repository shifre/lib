package com.cantonsoft.admin.template.processor.data;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class OrderStatusProcessor extends AbstractModelVariableModifierProcessor {

	private Class<?> clazz;
	
	public OrderStatusProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		String className = element.getAttributeValue("className");
		String fieldName = element.getAttributeValue("fieldName");
		Object result = null;
		if (StringUtils.isNotEmpty(fieldName))
		{
			try {
				clazz = Class.forName(className);
				Object o=clazz.newInstance();
				String firstLetter = fieldName.substring(0, 1).toUpperCase(); 
				String getter = "get"+firstLetter + fieldName.substring(1);      
			    Method method = o.getClass().getMethod(getter, new Class[] {}); 
			    
				result =  method.invoke(o, new Object[] {});
			} catch (Exception e) {
				e.printStackTrace();
				result = null;
			}
		}
		addToModel(arguments, element, var, result);
	}
}
