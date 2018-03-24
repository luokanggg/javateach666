package com.ctbu.javateach666.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.LKMyRepairListReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyRepairListRspBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.service.interfac.LKMyRepairService;

/**
 * 我的重修控制器类
 *
 * @author luokan
 */
@Controller
public class LKMyRepairController {
	
	@Autowired
	private LKMyRepairService lKMyRepairService;
	
	@RequestMapping("myrepairlist")
	public String goMyRepairList(){
		return "lkmyrepair/myrepairlist";
	}
	
	@ResponseBody
	@RequestMapping("getmyrepairlist")
	public PageInfoBo<LKMyRepairListRspBO>  getMyRepairList(LKMyRepairListReqBO lKMyRepairListReqBO){
		return lKMyRepairService.getMyRepairList(lKMyRepairListReqBO);
	}
}
