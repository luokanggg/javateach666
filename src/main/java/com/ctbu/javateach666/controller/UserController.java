package com.ctbu.javateach666.controller;




import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.po.User;
import com.ctbu.javateach666.service.interfac.UserService;


@Controller
// @RequestMapping("/lk")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/test1")
	@ResponseBody
	public String gotest(){
		User user =  userService.queryUserById(1);
		System.out.println(user.toString());
		return user.toString();
	}
	
	//@RequestMapping("/hello")
	public String goHello(){
		return "helloword";
	}
	
/*	@RequestMapping("/logined")
	public String goLogined(){
		System.out.println("111111");
		System.out.println("111111");
		return "logined";
	}*/
	
/*	@RequestMapping("/logout")
	public String goLoginout(){
		System.out.println("333333");
		System.out.println("333333");
		return "redirect: logout";
	}*/
	
	//@RequestMapping("/login1")
	public String login1(){
		return "login1";
	}
	//@RequestMapping("/index")
	public String index(){
		return "index";
	}
	//@RequestMapping("/main")
	public String main(){
		return "main";
	}
	//@RequestMapping("/wel")
	public String wel(HttpServletRequest request){
		return "wel";
	}

}
