package com.cantonsoft.core.common.member.auth;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.member.auth.model.MemberAuth;
import com.cantonsoft.core.common.member.auth.model.MemberAuthDao;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.util.StringTool;

@Service
@Lazy
public class MemberAuthService extends BaseEntityService<MemberAuth, Long> {
	@Autowired
	private MemberAuthDao dao;

	@Override
	protected BaseEntityDao<MemberAuth, Long> getDao() {
		return dao;
	}

	public MemberAuth findByMemberId(Long memberId) {
		return dao.findByMemberId(memberId);
	}

	public MemberAuth findByAccount(String account) {
		return dao.findByAccount(account);
	}

	public MemberAuth findByToken(String token) {
		if (null != token) {
			return dao.findByToken(token);
		}
		return null;
	}

	public MemberAuth findByAccountAndPassword(String account, String password) {
		String encriptedPwd = StringTool.getMd5AndBase64EncodeString(password);
		return dao.findByAccountAndPassword(account, encriptedPwd);
	}

	public void updatePassword(Long memberId, String oldPassword, String newPassword) {

		MemberAuth auth = dao.findByMemberId(memberId);
		if (null != auth) {
			String encriptedOld = StringTool.getMd5AndBase64EncodeString(oldPassword);
			String encriptedNew = StringTool.getMd5AndBase64EncodeString(newPassword);
			if (auth.getPassword().equals(StringUtils.defaultIfBlank(encriptedOld, ""))) {
				dao.updatePassword(StringUtils.defaultIfBlank(encriptedNew, ""), auth.getId());
				return;
			}
		}
		throw new ServiceException("error.update.password");
	}

	public void updatePassword(Long memberId, String newPassword) {

		String encriptedNew = StringTool.getMd5AndBase64EncodeString(newPassword);
		dao.updatePassword(StringUtils.defaultIfBlank(encriptedNew, ""), memberId);
	}

	public void updateLoginStatus(String account, boolean success, String token) {
		MemberAuth auth = dao.findByAccount(account);
		if (success) {
			auth.setErrorCount(0);
			auth.setToken(token);
		} else {
			auth.setErrorCount(auth.getErrorCount() + 1);
		}
		auth.setLastLoginTime(new Date());
		dao.save(auth);
	}

	public boolean updateLogoutStatus(String account) {
		MemberAuth auth = dao.findByAccount(account);
		if (null != auth) {
			auth.setToken(null);
			dao.save(auth);
			return true;
		}
		return false;
	}

	public String findAccount(Long id) {
		MemberAuth auth = dao.findByMemberId(id);
		return auth.getAccount();
	}

	public boolean checkUniqueAccount(String account) throws ServiceException {
		MemberAuth auth = dao.findByAccount(account);
		if (auth != null) {
			return false;
		}
		return true;
	}

	public void checkUpdateAccount(Long id, String account) throws ServiceException {
		MemberAuth auth = dao.findByAccount(account);
		if (auth != null && !auth.getMemberId().equals(id)) {
			throw new ServiceException("error.user.name.employ");
		}
	}

	public void createAuthInfo(Long id, String account, String password, String token, String imToken) {
		checkUniqueAccount(account);
		MemberAuth auth = new MemberAuth();
		auth.setMemberId(id);
		auth.setAccount(account);
		auth.setPassword(StringTool.getMd5AndBase64EncodeString(password));
		auth.setUpdatedPasswordTime(new Date());
		auth.setToken(token);
		auth.setImToken(imToken);
		auth.setErrorCount(0);

		dao.save(auth);
	}
}
