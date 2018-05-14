package com.ctbu.javateach666.controller.thc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRepBO;
import com.ctbu.javateach666.pojo.bo.thcbo.THCDictionariesListRspBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCourseIntroducePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCDictionariesPO;
import com.ctbu.javateach666.pojo.po.thcpo.THCJournalismPO;
import com.ctbu.javateach666.service.interfac.thc.THCDicService;

@Controller
public class THCDicController {
	
	@Autowired
	private THCDicService tHCDicService;
	
	//数据字典管理
	@RequestMapping("/godiclist")
	public String goDic(){
		return "thcadmin/dictionaries/diclist";
	}
	
	/**
	 * 查询字典列表
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/getdiclist")
	public PageInfoBo<THCDictionariesListRspBO> getDicList(THCDictionariesListRepBO tHCDictionariesListRepBO){
		System.out.println("getdiclist");
		PageInfoBo<THCDictionariesListRspBO> page = new PageInfoBo<THCDictionariesListRspBO>();
		page = tHCDicService.getDicList(tHCDictionariesListRepBO);
		return page;
	}
	
	/**
	 * 下拉框获取字典类型
	 */
	@ResponseBody
	@RequestMapping("/getDicNameList")
	public List<THCDictionariesListRspBO> getDicNameList(){
		List<THCDictionariesListRspBO> list = tHCDicService.getDicNameList();
		System.out.println(list);
		return list;
	}
	
	/**
	 * 逻辑删除字典
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/deldics")
	public String delNews(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("dicsids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			THCDictionariesPO tHCDictionariesPO = new THCDictionariesPO();
			tHCDictionariesPO.setId(Integer.valueOf(id));
			tHCDicService.deleteByLogic(tHCDictionariesPO);
		}
		return "OK";
	}
	
	/**
	 * 添加字典
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/adddic")
	public String addNews(THCDictionariesPO tHCDictionariesPO){
		int m = tHCDicService.insert(tHCDictionariesPO);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 修改字典
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/updatedic")
	public String updateDic(THCDictionariesPO tHCDictionariesPO){
		int m = tHCDicService.update(tHCDictionariesPO);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 检查字典名称是否存在
	 * @param dicname
	 */
	@ResponseBody
	@RequestMapping("/checkDicname")
	public String checkDicname(String dicname){
		THCDictionariesPO tHCDictionariesPO = tHCDicService.checkDicname(dicname);
		if(tHCDictionariesPO != null){
			return "OK";
		}else{
			return "NO";
		}
	}
}
