package cn.finance.dubbo.framework.flow;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FlowBaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private int page = 1;
	private int rows = 10;
	private byte[] zipByte;
	private String deployName;
	private String deployKey;
	private String businessKey;
	private String startUser;
	private List<String> userGroups;
	private String taskId;
	private Map<String, Object> variables;
	
	
	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}


	public byte[] getZipByte() {
		return zipByte;
	}

	public void setZipByte(byte[] zipByte) {
		this.zipByte = zipByte;
	}

	public String getDeployName() {
		return deployName;
	}

	public void setDeployName(String deployName) {
		this.deployName = deployName;
	}

	public String getDeployKey() {
		return deployKey;
	}

	public void setDeployKey(String deployKey) {
		this.deployKey = deployKey;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getStartUser() {
		return startUser;
	}

	public void setStartUser(String startUser) {
		this.startUser = startUser;
	}

	public List<String> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<String> userGroups) {
		this.userGroups = userGroups;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getFirstResult() {
		return (page - 1) * rows;
	}

	public int getMaxResult() {
		return rows;
	}
}
