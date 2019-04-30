package com.casic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TaskRequestUtil {
	
	private static Properties properties;

	static {
		properties = new Properties();
		InputStream inStream = TaskRequestUtil.class.getClassLoader().getResourceAsStream("conf/task.properties");
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
