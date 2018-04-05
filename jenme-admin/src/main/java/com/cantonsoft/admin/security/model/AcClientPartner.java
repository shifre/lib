package com.cantonsoft.admin.security.model;

import java.io.Serializable;

import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.core.account.user.model.UserAuth;

public class AcClientPartner implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long partnerId;
	private Long clientId;
	private Long hotelId;
	public static String CONTROL_LEVEL_CLIENT = "client";
	public static String CONTROL_LEVEL_HOTEL = "hotel";

	public boolean isCanOper(GenericUser user, Long partnerId, Long clientId,
			Long hotelId, String controlLevel) {
		String domin = user.getDomain();
		System.out.print(domin + "---domin");
		if (user == null) {
			return false;
		}
		if (UserAuth.AUTH_TYPE_MAIN.equals(user.getDomain()))// 平台用户
		{
			return true;
		}
		if (UserAuth.AUTH_TYPE_PARTNER.equals(user.getDomain()))// 渠道用户
		{
			return (partnerId != null && partnerId.equals(this.partnerId));
		}
		if (UserAuth.AUTH_TYPE_CLIENT.equals(user.getDomain()))// client用户
		{
			if (clientId != null && clientId.equals(this.clientId)) {
				if (controlLevel.equals(AcClientPartner.CONTROL_LEVEL_CLIENT)) {// 控制到client
					return true;
				}
				if (controlLevel.equals(AcClientPartner.CONTROL_LEVEL_HOTEL)) {// 控制到hotel
					if (hotelId != null && hotelId.equals(this.hotelId)) {
						return true;
					}
				}
			}
			return false;
		}

		return false;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	@Override
	public String toString() {
		return "AcHotel [partnerId=" + partnerId + ", clientId=" + clientId
				+ "]";
	}

}
