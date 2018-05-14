package com.ctbu.javateach666.controller.thc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
import com.ctbu.javateach666.pojo.bo.thcbo.THCMaterialsRepBO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCourseIntroducePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCMaterialsPO;
import com.ctbu.javateach666.service.interfac.thc.THCMaterialsService;

@Controller
public class THCMaterialsController {
	
	@Autowired
	private THCMaterialsService tHCMaterialsService;
	
	/**
	 * 首页学习资源
	 */
	@ResponseBody
	@RequestMapping("/getmaterials")
	public List<THCMaterialsPO> getMaterials(){
		return tHCMaterialsService.getMaterials();
	}
	
	//后台学习资源管理
	@RequestMapping("/gomaterials")
	public String goMaterials(){
		return "thcadmin/indexinfo/materials";
	}
	
	/**
	 * 获取学习资源列表
	 */
	@ResponseBody
	@RequestMapping("/getmaterialslist")
	public PageInfoBo<THCMaterialsPO> getCourseIntroduceList(THCMaterialsRepBO tHCMaterialsRepBO){
		PageInfoBo<THCMaterialsPO> page = new PageInfoBo<THCMaterialsPO>();
		page = tHCMaterialsService.getMaterialsList(tHCMaterialsRepBO);
		return page;
	}
	
	/**
	 * 修改学习资源
	 * @param Object
	 */
	@ResponseBody
	@RequestMapping("/updatematerials")
	public String updateMaterials(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request){
		int m = tHCMaterialsService.updateMaterials(file, request);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 逻辑删除学习资源
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/delmaterials")
	public String delMaterials(HttpServletRequest request,HttpServletResponse response) {
		String ids = request.getParameter("materialsids");
		String[] idarr = ids.split(",");
		for(String id : idarr) {
			THCMaterialsPO tHCMaterialsPO = new THCMaterialsPO();
			tHCMaterialsPO.setId(Integer.valueOf(id));
			tHCMaterialsService.deleteByLogic(tHCMaterialsPO);
		}
		return "OK";
	}
	
	/**
	 * 添加学习资源
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/addmaterials")
	public String addMaterials(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request){
		int m = tHCMaterialsService.insertMaterials(file, request);
		if(m == 1){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 检查学习资源是否存在
	 * @param mtitle
	 */
	@ResponseBody
	@RequestMapping("/checkMtitle")
	public String checkMtitle(String mtitle){
		THCMaterialsPO tHCMaterialsPO = tHCMaterialsService.checkMtitle(mtitle);
		if(tHCMaterialsPO != null){
			return "OK";
		}else{
			return "NO";
		}
	}
	
	/**
	 * 定义下载的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/downloadMaterials")
	public String downloadMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return tHCMaterialsService.downloadMaterials(request, response);
	}
	
	
	
}
