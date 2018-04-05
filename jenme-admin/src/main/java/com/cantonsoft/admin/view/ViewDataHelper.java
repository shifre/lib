package com.cantonsoft.admin.view;

import java.lang.reflect.Method;

import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.core.cloud.client.ClientService;
import com.cantonsoft.core.cloud.client.service.usage.ServiceUsageService;
import com.cantonsoft.core.cloud.partner.PartnerService;
import com.cantonsoft.core.cloud.site.SiteService;
import com.cantonsoft.core.common.asset.AssetService;
import com.cantonsoft.core.common.asset.text.AssetTextService;
import com.cantonsoft.core.common.member.level.MemberLevelService;
import com.cantonsoft.core.common.member.level.model.MemberLevel;
import com.cantonsoft.core.common.notice.NoticeService;
import com.cantonsoft.framework.mvc.util.ApplicationUtil;

public class ViewDataHelper {

	public static <T> T getBean(Class<T> clazz) {
		return ApplicationUtil.WEBAPP_CONTEXT.getBean(clazz);
	}

	public static Object getNotice(Long id) {
		NoticeService service = getBean(NoticeService.class);
		return service.find(id);
	}

	public static Object getNotices(Integer type) {
		NoticeService service = getBean(NoticeService.class);
		return service.getNoticesByType(type);
	}

	public static Object getPartner(Long id) {
		PartnerService service = getBean(PartnerService.class);
		return service.find(id);
	}

	public static Object getClient(Long id) {
		ClientService service = getBean(ClientService.class);
		return service.find(id);
	}

	public static Long siteId() {
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSession().getSite().getId();
	}

	public static Long getId() {
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSession().getSite().getId();
	}

	public static Long getClientId() {
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSession().getClient().getId();
	}

	public static Long getPartnerId() {
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSession().getPartner().getId();
	}

	public static Object getSiteByClientId(Long clientId) {
		SiteService service = getBean(SiteService.class);
		return service.findByClientId(clientId);
	}

	public static Object getServiceUsage(Long id) {
		ServiceUsageService service = getBean(ServiceUsageService.class);
		return service.find(id);
	}

	public static Long getSiteId() {
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Long siteId = sessionHelper.getSession().getSite().getId();
		return siteId;
	}

	public static Object getCurrentSite() {
		SiteService service = getBean(SiteService.class);
		return service.find(getSiteId());
	}

	public static Object getMemberLevel(Long id) {
		if (id == 0) {
			return new MemberLevel();
		}
		MemberLevelService service = getBean(MemberLevelService.class);
		return service.find(id);
	}

	public static Object getSelectMap(String className, String fieldName) {
		try {
			Class clazz = Class.forName(className);
			Object o = clazz.newInstance();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object map = method.invoke(o, new Object[] {});
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Object getMemberLevelList(Long clientId) {
		/*
		 * if(clientId==0){ ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class); clientId=sessionHelper.getSession().getSite().getClientId(); } MemberLevelService service =
		 * getBean(MemberLevelService.class); return service.findMemberLevelByClient(clientId);
		 */
		return null;
	}

	public static Object getAsset(Long id) {
		AssetService service = getBean(AssetService.class);
		return service.find(id);
	}

	public static Object getClients() {
		ClientService service = getBean(ClientService.class);
		return service.findAll();
	}

	public static Object getActivityInfo(Long id) {
		// ActivityService service = getBean(ActivityService.class);
		// return service.find(id);
		return null;
	}

	public static Object getActivityMemberInfo(Long id) {
		// ActivityMemberViewService service = getBean(ActivityMemberViewService.class);
		// return service.find(id);
		return null;
	}

	public static Object getActivityPartakeListInfo(Long id) {
		// ActivityPartakeListViewService service = getBean(ActivityPartakeListViewService.class);
		// return service.find(id);
		return null;
	}

	public static Object getAssetText(Long id) {
		AssetTextService service = getBean(AssetTextService.class);
		return service.find(id);
	}

	public static Long clientBySession() {
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSession().getSite().getClientId();
	}



	public static Object getWxMenu() throws Exception {
		/*
		 * CustomeMenuService service = getBean(CustomeMenuService.class); WxSetting wxSetting = (WxSetting) getWxSettingInfo(); WxAccessService wxAccessService = getBean(WxAccessService.class);
		 * 
		 * WxAccess wxAccess = wxAccessService.getActivityWxAccess(wxSetting, getClientId()); List<Button> buttons = service.getMenu(wxAccess.getToken()); Menu menu = new Menu();
		 * menu.setButton(buttons); String menuStr = "'" + JsonUtil.writeValueAsString(menu) + "'"; return menuStr;
		 */
		return null;
	}
}