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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCJournalismListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCJournalismListRspBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCJournalismRepBO;
import com.ctbu.javateach666.pojo.po.questions.SingleChoice;
import com.ctbu.javateach666.pojo.po.thcpo.THCIndexImgPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCJournalismPO;
import com.ctbu.javateach666.service.interfac.thc.THCNewsService;

@Controller
public class THCNewsController {
	
	@Autowired
	private THCNewsService tHCNewsService;
	
	//新闻管理模块
	@RequestMapping("/gonewsinfo")
	public String goNewsInfo(){
		return "thcadmin/indexinfo/newsinfo";
	}
	
	/**
	 * 查询新闻列表
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/getnewslist")
	public PageInfoBo<THCJournalismListRspBO> getNewsList(THCJournalismListRepBO tHCJournalismListRepBO){
		System.out.println("getNewsList");
		PageInfoBo<THCJournalismListRspBO> page = new PageInfoBo<THCJournalismListRspBO>();
		tHCJournalismListRepBO.setJ_type(1);//1-新闻  2-公告
		tHCJournalismListRepBO.setDtype("新闻类型");
		page = tHCNewsService.getJourList(tHCJournalismListRepBO);
		return page;
	}
	
	@RequestMapping("/gopubinfo")
	public String goNoticeInfo(){
		return "thcadmin/indexinfo/pubinfo";
	}
	
	/**
	 * 查询公告列表
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/getpublist")
	public PageInfoBo<THCJournalismListRspBO> getPubList(THCJournalismListRepBO tHCJournalismListRepBO){
		System.out.println("getPubList");
		PageInfoBo<THCJournalismListRspBO> page = new PageInfoBo<THCJournalismListRspBO>();
		tHCJournalismListRepBO.setJ_type(2);//1-新闻  2-公告
		tHCJournalismListRepBO.setDtype("公告类型");
		page = tHCNewsService.getJourList(tHCJournalismListRepBO);
		return page;
	}
	
	/**
	 * 下拉框显示新闻类型
	 * @param String
	 */
	@ResponseBody
	@RequestMapping("/getJoutypeList")
	public List<THCDictionariesListRspBO> getJoutypeList(@RequestParam("dtype") String dtype){
		List<THCDictionariesListRspBO> list = tHCNewsService.getJoutypeList(dtype);
		System.out.println(list);
		return list;
	}
	
	/**
	 * 添加新闻
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/addnews")
	public String addNews(THCJournalismPO tHCJournalismPO){
		Date sTime = new Date();
		Date eTime = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sTime);
		calendar.add(calendar.MONTH, 1); 
		eTime = calendar.getTime(); 
		tHCJournalismPO.setStarttime(sTime);
		tHCJournalismPO.setEndtime(eTime);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int id = tHCNewsService.getAdminId(userDetails.getUsername());
		tHCJournalismPO.setPubid(id);
		tHCJournalismPO.setJ_type(1);
		int m = tHCNewsService.insert(tHCJournalismPO);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 修改新闻
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/updatenews")
	public String updateNews(THCJournalismPO tHCJournalismPO){
		int m = tHCNewsService.update(tHCJournalismPO);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 逻辑删除新闻
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/delnews")
	public String delNews(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("newsids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			THCJournalismPO tHCJournalismPO = new THCJournalismPO();
			tHCJournalismPO.setId(Integer.valueOf(id));
			tHCNewsService.deleteByLogic(tHCJournalismPO);
		}
		return "OK";
	}
	
	/**
	 * 下拉框获取公告类型
	 * @param String
	 */
	@ResponseBody
	@RequestMapping("/getJoutypeList1")
	public List<THCDictionariesListRspBO> getJoutypeList1(@RequestParam("dtype")String dtype){
		List<THCDictionariesListRspBO> list = tHCNewsService.getJoutypeList(dtype);
		System.out.println(list);
		return list;
	}
	
	/**
	 * 添加公告
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/addpubs")
	public String addPubs(THCJournalismPO tHCJournalismPO){
		Date sTime = new Date();
		Date eTime = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sTime);
		calendar.add(calendar.MONTH, 1); 
		eTime = calendar.getTime(); 
		tHCJournalismPO.setStarttime(sTime);
		tHCJournalismPO.setEndtime(eTime);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int id = tHCNewsService.getAdminId(userDetails.getUsername());
		tHCJournalismPO.setPubid(id);
		tHCJournalismPO.setJ_type(2);
		int m = tHCNewsService.insert(tHCJournalismPO);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 逻辑删除公告
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/delpubs")
	public String delPubs(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("pubsids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			THCJournalismPO tHCJournalismPO = new THCJournalismPO();
			tHCJournalismPO.setId(Integer.valueOf(id));
			tHCNewsService.deleteByLogic(tHCJournalismPO);
		}
		return "OK";
	}
	
	/**
	 * 修改公告
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/updatepubs")
	public String updatePubs(THCJournalismPO tHCJournalismPO){
		int m = tHCNewsService.update(tHCJournalismPO);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 首页显示新闻列表
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/getlist")
	public List<THCJournalismPO> getList(int j_type){
		return tHCNewsService.getList(j_type);
	}
	
	/**
	 * 检查新闻/公告标题是否存在
	 * @param title
	 */
	@ResponseBody
	@RequestMapping("/checkTitle")
	public String checkTitle(String title){
		THCJournalismPO tHCJournalismPO = tHCNewsService.checkTitle(title);
		if(tHCJournalismPO != null){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 新闻/公告详情
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/jouDetail")
	public THCJournalismPO getJouDetail(int id){
		return tHCNewsService.getJouDetail(id);
	}
}
