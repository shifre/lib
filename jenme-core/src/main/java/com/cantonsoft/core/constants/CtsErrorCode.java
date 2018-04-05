package com.cantonsoft.core.constants;

public class CtsErrorCode {
	public static final int NO_ERROR = 0;

	public class COMM {
		private static final int ERROR_CODE_PREFIX = 1000;

		public static final int ERROR_API_ERROR = ERROR_CODE_PREFIX + 1;
		public static final int ERROR_NOT_LOGIN = ERROR_CODE_PREFIX + 1;
		public static final int ERROR_UNKNOWN = ERROR_CODE_PREFIX + 1;
		public static final int ERROR_NOT_AVAILABLE = ERROR_CODE_PREFIX + 1;
	}

	public class MVC {
		private static final int ERROR_CODE_PREFIX = 2000;
	}

	public class BIZ {
		private static final int ERROR_CODE_PREFIX = 3000;
	}

}
