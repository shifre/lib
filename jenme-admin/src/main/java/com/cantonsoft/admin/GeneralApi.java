package com.cantonsoft.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.framework.api.ApiException;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;

@JsonApi
public class GeneralApi {
	@RequestMapping(value = "/**")
	public @ResponseBody Object gotoApi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		throw new ApiException(IErrors.API_NOT_AVAILABLE, "Api is unavailable.");
	}
}
