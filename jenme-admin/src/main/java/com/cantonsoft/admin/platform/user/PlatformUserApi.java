package com.cantonsoft.admin.platform.user;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.account.user.UserCreation;
import com.cantonsoft.core.account.user.UserService;
import com.cantonsoft.core.account.user.model.User;
import com.cantonsoft.core.account.user.model.UserAuth;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service.EntityAccessControl;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.validation.IValidationDirector;

@JsonApi
@Lazy
@RequestMapping(value = "platform/user")
@Access(value = "fn.main.user")
public class PlatformUserApi extends BaseEntityApi<User, Long, UserCreation, UserCreation>{
	@Autowired
	private UserService userService;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	@Autowired
	private IValidationDirector vDirector;
	
	@Override
	protected BaseEntityService<User, Long> getEntityService() {
		return userService;
	}
	@Override
	protected EntityAccessControl<Long> getControl() {
		return null;
	}
	
	@PostConstruct
	public void init()
	{
		searchDef = new SearchDef().addField("title");
	}
	
	@Override
	protected User doAdd(Map<String, Object> respMap, UserCreation data, HttpServletRequest request, HttpServletResponse response) {
		
		vDirector.findValidator("createPassword").validate(data);
		vDirector.findValidator("userName").validate(data);
		User user = userService.getNewObject(data);
		userService.addWithPassword(user, data.getUsername(), data.getPassword());
		return user;
	}

	@Override
	protected void doDelete(Long id, HttpServletRequest request, HttpServletResponse response) {
		if (sessionHelper.getCurrentUser().getDomain().equals(UserAuth.AUTH_TYPE_MAIN) && id.equals(sessionHelper.getCurrentUser().getId())) {
			throw new ServiceException("error.user.removeself");
		}
		super.doDelete(id, request, response);
	}
	
	@Override
	protected User doUpdate(Map<String, Object> respMap, Long id, UserCreation data, HttpServletRequest request, HttpServletResponse response) {
		User user = userService.getUpdateObject(id, data);
		if (StringUtils.isEmpty(data.getPassword()))
		{
			userService.update(user);
		}
		else
		{
			vDirector.findValidator("createPassword").validate(data);
			userService.updateWithPassword(user, data.getPassword());
		}
		return user;
	}
}
