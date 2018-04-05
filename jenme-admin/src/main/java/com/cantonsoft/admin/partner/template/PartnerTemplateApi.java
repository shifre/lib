package com.cantonsoft.admin.partner.template;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.core.cloud.cache.CacheRegister;
import com.cantonsoft.core.common.template.TemplateService;
import com.cantonsoft.core.common.template.model.Template;
import com.cantonsoft.core.cloud.site.SiteService;
import com.cantonsoft.core.cloud.site.model.Site;
import com.cantonsoft.core.cloud.site.model.SiteTemplate;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.event.Event;
import com.cantonsoft.framework.event.IEventDispatcher;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;


@JsonApi
@Lazy
@RequestMapping(value = "partner/template")
@Access(value = "fn.partner.template")
public class PartnerTemplateApi extends BaseEntityApi<Template, Long, Template, Template>{
	@Autowired
	private TemplateService service;
	@Autowired
	private SiteService siteService;
	@Autowired
	protected IEventDispatcher eventDispatcher;

	@Override
	protected BaseEntityService<Template, Long> getEntityService() {
		return service;
	}
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}
	
	@Access(value = "read")
	@RequestMapping(value = "change.json", method = RequestMethod.POST)  
    public @ResponseBody Object change(@RequestBody SiteTemplate siteTemplate, HttpServletRequest request, HttpServletResponse response) {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try
    	{    		
    		Template template = service.find(siteTemplate.getTemplateId());
    		
    		Site site = siteService.find(siteTemplate.getSiteId());
    		site.setTemplateId(siteTemplate.getTemplateId());
    		
    		service.changeTemplate(siteTemplate.getSiteId(),siteTemplate.getTemplateId(),template.getVersion());
    		siteService.update(site);
    		
    		eventDispatcher.dispatch(new Event(CacheRegister.CLEAR_TEMPLATE_CACHE, null, site.getClientId()));
    	}
    	catch (Exception ex)
    	{
    		throw new ServiceException(ex.getMessage(),ex);
    	}
    	
    	return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), returnMap);
    }
}
