package com.casic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.casic.node.NodeClient;

/**
 * @author: jiming
 * @date: 2019年3月13日 下午1:50:01
 * @Description:  流程节点信息测试类
 */
public class NodeClientTest {
	private static NodeClient client =new NodeClient();
	
	/**
	 * @author: jiming
	 * @date: 2019年3月13日 下午1:54:57
	 * @Description: 根据任务id获取流程实例某个节点上的执行人员
	 * @return: void
	 */
	@Test
	public void getNodeUsers(){
		Map<String, Object> params = new HashMap<>();
		params.put("taskId", "10000003140042");
		String nodeUsers = client.getNodeUsers(params);
		System.out.println(nodeUsers);
		Object parse = JSONObject.parse(nodeUsers);
		if(parse instanceof JSONObject){
			JSONObject parseObject = JSON.parseObject(nodeUsers);
			//断言是否是异常信息
			assertNotEquals(parseObject.getString("code"), "500");
		}
		if(parse instanceof JSONArray){
			JSONArray jsonArray = JSONObject.parseArray(nodeUsers);
			//断言是否获取到列表
			assertTrue(jsonArray.size()>0);
		}
	}
	
	/**
	 * @author: jiming
	 * @date: 2019年3月13日 下午4:51:21
	 * @Description: 根据流程定义key获取流程的所有节点信息
	 * @return: void
	 */
	@Test
	public void getNodesByDefKey(){
		Map<String, Object> params = new HashMap<>();
		params.put("defKey", "wfsfwf"); 
		params.put("account", "500000_system"); 
		String getNodesByDefKey = client.getNodesByDefKey(params);
		System.out.println(getNodesByDefKey);
		Object parse = JSONObject.parse(getNodesByDefKey);
		if(parse instanceof JSONObject){
			JSONObject parseObject = JSON.parseObject(getNodesByDefKey);
			//断言是否是异常信息
			assertNotEquals(parseObject.getString("code"), "500");
		}
		if(parse instanceof JSONArray){
			JSONArray jsonArray = JSONObject.parseArray(getNodesByDefKey);
			//断言是否获取到列表
			assertTrue(jsonArray.size()>0);
		}
	}
	
	/**
	 * @author: jiming
	 * @date: 2019年3月13日 下午4:53:55
	 * @Description: 根据实例id和节点id获取节点状态
	 * @return: void
	 */
	@Test
	public void getStatusByRunidNodeId(){
		Map<String, Object> params = new HashMap<>();
		params.put("instId", "10000004000158");
		params.put("nodeId", "UserTask1");
		String statusByRunidNodeId = client.getStatusByRunidNodeId(params);
		System.out.println(statusByRunidNodeId);
		Object parse = JSONObject.parse(statusByRunidNodeId);
		if(parse instanceof JSONObject){
			JSONObject parseObject = JSON.parseObject(statusByRunidNodeId);
			//断言是否是异常信息
			assertNotEquals(parseObject.getString("code"), "500");
		}
		if(parse instanceof String){
			//断言节点状态
			assertEquals(parse.toString(), "pending——待审批");
		}
	}
}
