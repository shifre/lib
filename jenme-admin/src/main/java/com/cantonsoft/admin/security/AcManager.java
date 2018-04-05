package com.cantonsoft.admin.security;

import org.slf4j.LogFactory;
import org.slf4j.Logger;

import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.core.cloud.cache.CacheRegister;
import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.core.cloud.partner.model.Partner;
import com.cantonsoft.core.cloud.site.SiteService;
import com.cantonsoft.core.cloud.site.model.Site;
import com.cantonsoft.core.common.asset.AssetService;
import com.cantonsoft.core.common.asset.model.Asset;
import com.cantonsoft.admin.security.model.AcClientPartner;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.cache.TwoLevelCacheManager;
import com.cantonsoft.framework.mvc.util.ApplicationUtil;

public class AcManager {
	private static final Logger LOG = LogFactory.getLogger(AcManager.class);

	// 会员
	public static Object getMember(Long id) {
/*		MemberService service = getBean(MemberService.class);
		Member member = service.find(id);
		return getReturnObject(CacheRegister.AC_HOTEL, member,
				member.getId(),AcClientPartner.CONTROL_LEVEL_CLIENT);*/
		return null;

	}

	// 会员级别
	public static Object getMemberLevel(Long id) {
/*		MemberLevelService service = getBean(MemberLevelService.class);
		MemberLevel level = service.find(id);
		return getReturnObject(CacheRegister.AC_CLIENT, level,
				level.getClientId(),AcClientPartner.CONTROL_LEVEL_CLIENT);*/
		return null;
	}

	

	// site 根据什么去控制权限？ 站点设置由渠道商操作？
	public static Object getAsset(Long id) {
		AssetService service = getBean(AssetService.class);
		Asset vo = service.find(id);
		SiteService siteService = getBean(SiteService.class);
		Site site = siteService.find(vo.getSiteId());
		return getReturnObject(CacheRegister.AC_HOTEL, vo, site.getId());
	}

	// 活动
	public static Object getActivityInfo(Long id) {
//		ActivityService service = getBean(ActivityService.class);
//		Activity vo = service.find(id);
		
//		return getReturnObject(CacheRegister.AC_HOTEL, vo, vo.getId());
		return null;
	}

	// 活动会员
	public static Object getActivityMemberInfo(Long id) {
/*		ActivityMemberViewService service = getBean(ActivityMemberViewService.class);
		ActivityMemberView vo = service.find(id);
		MemberService mservice = getBean(MemberService.class);
		Member member = mservice.find(vo.getMemberId());
		return getReturnObject(CacheRegister.AC_HOTEL, vo, member.getId());*/
		return null;
	}

	public static Object getAssetString(Long id) {
/*		AssetStringService service = getBean(AssetStringService.class);
		AssetString vo = service.find(id);
		AssetSiteViewService viewService = getBean(AssetSiteViewService.class);
		AssetSiteView view = viewService.find(vo.getAssetId());
		return getReturnObject(CacheRegister.AC_HOTEL, vo, view.getId());*/
		return null;
	}




	private static AcClientPartner getAcClientPartner(String key, Long id) {
		TwoLevelCacheManager cacheManager = getBean(TwoLevelCacheManager.class);
		System.out.print("getAcHotel:" + "key:" + key + "id:" + id);
		return (AcClientPartner) cacheManager.getData(key, id);
	}

	//默认权限控制到酒店
	private static Object getReturnObject(String cacheKey, Object origin,Long id) {
		return getReturnObject(cacheKey, origin, id,AcClientPartner.CONTROL_LEVEL_HOTEL);
	}

	private static Object getReturnObject(String cacheKey, Object origin,Long id, String controlLevel) {
		if (origin != null) {
			LOG.info("cacheKey:"+cacheKey+"id:"+id);
			// 获得权限控制对象
			AcClientPartner acClientPartner = getAcClientPartner(cacheKey, id);
			LOG.info("clientId:"+acClientPartner.getClientId()+"hotelid:"+acClientPartner.getId());

			// 比较session中的值是否与权限控制对象中的值匹配
			if (acClientPartner != null
					&& acClientPartner.isCanOper(getUser(), getPartnerId(),
							getClientId(), getId(), controlLevel)) {
				return origin;
			}
		}

		LOG.info("getReturnObject failed");
		return null;
	}

	public static GenericUser getUser() {
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSession().getUser();
	}

	public static Long getPartnerId() {
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Partner partner = sessionHelper.getSession().getPartner();
		return partner == null ? null : partner.getId();
	}

	public static Long getClientId() {
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		Client client = sessionHelper.getSession().getClient();
		return client == null ? null : client.getId();
	}

	public static Long getId() {
		ShiroSessionHelper sessionHelper = getBean(ShiroSessionHelper.class);
		return sessionHelper.getSession().getSite().getId();

	}

	public static <T> T getBean(Class<T> clazz) {
		return ApplicationUtil.WEBAPP_CONTEXT.getBean(clazz);
	}
	
	
	public static boolean isCanOper(Long hotelId) throws Exception
	{
		AcClientPartner acHotel = getAcClientPartner(CacheRegister.AC_HOTEL, hotelId);

		if (acHotel != null && acHotel.isCanOper(getUser(), getPartnerId(), getClientId(),getId(),AcClientPartner.CONTROL_LEVEL_HOTEL))
		{
			return true;
		}

		return false;
	}
	
	public static boolean isCanOperClient(Long clientId) throws Exception
	{
		AcClientPartner acHotel = getAcClientPartner(CacheRegister.AC_CLIENT, clientId);

		if (acHotel != null && acHotel.isCanOper(getUser(), getPartnerId(), getClientId(),getId(),AcClientPartner.CONTROL_LEVEL_CLIENT))
		{
			return true;
		}

		return false;
	}
	
	
	
}
