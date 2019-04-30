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
import com.casic.opt.OptClientForCas;

/**
 * @author: jiming
 * @date: 2019年3月12日 上午9:10:06
 * @Description:  流程操作测试
 */
public class OptClientTestForCas {
	private static String username = "500000_system";
	private static String password = "A951E5D7542CABFDCD1E45FB36E3D53A7B7DD50442AE6306";
	
	public OptClientTestForCas(){
		OptClientForCas.getOptClientForCas().login(username, password);
	}
	
	private static OptClientForCas client = OptClientForCas.getOptClientForCas();
	
	/**
	 * @author: jiming
	 * @date: 2019年3月12日 上午10:52:07
	 * @Description: 启动流程测试
	 * @return: void
	 */
	@Test
	public void startProcess(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", "500000_system");
		params.put("defId", "10000000090056");
		String startProcess = OptClientForCas.getOptClientForCas().startProcess(params);
		System.out.println(startProcess);
		JSONObject parseObject = JSON.parseObject(startProcess);
		//断言是否是异常信息
		assertNotEquals(parseObject.getString("code"), "500");
		//断言流程启动成功
		assertEquals(parseObject.get("message"), "流程启动成功");
		
	}
	
	/**
	 * @author: jiming
	 * @date: 2019年3月12日 上午11:01:30
	 * @Description: 流程跳转测试
	 * @return: void
	 */
	@Test
	public void doNext(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", "500000_system");
		params.put("actionName", "agree");
		params.put("taskId", "10000000091339");
		String doNext = OptClientForCas.getOptClientForCas().doNext(params);
		System.out.println(doNext);
		JSONObject parseObject = JSON.parseObject(doNext);
		//断言是否是异常信息
		assertNotEquals(parseObject.getString("code"), "500");
		//断言任务处理成功
		assertEquals(parseObject.get("message"), "任务处理成功");
		
	}
	
	/**
	 * @author: jiming
	 * @date: 2019年3月12日 上午11:01:30
	 * @Description: 人工终止流程测试
	 * @return: void
	 */
	@Test
	public void doEndProcess(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", "500000_system");
		params.put("endReason", "我愿意");
		params.put("taskId", "10000000091367");
		//params.put("messageType", "inner");
		String doEndProcess = client.doEndProcess(params);
		System.out.println(doEndProcess);
		JSONObject parseObject = JSON.parseObject(doEndProcess);
		//断言是否是异常信息
		assertNotEquals(parseObject.getString("code"), "500");
		//断言终止流程成功
		assertEquals(parseObject.get("message"), "操作成功： 已终止流程");
		
	}
	
	/**
	 * @author: jiming
	 * @date: 2019年3月12日 上午11:01:30
	 * @Description: 根据流程实例id或任务id获取某个流程实例的审批历史测试
	 * @return: void
	 */
	@Test
	public void getHistoryOpinion(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("instanId", "10000000091374");
		String historyOpinion = client.getHistoryOpinion(params);
		System.out.println(historyOpinion);
		Object parse = JSONObject.parse(historyOpinion);
		if(parse instanceof JSONObject){
			JSONObject parseObject = JSON.parseObject(historyOpinion);
			//断言是否是异常信息
			assertNotEquals(parseObject.getString("code"), "500");
		}
		if(parse instanceof JSONArray){
			JSONArray jsonArray = JSONObject.parseArray(historyOpinion);
			//断言是否获取到列表
			assertTrue(jsonArray.size()>0);
		}
	}
}
