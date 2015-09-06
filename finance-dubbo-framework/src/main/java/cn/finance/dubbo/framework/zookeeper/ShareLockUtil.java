package cn.finance.dubbo.framework.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

public class ShareLockUtil
  implements InitializingBean
{
  private String zookeeperConnectionString;
  private RetryPolicy retryPolicy = null;
  private CuratorFramework client = null;

  public InterProcessMutex getLock(String path) {
    if (!StringUtils.hasText(path))
      path = "/shareLocks";
    else if (!path.startsWith("/")) {
      path = path + "/";
    }
    InterProcessMutex lock = new InterProcessMutex(this.client, path);
    return lock;
  }

  public void afterPropertiesSet() throws Exception
  {
    this.retryPolicy = new ExponentialBackoffRetry(1000, 3);
    this.client = CuratorFrameworkFactory.newClient(this.zookeeperConnectionString, this.retryPolicy);

    this.client.start();
  }

  public String getZookeeperConnectionString() {
    return this.zookeeperConnectionString;
  }

  public void setZookeeperConnectionString(String zookeeperConnectionString) {
    this.zookeeperConnectionString = zookeeperConnectionString;
  }
}