package com.ctbu.javateach666.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LKHrefController {
	
	@RequestMapping("/wel")
	public String goWel(HttpServletRequest request){
		return "wel";
	}

}
