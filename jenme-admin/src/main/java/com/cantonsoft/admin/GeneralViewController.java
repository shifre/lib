package com.cantonsoft.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralViewController {
	private final static String SUFFIX = ".xhtml";
	private final static String WELCOME_PAGE = "index";

	@RequestMapping(value = "/**")
	public String gotoView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getRequestURI().substring(request.getContextPath().length());

		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		if (path.endsWith(SUFFIX)) {
			path = path.substring(0, path.length() - SUFFIX.length());
		} else {
			path = path + WELCOME_PAGE;
		}
		return path;
	}
}
