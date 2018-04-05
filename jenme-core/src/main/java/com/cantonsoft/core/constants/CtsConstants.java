package com.cantonsoft.core.constants;

public class CtsConstants {

	public class DomainConstant {
		public static final int MAIN = 1;
		public static final int PARTNER = 2;
		public static final int CLIENT = 3;
		public static final int SITE = 4;
	}

	public class SexConstant {
		public static final int BOTH = 2;
		public static final int MALE = 1;
		public static final int FAMALE = 0;
	}

	// 验证码使用状态：0-未使用；1-可用
	public class CaptchaStatus {
		public static final int DISABLED = 0;
		public static final int ENABLED = 1;
	}

	// 短信发送状态：0-未发送；1-已发送
	public class SmsStatus {
		public static final int NOT_SENT = 0;
		public static final int HAS_SENT = 1;
	}

	public class EntityType {
		public static final String CLIENT = "client";
		public static final String MEMBER = "member";
		public static final String PRODUCT = "product";
		public static final String BRAND = "brand";
	}

}
