package com.cantonsoft.admin.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/client")
public class ClientIndexViewController {
	@RequestMapping(value = {"index.html", "dashboard.html"})
	public String gotoView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "client/dashboard";
	}	
	
	@RequestMapping(value = {"waiting.html"})
	public String gotoWaitView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "error/waiting";
	}
}
