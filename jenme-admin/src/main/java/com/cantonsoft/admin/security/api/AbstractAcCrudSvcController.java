package com.cantonsoft.admin.security.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cantonsoft.admin.security.AcManager;
import com.cantonsoft.core.common.member.level.model.MemberLevel;
import com.cantonsoft.core.common.member.model.Member;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.service.CrudSvcController;

public abstract class AbstractAcCrudSvcController<ENTITY, ADD, UPDATE> extends CrudSvcController<ENTITY, ADD, UPDATE> {

	@Override
	protected ENTITY doAdd(Map<String, Object> respMap, ADD data, HttpServletRequest request, HttpServletResponse response) {
		// if (check(data))
		// {
		return super.doAdd(respMap, data, request, response);
		// } else
		// {
		// throw new ServiceException("Check failed.");
		// }
	}

	@Override
	protected void doDelete(Map<String, Object> respMap, Long id, HttpServletRequest request, HttpServletResponse response) {
		Object dbData = super.find(id, request, response);
		ApiResponse rep = (ApiResponse) dbData;
		if (check(rep.getData())) {
			super.doDelete(respMap, id, request, response);
		} else {
			throw new ServiceException("Check failed.");
		}
	}

	@Override
	protected ENTITY doUpdate(Map<String, Object> respMap, Long id, UPDATE data, HttpServletRequest request, HttpServletResponse response) {
		Object dbData = super.find(id, request, response);
		ApiResponse rep = (ApiResponse) dbData;
		if (check(rep.getData())) {
			return super.doUpdate(respMap, id, data, request, response);
		} else {
			throw new ServiceException("Check auth failed.");
		}
	}

	protected boolean check(Object data) {
		if (data != null) {
			try {
				
				if (data instanceof MemberLevel) {// 会员等级
					return AcManager.getMemberLevel(((MemberLevel) data).getId()) != null;
				}
				if (data instanceof Member) {// 会员
					return AcManager.getMember(((Member) data).getId()) != null;
				}

				

				return false;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
