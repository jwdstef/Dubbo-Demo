package cn.finance.dubbo.framework.session;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 本拦截器只是对HttpSession对象的转储，将httpsession对象和usersession对象存入线程池中
 * 
 * @author jianwei.li
 * @date 2015年7月14日
 */
public class EmployeeSessionFilter implements Filter {

	private static ThreadLocal<HttpSession> threadLocalHttpSession = new ThreadLocal<HttpSession>();

	public static HttpSession getHttpSession() {
		return threadLocalHttpSession.get();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		String uri = ((HttpServletRequest) request).getRequestURI();
		if (uri.endsWith(".jsp")) {
			String url = ((HttpServletRequest) request).getRequestURL().toString();
			if (!url.contains("localhost")) {
				HttpServletResponse response = (HttpServletResponse) arg1;
				response.sendRedirect("/notFound");
				return;
			}
		}
		HttpSession session = ((HttpServletRequest) request).getSession();
		threadLocalHttpSession.set(session);
		EmployeeSession employeeSession = (EmployeeSession) session.getAttribute(HttpSessionTool.EMPLOYEE_SESSION_KEY);
		EmployeeSession.setEmployeeSession(employeeSession);
		arg2.doFilter(request, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
