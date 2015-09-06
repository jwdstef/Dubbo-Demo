package cn.finance.dubbo.framework.zookeeper;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zookeeper 锁拦截器
 * @author jianwei.li
 * 2015年9月2日
 */
public class LockInterceptor implements MethodInterceptor {
	Logger logger = LoggerFactory.getLogger(getClass());
	private ShareLockUtil shareLockUtil;

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object res = null;
		Method method = invocation.getMethod();
		NeedLock need = (NeedLock) method.getAnnotation(NeedLock.class);
		if (need != null) {
			InterProcessLock lock = this.shareLockUtil.getLock(need.value());
			if (lock.acquire(120L, TimeUnit.SECONDS))
				try {
					res = invocation.proceed();
				} catch (Exception ex) {
					throw ex;
				} finally {
					lock.release();
				}
			else
				this.logger.error("等待锁超时！！！");
		} else {
			res = invocation.proceed();
		}
		return res;
	}

	public ShareLockUtil getShareLockUtil() {
		return this.shareLockUtil;
	}

	public void setShareLockUtil(ShareLockUtil shareLockUtil) {
		this.shareLockUtil = shareLockUtil;
	}
}