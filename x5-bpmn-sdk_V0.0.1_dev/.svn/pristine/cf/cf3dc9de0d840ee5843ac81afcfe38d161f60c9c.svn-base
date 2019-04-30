package com.casic.inst;

import java.util.Map;

import com.casic.util.HttpClientUtil;
import com.casic.util.HttpClientUtilForCas;
import com.casic.util.InstRequestUtil;
import com.casic.util.LoginCasUtil;

/**
 * @author: jiming
 * @date: 2019年3月12日 下午2:55:12
 * @Description: 流程实例客户端
 */
public class InstClientForCas {

	private static InstClientForCas instClientForCas;

	private InstClientForCas(){
		
	}

	public static InstClientForCas getInstClientForCas() {
		if (instClientForCas == null) {
			instClientForCas = new InstClientForCas();
		}
		return instClientForCas;
	}

	public void login(String username, String password) {
		LoginCasUtil.getInstance().login(username, password);
	}

	/**
	 * @param Map<String,
	 *            Object>
	 * @author: jiming
	 * @date: 2019年3月12日 下午3:10:23
	 * @Description: 根据实例id获取实例对象
	 * @return: String
	 */
	public String getInstanceByInstId(Map<String, Object> params) {
		String url = null;
		String result = null;
		try {
			url = InstRequestUtil.getRequestUrl("instance_getInstanceByInstId");
			result = HttpClientUtilForCas.simpleJsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param Map<String,
	 *            Object>
	 * @author: jiming
	 * @date: 2019年3月12日 下午4:52:35
	 * @Description: 根据流程定义id和实例状态获取流程实例列表
	 * @return: String
	 */
	public String getInstancesByDefId(Map<String, Object> params) {
		String url = null;
		String result = null;
		try {
			url = InstRequestUtil.getRequestUrl("instance_getInstancesByDefId");
			result = HttpClientUtilForCas.simpleJsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param account(用户账号，必填)；orderField(排序字段，固定为实例创建时间：wfInst.create_time_)；
	 *            其他为可选项
	 * @author: jiming
	 * @date: 2019年3月12日 下午4:52:35
	 * @Description: 获取用户的已办事宜
	 * @return: String
	 */
	public String getDoneList(Map<String, Object> params) {
		String url = null;
		String result = null;
		try {
			url = InstRequestUtil.getRequestUrl("instance_getDoneList");
			result = HttpClientUtilForCas.jsonPostInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
