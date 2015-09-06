package cn.finance.dubbo.demo.service;

import java.util.List;

import cn.finance.dubbo.demo.model.SinaCard;
import cn.finance.dubbo.demo.model.SinaCardExample;
import cn.finance.dubbo.framework.zookeeper.NeedLock;

public interface IDemoService {
	
	String sayHello(String name);
	
	@NeedLock(value = "/test/lock")
	List<SinaCard> selectByExample(SinaCardExample exampleJson);
}
