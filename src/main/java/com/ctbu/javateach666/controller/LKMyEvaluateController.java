package com.ctbu.javateach666.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.LKInitEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitEvaluateRspBO;
import com.ctbu.javateach666.pojo.bo.LKSubmitEvaluateBO;
import com.ctbu.javateach666.service.interfac.LKMyEvaluateService;

/**
 * 我的教学质量评价控制器类
 *
 * @author luokan
 */
@Controller
public class LKMyEvaluateController {
	
	@Autowired
	private LKMyEvaluateService lKMyEvaluateService;
	
	@RequestMapping("gomyevaluate")
	public String goMyEvaluate(){
		return "lkmyevaluate/myevaluate";
	}
	
	@ResponseBody
	@RequestMapping("initevaluate")
	public List<LKInitEvaluateRspBO> initEvaluate(){
		LKInitEvaluateReqBO req = new LKInitEvaluateReqBO();
		return lKMyEvaluateService.initEvaluate(req);
	}
	
	@ResponseBody
	@RequestMapping("submitevaluate")
	public BaseInfoBO submitEvaluate(@RequestBody LKSubmitEvaluateBO lKSubmitEvaluateBO){
		/*for (int l : lKSubmitEvaluateBO.getId()) {
			System.out.print("id:" + l);
		}
		System.out.println();
		for (int l : lKSubmitEvaluateBO.getScore()) {
			System.out.print("score:" + l);
		}*/
		return lKMyEvaluateService.submitEvaluate(lKSubmitEvaluateBO);
	}
}
