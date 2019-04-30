package com.casic.node;

import java.util.Map;

import org.junit.Test;

import com.casic.util.HttpClientUtilForCas;
import com.casic.util.LoginCasUtil;
import com.casic.util.NodeRequestUtil;

/**
 * @author: jiming
 * @date: 2019年3月13日 下午1:43:10
 * @Description:  流程节点信息客户端
 */
public class NodeClientForCas {
	private static NodeClientForCas nodeClientForCas;

	private NodeClientForCas(){
		
	}

	public static NodeClientForCas getNodeClientForCas() {
		if (nodeClientForCas == null) {
			nodeClientForCas = new NodeClientForCas();
		}
		return nodeClientForCas;
	}

	public void login(String username, String password) {
		LoginCasUtil.getInstance().login(username, password);
	}
	/**
	 * @param String 例：taskId=10000002310022
	 * @author: jiming
	 * @date: 2019年3月13日 下午1:47:11
	 * @Description: 根据任务id获取流程实例某个节点上的执行人员--x5平台暂时未放开此接口
	 * @return: String
	 */
/*	public String getNodeUsers(Map<String, Object> params){
		String url = null;
		String result = null;
		try {
			url = NodeRequestUtil.getRequestUrl("node_getNodeUsers");
			result = HttpClientUtilForCas.simpleJsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	/**
	 * @param defKey-流程定义key;例如：defKey=wfsfwf
	 * @author: jiming
	 * @date: 2019年3月13日 下午1:56:31
	 * @Description: 根据流程定义key获取流程的所有节点信息-x5平台暂时未放开此接口
	 * @return: String
	 */
	/*public String getNodesByDefKey(Map<String, Object> params){
		String url = null;
		String result = null;
		try {
			url = NodeRequestUtil.getRequestUrl("node_getNodesByDefKey");
			result= HttpClientUtilForCas.simpleJsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	/**
	 * @author: jiming
	 * @date: 2019年3月13日 下午4:47:50
	 * @Description: 根据实例id和节点id获取节点状态
	 * @return: String
	 */
	@Test
	public String getStatusByRunidNodeId(Map<String, Object> params){
		String url = null;
		String result = null;
		try {
			url = NodeRequestUtil.getRequestUrl("node_getStatusByRunidNodeId");
			result = HttpClientUtilForCas.simpleJsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
