package cn.finance.dubbo.flow.listener;

import java.util.Set;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("taskListenerImpl")
public class TaskListenerImpl implements TaskListener {
	@Autowired
	private TaskService taskService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		String taskKey = delegateTask.getTaskDefinitionKey();
		String taskUser = delegateTask.getVariable(taskKey) == null?null:delegateTask.getVariable(taskKey).toString();
		if(StringUtils.hasText(taskUser)){
			delegateTask.setAssignee(taskUser);
		}else{
			String random = delegateTask.getAssignee();
			if(StringUtils.hasText(random) && random.equals("random")){
				Set<IdentityLink> set = delegateTask.getCandidates();
				for (IdentityLink identityLink : set) {
					String groupId = identityLink.getGroupId();
					if(StringUtils.hasText(groupId)){
						//TODO
//						List<Map<String, Object>> users = this.employeeService.selectUsersByGroup(groupId);
//						int userCount = users.size();
//						int randomCount = new Random().nextInt(userCount);
//						delegateTask.setAssignee(users.get(randomCount).get("emp_id").toString());
//						System.out.println(groupId);
						break;
					}
					
				}
			}
		}
	}
}
