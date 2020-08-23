package com.macrosan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macrosan.common.utils.ShiroUtils;
import com.macrosan.pojo.User;

@Controller
@RequestMapping("/")
public class PageController {
	
	@RequestMapping("")
	public String doIndexUI(Model model) {
		User user = ShiroUtils.getUser();
		model.addAttribute("user", user);
		return "starter";
	}
	
	//使用restful风格的实现通用页面跳转
	@RequestMapping("{module}/{module_list}")
	public String doModuleUI(@PathVariable String module_list) {
		return "sys/"+module_list;
	}
	
	//加载分页信息
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "/common/page";
	}
	//加载登录页面
	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}
}
