package com.ctbu.javateach666.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.LKGetChooseActivityListReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetChooseActivityListRspBO;
import com.ctbu.javateach666.pojo.bo.LKGetManageActivityReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetManageActivityRspBO;
import com.ctbu.javateach666.pojo.bo.LKMoveActivityMemberReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyPubNameCombBoxBO;
import com.ctbu.javateach666.pojo.bo.LKPubActivityReqBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.service.interfac.LKMyActivityService;

@Controller
public class LKMyActivityController {
	
	@Autowired
	private LKMyActivityService lKMyActivityService;
	
	//发布活动小模块
	
	@RequestMapping("/gopubactivity")
	public String goPubActivity(){
		return "lkmyactivity/pubactivity";
	}
	
	@ResponseBody
	@RequestMapping("/pubactivity")
	public BaseInfoBO pubActivity(@RequestBody LKPubActivityReqBO lKPubActivityReqBO){
		return lKMyActivityService.pubActivity(lKPubActivityReqBO);
	}
	
	//活动报名小模块
	
	@RequestMapping("/gochooseactivity")
	public String goChooserActivity(){
		return "lkmyactivity/chooseactivity";
	}
	
	@ResponseBody
	@RequestMapping("/getchooseactivitylist")
	public PageInfoBo<LKGetChooseActivityListRspBO> getChooseActivityList(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO){
		return lKMyActivityService.getChooseActivityList(lKGetChooseActivityListReqBO);
	}
	
	@ResponseBody
	@RequestMapping("/chooseactivity")
	public BaseInfoBO chooseActivity(@RequestBody LKcancelClassReqBO lKcancelClassReqBO){
		return lKMyActivityService.chooseActivity(lKcancelClassReqBO);
	};
	
	@RequestMapping("/gomychooseactivity")
	public String goMyChooseActivity(){
		return "lkmyactivity/mychooseactivity";
	}
	
	@ResponseBody
	@RequestMapping("getmychooseactivitylist")
	public PageInfoBo<LKGetChooseActivityListRspBO> getMyChooseActivityList(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO){
		return lKMyActivityService.getMyChooseActivityList(lKGetChooseActivityListReqBO);
	}
	
	@ResponseBody
	@RequestMapping("/cancelchooseactivity")
	public BaseInfoBO cancelChooseActivity(@RequestBody LKcancelClassReqBO lKcancelClassReqBO){
		return lKMyActivityService.cancelChooseActivity(lKcancelClassReqBO);
	}
	
	//活动管理小模块
	
	@RequestMapping("/gomanageactivity")
	public String goManageActivity(){
		return "lkmyactivity/manageactivity";
	}
	
	@ResponseBody
	@RequestMapping("/mypubactivitynamelist")
	public List<LKMyPubNameCombBoxBO> getMyPubNameCombList(LKGetChooseActivityListReqBO lKGetChooseActivityListReqBO){
		return lKMyActivityService.getMyPubNameCombList(lKGetChooseActivityListReqBO);
	}
	
	@ResponseBody
	@RequestMapping("/getmanageactivitylist")
	public PageInfoBo<LKGetManageActivityRspBO> getManageActivityList(LKGetManageActivityReqBO lKGetManageActivityReqBO){
		return lKMyActivityService.getManageActivityList(lKGetManageActivityReqBO);
	}
	
	@ResponseBody
	@RequestMapping("/moveactivitymember")
	public BaseInfoBO moveActivityMember(@RequestBody LKMoveActivityMemberReqBO lKMoveActivityMemberReqBO){
		return lKMyActivityService.moveActivityMember(lKMoveActivityMemberReqBO);
	}
	
}
