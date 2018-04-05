package com.cantonsoft.admin.client.user;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.account.user.ClientUserService;
import com.cantonsoft.core.account.user.UserCreation;
import com.cantonsoft.core.account.user.model.ClientUser;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.core.cloud.client.model.Client;
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
@RequestMapping(value = "client/user")
@Access(value = "fn.client.user")
public class ClientUserApi extends BaseEntityApi<ClientUser, Long, UserCreation, UserCreation>{
	@Autowired
	private ClientUserService service;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	@Autowired
	private IValidationDirector vDirector;
	@Autowired
	private ClientUserAccessControl accessControl;
	

	@Override
	protected BaseEntityService<ClientUser, Long> getEntityService() {
		return service;
	}
	@Override
	protected EntityAccessControl<Long> getControl() {
		return null;
	}
	
	@PostConstruct
	public void init()
	{
		searchDef = new SearchDef().addField("title").addField("clientId");
	}
	
	@Override
	protected ClientUser doAdd(Map<String, Object> respMap, UserCreation data, HttpServletRequest request, HttpServletResponse response) {
		vDirector.findValidator("createPassword").validate(data);
		vDirector.findValidator("userName").validate(data);
		
		Long clientId = sessionHelper.getSession().getClient().getId();
		ClientUser user = service.getNewObject(data);
		
		user.setClientId(clientId);
		service.addWithPassword(user, data.getUsername() + "@" + clientId, data.getPassword());
		
		return user;
	}
	
	@Override
	protected void doDelete(Long id, HttpServletRequest request, HttpServletResponse response) {
		if (sessionHelper.getCurrentUser().getDomain().equals(UserAuth.AUTH_TYPE_CLIENT) && id.equals(sessionHelper.getCurrentUser().getId())) {
			throw new ServiceException("error.user.removeself");
		}
		super.doDelete(id, request, response);
	}
	
	@Override
	protected PageData<ClientUser> doSearch(PageFilter filter, HttpServletRequest request,
			HttpServletResponse response) {
		Client client = sessionHelper.getSession().getClient();
		filter.getConds().add(new Cond("clientId", Cond.OP_EQ, client.getId().toString()));
		return super.doSearch(filter, request, response);
	}

	@Override
	protected ClientUser doUpdate(Map<String, Object> respMap, Long id, UserCreation data, HttpServletRequest request, HttpServletResponse response) {
		ClientUser user = service.getUpdateObject(id, data);
		if (StringUtils.isEmpty(data.getPassword()))
		{
			service.update(user);
		}
		else
		{
			vDirector.findValidator("createPassword").validate(data);
			service.updateWithPassword(user, data.getPassword());
		}
		return user;
	}
}