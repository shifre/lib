package com.cantonsoft.admin.client.asset;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.core.common.asset.AssetService;
import com.cantonsoft.core.common.asset.model.Asset;
import com.cantonsoft.core.common.asset.model.AssetUpdate;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.Cond;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;

@JsonApi
@Lazy
@RequestMapping(value = "client/asset")
@Access(value = "fn.client.asset")
public class ClientAssetApi extends BaseEntityApi<Asset, Long, Asset, AssetUpdate> {

	@Autowired
	private AssetService service;

	@Autowired
	private ShiroSessionHelper sessionHelper;
	
	@PostConstruct
	public void init()
	{
		searchDef = new SearchDef().addField("title");
	}
	
	@Override
	protected BaseEntityService<Asset, Long> getEntityService() {
		return service;
	}

	@Override
	protected Asset doAdd(Map<String, Object> respMap, Asset data, HttpServletRequest request,
			HttpServletResponse response) {
		data.setSiteId(sessionHelper.getSession().getSite().getId());
		data.setSourceType("");
		if (StringUtils.isEmpty(data.getName())) {
			data.setName(data.getType() + service.findByIdMaxCount(data.getSiteId(), data.getType()));
		}
		return super.doAdd(respMap, data, request, response);
	}

	@Override
	protected PageData<Asset> doSearch(PageFilter filter, HttpServletRequest request, HttpServletResponse response) {
		filter.getConds().add(new Cond("siteId", Cond.OP_EQ, sessionHelper.getSession().getSite().getId().toString()));
		return super.doSearch(filter, request, response);
	}

	@Override
	protected void doDelete(Long id, HttpServletRequest request, HttpServletResponse response) {
		Asset asset = service.find(id);
		if (!asset.isTemplateField()) {
			super.doDelete(id, request, response);
		}else{
			throw new ServiceException("error.template.asset.not.delete");
		}
	}
}
