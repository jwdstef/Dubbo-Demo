package cn.finance.dubbo.framework.session;

import javax.servlet.http.HttpSession;

import cn.finance.dubbo.framework.utils.MyBeanUtils;

public class HttpSessionTool {
	public static String SESSION_KEY = "loginedSession";
	
	public static String EMPLOYEE_SESSION_KEY = "employeeLoginedSession";
	
	public static String LOGINERROR_KEY = "login_error_number";

	/**
	 * 将成功登陆的用户保存到session中
	 * 
	 * @author jianwei.li
	 * @date 2015年7月14日
	 * @param httpSession
	 * @param baseUserSession
	 */
	public static void doLogin(HttpSession httpSession, UserSession baseUserSession) {
		httpSession.setAttribute(SESSION_KEY, baseUserSession);
		httpSession.removeAttribute(LOGINERROR_KEY);
	}
	
	
	
	/**
	 * 将成功登陆的用户保存到session中
	 * 
	 * @author jianwei.li
	 * @date 2015年7月14日
	 * @param httpSession
	 * @param baseUserSession
	 */
	public static void doLogin(HttpSession httpSession, EmployeeSession employeeSession) {
		httpSession.setAttribute(EMPLOYEE_SESSION_KEY, employeeSession);
		httpSession.removeAttribute(LOGINERROR_KEY);
	}

	/**
	 * 错误的登陆次数
	 * 
	 * @author jianwei.li
	 * @date 2015年7月14日
	 * @param httpSession
	 * @return
	 */
	public static Integer getLoginErrorNumber(HttpSession httpSession) {
		return (Integer) httpSession.getAttribute(LOGINERROR_KEY);
	}

	/**
	 * 增加登陆错误次数
	 * 
	 * @author jianwei.li
	 * @date 2015年7月14日
	 * @param httpSession
	 */
	public static void addLoginErrorNumber(HttpSession httpSession) {
		Integer number = getLoginErrorNumber(httpSession);
		number = number == null ? 1 : number + 1;
		httpSession.setAttribute(LOGINERROR_KEY, number);
	}

	/**
	 * 清除错误登录次数
	 * 
	 * @author jianwei.li
	 * @date 2015年7月14日
	 * @param httpSession
	 */
	public static void clearLoginErrorNumber(HttpSession httpSession) {
		httpSession.removeAttribute(LOGINERROR_KEY);
	}

	/**
	 * 退出
	 * 
	 * @author jianwei.li
	 * @date 2015年7月14日
	 * @param httpSession
	 */
	public static void doOut(HttpSession httpSession) {
		httpSession.removeAttribute(SESSION_KEY);
	}

	/**
	 * 根据Customer对象创建用户登录的Session信息
	 * 
	 * @author jianwei.li
	 * @date 2015年7月16日
	 * @param cus
	 * @return
	 */
	public static UserSession createUserSessionByCustormer(Object user) {
		return MyBeanUtils.copyBean(user, UserSession.class);
	}

	/**
	 * 根据User对象创建后台用户登录的Session信息
	 * 
	 * @author jianwei.li
	 * @date 2015年7月16日	
	 * @param cus
	 * @return
	 */
	public static EmployeeSession createEmployeeSessionByUser(Object employee) {
		return MyBeanUtils.copyBean(employee, EmployeeSession.class);
	}

}
