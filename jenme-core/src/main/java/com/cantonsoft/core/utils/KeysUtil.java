package com.cantonsoft.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.cantonsoft.framework.util.StringTool;

public class KeysUtil {

	public static String makeOrderNo() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + String.format("%05d", new Random().nextInt(10000));
	}

	public static String makeTransactionKey(Long orderId) {
		StringBuffer str = new StringBuffer();
		str.append(orderId);
		str.append(System.currentTimeMillis());
		str.append(String.format("%05d", new Random().nextInt(10000)));
		if (str.length() > 32) {
			return str.substring(str.length() - 32, str.length());
		}
		return str.toString();
	}

	public static String getSecretKey() {
		return StringTool.getRandomString(32);
	}

	public static void main(String args[]) {
		System.out.println(KeysUtil.makeOrderNo());
		// System.out.println(KeysUtil.getApiKey());
		// System.out.println(KeysUtil.makeParkCardCode(1));
		// System.out.println(KeysUtil.makeTerminalCode(1));
		// System.out.println(CalendarUtil.getCurrentDateyyyyMMddhhmmss());
		// for(int i=0;i<100;i++){
		// System.out.println(makeCouponNo(i%5L, i));
		// }
	}
}
