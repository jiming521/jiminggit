package com.casic.util;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * @author: jiming
 * @date: 2019年3月13日 下午5:12:48
 * @Description: HttpClient工具类
 */
public class HttpClientUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	public static final String CONTENT_TYPE_JSON_CHARSET_UTF8 = "application/json;charset=utf-8";
	public static final String CHARSET_UTF8 = "UTF-8";

	/**
	 * @param url
	 * @param params
	 * @throws ClientProtocolException
	 * @throws IOException:
	 * @author: jiming
	 * @date: 2019年3月13日 下午5:09:04
	 * @Description: post方式提交json数据
	 * @return: String
	 */
	public static String JsonPostInvoke(String url, Map<String, Object> params)
			throws ClientProtocolException, IOException {

		System.out.println("post方式提交json数据开始......");
		// 接收参数json列表
		JSONObject jsonParam = new JSONObject();
		// http客户端
		HttpClient httpClient = buildHttpClient(false);
		// post请求
		HttpPost method = new HttpPost(url);
		if (null != params) {
			for (String key : params.keySet()) {
				jsonParam.put(key, params.get(key));
			}
			// 参数实体
			StringEntity entity = new StringEntity(jsonParam.toString(), CHARSET_UTF8);// 解决中文乱码问题
			entity.setContentEncoding(CHARSET_UTF8);
			entity.setContentType(CONTENT_TYPE_JSON_CHARSET_UTF8);
			method.setEntity(entity);
		}
		// 执行响应操作
		HttpResponse result = null;
		String data = "";
		try {
			result = httpClient.execute(method);
			data = EntityUtils.toString(result.getEntity());
		} catch (Exception e) {
			data = "请求错误";
			logger.error(e.getMessage());
		}
		return data;
	}

	/**
	 * @param url
	 * @param params
	 * @throws ClientProtocolException
	 * @throws IOException:
	 * @author: jiming
	 * @date: 2019年3月14日 上午9:53:42
	 * @Description: get方式提交json数据
	 * @return: String
	 */
	public static String JsonGetInvoke(String url, Map<String, Object> params)
			throws ClientProtocolException, IOException {
		System.out.println("get方式提交json数据开始......");
		// http客户端
		HttpClient httpClient = buildHttpClient(false);
		// get请求
		String paramsForUrl = paramsForUrl(url, params);
		HttpGet method = new HttpGet(paramsForUrl);
		// 执行响应操作
		HttpResponse result = null;
		String data = "";
		try {
			result = httpClient.execute(method);
			data = EntityUtils.toString(result.getEntity());
		} catch (Exception e) {
			data = "请求错误";
			logger.error(e.getMessage());
		}
		return data;
	}

	/**
	 * @param url
	 * @param params
	 * @throws ClientProtocolException
	 * @throws IOException: 
	 * @author: jiming
	 * @date: 2019年3月14日 上午11:00:44
	 * @Description: 简单post方式提交json数据
	 * @return: String
	 */
	public static String SimpleJsonPostInvoke(String url, Map<String, Object> params)
			throws ClientProtocolException, IOException {

		System.out.println("post方式提交json数据开始......");
		// 接收参数json列表
		JSONObject jsonParam = new JSONObject();
		// http客户端
		HttpClient httpClient = buildHttpClient(false);
		String paramsForUrl = paramsForUrl(url, params);
		// post请求
		HttpPost method = new HttpPost(paramsForUrl);
		// 执行响应操作
		HttpResponse result = null;
		String data = "";
		try {
			result = httpClient.execute(method);
			data = EntityUtils.toString(result.getEntity());
		} catch (Exception e) {
			data = "请求错误";
			logger.error(e.getMessage());
		}
		return data;
	}

	/**
	 * @param isMultiThread
	 * @author: jiming
	 * @date: 2019年3月13日 下午5:06:08
	 * @Description: 创建HttpClient
	 * @return: HttpClient
	 */
	public static HttpClient buildHttpClient(boolean isMultiThread) {
		CloseableHttpClient client;
		if (isMultiThread) {
			client = HttpClientBuilder.create().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
		} else {
			client = HttpClientBuilder.create().build();
		}
		return client;
	}

	/**
	 * @param url
	 * @param Map<String,
	 *            Object>
	 * @author: jiming
	 * @date: 2019年3月14日 上午9:56:22
	 * @Description: 拼接get请求的参数到url上
	 * @return: String
	 */
	public static String paramsForUrl(String url, Map<String, Object> params) {
		String parameter = "?";
		for (Entry<String, Object> entry : params.entrySet()) {
			parameter += entry.getKey() + "=" + entry.getValue() + "&";
		}
		if (params.size() > 0) {
			url = url + parameter.substring(0, parameter.lastIndexOf("&"));
		}
		return url;
	}
}
