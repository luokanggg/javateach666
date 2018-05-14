package com.ctbu.javateach666.controller.thc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.po.thcpo.THCOurInfoPO;
import com.ctbu.javateach666.service.interfac.thc.THCOurInfoService;

@Controller
public class THCOurInfoController {
	@Autowired
	private THCOurInfoService tHCOurInfoService;
	
/*	@RequestMapping("/indexiframe")
	public String goNoticeInfo(){
		return "thcindex/indexiframe";
	}*/
	
	/**
	 * 首页关于我们
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/getourinfo")
	public List<THCOurInfoPO> getOurInfo(){
		return tHCOurInfoService.getOurInfoList();
	}
}
