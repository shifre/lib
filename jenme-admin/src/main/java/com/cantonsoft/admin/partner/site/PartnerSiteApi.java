package com.cantonsoft.admin.partner.site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.core.cloud.site.SiteService;
import com.cantonsoft.core.cloud.site.model.Site;
import com.cantonsoft.core.cloud.site.model.SiteUpdate;
import com.cantonsoft.core.cloud.site.view.SiteViewService;
import com.cantonsoft.core.common.template.TemplateService;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.event.IEventDispatcher;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;


@JsonApi
@Lazy
@RequestMapping(value = "partner/site")
@Access(value = "fn.partner.site")
public class PartnerSiteApi extends BaseEntityApi<Site, Long, Site, SiteUpdate>{
	@Autowired
	private SiteService service;
	@Autowired
	private SiteViewService viewService;
	@Autowired
	protected IEventDispatcher eventDispatcher;
	@Autowired
	private TemplateService templateService;

	@Override
	protected BaseEntityService<Site, Long> getEntityService() {
		return service;
	}
	
	@Access(value = "read")
	@RequestMapping(value = "view/search.json", method = RequestMethod.POST)
	public @ResponseBody Object viewSearch(@RequestBody PageFilter filter, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PageData<?> page = viewService.find(filter);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), page);
	}
	
	@Access(value = "update")
	@RequestMapping(value = "export.json", method = RequestMethod.GET)
	public void export(@RequestParam Long siteId, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		templateService.exportSiteTemplate(siteId, response);
	}
}
