package com.ctbu.javateach666.controller;





import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 登录控制器类
 *
 * @author luokan
 */
@Controller
public class LoginController {	
		
	@RequestMapping("/login")
	public String goLogin(){
		System.out.println("222222");
		System.out.println("222222");
		return "login";
	}
	
	@RequestMapping("/main")
	public String goLogined(){
		System.out.println("111111");
		System.out.println("111111");
		return "main";
	}
	
	@RequestMapping("/index")
	public String goIndex(){
		System.out.println("333333");
		System.out.println("333333");
		return "index";
	}

}
