package com.ctbu.javateach666.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.LKGetMyNoticeListReqBO;
import com.ctbu.javateach666.pojo.bo.LKGetMyNoticeListRspBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.service.interfac.LKMyNoticeService;

/**
 * 我的通知控制器类
 *
 * @author luokan
 */
@Controller
public class LKMyNoticeController {
	
	@Autowired
	private LKMyNoticeService lKMyNoticeService;
	
	@RequestMapping("mynoticelist")
	public String goMyNotice(){
		return "lkmynotice/mynoticelist";
	}
	
	@ResponseBody
	@RequestMapping("getmynoticelist")
	public PageInfoBo<LKGetMyNoticeListRspBO> getMyNoticeList(LKGetMyNoticeListReqBO lKGetMyNoticeListReqBO){
		return lKMyNoticeService.getMyNoticeList(lKGetMyNoticeListReqBO);
	}
}
