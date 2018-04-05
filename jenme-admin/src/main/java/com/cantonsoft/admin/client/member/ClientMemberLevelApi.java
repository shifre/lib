package com.cantonsoft.admin.client.member;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.core.common.member.level.MemberLevelService;
import com.cantonsoft.core.common.member.level.model.MemberLevel;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@JsonApi
@Lazy
@RequestMapping(value = "/client/member/memberlevel")
@Access(value = "fn.client.member.memberlevel.search")
public class ClientMemberLevelApi extends BaseEntityApi<MemberLevel,Long, MemberLevel, MemberLevel>{
	
	@Autowired
	private MemberLevelService service;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	

	@PostConstruct
	private void init()
	{
		super.searchDef = new SearchDef().addField("title").addField("level");
	}

	@Override
	protected BaseEntityService<MemberLevel, Long> getEntityService() {
		return service;
	}
	
	@Override
	protected MemberLevel doAdd(Map<String, Object> respMap, MemberLevel data,
			HttpServletRequest request, HttpServletResponse response) {
		data.setClientId(sessionHelper.getSession().getSite().getClientId());
		data.setPosition(service.getMaxPosition() + 1);
		return super.doAdd(respMap, data, request, response);
	}

/*	@Override
	protected Map<String, Object> doSearch(DataPageRequest dataPageReq,
			HttpServletRequest request, HttpServletResponse response) {
		Site site = sessionHelper.getSession().getSite();
		Long clientId=site.getClientId();
		
		dataPageReq.getWheres().add(new WhereCond("clientId", ICond.OPERATOR_EQ, clientId));
		
		dataPageReq.getWheres().add(new WhereCond("status", ICond.OPERATOR_EQ, MemberLevel.MEMBER_LEVEL_STATUS_ACTIVE));//查状态为有效的记录
		
		return super.doSearch(dataPageReq, request, response);
	}*/
	
/*	@Override
	protected void doDelete(Map<String, Object> respMap, Long id, HttpServletRequest request, HttpServletResponse response){
		MemberLevel vo=service.find(id);
		//检查是否已有会员设置为此级别
		Long memberCount=service.getMemberCountByLevel(id);
		if(memberCount>0){
			throw new ServiceException("MemberLever in use,delete failed.");
		}else{
			if(check(vo)){
				vo.setStatus(MemberLevel.MEMBER_LEVEL_STATUS_INACTIVE);//删除时将状态置为无效
				service.update(vo);
			} else
			{
				throw new ServiceException("Check failed.");
			}
		}
	}*/
	
	@Access(value = "update")
	@RequestMapping(value = "move.json", method = RequestMethod.POST)
	public @ResponseBody Object add(@RequestParam Long id1, @RequestParam Long id2, HttpServletRequest request, HttpServletResponse response)
	{
		MemberLevel level = service.find(id1);
		MemberLevel level2 = service.find(id2);
		
		Long position = level.getPosition();
		level.setPosition(level2.getPosition());
		level2.setPosition(position);
		
		service.update(level);
		service.update(level2);
		
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
	}
}
