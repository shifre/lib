package com.cantonsoft.admin.platform;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/platform")
public class PlatformIndexViewController {
	@RequestMapping(value = {"index.xhtml", "dashboard.xhtml"})
	public String gotoView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "platform/dashboard";
	}	
	
	@RequestMapping(value = {"waiting.xhtml"})
	public String gotoWaitView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "error/waiting";
	}
}
