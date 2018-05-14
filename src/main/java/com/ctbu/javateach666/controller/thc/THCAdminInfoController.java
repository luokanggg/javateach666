package com.ctbu.javateach666.controller.thc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.thcbo.THCUpdateAdminInBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCUpdateAdminPassBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAccountPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAdminInfoPO;
import com.ctbu.javateach666.service.interfac.thc.THCAdminInfoService;

@Controller
public class THCAdminInfoController {
	
	@Autowired
	private THCAdminInfoService tHCAdminInfoService;
	
	@RequestMapping("/admininfo")
	public String goAdminInfo(){
		return "thcadmin/personinfo/admininfo";
	}
	
	//个人设置模块
	@ResponseBody
	@RequestMapping("/initadmininfo")
	public THCAdminInfoPO initStuInfo(){
		System.out.println("initAdminInfo");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getUsername());
		return tHCAdminInfoService.initAdminInfo(userDetails.getUsername());
	}
	
	@ResponseBody
	@RequestMapping("/updateadmininfo")
	public THCUpdateAdminInBO updateAdminInfo(@RequestBody THCAdminInfoPO adinfo){
		System.out.println("update"+ adinfo);
		System.out.println(adinfo.getAdminno());
		return tHCAdminInfoService.updateAdminInfo(adinfo.getAdminno(), adinfo.getAdminphone(), adinfo.getAdminemail());
	}

	@RequestMapping("/adminsetpass")
	public String goAdminSetPass(){
		return "thcadmin/personinfo/adminsetpass";
	}
	
	@ResponseBody
	@RequestMapping("/getadminpass")
	public THCAccountPO getAdminPass(){
		System.out.println("getAdminPass");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getUsername());
		return tHCAdminInfoService.getAdminPass(userDetails.getUsername());
	}
	
	@ResponseBody
	@RequestMapping("/updateadminpass")
	public THCUpdateAdminPassBO updateAdminPass(@RequestBody THCAccountPO adaccount){
		return tHCAdminInfoService.updateAdminPass(adaccount.getUsername(), adaccount.getPassword());
	}
}
