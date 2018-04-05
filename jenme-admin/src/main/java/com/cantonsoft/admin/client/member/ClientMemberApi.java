package com.cantonsoft.admin.client.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.core.common.member.MemberService;
import com.cantonsoft.core.common.member.address.MemberAddressService;
import com.cantonsoft.core.common.member.address.model.MemberAddress;
import com.cantonsoft.core.common.member.model.Member;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.Cond;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;

@JsonApi
@Lazy
@RequestMapping(value = "client/member")
@Access(value = "fn.client.member")
public class ClientMemberApi extends BaseEntityApi<Member, Long, Member, Member>{
	@Autowired
	private MemberService service;
	@Autowired
	private ShiroSessionHelper shiro;
	@Autowired
	private MemberAddressService memberAddressService;

	@Override
	protected BaseEntityService<Member, Long> getEntityService() {
		return service;
	}

	@Override
	protected Member doAdd(Map<String, Object> respMap, Member data,
			HttpServletRequest request, HttpServletResponse response) {
		data.setClientId(shiro.getSession().getSite().getClientId());
		return super.doAdd(respMap, data, request, response);
	}
	
	@Access(value = "read")
	@RequestMapping(value = "address/search.json", method = RequestMethod.POST)
	public @ResponseBody Object addressSearch(@RequestBody PageFilter filter, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PageData<MemberAddress> page = memberAddressService.find(filter);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), page);
	}
}
