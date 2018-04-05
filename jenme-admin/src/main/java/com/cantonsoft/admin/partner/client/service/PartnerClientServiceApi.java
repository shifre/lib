package com.cantonsoft.admin.partner.client.service;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.cloud.client.service.usage.ServiceUsageService;
import com.cantonsoft.core.cloud.client.service.usage.model.ServiceUsageView;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.DataPageRequest;
import com.cantonsoft.framework.mvc.model.filter.ICond;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.model.filter.WhereCond;
import com.cantonsoft.framework.mvc.service.CrudSvcController;
import com.cantonsoft.framework.mvc.service.ICrudService;

@JsonApi
@Lazy
@RequestMapping(value = "partner/client/service")
@Access(value = "fn.partner.client.service")
public class PartnerClientServiceApi extends CrudSvcController<ServiceUsageView, ServiceUsageView, ServiceUsageView>{
	@Autowired
	private ServiceUsageService service;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	
	@Override
    protected ICrudService<ServiceUsageView> getEntityService() {
	    return service;
    }
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("clientName");
	}
	
	@Override
    protected Map<String, Object> doSearch(DataPageRequest dataPageReq, HttpServletRequest request, HttpServletResponse response) {
		dataPageReq.getWheres().add(new WhereCond("partnerId", ICond.OPERATOR_EQ, sessionHelper.getSession().getPartner().getId()));
	    return super.doSearch(dataPageReq, request, response);
    }
}
