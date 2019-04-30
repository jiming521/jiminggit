package com.casic.util;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * @author: jiming
 * @date: 2019年3月13日 下午5:12:48
 * @Description: HttpClient工具类
 */
public class HttpClientUtilForCas {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtilForCas.class);

	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CHARSET_UTF8 = "UTF-8";

	/**
	 * @params: 
	 * @author: jiming
	 * @param casParams 
	 * @param httpClient 
	 * @date: 2019年4月4日 上午9:32:19
	 * @Description: post请求json数据
	 * @return: String
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String jsonPostInvoke(String url, Map<String, Object> params) throws Exception{
		HttpClient httpClient = LoginCasUtil.getInstance().getHttpClient();
		PostMethod post = new PostMethod(url);
		JSONObject jsonParam = new JSONObject();
		if (null != params) {
			for (String key : params.keySet()) {
				jsonParam.put(key, params.get(key));
			}
		}
		StringRequestEntity entity = new StringRequestEntity (jsonParam.toString() ,CONTENT_TYPE_JSON ,CHARSET_UTF8);
		post.setRequestEntity(entity);
		httpClient.executeMethod(post);
		return post.getResponseBodyAsString();
	}
	
	/**
	 * @params: 
	 * @author: jiming
	 * @param casParams 
	 * @date: 2019年4月4日 上午10:29:50
	 * @Description: 简单post方式提交json数据
	 * @return: String
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String simpleJsonPostInvoke(String url, Map<String, Object> params) throws HttpException, IOException{
		PostMethod post = new PostMethod(url);
		String paramsForUrl = paramsForUrl(url, params);
		HttpClient httpClient = LoginCasUtil.getInstance().getHttpClient();
		httpClient.executeMethod(post);
		return post.getResponseBodyAsString();
	}
	
	/**
	 * @param url
	 * @param Map<String,
	 *            Object>
	 * @author: jiming
	 * @date: 2019年3月14日 上午9:56:22
	 * @Description: 拼接请求的参数到url上
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
	
	/**
	 * @params: 
	 * @author: jiming
	 * @date: 2019年4月8日 上午9:42:34
	 * @Description: 简单get方式提交json数据
	 * @return: String
	 * @throws IOException 
	 */
	public static String simpleJsonGetInvoke(String url, Map<String, Object> params) throws IOException {
		HttpClient httpClient = LoginCasUtil.getInstance().getHttpClient();
		String paramsForUrl = paramsForUrl(url, params);
		GetMethod method = new GetMethod(paramsForUrl);
		try {
			httpClient.executeMethod(method);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return method.getResponseBodyAsString();
	}
	
}
