package com.casic.task;

import java.util.Map;

import com.casic.util.HttpClientUtilForCas;
import com.casic.util.LoginCasUtil;
import com.casic.util.TaskRequestUtil;

/**
 * @author: jiming
 * @date: 2019年3月12日 下午2:55:12
 * @Description: 流程任务客户端
 */
public class TaskClientForCas {

	private static TaskClientForCas taskClientForCas;

	private TaskClientForCas(){
		
	}

	public static TaskClientForCas getTaskClientForCas() {
		if (taskClientForCas == null) {
			taskClientForCas = new TaskClientForCas();
		}
		return taskClientForCas;
	}

	public void login(String username, String password) {
		LoginCasUtil.getInstance().login(username, password);
	}

	/**
	 * @param String
	 *            例：taskId=10000002310022
	 * @author: jiming
	 * @date: 2019年3月13日 上午10:16:33
	 * @Description: 根据任务taskId,获取任务对象信息
	 * @return: String
	 */
	public String getTaskByTaskId(Map<String, Object> params) {
		String url = null;
		String result = null;
		try {
			url = TaskRequestUtil.getRequestUrl("task_getTaskByTaskId");
			result = HttpClientUtilForCas.simpleJsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param String
	 *            例：instId=10000004010134
	 * @author: jiming
	 * @date: 2019年3月13日 上午10:28:58
	 * @Description: 根据流程实例id获取任务列表
	 * @return: String
	 */
	public String getTasksByInstId(Map<String, Object> params) {
		String url = null;
		String result = null;
		try {
			url = TaskRequestUtil.getRequestUrl("task_getTasksByInstId");
			result = HttpClientUtilForCas.simpleJsonGetInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param Map<String,
	 *            Object> params--account(用户账号，必填);
	 * @author: jiming
	 * @date: 2019年3月13日 上午10:33:47
	 * @Description: 获取用户的待办事宜
	 * @return: String
	 */
	public String getTodoList(Map<String, Object> params) {
		String url = null;
		String result = null;
		try {
			url = TaskRequestUtil.getRequestUrl("task_getTodoList");
			result = HttpClientUtilForCas.jsonPostInvoke(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
