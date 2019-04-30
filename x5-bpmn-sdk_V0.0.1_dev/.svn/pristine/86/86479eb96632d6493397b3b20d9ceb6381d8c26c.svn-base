package com.casic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NodeRequestUtil {
	
	private static Properties properties;

	static {
		properties = new Properties();
		InputStream inStream = NodeRequestUtil.class.getClassLoader().getResourceAsStream("conf/node.properties");
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
