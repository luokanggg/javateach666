package com.ctbu.javateach666.controller.thc;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRspBO;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAccountPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAuthoritiesPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCClassPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCDictionariesPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCJournalismPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCStudentInfoPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCTeachersInfoPO;
import com.ctbu.javateach666.service.interfac.thc.THCStudentService;
import com.ctbu.javateach666.util.BCryptEncoderUtil;

@Controller
public class THCStudentController {
	
	@Autowired
	private THCStudentService tHCStudentService;
	
	//学生信息管理
	@RequestMapping("/gostuaccount")
	public String goStuAccount(){
		return "thcadmin/stuinfo/stuaccount";
	}
	
	/**
	 * 获取学生账户列表
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/getstulist")
	public PageInfoBo<THCAccountListRspBO> getStuList(THCAccountListRepBO tHCAccountListRepBO){
		System.out.println("getstulist");
		PageInfoBo<THCAccountListRspBO> page = new PageInfoBo<THCAccountListRspBO>();
		page = tHCStudentService.getStuList(tHCAccountListRepBO);
		return page;
	}
	
	/**
	 * 添加学生账户
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/addstu")
	public String addStu(THCAccountPO tHCAccountPO, THCStudentInfoPO tHCStudentInfoPO){
		int a = tHCStudentService.getClassid(tHCStudentInfoPO.getClassname());
		tHCStudentInfoPO.setClassid(a);
		int n = tHCStudentService.insertstu(tHCStudentInfoPO);
		if(n == 1){
			THCStudentInfoPO tHCStudentInfoPO1 = new THCStudentInfoPO();
			tHCStudentInfoPO1 = tHCStudentService.selectIdbyStuno(tHCStudentInfoPO);
			tHCAccountPO.setUserdetailid(tHCStudentInfoPO1.getId());
			String password = BCryptEncoderUtil.passwordEncoder(tHCAccountPO.getPassword());
			tHCAccountPO.setPassword(password);
			int m = tHCStudentService.insert(tHCAccountPO);
			THCAuthoritiesPO tHCAuthoritiesPO = new THCAuthoritiesPO();
			tHCAuthoritiesPO.setUsername(tHCAccountPO.getUsername());
			tHCAuthoritiesPO.setAuthorities("STUDENT");
			int x = tHCStudentService.insertauth(tHCAuthoritiesPO);
			if(m == 1 && x == 1){
				return "OK";
			}else{
				return "NO";
			}
		}else{
			return "NO";
		}
	}
	
	/**
	 * 下拉框显示性别列表
	 * @param String
	 */
	@ResponseBody
	@RequestMapping("/getSexList")
	public List<THCDictionariesListRspBO> getSexList(@RequestParam("dtype") String dtype){
		String dtype1 = null;
		try {
			dtype1=new String(dtype.getBytes("ISO-8859-1"),"utf-8");
			//lKMyClassInfoListRepBO.setStuname(stuname);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<THCDictionariesListRspBO> list = tHCStudentService.getSelectList(dtype1);
		System.out.println("性别"+list);
		return list;
	}
	
	/**
	 * 下拉框显示政治面貌列表
	 * @param String
	 */
	@ResponseBody
	@RequestMapping("/getPoliticalList")
	public List<THCDictionariesListRspBO> getPoliticalList(@RequestParam("dtype") String dtype){
		String dtype1 = null;
		try {
			dtype1=new String(dtype.getBytes("ISO-8859-1"),"utf-8");
			//lKMyClassInfoListRepBO.setStuname(stuname);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(dtype1);
		List<THCDictionariesListRspBO> list = tHCStudentService.getSelectList(dtype1);
		System.out.println("政治面貌"+list);
		return list;
	}
	
	/**
	 * 下拉框显示学院列表
	 * @param String
	 */
	@ResponseBody
	@RequestMapping("/getCollegeList")
	public List<THCClassPO> getCollegeList(){
		List<THCClassPO> list = tHCStudentService.getCollegeList();
		System.out.println("学院"+list);
		return list;
	}
	
	/**
	 * 下拉框显示专业列表
	 * @param String
	 */
	@ResponseBody
	@RequestMapping("/getMajorList")
	public List<THCClassPO> getMajorList(@RequestParam("college") String college){
		String college1 = null;
		try {
			college1=new String(college.getBytes("ISO-8859-1"),"utf-8");
			//lKMyClassInfoListRepBO.setStuname(stuname);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<THCClassPO> list = tHCStudentService.getMajorList(college1);
		System.out.println("专业"+list);
		return list;
	}
	
	/**
	 * 下拉框显示班级名称列表
	 * @param String
	 */
	@ResponseBody
	@RequestMapping("/getClassnameList")
	public List<THCClassPO> getClassnameList(@RequestParam("major") String major){
		String major1 = null;
		try {
			major1=new String(major.getBytes("ISO-8859-1"),"utf-8");
			//lKMyClassInfoListRepBO.setStuname(stuname);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<THCClassPO> list = tHCStudentService.getClassnameList(major1);
		System.out.println("班级"+list);
		return list;
	}
	
	/**
	 * 逻辑删除学生账号
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/delstuaccounts")
	public String delStus(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("accountids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			THCAccountPO tHCAccountPO = new THCAccountPO();
			tHCAccountPO.setId(Integer.valueOf(id));
			tHCStudentService.deleteByLogic(tHCAccountPO);
			
			tHCAccountPO = tHCStudentService.selectById(tHCAccountPO);
			THCStudentInfoPO tHCStudentInfoPO = new THCStudentInfoPO();
			tHCStudentInfoPO.setId(tHCAccountPO.getUserdetailid());
			tHCStudentService.deleteByLogicStu(tHCStudentInfoPO);
			
			THCAuthoritiesPO tHCAuthoritiesPO = new THCAuthoritiesPO();
			tHCAuthoritiesPO.setUsername(tHCAccountPO.getUsername());
			tHCStudentService.deleteByLogicAuth(tHCAuthoritiesPO);
		}
		return "OK";
	}
	
	/**
	 * 修改账户信息
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/updatestu")
	public String updateStu(THCAccountPO tHCAccountPO, THCStudentInfoPO tHCStudentInfoPO){
		String password = BCryptEncoderUtil.passwordEncoder(tHCAccountPO.getPassword());
		tHCAccountPO.setPassword(password);
		int m = tHCStudentService.update(tHCAccountPO);
		int n = tHCStudentService.updateStu(tHCStudentInfoPO);
		if(m == 1 && n == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 检查学生学号是否存在
	 * @param stuno
	 */
	@ResponseBody
	@RequestMapping("/checkStuno")
	public String checkStuno(String stuno){
		THCStudentInfoPO tHCStudentInfoPO = tHCStudentService.checkStuno(stuno);
		if(tHCStudentInfoPO != null){
			return "OK";
		}else{
			return "NO";
		}
	}
}
