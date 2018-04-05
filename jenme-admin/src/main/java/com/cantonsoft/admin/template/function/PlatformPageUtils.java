package com.cantonsoft.admin.template.function;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.mvc.template.function.PageUtils;
import com.cantonsoft.framework.util.Config;
import com.cantonsoft.framework.validation.js.UiValidationCreator;

public class PlatformPageUtils extends PageUtils {
	@Autowired
	private Config config;
	
	@Autowired
	private ShiroSessionHelper sessionHelper;
	
	private String filehost;
	
	@PostConstruct
	public void init()
	{
		filehost = config.getProperty("filehost");
	}
	
	public String file(String path, String defaultFile)
	{
		if (StringUtils.isEmpty(path))
		{
			return this.context(defaultFile);
		}
		else
		{
			return filehost + path;
		}
	}
	
	public String validate(String formConfig, String formId)
	{
		return UiValidationCreator.validate(formConfig, formId);
	}
	
	public String getCurrentSite(){
		return config.getProperty("sitehost")+"/"+sessionHelper.getSession().getSite().getId();
	}
}
