package com.ctbu.javateach666.controller.thc;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCAccountListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCProfessionalRanksListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCProfessionalRanksListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCTeaPostListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCTeaPostListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAccountPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCAuthoritiesPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCJournalismPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCProfessionalRanksPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCStudentInfoPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCTeachersInfoPO;
import com.ctbu.javateach666.service.interfac.thc.THCTeacherService;
import com.ctbu.javateach666.util.BCryptEncoderUtil;

@Controller
public class THCTeacherController {
	
	@Autowired
	private THCTeacherService tHCTeacherService;
	
	//师资力量管理
	@RequestMapping("/goteacherintroduce")
	public String goTeacherIntroduce(){
		return "thcadmin/indexinfo/teacherintroduce";
	}
	
	/**
	 * 修改教师介绍
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/updateteacherintroduce")
	public String addIndexImg(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request){
		int m = tHCTeacherService.updateTeacherIntroduce(file, request);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	//教师信息管理
	@RequestMapping("/goteaaccount")
	public String goTeaAccount(){
		return "thcadmin/teainfo/teaaccount";
	}
	
	@ResponseBody
	@RequestMapping("/gettealist")
	public PageInfoBo<THCAccountListRspBO> getTeaList(THCAccountListRepBO tHCAccountListRepBO){
		System.out.println("gettealist");
		PageInfoBo<THCAccountListRspBO> page = new PageInfoBo<THCAccountListRspBO>();
		page = tHCTeacherService.getTeaList(tHCAccountListRepBO);
		return page;
	}
	
	@RequestMapping("/goteapost")
	public String goTeaPost(){
		return "thcadmin/teainfo/teapost";
	}
	
	@ResponseBody
	@RequestMapping("/getteapostlist")
	public PageInfoBo<THCTeaPostListRspBO> getTeaPostList(THCTeaPostListRepBO tHCTeaPostListRepBO){
		System.out.println("gettealist");
		PageInfoBo<THCTeaPostListRspBO> page = new PageInfoBo<THCTeaPostListRspBO>();
		page = tHCTeacherService.getTeaPostList(tHCTeaPostListRepBO);
		return page;
	}
	
	@RequestMapping("/gopostrecord")
	public String goPostRecord(){
		return "thcadmin/teainfo/postapplyrecord";
	}
	
	@ResponseBody
	@RequestMapping("/getpostrecordlist")
	public PageInfoBo<THCProfessionalRanksListRspBO> getPostRecordList(THCProfessionalRanksListRepBO tHCProfessionalRanksListRepBO){
		System.out.println("getpostrecordlist");
		PageInfoBo<THCProfessionalRanksListRspBO> page = new PageInfoBo<THCProfessionalRanksListRspBO>();
		page = tHCTeacherService.getPostRecordList(tHCProfessionalRanksListRepBO);
		return page;
	}
	
	/**
	 * 添加教师账户
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/addtea")
	public String addTea(THCAccountPO tHCAccountPO, THCTeachersInfoPO tHCTeachersInfoPO){
		int n = tHCTeacherService.inserttea(tHCTeachersInfoPO);
		if(n == 1){
			THCTeachersInfoPO tHCTeachersInfoPO1 = new THCTeachersInfoPO();
			tHCTeachersInfoPO1 = tHCTeacherService.selectIdbyTeano(tHCTeachersInfoPO);
			tHCAccountPO.setUserdetailid(tHCTeachersInfoPO1.getId());
			String password = BCryptEncoderUtil.passwordEncoder(tHCAccountPO.getPassword());
			tHCAccountPO.setPassword(password);
			int m = tHCTeacherService.insert(tHCAccountPO);
			THCAuthoritiesPO tHCAuthoritiesPO = new THCAuthoritiesPO();
			tHCAuthoritiesPO.setUsername(tHCAccountPO.getUsername());
			tHCAuthoritiesPO.setAuthorities("TEACHER");
			int x = tHCTeacherService.insertauth(tHCAuthoritiesPO);
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
	 * 修改教师职位
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/updateteapost")
	public String updateTeapost(THCTeachersInfoPO tHCTeachersInfoPO){
		int m = tHCTeacherService.updateTeaPost(tHCTeachersInfoPO);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 职位申请审批
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/updatepostrecord")
	public String updatePostRecord(THCProfessionalRanksPO tHCProfessionalRanksPO){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		tHCProfessionalRanksPO.setApprove_time(sdf.format(now));
		int m = tHCTeacherService.updatePostRecord(tHCProfessionalRanksPO);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 逻辑删除教师账号
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/delteaaccounts")
	public String delTeas(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("accountids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			THCAccountPO tHCAccountPO = new THCAccountPO();
			tHCAccountPO.setId(Integer.valueOf(id));
			tHCTeacherService.deleteByLogic(tHCAccountPO);
			
			tHCAccountPO = tHCTeacherService.selectById(tHCAccountPO);
			THCTeachersInfoPO tHCTeachersInfoPO = new THCTeachersInfoPO();
			tHCTeachersInfoPO.setId(tHCAccountPO.getUserdetailid());
			tHCTeacherService.deleteByLogicTea(tHCTeachersInfoPO);
			
			THCAuthoritiesPO tHCAuthoritiesPO = new THCAuthoritiesPO();
			tHCAuthoritiesPO.setUsername(tHCAccountPO.getUsername());
			tHCTeacherService.deleteByLogicAuth(tHCAuthoritiesPO);
		}
		return "OK";
	}
	
	/**
	 * 修改账户信息
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/updatetea")
	public String updateTea(THCAccountPO tHCAccountPO, THCTeachersInfoPO tHCTeachersInfoPO){
		String password = BCryptEncoderUtil.passwordEncoder(tHCAccountPO.getPassword());
		tHCAccountPO.setPassword(password);
		int m = tHCTeacherService.update(tHCAccountPO);
		int n = tHCTeacherService.updateTea(tHCTeachersInfoPO);
		if(m == 1 && n == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 首页展示师资力量
	 */
	@ResponseBody
	@RequestMapping("/getteacherintroduce")
	public List<THCTeachersInfoPO> getTeacherIntroduce(){
		return tHCTeacherService.getTeacherIntroduce();
	}
	
	/**
	 * 检查用户名是否存在
	 * @param username
	 */
	@ResponseBody
	@RequestMapping("/checkUsername")
	public String checkUsername(String username){
		THCAccountPO tHCAccountPO = tHCTeacherService.checkUsername(username);
		if(tHCAccountPO != null){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 检查教师编号是否存在
	 * @param teano
	 */
	@ResponseBody
	@RequestMapping("/checkTeano")
	public String checkTeano(String teano){
		THCTeachersInfoPO tHCTeachersInfoPO = tHCTeacherService.checkTeano(teano);
		if(tHCTeachersInfoPO != null){
			return "OK";
		}else{
			return "NO";
		}
	}
}
