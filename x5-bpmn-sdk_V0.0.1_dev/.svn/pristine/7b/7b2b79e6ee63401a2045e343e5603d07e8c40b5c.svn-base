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
import com.casic.task.TaskClient;

/**
 * @author: jiming
 * @date: 2019年3月13日 上午10:38:54
 * @Description:  流程任务测试
 */
public class TaskClientTest {
	private static TaskClient client = new TaskClient();
	
	/**
	 * @author: jiming
	 * @date: 2019年3月13日 上午10:19:17
	 * @Description: 根据任务taskId,获取任务对象信息
	 * @return: void
	 */
	@Test
	public void getTaskByTaskId(){
		Map<String, Object> params = new HashMap<>();
		params.put("taskId", "10000002310022");
		String taskByTaskId = client.getTaskByTaskId(params);
		System.out.println(taskByTaskId);
		JSONObject parseObject = JSON.parseObject(taskByTaskId);
		//断言是否是异常信息
		assertNotEquals(parseObject.getString("code"), "500");
		//断言任务名称
		assertEquals(parseObject.getString("taskName"), "用户任务2");
		//断言节点id
		assertEquals(parseObject.getString("nodeId"), "UserTask2");
	}
	
	/**
	 * @author: jiming
	 * @date: 2019年3月13日 上午10:29:41
	 * @Description: 根据流程实例id获取任务列表
	 * @return: void
	 */
	@Test
	public void getTasksByInstId(){
		Map<String, Object> params = new HashMap<>();
		params.put("instId", "10000004060001");
		String getTasksByInstId = client.getTasksByInstId(params);
		System.out.println(getTasksByInstId);
		JSONObject parseObject = JSON.parseObject(getTasksByInstId);
		//断言是否是异常信息
		assertNotEquals(parseObject.getString("code"), "500");
		JSONArray jsonArray = parseObject.getJSONArray("taskList");
		//断言是否获取到列表
		assertTrue(jsonArray.size()>0);
	}
	
	/**
	 * @author: jiming
	 * @date: 2019年3月13日 上午10:29:41
	 * @Description: 获取用户的待办事宜
	 * @return: void
	 */
	@Test
	public void getTodoList(){
		Map<String, Object> params = new HashMap<>();
		params.put("account", "500000_system");
		//params.put("processName", "流程操作测试流程");
		String getTodoList = client.getTodoList(params);
		System.out.println(getTodoList);
		JSONObject parseObject = JSON.parseObject(getTodoList);
		//断言是否是异常信息
		assertNotEquals(parseObject.getString("code"), "500");
		JSONArray jsonArray = parseObject.getJSONArray("rows");
		//断言是否获取到列表
		assertTrue(jsonArray.size()>0);
		//断言列表长度
		assertEquals(jsonArray.size(), 16);
	}
}
