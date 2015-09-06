package cn.finance.dubbo.framework.session;

import java.io.Serializable;

/**
 * 后台用户session封装类
 * 
 * @author jianwei.li
 * @date 2015年7月14日
 */
public class EmployeeSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4161795715304970832L;
	/**
	 * 数据的交互枢纽
	 */
	protected static ThreadLocal<EmployeeSession> threadLocalUserSession = new ThreadLocal<EmployeeSession>();

	public static void setEmployeeSession(EmployeeSession userSession) {
		threadLocalUserSession.set(userSession);
	}

	/**
	 * 从线程池中获取UserSession对象
	 * 
	 * @author jianwei.li
	 * @date 2015年7月16日
	 * @return
	 */
	public static EmployeeSession getEmployeeSession() {
		return threadLocalUserSession.get();
	}

	/**
	 * 获取登录用户的ID
	 * 
	 * @author jianwei.li
	 * @date 2015年7月16日
	 * @return
	 */
	public static Integer getLoginEmployeeId() {
		EmployeeSession userSession = (EmployeeSession) threadLocalUserSession.get();
		if (userSession == null) {
			return null;
		} else {
			return userSession.getId();
		}
	}
	
	//内部用户ID
	private Integer id;
	//登录用户名(邮箱)
	private String email;
	//真实用户名
	private String name;
	//上次登录时间
	private String lastLoginTime;
	//密码初始状态 0：非初始 1：初始
	private Byte isInitial;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Byte getIsInitial() {
		return isInitial;
	}

	public void setIsInitial(Byte isInitial) {
		this.isInitial = isInitial;
	}
	
}
