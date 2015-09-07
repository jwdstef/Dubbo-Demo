package cn.finance.dubbo.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.finance.dubbo.demo.model.SinaCard;
import cn.finance.dubbo.demo.model.SinaCardExample;
import cn.finance.dubbo.demo.service.IDemoService;
import cn.finance.dubbo.framework.logger.Log;

@Controller
@RequestMapping("/demo")
public class DemoController {
	@Reference
	private IDemoService demoService;
	
	@RequestMapping("/helloWord")
	public String helloWord(Model model){
		Log.error("日志测试测试日志Error");
		Log.info("日志测试测试日志Info");
		Log.debug("日志测试测试日志debg");
//		SinaCardExample sce = new SinaCardExample();
//		List<SinaCard> scs = this.demoService.selectByExample(sce);
		model.addAttribute("say", "121231234444");
		return "demo";
	}
}
