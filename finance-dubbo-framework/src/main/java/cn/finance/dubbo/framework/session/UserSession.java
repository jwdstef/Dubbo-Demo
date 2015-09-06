package cn.finance.dubbo.framework.session;

import java.io.Serializable;

/**
 * 用户session封装类
 * 
 * @author jianwei.li
 * @date 2015年7月14日
 */
public class UserSession implements Serializable {

	private static final long serialVersionUID = -3047902397882241246L;

	public UserSession() {
		// TODO Auto-generated constructor stub
	}
	
	public UserSession(Integer cusId) {
		this.cusId = cusId;
	}
	
	public UserSession(byte realStatus) {
		this.realStatus = realStatus;
	}
	
	public UserSession(Integer cusId, byte realStatus) {
		this.cusId = cusId;
		this.realStatus = realStatus;
	}
	
	/**
	 * 数据的交互枢纽
	 */
	protected static ThreadLocal<UserSession> threadLocalUserSession = new ThreadLocal<UserSession>();

	public static void setUserSession(UserSession userSession) {
		threadLocalUserSession.set(userSession);
	}

	/**
	 * 从线程池中获取UserSession对象
	 * @author jianwei.li
	 * @date 2015年7月16日
	 * @return
	 */
	public static UserSession getUserSession() {
		return threadLocalUserSession.get();
	}

	/**
	 * 获取登录用户的ID
	 * 
	 * @author jianwei.li
	 * @date 2015年7月16日
	 * @return
	 */
	public static Integer getLoginUserId() {
		UserSession userSession = (UserSession) threadLocalUserSession.get();
		if (userSession == null) {
			return null;
		} else {
			return userSession.getCusId();
		}
	}

	/**
	 * 用户ID
	 */
	private Integer cusId;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 实名认证状态
	 */
	private Byte realStatus;
	/**
	 * 真实姓名
	 */
	private String cardName;
	/**
	 * 是否是借款用户
	 */
	private Byte isBorrow;
	/**
	 * 是否是借款用户
	 */
	private Byte isTender;

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Byte getRealStatus() {
		return realStatus;
	}

	public void setRealStatus(Byte realStatus) {
		this.realStatus = realStatus;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Byte getIsBorrow() {
		return isBorrow;
	}

	public void setIsBorrow(Byte isBorrow) {
		this.isBorrow = isBorrow;
	}

	public Byte getIsTender() {
		return isTender;
	}

	public void setIsTender(Byte isTender) {
		this.isTender = isTender;
	}

}
