package com.cantonsoft.admin.template.processor.data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.common.goods.detail.GoodsDetailService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class GoodsDetailListProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private GoodsDetailService service;
	
	public GoodsDetailListProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		String idStr = element.getAttributeValue("id");
		Object result = null;
		if (StringUtils.isNumeric(idStr))
		{
			result = service.findAllByGoodsId(Long.valueOf(idStr));
		}
		addToModel(arguments, element, var, result);
	}
}
