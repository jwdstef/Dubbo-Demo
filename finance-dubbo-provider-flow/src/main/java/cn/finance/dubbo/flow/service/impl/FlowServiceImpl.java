package cn.finance.dubbo.flow.service.impl;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.finance.dubbo.flow.service.IFlowService;
import cn.finance.dubbo.framework.flow.FlowBaseBean;
import cn.finance.dubbo.framework.logger.Log;
import cn.finance.dubbo.framework.result.Json;

/**
 * 流程控制通用方案
 * 
 * @author lijianwei
 * 
 */
@Service
public class FlowServiceImpl implements IFlowService{
	@Autowired
	private RepositoryService repositoryService;// 管理流程定义
	@Autowired
	private RuntimeService runtimeService;// 执行管理，包括启动、推进、删除流程实例等操作
	@Autowired
	private TaskService taskService;// 任务管理
	@Autowired
	private HistoryService historyService;// 历史管理(执行完的数据的管理)
	/**
	 * 部署项目
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json deploymentFlow(FlowBaseBean flow) {
		Log.info("********[流程部署开始,流程部署名称]:[" + flow.getDeployName() + "]");
		ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(flow.getZipByte()));
		try {
			Deployment deployment = repositoryService// 与流程定义和部署对象相关的Service
					.createDeployment()// 创建一个部署对象
					.name(flow.getDeployName())// 添加部署的名称
					.addZipInputStream(zipStream)// 指定zip格式的文件完成部署
					.deploy();// 完成部署
			Log.info("********[流程部署完成,流程部署名称]:[" + flow.getDeployName() + "]:[流程详情]:[部署ID:" + deployment.getId() + "]");
			return Json.resultTrue();
		} catch (Exception e) {
			Log.error("********[流程部署异常,流程部署名称]:[" + flow.getDeployName() + "]:[异常详情]:[" + e.getMessage() + "]");
			e.printStackTrace();
			return Json.resultFalseAndMsg("流程部署异常");
		}
	}

	/**
	 * 启动项目
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json startProcessInstance(FlowBaseBean flow) {
		Log.info("********[启动流程开始]:[流程定义Key]:[" + flow.getDeployKey() + "]");
		try {
			Map<String, Object> startUserMap = new HashMap<>();
			startUserMap.put("nextTaskUser", flow.getStartUser());
			ProcessInstance processInstance;
			if (StringUtils.hasText(flow.getBusinessKey()))
				processInstance = runtimeService.startProcessInstanceByKey(flow.getDeployKey(), flow.getBusinessKey(), startUserMap);
			else
				processInstance = runtimeService.startProcessInstanceByKey(flow.getDeployKey(), startUserMap);
			Log.info("********[启动流程完成]:[流程定义Key]:[" + flow.getDeployKey() + "]:[流程实例ID]:[" + processInstance.getId() + "]");
			return Json.resultTrue();
		} catch (Exception e) {
			Log.error("********[启动流程异常]:[流程定义Key]:[" + flow.getDeployKey() + "]:[异常详情]:[" + e.getMessage() + "]");
			e.printStackTrace();
			return Json.resultFalseAndMsg("流程启动异常");
		}
	}

	/**
	 * 查询个人任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectTaskUserArray(FlowBaseBean flow) {
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> userTasks = taskQuery.taskAssignee(flow.getUserId()).orderByTaskDueDate().asc().listPage(flow.getFirstResult(), flow.getMaxResult());
		return Json.resultTrueAndObj(userTasks);
	}

	/**
	 * 查询角色组任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectTaskGroupArray(FlowBaseBean flow) {
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> userTasks = taskQuery.taskCandidateGroupIn(flow.getUserGroups()).orderByTaskDueDate().asc().listPage(flow.getFirstResult(), flow.getMaxResult());
		return Json.resultTrueAndObj(userTasks);
	}

	/**
	 * 查询定义工作流
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectProcessDefinition(FlowBaseBean flow) {
		List<ProcessDefinition> pdList = repositoryService.createProcessDefinitionQuery().latestVersion().listPage(flow.getFirstResult(), flow.getMaxResult());
		return Json.resultTrueAndObj(pdList);
	}

	/**
	 * 领取任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json receiveTask(FlowBaseBean flow) {
		return this.receiveTask(flow.getTaskId(), flow.getUserId());
	}

	private Json receiveTask(String taskId, String userId) {
		Log.info("********[领取任务开始]:[taskId:" + taskId + "]:[userId:" + userId + "]");
		try {
			taskService.setAssignee(taskId, userId);
			Log.info("********[领取任务结束]:[taskId:" + taskId + "]:[userId:" + userId + "]");
			return Json.resultTrue();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("********[领取任务异常]:[taskId:" + taskId + "]:[userId:" + userId + "]:[异常详情:" + e.getMessage() + "]");
			return Json.resultFalseAndMsg("领取任务出现异常");
		}
	}

	/**
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public synchronized Json completeTask(FlowBaseBean flow) {
		Log.info("********[任務节点完成开始]:[taskId:" + flow.getTaskId() + "]:[userId:" + flow.getUserId() + "]");
		Task task = this.taskService.createTaskQuery().taskId(flow.getTaskId()).singleResult();
		if (task == null) {
			HistoricTaskInstance ht = this.historyService.createHistoricTaskInstanceQuery().taskId(flow.getTaskId()).singleResult();
			if (ht == null) {
				return Json.resultFalseAndMsg("任务异常,请先进行其他任务");
			} else {
				//TODO
//				Employee emp = this.employeeService.selectEmployeeNameByID(Integer.parseInt(flow.getUserId()));
//				return Json.resultFalseAndMsg("该任务已被用户" + emp.getEmpName() + "完成.");
				return Json.resultFalseAndMsg("该任务已被用户" + "完成.");
			}
		}
		if (StringUtils.hasText(task.getAssignee())) {
			Log.info("********[任务已有指定执行者]:[执行者ID:" + task.getAssignee() + "]:[taskId:" + flow.getTaskId() + "]:[userId:" + flow.getUserId() + "]");
			this.complete(flow);
			Log.info("********[任务完成]:[执行者ID:" + task.getAssignee() + "]:[taskId:" + flow.getTaskId() + "]:[userId:" + flow.getUserId() + "]");
			return Json.resultTrue();
		} else {
			Log.info("********[任务未有指定执行者,将任务归于自己]:[taskId:" + flow.getTaskId() + "]:[userId:" + flow.getUserId() + "]");
			Json json = this.receiveTask(flow.getTaskId(), flow.getUserId());
			if (json.getSuccess()) {
				Log.info("********[任务节点完成]:[taskId:" + flow.getTaskId() + "]:[userId:" + flow.getUserId() + "]");
				this.complete(flow);
				return Json.resultTrue();
			} else {
				return Json.resultFalseAndMsg("任务处理失败");
			}
		}
	}

	private void complete(FlowBaseBean flow){
		Task task = this.singleTask(flow.getTaskId());
		taskService.setVariable(flow.getTaskId(), task.getTaskDefinitionKey(), task.getAssignee());
		if(flow.getVariables() == null){
			this.taskService.complete(flow.getTaskId());
		}else{
			this.taskService.complete(flow.getTaskId(),flow.getVariables());
		}
	}
	
	
	/**
	 * 根据任务ID查询流程实例
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	@RequestMapping("/selectProcessInstanceByTaskId")
	public Json selectProcessInstanceByTaskId(FlowBaseBean flow) {
		Task task = this.taskService.createTaskQuery().taskId(flow.getTaskId()).singleResult();
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		return Json.resultTrueAndObj(pi);
	}

	/**
	 * 根据任务ID查询任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectTaskByTaskId(FlowBaseBean flow) {
		Task task = this.singleTask(flow.getTaskId());
		return Json.resultTrueAndObj(task);
	}
	
	private Task singleTask(String taskId){
		return this.taskService.createTaskQuery().taskId(taskId).singleResult();
	}

	/**
	 * 根据用户查询历史任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	@RequestMapping("/selectHistoryTaskByUser")
	public Json selectHistoryTaskByUser(FlowBaseBean flow) {
		List<HistoricTaskInstance> historiceTasks = this.historyService.createHistoricTaskInstanceQuery().orderByTaskDueDate().desc().taskAssignee(flow.getUserId())
				.listPage(flow.getFirstResult(), flow.getMaxResult());
		return Json.resultTrueAndObj(historiceTasks);
	}

	/**
	 * 根据用户组查询组内历史任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectHistoryTaskByGroup(FlowBaseBean flow) {
		List<HistoricTaskInstance> historiceTasks = this.historyService.createHistoricTaskInstanceQuery().orderByTaskDueDate().desc().taskCandidateGroupIn(flow.getUserGroups())
				.listPage(flow.getFirstResult(), flow.getMaxResult());
		return Json.resultTrueAndObj(historiceTasks);
	}

	/**
	 * 根据用户查询历史流程实例
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectHistoryProcessInstanceByUser(FlowBaseBean flow) {
		List<HistoricProcessInstance> HistoricProcessInstances = this.historyService.createHistoricProcessInstanceQuery().involvedUser(flow.getUserId()).orderByProcessInstanceStartTime().desc()
				.listPage(flow.getFirstResult(), flow.getMaxResult());
		return Json.resultTrueAndObj(HistoricProcessInstances);
	}

}
