package com.cantonsoft.admin.template.processor.data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import com.cantonsoft.core.common.notice.NoticeService;
import com.cantonsoft.framework.mvc.template.processor.AbstractModelVariableModifierProcessor;

public class NoticesProcessor extends AbstractModelVariableModifierProcessor {

	@Autowired
	private NoticeService noticeService;
	
	public NoticesProcessor(String elementName) {
		super(elementName);
	}

	@Override
	protected void modifyModelAttributes(Arguments arguments, Element element) {
		String var = element.getAttributeValue("var");
		String type = element.getAttributeValue("type");
		Object result = null;
		if (StringUtils.isNumeric(type))
		{
			result = noticeService.getNoticesByType(Integer.valueOf(type));
		}
		addToModel(arguments, element, var, result);
	}
}
