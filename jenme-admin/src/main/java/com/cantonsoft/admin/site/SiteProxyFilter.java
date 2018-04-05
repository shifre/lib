package com.cantonsoft.admin.site;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.LogFactory;
import org.slf4j.Logger;

import com.cantonsoft.admin.account.auth.SessionStorage;

public class SiteProxyFilter implements Filter{
	private static Logger LOG = LogFactory.getLogger(SiteProxyFilter.class);
	private String access;
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		Subject subject =  SecurityUtils.getSubject();

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		if (subject.isRemembered() || subject.isAuthenticated())
		{
			SessionStorage session = (SessionStorage) subject.getPrincipal();
			if (null != session.getSite())
			{
				filterChain.doFilter(request, response);
				return;
			}
		}
		StringBuffer redirectPage = new StringBuffer();
		try
		{
			if (null != httpRequest.getQueryString() && httpRequest.getQueryString().length() > 0)
			{
				redirectPage.append("?redirect=").append(httpRequest.getRequestURI().toString()).append("?").append(httpRequest.getQueryString());
			}
			else
			{
				redirectPage.append("?redirect=").append(httpRequest.getRequestURI().toString());
			}
		}
		catch (Exception ex)
		{
			
		}
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setStatus(401);
		if (redirectPage.length() == 0)
		{
			httpResponse.sendRedirect(httpRequest.getContextPath() + access);
		}
		else
		{
			httpResponse.sendRedirect(httpRequest.getContextPath() + access + redirectPage);
		}
		return;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
}
