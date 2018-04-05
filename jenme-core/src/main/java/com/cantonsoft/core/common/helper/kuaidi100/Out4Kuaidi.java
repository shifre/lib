package com.cantonsoft.core.common.helper.kuaidi100;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.cantonsoft.core.common.helper.kuaidi100.Out4Kuaidi.Out4Response;
import com.cantonsoft.framework.http.SyncResponseHandler;

public class Out4Kuaidi extends SyncResponseHandler<Out4Response> {

	@Override
	public Out4Response parse(String content) throws Exception {
		Out4Response result = new Out4Response();
		if (StringUtils.isBlank(content)) {
			return null;
		} else {
			String[] arrayStr = content.split("&");
			if (StringUtils.isBlank(arrayStr[1]))
				result.setState(Integer.parseInt(arrayStr[1]));
		}
		return result;
	}

	@Override
	public String toString() {
		return "Out4SendSms ";
	}
	
	@SuppressWarnings("serial")
	public class Out4Response implements Serializable {
		private String success = "";
		private int state;
		private String message;

		public String getSuccess() {
			return success;
		}

		public void setSuccess(String success) {
			this.success = success;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

}
