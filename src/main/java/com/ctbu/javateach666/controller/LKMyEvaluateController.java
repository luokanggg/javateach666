package com.ctbu.javateach666.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.LKGoUpdateEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitEvaluateRspBO;
import com.ctbu.javateach666.pojo.bo.LKMyRepairListReqBO;
import com.ctbu.javateach666.pojo.bo.LKSubmitEvaluateBO;
import com.ctbu.javateach666.pojo.bo.LKUpdateEvaluateReqBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.getEvaluateListRspBO;
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
	
	//教学评价管理小模块
	
	@RequestMapping("goevaluatelist")
	public String goEvaluateList(){
		return "lkmyevaluate/evaluatelist";
	}
	
	@ResponseBody
	@RequestMapping("/getevaluatelist")
	public PageInfoBo<getEvaluateListRspBO> getEvaluateList(LKMyRepairListReqBO lKMyRepairListReqBO){
		return lKMyEvaluateService.getEvaluateList(lKMyRepairListReqBO);
	}
	
	@ResponseBody
	@RequestMapping("/checkgoupdateevaluate")
	public BaseInfoBO checkGoUpdateEvaluate(@RequestBody LKGoUpdateEvaluateReqBO lKGoUpdateEvaluateReqBO){
		
		BaseInfoBO rsp = new BaseInfoBO();
		
		//取得当前时间
		Calendar cal =Calendar.getInstance();
		int couyear = cal.get(Calendar.YEAR);
		int semester = cal.get(Calendar.MONTH) + 1;
		if(semester <= 6){
			semester = 1;
		}else{
			semester = 2;
		}
		if(couyear == lKGoUpdateEvaluateReqBO.getCouyear() && semester == lKGoUpdateEvaluateReqBO.getSemester()){
			rsp.setResponseCode("0000");
		}else{
			rsp.setResponseCode("8888");
			rsp.setResponseDesc("只能评价当前学期的课程");
		}
		return rsp;
	}
	
	@RequestMapping("/goupdateevaluate")
	public String goUpdateEvaluate(int id,HttpServletRequest request){
		request.setAttribute("id", id);
		return "lkmyevaluate/updateevaluate";
	};
	
	@ResponseBody
	@RequestMapping("/getevaluatebyid")
	public LKInitEvaluateRspBO getEvaluateById(@RequestBody LKcancelClassReqBO lKcancelClassReqBO){
		return lKMyEvaluateService.getEvaluateById(lKcancelClassReqBO);
	}
	
	@ResponseBody
	@RequestMapping("/updateevaluate")
	public BaseInfoBO updateEvaluate(@RequestBody LKUpdateEvaluateReqBO lKUpdateEvaluateReqBO){
		return lKMyEvaluateService.updateEvaluate(lKUpdateEvaluateReqBO);
	}
}
