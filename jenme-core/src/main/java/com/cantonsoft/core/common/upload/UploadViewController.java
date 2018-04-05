package com.cantonsoft.core.common.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cantonsoft.framework.mvc.controller.AbstractViewController;
import com.cantonsoft.framework.mvc.meta.ViewBase;

@Controller
@Lazy
@RequestMapping(value = "/upload")
@ViewBase(path = "/upload", prefix="upload")
public class UploadViewController extends AbstractViewController {
	
	@RequestMapping(value = "image", method = RequestMethod.GET)
	public String uploadImage(HttpServletRequest request, HttpServletResponse response)
	{
		return getView("image");
	}
	
}
