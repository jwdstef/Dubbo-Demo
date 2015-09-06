package cn.finance.dubbo.framework.logger;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

/**
 * 日志工具
 * @author jianwei.li
 *
 */
public class Log {
	private static Logger logger = LoggerFactory.getLogger("application");
	
	public static void error(String message){
		logger.error(message);
	}
	
	public static void info(String message){
		logger.info(message);
	}
	
	public static void debug(String message){
		logger.debug(message);
	}
}
