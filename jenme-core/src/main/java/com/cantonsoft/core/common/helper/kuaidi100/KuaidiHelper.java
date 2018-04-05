package com.cantonsoft.core.common.helper.kuaidi100;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cantonsoft.core.common.helper.kuaidi100.Out4Kuaidi.Out4Response;
import com.cantonsoft.framework.http.SyncHttpClient;
import com.cantonsoft.framework.util.Config;

@Component
public class KuaidiHelper {
	@Autowired
	private SyncHttpClient httpclient;
	@Autowired
	private Config config;

	public Out4Response queryKuaidi(String mobile, String content) {
		In4Kuaidi input = new In4Kuaidi();
		input.setId("stonecai");// partnerId
		input.setCom("tiantian");// company
		input.setNu("11111");// express Number
		input.setShow("2");
		input.setMuti("1");
		input.setOrder("desc");

		Out4Kuaidi output = new Out4Kuaidi();
		httpclient.post("http://api.kuaidi100.com", "api", false, input.toParameters(), output);

		return output.getData();
	}

	public static void main(String[] agrs) {

		try {
			String PartnerId = "";
			URL url = new URL("http://api.kuaidi100.com/api?id=XXXX&com=tiantian&nu=11111&show=2&muti=1&order=desc");
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null)
				type = con.getContentType();

			if (type == null || type.trim().length() == 0 || type.trim().indexOf("text/html") < 0)
				return;

			if (type.indexOf("charset=") > 0)
				charSet = type.substring(type.indexOf("charset=") + 8);

			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			String content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					// String newContent = new String(b, 0, numRead);
					String newContent = new String(b, 0, numRead, charSet);
					content += newContent;
				}
			}
			// System.out.println("content:" + content);
			urlStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
