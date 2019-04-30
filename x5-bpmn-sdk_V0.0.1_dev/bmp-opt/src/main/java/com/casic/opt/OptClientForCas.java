package com.casic.opt;

import java.util.Map;

import com.casic.util.HttpClientUtil;
import com.casic.util.HttpClientUtilForCas;
import com.casic.util.LoginCasUtil;
import com.casic.util.OptRequestUtil;

/**
 * @author: jiming
 * @date: 2019年3月12日 上午9:08:12
 * @Description: 流程操作客户端
 */
public class OptClientForCas {

	private static OptClientForCas optClientForCas;

	private OptClientForCas(){
		
	}

	public static OptClientForCas getOptClientForCas() {
		if (optClientForCas == null) {
			optClientForCas = new OptClientForCas();
		}
		return optClientForCas;
	}

	public void login(String username, String password) {
		LoginCasUtil.getInstance().login(username, password);
	}

	/**
	 * @param Map<String,Object>
	 * @author: jiming
	 * @date: 2019年3月12日 上午9:02:43
	 * @Description: 启动流程
	 * @return: String
	 */
	public String startProcess(Map<String, Object> params) {
		// 获取接口请求地址
		String url = null;
		String result = null;
		try {
			url = OptRequestUtil.getRequestUrl("operate_startProcess");
			result = HttpClientUtilForCas.jsonPostInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param Map<String,Object>
	 * @author: jiming
	 * @date: 2019年3月12日 上午10:51:28
	 * @Description: 流程流转
	 * @return: String
	 */
	public String doNext(Map<String, Object> params) {
		// 获取接口请求地址
		String url = null;
		String result = null;
		try {
			url = OptRequestUtil.getRequestUrl("operate_doNext");
			result = HttpClientUtilForCas.jsonPostInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param params
	 * @author: jiming
	 * @date: 2019年3月12日 下午1:27:18
	 * @Description: 人工终止流程
	 * @return: String
	 */
	public String doEndProcess(Map<String, Object> params) {
		// 获取接口请求地址
		String url = null;
		String result = null;
		try {
			url = OptRequestUtil.getRequestUrl("operate_doEndProcess");
			result = HttpClientUtilForCas.jsonPostInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @author: jiming
	 * @date: 2019年3月12日 下午1:38:55
	 * @Description: 根据流程实例id或任务id获取某个流程实例的审批历史
	 * @return: String
	 */
	public String getHistoryOpinion(Map<String, Object> params) {
		String url = null;
		String result = null;
		try {
			url = OptRequestUtil.getRequestUrl("operate_getHistoryOpinion");
			result = HttpClientUtilForCas.simpleJsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
