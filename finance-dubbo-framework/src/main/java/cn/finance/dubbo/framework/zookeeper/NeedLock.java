package cn.finance.dubbo.framework.zookeeper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * zookeeper锁注解
 * @author jianwei.li
 * 2015年9月2日
 */
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLock
{
  public abstract String value();
}