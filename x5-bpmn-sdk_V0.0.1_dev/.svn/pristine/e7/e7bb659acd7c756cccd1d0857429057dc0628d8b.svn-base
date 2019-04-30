package com.casic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.casic.inst.InstClientForCas;

/**
 * 
 * @author: jiming
 * @date: 2019年3月12日 下午3:12:42
 * @Description:  流程实例测试类
 */
public class InstClientTestForCas {
	private static String username = "500000_system";
	private static String password = "A951E5D7542CABFDCD1E45FB36E3D53A7B7DD50442AE6306";
	
	public InstClientTestForCas(){
		InstClientForCas.getInstClientForCas().login(username, password);
	}
	
	
	/**
	 * @author: jiming
	 * @date: 2019年3月12日 下午3:14:42
	 * @Description: 根据实例id获取实例对象
	 * @return: void
	 */
	@Test
	public void getInstanceByInstId(){
		Map<String, Object> params = new HashMap<>();
		params.put("instId", "10000000091320");
		String instanceByInstId = InstClientForCas.getInstClientForCas().getInstanceByInstId(params);
		System.out.println(instanceByInstId);
		JSONObject parseObject = JSON.parseObject(instanceByInstId);
		//断言是否是异常信息
		assertNotEquals(parseObject.getString("code"), "500");
		String subject = parseObject.get("subject").toString();
		String status = parseObject.get("status").toString();
		//断言流程实例的标题
		assertEquals(subject, "九州科技_企业管理员在2019-04-08发起稳稳");
		//断言流程实例的状态
		assertEquals(status, "running");
	}
	
	/**
	 * @author: jiming
	 * @date: 2019年3月12日 下午5:06:46
	 * @Description: 根据流程定义id和实例状态获取流程实例列表
	 * @return: void
	 */
	@Test
	public void getInstancesByDefId(){
		Map<String, Object> params = new HashMap<>();
		params.put("defId", "10000000090056");
		params.put("status", "running");
		//params.put("account", "740116_system1");
		String instancesByDefId = InstClientForCas.getInstClientForCas().getInstancesByDefId(params);
		System.out.println(instancesByDefId);
		Object parse = JSONObject.parse(instancesByDefId);
		if(parse instanceof JSONObject){
			JSONObject parseObject = JSON.parseObject(instancesByDefId);
			//断言是否是异常信息
			assertNotEquals(parseObject.getString("code"), "500");
		}
		if(parse instanceof JSONArray){
			JSONArray jsonArray = JSONObject.parseArray(instancesByDefId);
			//断言获取到的流程实例列表长度
			assertEquals(jsonArray.size(), 1);
		}
	}
	
	
	/**
	 * @author: jiming
	 * @date: 2019年3月12日 下午5:06:46
	 * @Description: 获取用户的已办事宜;account(用户名)和orderField(排序字段)为必填项，其他为可选
	 * @return: void
	 */
	@Test
	public void getDoneList(){
		Map<String, Object> params = new HashMap<>();
		params.put("account", "500000_system");
		params.put("orderField", "wfInst.create_time_");
		params.put("pageSize", 100);
		/*params.put("processName", "流程操作测试流程");
		params.put("subject", "流程操作测试流程");*/
		String doneList = InstClientForCas.getInstClientForCas().getDoneList(params);
		System.out.println(doneList);
		JSONObject parseObject = JSON.parseObject(doneList);
		//断言是否是异常信息
		assertNotEquals(parseObject.getString("code"), "500");
		JSONArray jsonArray = parseObject.getJSONArray("rows");
		//断言获取到的已办事宜的列表长度
		assertEquals(jsonArray.size(), 8);
	}
}
