package com.casic.node;

import java.util.Map;

import org.junit.Test;

import com.casic.util.HttpClientUtil;
import com.casic.util.NodeRequestUtil;

/**
 * @author: jiming
 * @date: 2019年3月13日 下午1:43:10
 * @Description:  流程节点信息客户端
 */
public class NodeClient {
	
	/**
	 * @param String 例：taskId=10000002310022
	 * @author: jiming
	 * @date: 2019年3月13日 下午1:47:11
	 * @Description: 根据任务id获取流程实例某个节点上的执行人员
	 * @return: String
	 */
	public String getNodeUsers(Map<String, Object> params){
		String url = null;
		String result = null;
		try {
			url = NodeRequestUtil.getRequestUrl("node_getNodeUsers");
			result = HttpClientUtil.JsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @param defKey-流程定义key;例如：defKey=wfsfwf
	 * @author: jiming
	 * @date: 2019年3月13日 下午1:56:31
	 * @Description: 根据流程定义key获取流程的所有节点信息
	 * @return: String
	 */
	public String getNodesByDefKey(Map<String, Object> params){
		String url = null;
		String result = null;
		try {
			url = NodeRequestUtil.getRequestUrl("node_getNodesByDefKey");
			result= HttpClientUtil.JsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
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
			result = HttpClientUtil.JsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
