package com.cantonsoft.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cantonsoft.framework.mvc.controller.AbstractViewController;

@Controller
@Lazy
public class IndexViewController extends AbstractViewController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexView(HttpServletRequest request, HttpServletResponse response)
	{
		return "index";
	}
}