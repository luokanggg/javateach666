package com.ctbu.javateach666.controller.thc;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCCourseListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCourseIntroducePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCDictionariesPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCJournalismPO;
import com.ctbu.javateach666.service.interfac.thc.THCCourseService;

@Controller
public class THCCourseController {
	
	@Autowired
	private THCCourseService tHCCourseService;
	
	//学科信息管理
	@RequestMapping("/gocourseinfo")
	public String goCourseInfo(){
		return "thcadmin/courseinfo/courseinfo";
	}
	
	/**
	 * 查询课程列表
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/getcourselist")
	public PageInfoBo<THCCourseListRspBO> getCourseList(THCCourseListRepBO tHCCourseListRepBO){
		System.out.println("getcourselist");
		PageInfoBo<THCCourseListRspBO> page = new PageInfoBo<THCCourseListRspBO>();
		page = tHCCourseService.getCourseList(tHCCourseListRepBO);
		return page;
	}
	
	/**
	 * 获取课程类型
	 */
	@ResponseBody
	@RequestMapping("/getCtype")
	public List<THCDictionariesPO> getCtypeList(){
		List<THCDictionariesPO> list = tHCCourseService.getCtypeList();
		System.out.println(list);
		return list;
	}
	
	/**
	 * 逻辑删除课程
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/delcourses")
	public String delNews(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("courseids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			THCCoursePO tHCCoursePO = new THCCoursePO();
			tHCCoursePO.setId(Integer.valueOf(id));
			tHCCourseService.deleteByLogic(tHCCoursePO);
		}
		return "OK";
	}
	
	/**
	 * 添加课程
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/addcourse")
	public String addNews(THCCoursePO tHCCoursePO){
		int m = tHCCourseService.insert(tHCCoursePO);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 修改课程
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/updatecourse")
	public String updateNews(THCCoursePO tHCCoursePO){
		int m = tHCCourseService.update(tHCCoursePO);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 检查课程是否存在
	 * @param cname
	 */
	@ResponseBody
	@RequestMapping("/checkCoursename")
	public String checkCoursename(String couname){
		THCCoursePO tHCCoursePO = tHCCourseService.checkCoursename(couname);
		if(tHCCoursePO != null){
			return "OK";
		}else{
			return "NO";
		}
	}
}
