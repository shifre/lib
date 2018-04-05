package com.cantonsoft.admin.account.auth;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;

import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.framework.security.IMenuLoader;
import com.cantonsoft.framework.security.Menu;
import com.cantonsoft.framework.security.NotPermittedError;
import com.cantonsoft.framework.security.SessionHelper;

public class ShiroSessionHelper implements SessionHelper<GenericUser> {
	@Autowired
	@Qualifier("mainMenuLoader")
	private IMenuLoader mainMenuLoader;

	public boolean hasPermission(String permission)
	{
		if (null == permission || null == getCurrentUser())
		{
			return false;
		}
		return SecurityUtils.getSubject().isPermitted(permission);
	}
	
	@Override
	public void checkPermission(String permission) throws NotPermittedError {
		if (null == permission)
		{
			return;
		}
		
		if (null == getCurrentUser())
		{
			throw new NotPermittedError();
		}
		
		if (!SecurityUtils.getSubject().isPermitted(permission))
		{
			throw new NotPermittedError(permission);
		}
	}

	public void checkPermission(Model model, String permission) throws NotPermittedError {
		model.addAttribute("$", permission);
		checkPermission(permission);
	}
	
	@Override
	public GenericUser getCurrentUser() {
		try
		{
			SessionStorage sessionStorage = (SessionStorage)SecurityUtils.getSubject().getPrincipal();
			if (null != sessionStorage)
			{
				return sessionStorage.getUser();
			}
		}
		catch(Exception ex)
		{
		}
		return null;
	}
	
	@Override
	public void login(RememberMeAuthenticationToken token)
	{
		SecurityUtils.getSubject().login(token);
	}

	@Override
	public void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	public Menu getSiteMenu()
	{
		SessionStorage user = (SessionStorage)SecurityUtils.getSubject().getPrincipal();
		if (null == user.getSite())
		{
			return null;
		}
		if (null == user.getSiteMenu())
		{
			synchronized(user)
			{
				Menu menu = mainMenuLoader.find("site");
				user.setSiteMenu(createUserMenu(user.getUser().getDomain(), menu));
			}
		}
		return user.getSiteMenu();
	}
	
	public Menu getUserMenu()
	{
		SessionStorage user = (SessionStorage)SecurityUtils.getSubject().getPrincipal();
		if (null == user.getUserMenu())
		{
			synchronized(user)
			{
				Menu menu = mainMenuLoader.find("main");
				user.setUserMenu(createUserMenu(user.getUser().getDomain(), menu));
				
				Map<String, Menu> accessMenu = new HashMap<String, Menu>();
				createAccessMenu(user.getUserMenu(), accessMenu);
				user.setAccessMenu(accessMenu);
			}
		}
		return user.getUserMenu();
	}
	
	public SessionStorage getSession()
	{
		SessionStorage sessionStorage = (SessionStorage)SecurityUtils.getSubject().getPrincipal();
		return sessionStorage;
	}
	
	protected void createAccessMenu(Menu menu, Map<String, Menu> accessMenu)
	{
		if (null != menu.getAccess())
		{
			accessMenu.put(menu.getAccess(), menu);
		}
		if (null != menu.getChildren())
		{
			for (Menu subMenu : menu.getChildren())
			{
				createAccessMenu(subMenu, accessMenu);
			}
		}
	}
	
	protected Menu createUserMenu(String domain, Menu menu)
	{
		Menu userMenu = menu.copy();
		if (!StringUtils.isEmpty(menu.getRole()) && !domain.equals(menu.getRole()))
		{
			return null;
		}
		
		if (null == menu.getChildren() || menu.getChildren().isEmpty())
		{
			//leaf menu
			if (StringUtils.isEmpty(menu.getAccess()) || SecurityUtils.getSubject().isPermitted(menu.getAccess()))
			{
				return userMenu;
			}
			else
			{
				return null;
			}
		}
		else
		{
			//node menu
			boolean firstSubMenu = true;
			for (Menu subMenu : menu.getChildren())
			{
				Menu userSubMenu = createUserMenu(domain, subMenu);
				if (null != userSubMenu)
				{
					if (firstSubMenu)
					{
						if (null == userMenu.getMapping() || userMenu.getMapping().length() == 0)
						{
							userMenu.setMapping(userSubMenu.getMapping());
						}
						firstSubMenu = false;
					}
					userMenu.add(userSubMenu);
				}
			}
			
			if (null == userMenu.getChildren() || userMenu.getChildren().isEmpty())
			{
				return null;
			}
			else
			{
				return userMenu;
			}
		}
	}
	
	@Override
	public boolean isAutoLogin() {
		
		return false;
	}
}
