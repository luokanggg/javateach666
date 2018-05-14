package com.ctbu.javateach666.controller.thc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseIntroduceRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCourseIntroducePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCJournalismPO;
import com.ctbu.javateach666.service.interfac.thc.THCCourseIntroduceService;

@Controller
public class THCCourseIntroduceController {
	
	@Autowired
	private THCCourseIntroduceService tHCCourseIntroduceService;
	
	/**
	 * 首页显示课程介绍列表
	 */
	@ResponseBody
	@RequestMapping("/getcourseintroduce")
	public List<THCCourseIntroducePO> getCourseIntroduce(){
		return tHCCourseIntroduceService.getCourseIntroduce();
	}
	
	//后台管理
	@RequestMapping("/gocourseintroduce")
	public String goCourseIntroduce(){
		return "thcadmin/indexinfo/courseintroduce";
	}
	
	/**
	 * 获取课程介绍列表
	 */
	@ResponseBody
	@RequestMapping("/getcourseintroducelist")
	public PageInfoBo<THCCourseIntroducePO> getCourseIntroduceList(THCCourseIntroduceRepBO tHCCourseIntroduceRepBO){
		PageInfoBo<THCCourseIntroducePO> page = new PageInfoBo<THCCourseIntroducePO>();
		page = tHCCourseIntroduceService.getCourseIntroduceList(tHCCourseIntroduceRepBO);
		return page;
	}
	
	/**
	 * 修改课程介绍
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/updatecourseintroduce")
	public String updateCourseIntroduce(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request){
		int m = tHCCourseIntroduceService.updateCourseIntroduce(file, request);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 逻辑删除课程介绍
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/delcourseintroduces")
	public String delCourseIntroduce(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("courseintroducesids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			THCCourseIntroducePO tHCCourseIntroducePO = new THCCourseIntroducePO();
			tHCCourseIntroducePO.setId(Integer.valueOf(id));
			tHCCourseIntroduceService.deleteByLogic(tHCCourseIntroducePO);
		}
		return "OK";
	}
	
	/**
	 * 添加课程介绍
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/addcourseintroduce")
	public String addIndexImg(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request){
		int m = tHCCourseIntroduceService.insertCourseIntroduce(file, request);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 检查课程介绍是否存在
	 * @param cname
	 */
	@ResponseBody
	@RequestMapping("/checkCname")
	public String checkCname(String cname){
		THCCourseIntroducePO tHCCourseIntroducePO = tHCCourseIntroduceService.checkCname(cname);
		if(tHCCourseIntroducePO != null){
			return "OK";
		}else{
			return "NO";
		}
	}
}
