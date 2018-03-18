package com.ctbu.javateach666.controller;



import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.pojo.bo.LKUpdateStuInfoBO;
import com.ctbu.javateach666.pojo.bo.BaseInfoBO;
import com.ctbu.javateach666.pojo.bo.DeleteMyFileReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRepBO;
import com.ctbu.javateach666.pojo.bo.LKMyClassInfoListRspBO;
import com.ctbu.javateach666.pojo.bo.LKMyFileListReqBO;
import com.ctbu.javateach666.pojo.bo.LKMyFileListRspBO;
import com.ctbu.javateach666.pojo.bo.LKSendMessageToStuReqBO;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.po.LKStudentInfoPO;
import com.ctbu.javateach666.service.interfac.LKMyInfoService;
/**
 * 我的信息控制器类
 *
 * @author luokan
 */
@Controller
public class LKMyInfoController {
	
	@Autowired
	private LKMyInfoService lKMyInfoService;
	
	//个人信息小模块
	
	@RequestMapping("/stuowninfo")
	public String goStuOwnInfo(){
		return "lkmyinfo/stuowninfo";
	}
	
	@ResponseBody
	@RequestMapping("/initstuinfo")
	public LKStudentInfoPO initStuInfo(){
		System.out.println("initStuInfo");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getUsername());
		return lKMyInfoService.initStuInfo(userDetails.getUsername());
	}
	
	@ResponseBody
	@RequestMapping("/updatestuinfo")
	public LKUpdateStuInfoBO updateStuInfo(@RequestBody LKStudentInfoPO stu/*, @RequestParam("file") CommonsMultipartFile file*/){
		System.out.println("updateStuInfo" + stu );	
		return lKMyInfoService.updateStuInfo(stu.getStuno(), stu.getStuphone());
	}
	
	@RequestMapping("/updatestuinfoimg")
	public String updateStuInfoImg(/*String file*/@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		lKMyInfoService.updateStuInfoImg(userDetails.getUsername(), file, request);
		return "lkmyinfo/stuowninfo";
	}
	
	//我的班级小模块
	
	@RequestMapping("/myclassinfo")
	public String goMyClassInfo(){
		return "lkmyinfo/myclassinfo";
	}
	
	@ResponseBody
	@RequestMapping("/getmyclassinfolist")
	public PageInfoBo<LKMyClassInfoListRspBO> getMyClassInfoList(LKMyClassInfoListRepBO lKMyClassInfoListRepBO){
		PageInfoBo<LKMyClassInfoListRspBO> page = new PageInfoBo<LKMyClassInfoListRspBO>();
		page = lKMyInfoService.getMyClassInfoList(lKMyClassInfoListRepBO);
		return page;
	}
	
	//@ResponseBody
	@RequestMapping("/exportclassinfo")
	public String ExportClassInfo(LKMyClassInfoListRepBO lKMyClassInfoListRepBO, HttpServletResponse response){
		String stuname = null;
		try {
			stuname=new String(lKMyClassInfoListRepBO.getStuname().getBytes("ISO-8859-1"),"utf-8");
			lKMyClassInfoListRepBO.setStuname(stuname);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		//System.out.println(lKMyClassInfoListRepBO.getStuno());
		//System.out.println(lKMyClassInfoListRepBO.getStuname());
		//return "lkmyinfo/myclassinfo";
		
		List<LKMyClassInfoListRspBO> list = lKMyInfoService.getMyClassInfoBySearch(lKMyClassInfoListRepBO);
		//System.out.println(list.toString());
		
		//创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("成绩表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("我的班级信息");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,5));
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(2);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("学号");
        row2.createCell(1).setCellValue("学生姓名");
        row2.createCell(2).setCellValue("班级名");
        row2.createCell(3).setCellValue("学院");
        row2.createCell(4).setCellValue("专业");
        row2.createCell(5).setCellValue("年级");
        //循环输出班级学生信息
        for (int i = 0; i< list.size(); i++) {
        	HSSFRow rows = sheet.createRow(i+3);
        	rows.createCell(0).setCellValue(list.get(i).getStuno());
        	rows.createCell(1).setCellValue(list.get(i).getStuname());
        	rows.createCell(2).setCellValue(list.get(i).getClaname());
        	rows.createCell(3).setCellValue(list.get(i).getCollege());
        	rows.createCell(4).setCellValue(list.get(i).getMajor());
        	rows.createCell(5).setCellValue(list.get(i).getClassyear());
		}


        //输出Excel文件
        OutputStream output = null;
		try {
			output = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=myclassinfo.xls");
        response.setContentType("application/msexcel");
        try {
			wb.write(output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return "lkmyinfo/stuowninfo";
	}
	
	@ResponseBody
	@RequestMapping("/sendmessagetostu")
	public BaseInfoBO sendMessageToStu(@RequestBody LKSendMessageToStuReqBO lKSendMessageToStuReqBO){
		//System.out.println("学号：" + lKSendMessageToStuReqBO.getStuno() + "消息" + lKSendMessageToStuReqBO.getMessage());
		BaseInfoBO rsp = lKMyInfoService.sendMessageToStu(lKSendMessageToStuReqBO);
		
		return rsp;
	}
	
	//我的文档小模块
	
	@RequestMapping("/myfile")
	public String goMyFile(){
		return "lkmyinfo/myfile";
	}
	
	@ResponseBody
	@RequestMapping("/getmyfilelist")
	public PageInfoBo<LKMyFileListRspBO> getMyFileList(LKMyFileListReqBO lKMyFileListReqBO){
		PageInfoBo<LKMyFileListRspBO> page = new PageInfoBo<LKMyFileListRspBO>();
		/*SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		try {
			Date d1 = sdf.parse(lKMyFileListReqBO.getBeforeuploadtime());
			Date d2 = sdf.parse(lKMyFileListReqBO.getAfteruploadtime());
			System.out.println(d1 + " " + d2);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		page = lKMyInfoService.getMyFileList(lKMyFileListReqBO);
		return page;
	}
	
	@ResponseBody
	@RequestMapping("deletemyfile")
	public BaseInfoBO deleteMyFile(@RequestBody DeleteMyFileReqBO deleteMyFileReqBO, HttpServletRequest request){
		//lKMyInfoService.deleteMyFile(deleteMyFileReqBO, request);
		return lKMyInfoService.deleteMyFile(deleteMyFileReqBO, request);
	}
	
	@RequestMapping("uploadmyfile")
	public String uploadMyFile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean issuccess = lKMyInfoService.uploadMyFile(userDetails.getUsername(), file, request);
		return "lkmyinfo/myfile";
	}
	
	
}
