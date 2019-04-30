package com.casic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.casic.def.DefClient;
import com.casic.def.DefClientForCas;
import com.casic.util.LoginCasUtil;


/**
 * @author: jiming
 * @date: 2019年3月11日 下午3:39:04
 * @Description:  流程定义sdk客户端测试类
 */
public class DefClientTestForCas {
	private static String username = "500000_system";
	private static String password = "A951E5D7542CABFDCD1E45FB36E3D53A7B7DD50442AE6306";
	
	public DefClientTestForCas(){
		DefClientForCas.getDefClientForCas().login(username, password); 
	}
	/**
	 * @author: jiming
	 * @date: 2019年3月11日 下午3:39:39
	 * @Description: 通过用户名获取流程定义列表测试
	 * @return: void
	 */
	@Test
	public void getDefinitionList() {
		Map<String, Object> params = new HashMap<>();
		params.put("account", "740116_system");
		params.put("pageSize", 20);
		String definitionList = DefClientForCas.getDefClientForCas().getDefinitionList(params);
		System.out.println(definitionList);
		JSONObject parseObject = JSON.parseObject(definitionList);
		//断言是否是异常信息
		assertNotEquals(parseObject.getString("code"), "500");
		JSONArray jsonArray = parseObject.getJSONArray("rows");
		//断言是否获取到列表
		assertTrue(jsonArray.size()>0);
		//断言根据用户名获取流程定义的列表长度
		assertEquals(jsonArray.size(), 2);
	}
	
	/**
	 * @author: jiming
	 * @date: 2019年3月11日 下午3:39:39
	 * @Description: 根据流程定义id获取流程定义信息测试--x5平台暂时未放开此接口
	 * @return: void
	 * @throws IOException 
	 */
	/*@Test
	public void getBpmDefinitionByDefId(){
		Map<String, Object> params = new HashMap<>();
		params.put("defId", "10000000090056");
		String bpmDefinitionByDefId = DefClientForCas.getDefClientForCas().getBpmDefinitionByDefId(params);
		System.out.println(bpmDefinitionByDefId);
		JSONObject parseObject = JSON.parseObject(bpmDefinitionByDefId);
		//断言是否是异常信息
		assertNotEquals(parseObject.getString("code"), "500");
		//断言根据defId获取到的流程定义的defKey
		assertEquals(parseObject.getString("defKey"), "sadasdsadas");
		//断言根据defId获取到的流程定义流程发布id
		assertEquals(parseObject.getString("bpmnDeployId"), "10000003790047");
	}*/
}
