package com.casic.def;

import java.util.HashMap;
import java.util.Map;

import com.casic.util.DefRequestUtil;
import com.casic.util.HttpClientUtilForCas;
import com.casic.util.LoginCasUtil;

/**
 * @author: jiming
 * @date: 2019年3月11日 下午3:27:46
 * @Description: 流程定义sdk客户端
 */
public class DefClientForCas {
	private static DefClientForCas defClientForCas;
	
	private DefClientForCas(){
		
	}
	
	public static DefClientForCas getDefClientForCas(){
		if(defClientForCas==null){
			defClientForCas = new DefClientForCas();
		}
		return defClientForCas;
	}
	
	
	public void login(String username, String password){
		LoginCasUtil.getInstance().login(username, password);
	}

	/**
	 * @param Map<String, Object>
	 * @author: jiming
	 * @date: 2019年3月11日 下午3:35:53
	 * @Description: 通过用户名获取流程定义列表
	 * @return: String
	 */
	public String getDefinitionList(Map<String, Object> params) {
		// 获取接口请求地址
		String url = null;
		String result = null;
		try {
			url = DefRequestUtil.getRequestUrl("definition_getBpmDefList");
			result = HttpClientUtilForCas.jsonPostInvoke(url,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @param Map<String, Object>
	 * @author: jiming
	 * @date: 2019年3月11日 下午3:35:53
	 * @Description: 根据流程定义id获取流程定义信息--x5平台暂时未放开此接口
	 * @return: String
	 */
/*	public String getBpmDefinitionByDefId(Map<String, Object> params) {
		// 获取接口请求地址
		String url = null;
		String result = null;
		Map<String, Object> casParams = new HashMap<>();
		try {
			url = DefRequestUtil.getRequestUrl("definition_getBpmDefinitionByDefId");
			result = HttpClientUtilForCas.simpleJsonPostInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

}
