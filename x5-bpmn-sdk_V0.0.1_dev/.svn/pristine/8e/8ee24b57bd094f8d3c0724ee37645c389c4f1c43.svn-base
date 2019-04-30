package com.casic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LoginCasUtil {
	private static LoginCasUtil loginCasUtil=null;
	public static LoginCasUtil getInstance(){
		if (loginCasUtil==null){
			loginCasUtil=new LoginCasUtil();
		}
		
		return loginCasUtil;
	}
	
	private  Properties properties;
	private  HttpClient httpclient = new HttpClient();
	private  String castgc="";
	
	public LoginCasUtil(){
		properties = new Properties();
		InputStream inStream = DefRequestUtil.class.getClassLoader().getResourceAsStream("conf/def.properties");
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getcastgc(){
		return castgc;
	}
	
	
	public HttpClient getHttpClient(){
		return httpclient;
	}
	

	/**
	 * @params:
	 * @author: jiming
	 * @date: 2019年4月3日 下午2:59:27
	 * @Description: 获取参数
	 * @return: Map<String,String>
	 */
	public Map<String, Object> getParams(String loginUrl) throws Exception {
		Map<String, Object> params = new HashMap<>();
		HttpMethod method = new GetMethod();
		method.setPath(loginUrl);
		//method.setFollowRedirects(false);
		httpclient.executeMethod(method);
		String responseBodyAsString = method.getResponseBodyAsString();
		Document doc = Jsoup.parse(responseBodyAsString);
		params.put("Set-Cookie", method.getResponseHeader("Set-Cookie").getValue());
		params.put("lt", doc.getElementsByAttributeValue("name", "lt").attr("value"));
		params.put("execution", doc.getElementsByAttributeValue("name", "execution").attr("value"));
		return params;
	}
	
	/**
	 * @params: 
	 * @author: jiming
	 * @date: 2019年4月3日 下午3:16:15
	 * @Description: 设置登录参数
	 * @return: Map<String,String>
	 */
	public Map<String, Object> setParams(Map<String, Object> params){
		params.put("_eventId", "submit");
		params.put("loginType", "2");
		params.put("shortAccount_ind", "");
		params.put("password_ind", "");
		params.put("orgSn", "");
		return params;
	}
	
	/**
	 * @params: 
	 * @author: jiming
	 * @date: 2019年4月3日 下午3:18:28
	 * @Description: 拼接参数
	 * @return: String
	 */
	public String paramsForUrl(Map<String, Object> params) {
		String result = "";
		for (Entry<String, Object> entry : params.entrySet()) {
			result += entry.getKey() + "=" + entry.getValue() + "&";
		}
		if (params.size() > 0) {
			result = result.substring(0, result.lastIndexOf("&"));
		}
		return result;
	}
	
	/**
	 * @params: 
	 * @author: jiming
	 * @param cookie 
	 * @date: 2019年4月3日 下午3:35:28
	 * @Description: 获取Castgc
	 * @return: String
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public String getCastgc(HttpClient httpclient,String location,String paramsForUrl,String cookie) throws HttpException, IOException{
		PostMethod method = new PostMethod(location);
		method.setRequestBody(paramsForUrl);
        //method.setFollowRedirects(false);
        method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        method.addRequestHeader(new Header("Cookie", cookie));
        httpclient.executeMethod(method);
        String casTgtCookie = method.getResponseHeader("Set-Cookie").getValue();
        int indexOf = casTgtCookie.indexOf("CASTGC") + 7;
        int indexOf2 = casTgtCookie.indexOf("example.org") +11;
        String getCastgc = casTgtCookie.substring(indexOf, indexOf2);
        //String responseBodyAsString = method.getResponseBodyAsString();
		return getCastgc;
	}
	
	
	public void login(String account, String password){
		String plantform_page = properties.getProperty("plantform_page");
		Map<String, Object> params=null;
		PostMethod httpPost=null;
		String location = null;
		Map<String, Object> retparams=null;
		GetMethod httpGet = null ;
		try {
			//第一次请求到http://bpms.casicloud.com/main/home
			httpPost=new PostMethod(plantform_page);
			httpclient.executeMethod(httpPost);
			//返回重定向地址
			location = httpPost.getResponseHeader("location").getValue();
			//登录cas
			casLogin(account,password,location);
			//登录bpms流程建模
			httpGet=new GetMethod(plantform_page);
			httpclient.executeMethod(httpGet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void casLogin(String account, String password,String url){
		//Map<String, Object> map = new HashMap<>();
		Map<String, Object> params = null;
		try {
			//获取表单参数
			params = getParams(url);
			String cookie = (String) params.get("Set-Cookie");
			Map<String, Object> setParams = new HashMap<>();
			setParams.put("lt", (String) params.get("lt"));
			setParams.put("execution", (String) params.get("execution"));
			setParams.put("shortAccount_org", account);
			setParams.put("password_org", password);
			setParams = setParams(setParams);
			//请求参数拼接
			String paramsForUrl = paramsForUrl(setParams);
			//获取castgc
			castgc= getCastgc(httpclient,url, paramsForUrl,cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//map.put("castgc", castgc);
		//map.put("httpclient", httpclient);
		//return map;
	}
	
}
