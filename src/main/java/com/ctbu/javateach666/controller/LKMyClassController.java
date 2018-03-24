package com.ctbu.javateach666.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.AlreadyChooseComboBoxBO;
import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.LKAlreadyChooseReqBO;
import com.ctbu.javateach666.pojo.bo.LKAlreadyChooseRspBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassOnlineListReqBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassOnlineListRspBO;
import com.ctbu.javateach666.pojo.bo.LKChooseClassReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoReqBO;
import com.ctbu.javateach666.pojo.bo.LKInitMyClassInfoRspBO;
import com.ctbu.javateach666.pojo.bo.LKcancelClassReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.service.interfac.LKMyClassService;

/**
 * 我的课程控制器类
 *
 * @author luokan
 */
@Controller
public class LKMyClassController {
	
	@Autowired
	private LKMyClassService lKMyClassService;
	
	//查看我的课程表小模块
	
	@RequestMapping("myownclassinfo")
	public String goMyOwnClassInfo(){
		return "lkmyclass/myownclassinfo";
	}
	
	@ResponseBody
	@RequestMapping("initmyclassinfo")
	public List<LKInitMyClassInfoRspBO> initMyClassInfo(){
		return lKMyClassService.initMyClassInfo();
	}
	
	//网上选课小模块
	
	@RequestMapping("chooseclassonline")
	public String goChooseClassOnline(){
		return "lkmyclass/chooseclassonline";
	}
	
	@ResponseBody
	@RequestMapping("getchooseclassonlinelist")
	public PageInfoBo<LKChooseClassOnlineListRspBO> getChooseClassOnlineList(LKChooseClassOnlineListReqBO lKChooseClassOnlineListReqBO){
		return lKMyClassService.getChooseClassOnlineList(lKChooseClassOnlineListReqBO);
	}
	
	@ResponseBody
	@RequestMapping("chooseclass")
	public BaseInfoBO ChooseClass(@RequestBody LKChooseClassReqBO lKChooseClassReqBO){
		return lKMyClassService.ChooseClass(lKChooseClassReqBO);
	}
	
	@RequestMapping("goalreadychoosepage")
	public String goAlreadyChoosePage(){
		return "lkmyclass/alreadychoose";
	}
	
	@ResponseBody
	@RequestMapping("goalreadychoose")
	public PageInfoBo<LKAlreadyChooseRspBO> goAlreadyChoose(LKAlreadyChooseReqBO lKAlreadyChooseReqBO){
		PageInfoBo<LKAlreadyChooseRspBO> rsp = lKMyClassService.goAlreadyChoose(lKAlreadyChooseReqBO);
		/*if(rsp.getRows().size() > 0){
			request.setAttribute("classyear", rsp.getRows().get(0).getClassyear());
			request.setAttribute("couyear", rsp.getRows().get(0).getCouyear());
			request.setAttribute("semester", rsp.getRows().get(0).getSemester());
		}*/
		return rsp;
	}
	
	@RequestMapping("myownchooseclassinfo")
	public String goMyOwnChooseClassInfo(LKInitMyClassInfoReqBO lKInitMyClassInfoReqBO ,HttpServletRequest request){
		request.setAttribute("couyear", lKInitMyClassInfoReqBO.getCouyear());
		request.setAttribute("semester", lKInitMyClassInfoReqBO.getSemester());
		return "lkmyclass/myownchooseclassinfo";
	}
	
	@ResponseBody
	@RequestMapping("alreadychoosecombobox")
	public List<AlreadyChooseComboBoxBO> getAlreadyChooseComboBox(){
		List<AlreadyChooseComboBoxBO> rsp = lKMyClassService.getAlreadyChooseComboBox();
		return rsp;
	}
	
	@ResponseBody
	@RequestMapping("getmyclassinfo")
	public List<LKInitMyClassInfoRspBO> getMyClassInfo(@RequestBody LKInitMyClassInfoReqBO lKInitMyClassInfoReqBO){
		return lKMyClassService.getMyClassInfo(lKInitMyClassInfoReqBO);
	}
	
	@ResponseBody
	@RequestMapping("cancelclass")
	public BaseInfoBO cancelClass(@RequestBody LKcancelClassReqBO lKcancelClassReqBO){
		return lKMyClassService.cancelClass(lKcancelClassReqBO);
	}
	
}
