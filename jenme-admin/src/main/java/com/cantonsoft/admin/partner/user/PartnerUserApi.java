package com.cantonsoft.admin.partner.user;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.account.user.PartnerUserService;
import com.cantonsoft.core.account.user.UserCreation;
import com.cantonsoft.core.account.user.model.PartnerUser;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.core.cloud.partner.model.Partner;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service.EntityAccessControl;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.Cond;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;
import com.cantonsoft.framework.validation.IValidationDirector;

@JsonApi
@Lazy
@RequestMapping(value = "partner/user")
@Access(value = "fn.partner.user")
public class PartnerUserApi extends BaseEntityApi<PartnerUser, Long, UserCreation, UserCreation> {
	@Autowired
	private PartnerUserService service;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	@Autowired
	private IValidationDirector vDirector;
	@Autowired
	private PartnerUserAccessControl accessControl;

	@Override
	protected BaseEntityService<PartnerUser, Long> getEntityService() {
		return service;
	}

	@Override
	protected EntityAccessControl<Long> getControl() {
		return accessControl;
	}

	@PostConstruct
	public void init() {
		searchDef = new SearchDef().addField("title").addField("partnerId");
	}

	@Override
	protected PartnerUser doAdd(Map<String, Object> respMap, UserCreation data, HttpServletRequest request, HttpServletResponse response) {
		vDirector.findValidator("createPassword").validate(data);
		vDirector.findValidator("userName").validate(data);

		Long partnerId = sessionHelper.getSession().getPartner().getId();

		PartnerUser user = service.getNewObject(data);
		user.setPartnerId(partnerId);
		service.addWithPassword(user, data.getUsername() + "@" + partnerId, data.getPassword());
		return user;
	}

	
	@Override
	protected void doDelete(Long id, HttpServletRequest request, HttpServletResponse response) {
		if (sessionHelper.getCurrentUser().getDomain().equals(UserAuth.AUTH_TYPE_PARTNER) && id.equals(sessionHelper.getCurrentUser().getId())) {
			throw new ServiceException("error.user.removeself");
		}
		super.doDelete(id, request, response);
	}

	@Override
	protected PageData<PartnerUser> doSearch(PageFilter filter, HttpServletRequest request,
			HttpServletResponse response) {
		Partner partner = sessionHelper.getSession().getPartner();
		filter.getConds().add(new Cond("partnerId", Cond.OP_EQ, partner.getId().toString()));
		return super.doSearch(filter, request, response);
	}

	@Override
	protected PartnerUser doUpdate(Map<String, Object> respMap, Long id, UserCreation data, HttpServletRequest request, HttpServletResponse response) {
		PartnerUser user = service.getUpdateObject(id, data);
		if (StringUtils.isEmpty(data.getPassword())) {
			service.update(user);
		} else {
			vDirector.findValidator("createPassword").validate(data);
			service.updateWithPassword(user, data.getPassword());
		}
		return user;
	}
}
