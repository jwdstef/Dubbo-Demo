package cn.finance.dubbo.flow.service;

import cn.finance.dubbo.framework.flow.FlowBaseBean;
import cn.finance.dubbo.framework.result.Json;

/**
 * 流程控制通用方案
 * 
 * @author jianwei.li
 * 
 */
public interface IFlowService {

	/**
	 * 部署项目
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json deploymentFlow(FlowBaseBean flow);

	/**
	 * 启动项目
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json startProcessInstance(FlowBaseBean flow);

	/**
	 * 查询个人任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectTaskUserArray(FlowBaseBean flow);

	/**
	 * 查询角色组任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectTaskGroupArray(FlowBaseBean flow);

	/**
	 * 查询定义工作流
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectProcessDefinition(FlowBaseBean flow);

	/**
	 * 领取任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json receiveTask(FlowBaseBean flow);


	/**
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json completeTask(FlowBaseBean flow);

	
	
	/**
	 * 根据任务ID查询流程实例
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectProcessInstanceByTaskId(FlowBaseBean flow);

	/**
	 * 根据任务ID查询任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectTaskByTaskId(FlowBaseBean flow);
	

	/**
	 * 根据用户查询历史任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectHistoryTaskByUser(FlowBaseBean flow);

	/**
	 * 根据用户组查询组内历史任务
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectHistoryTaskByGroup(FlowBaseBean flow);

	/**
	 * 根据用户查询历史流程实例
	 * @param flow
	 * @return
	 * 2015年7月30日 jianwei
	 *
	 */
	public Json selectHistoryProcessInstanceByUser(FlowBaseBean flow);

}
