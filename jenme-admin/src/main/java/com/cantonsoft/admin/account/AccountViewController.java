package com.cantonsoft.admin.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cantonsoft.framework.mvc.controller.AbstractViewController;
import com.cantonsoft.framework.mvc.meta.Access;

@Controller
@Lazy
public class AccountViewController extends AbstractViewController {

	@Access(value = "fn.common.profile")
	@RequestMapping(value = {"platform/account/profile.xhtml", "partner/account/profile.xhtml", "client/account/profile.xhtml"}, method = RequestMethod.GET)
	public String profileView(HttpServletRequest request, HttpServletResponse response)
	{
		return "/common/account/profile";
	}
	
	@Access(value = "fn.common.password.change")
	@RequestMapping(value = {"platform/account/changepwd.xhtml", "partner/account/changepwd.xhtml", "client/account/changepwd.xhtml"}, method = RequestMethod.GET)
	public String changePasswordView(HttpServletRequest request, HttpServletResponse response)
	{
		return "/common/account/changepwd";
	}
}