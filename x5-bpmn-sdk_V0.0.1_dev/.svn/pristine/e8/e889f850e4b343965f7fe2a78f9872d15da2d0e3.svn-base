package com.casic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DefRequestUtil {
	
	private static Properties properties;

	static {
		properties = new Properties();
		InputStream inStream = DefRequestUtil.class.getClassLoader().getResourceAsStream("conf/def.properties");
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getRequestUrl(String requestUrl) throws IOException {
		requestUrl = properties.getProperty(requestUrl);
		String plantformUrl = properties.getProperty("plantform_url");
		return plantformUrl + requestUrl;
	}
}
