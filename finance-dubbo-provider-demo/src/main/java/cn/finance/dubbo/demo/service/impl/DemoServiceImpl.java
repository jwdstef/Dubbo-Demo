package cn.finance.dubbo.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;

import cn.finance.dubbo.demo.dao.SinaCardMapper;
import cn.finance.dubbo.demo.model.SinaCard;
import cn.finance.dubbo.demo.model.SinaCardExample;
import cn.finance.dubbo.demo.service.IDemoService;

@Service
public class DemoServiceImpl implements IDemoService {

	@Autowired
	private SinaCardMapper sinaCardMapper;

	@Override
	public String sayHello(String name) {
		return name + "say: Hello word!";
	}

	@Override
	public List<SinaCard> selectByExample(SinaCardExample example) {
		return this.sinaCardMapper.selectByExample(example);
	}

}
