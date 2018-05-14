package com.ctbu.javateach666.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.StuTeaCourseBo_zxy;
import com.ctbu.javateach666.pojo.bo.TeaCourseReqBo;
import com.ctbu.javateach666.pojo.bo.TeacourseBo_zxy;
import com.ctbu.javateach666.pojo.po.Course_zxy;
import com.ctbu.javateach666.pojo.po.Dictionaries_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.pojo.po.TeacoursePo_zxy;
import com.ctbu.javateach666.service.interfac.PublishCourseServise_zxy;
import com.ctbu.javateach666.service.interfac.TeacherInfoService_zxy;

@Controller
public class ZXYPublishCourseController {
	
	@Autowired
	PublishCourseServise_zxy publishcourse;
	@Autowired
	TeacherInfoService_zxy teacherservise;
	
	@RequestMapping("publishcourse")
	public String gopublishcourse(HttpServletRequest request){
		//获取所有的课程信息
				HttpSession session=request.getSession();
				List<Course_zxy> course=publishcourse.getCourseList();
				session.setAttribute("course", course);
				
				//获取所有的教师信息
				List<TeacherInfo_zxy> teacher=publishcourse.getAllTeacher();
				session.setAttribute("teas",teacher);
				
				String dtype="3";
				//获取所有的学年信息根据字典表
				List<Dictionaries_zxy> semester=publishcourse.getDictionariesByType(dtype);
				session.setAttribute("semester", semester);
		return "teacherzxy/teaclass/publishcouse";
	}
	@ResponseBody
	@RequestMapping("publishcouselist")
	public PageInfoBo<TeacoursePo_zxy> gopublishcouselist(HttpServletRequest request,TeaCourseReqBo teacourse){
		PageInfoBo<TeacoursePo_zxy> page=new PageInfoBo<TeacoursePo_zxy>();
		//System.out.println(teacourse.getPage()+"  "+teacourse.getRows());
		page=publishcourse.getTeaCourseByPage(teacourse);
		List<TeacoursePo_zxy> a=page.getRows();
		
		return page;
	}
	@ResponseBody
	@RequestMapping("maketeacourse")
	public void goMakeTeacourse(HttpServletResponse response,String teano,String couyear,String semester,String couseid,String couaddress,String counumber){
		//System.out.println("============="+teano+"  "+couyear+"  "+semester+"  "+couseid+"  "+couaddress+"  "+counumber);
		response.setCharacterEncoding("UTF-8");
		int semester1=Integer.parseInt(semester);//学期
		int id=Integer.parseInt(couseid);//课程id
		int counum=Integer.parseInt(counumber);//课程容量
		int couyear1=Integer.parseInt(couyear);
		int alreadnum=0;//已选人数
		TeacourseBo_zxy teacouBo=new TeacourseBo_zxy();
		TeacherInfo_zxy teacher=teacherservise.getTeacherInfoByTeano(teano);
		int teaid=teacher.getId();
		teacouBo.setTeaid(teaid);
		teacouBo.setCouyear(couyear1);
		teacouBo.setCouaddress(couaddress);
		teacouBo.setSemester(semester1);
		teacouBo.setCounumber(counum);
		teacouBo.setAlcounumber(alreadnum);
		Course_zxy course=publishcourse.getCourseById(id);
		String coursename=course.getCouname();
		teacouBo.setCouname(coursename);
		
		//先判断分配的教师课程信息是否存在
		int tag=0;
		tag=publishcourse.Is_HaveTeaCou(teacouBo);
		if(tag>0){
			try {
				response.getWriter().print("<script> alert('教师课程信息插入失败,该课程信息已存在') </script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				publishcourse.insertTeaCourse(teacouBo);
				
				response.getWriter().print("<script> alert('教师课程信息插入成功！') </script>");
				
			} catch (Exception e) {
				
				try {
					response.getWriter().print("<script> alert('教师课程信息插入失败！') </script>");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	
	}
	
	@ResponseBody
	@RequestMapping("makeclasstime")
	public Map gomakeclasstime(HttpServletResponse response,int id,String teaname,String couname,int couyear,int semester,String couaddress,int counumber,int alcounumber,int couhour,int coufhour,int coutime) throws UnsupportedEncodingException{
		response.setCharacterEncoding("UTF-8");
		Map map=new HashMap<String,String>();
		TeacourseBo_zxy teacouBo=new TeacourseBo_zxy();
		teacouBo.setTeaname(teaname);
		teacouBo.setCouname(couname);
		teacouBo.setId(id);
		teacouBo.setCouyear(couyear);
		teacouBo.setSemester(semester);
		teacouBo.setCouaddress(couaddress);
		teacouBo.setCouhour(couhour);
		teacouBo.setCoutime(coutime);
		teacouBo.setCoufhour(coufhour);
		teacouBo.setCounumber(counumber);
		teacouBo.setAlcounumber(alcounumber);
		//根据教师的姓名查询教师的id
		int teadetailid=publishcourse.getTeadeail_idByTeaname(teaname);
		teacouBo.setTeaid(teadetailid);

		List<TeacourseBo_zxy> sametimeteacou=new ArrayList<TeacourseBo_zxy>();
		sametimeteacou=publishcourse.getSameTimeTeaCou(teacouBo);
		int firstcou=0;
		if(sametimeteacou.size()==0){
			//更新传入参数id的教师课程表的时间信息
			int update=publishcourse.UpdateTeaCou(teacouBo);
			if(update==1){
				map.put("mess","教师课程信息设置成功！");
				return map;
			}else{
				map.put("mess","教师课程信息设置失败！");
				return map;
			}
		}else{
			for (int i = 0; i < sametimeteacou.size(); i++) {
				if(coutime==sametimeteacou.get(i).getCoutime()){
					if(coufhour==sametimeteacou.get(i).getCoufhour()){
						firstcou=1;	
					}
				}
				
			}
			if(firstcou==1){
				map.put("mess","教师课程信息的时间冲突，课程信息设置失败！");
				return map;
			}else{
				int update=publishcourse.UpdateTeaCou(teacouBo);
				if(update==1){
					map.put("mess","教师课程信息设置成功！");
					return map;
				}else{
					map.put("mess","教师课程信息设置失败！");
					return map;
				}
			}
		}
		
	}
	
	@RequestMapping("stucourseinfo")
	public String goStuCourseinfo(HttpServletRequest request){
		HttpSession session=request.getSession();
		String dtype="3";
		//获取所有的学年信息根据字典表
		List<Dictionaries_zxy> semester=publishcourse.getDictionariesByType(dtype);
		session.setAttribute("semester", semester);
		return "teacherzxy/teaclass/stuteacourse";
	}
	@ResponseBody
	@RequestMapping("getstuteacouinfolist")
	public PageInfoBo<StuTeaCourseBo_zxy> getStuTeaCourse(StuTeaCourseBo_zxy stcBo){
		PageInfoBo<StuTeaCourseBo_zxy> page = new PageInfoBo<StuTeaCourseBo_zxy>();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teacherservise.getTeacherInfo(userDetails.getUsername());
		String teano=tea.getTeano();
		stcBo.setTeano(teano);
		page=publishcourse.getStuTeaCourseBySeach(stcBo);
		return page;
	}
	
	@ResponseBody
	@RequestMapping("exportstuteaclassinfo")
	public Map goexportstuteaclassinfo(HttpServletResponse response,String couyear,String semester){
		Map map=new HashMap();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teacherservise.getTeacherInfo(userDetails.getUsername());
		String teano=tea.getTeano();
		int couyear1=Integer.parseInt(couyear);
		int semester1=Integer.parseInt(semester);
		StuTeaCourseBo_zxy teacouBo=new StuTeaCourseBo_zxy();
		teacouBo.setSemester(semester1);
		teacouBo.setCouyear(couyear1);
		teacouBo.setTeano(teano);
		List<StuTeaCourseBo_zxy> list=publishcourse.getAllTeaStuClassExport(teacouBo);
		//System.out.println("课程信息："+list.size());
		//创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("学生选课信息");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("学生选课信息");
        HSSFCellStyle cellStyle = wb.createCellStyle(); 
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		font.setFontName("微软雅黑");  
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中 
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,11));
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(2);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("学号");
        row2.createCell(1).setCellValue("学生姓名");
        row2.createCell(2).setCellValue("所在班级");
        row2.createCell(3).setCellValue("年级");
        row2.createCell(4).setCellValue("专业");
        row2.createCell(5).setCellValue("学院");
        row2.createCell(6).setCellValue("教师编号");
        row2.createCell(7).setCellValue("教师姓名");
        row2.createCell(8).setCellValue("学年");
        row2.createCell(9).setCellValue("学期");
        row2.createCell(10).setCellValue("课程名");
        row2.createCell(11).setCellValue("教室");
        
        for (int i = 0; i< list.size(); i++) {
        	HSSFRow rows = sheet.createRow(i+3);
        	rows.createCell(0).setCellValue(list.get(i).getStuno());
        	rows.createCell(1).setCellValue(list.get(i).getStuname());
        	rows.createCell(2).setCellValue(list.get(i).getClaname());
        	rows.createCell(3).setCellValue(list.get(i).getClassyear());
        	rows.createCell(4).setCellValue(list.get(i).getMajor());
        	rows.createCell(5).setCellValue(list.get(i).getCollage());
        	rows.createCell(6).setCellValue(list.get(i).getTeano());
        	rows.createCell(7).setCellValue(list.get(i).getTeaname());
        	rows.createCell(8).setCellValue(list.get(i).getCouyear());
        	rows.createCell(9).setCellValue(list.get(i).getSemester());
        	rows.createCell(10).setCellValue(list.get(i).getCouname());
        	rows.createCell(11).setCellValue(list.get(i).getCouaddress());
        }
      //输出Excel文件
        OutputStream output = null;
		try {
			output = response.getOutputStream();
			map.put("mess", "导出成功");
		} catch (IOException e) {
			//e.printStackTrace();
			map.put("mess", "导出失败");
		}
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=mystuteacourseinfo.xls");
        response.setContentType("application/msexcel");
        try {
			wb.write(output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//查询我的课程表
	@RequestMapping("initmyteaclassinfo")
	public String goMyTeaCourseKB(HttpServletRequest request){
		HttpSession session=request.getSession();
		String dtype="3";
		//获取所有的学年信息根据字典表
		List<Dictionaries_zxy> diccouyear=publishcourse.getDictionariesByType(dtype);
		session.setAttribute("diccouyear", diccouyear);
		return "teacherzxy/teaclass/myownteaclassinfo";
	}
	
	@ResponseBody
	@RequestMapping("myteacourseKBinfo")
	public List<TeacoursePo_zxy> getMyTeaKB(HttpServletRequest request){
		HttpSession session=request.getSession();
		String dtype="3";
		//获取所有的学年信息根据字典表
		List<Dictionaries_zxy> diccouyear=publishcourse.getDictionariesByType(dtype);
		session.setAttribute("diccouyear", diccouyear);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teacherservise.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		int couyear=2014;
		int semester1=1;
		Map map=new HashMap();
		map.put("teaid",teaid);
		map.put("semester",semester1);
		map.put("couyear",couyear);
		
		List<TeacoursePo_zxy> list=new ArrayList<TeacoursePo_zxy>();
		list=publishcourse.getTeaCourseKB(map);
		/*for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getCouname()+"  "+list.get(i).getCouhour());
		}*/
		return list;
	}
	
	@ResponseBody
	@RequestMapping("myteacourseKBinfoseach")
	public List<TeacoursePo_zxy> getMyTeaKBSearch(HttpServletRequest request,int couyear,int semester){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teacherservise.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		
		Map map=new HashMap();
		map.put("teaid",teaid);
		map.put("semester",semester);
		map.put("couyear",couyear);
		
		List<TeacoursePo_zxy> list=new ArrayList<TeacoursePo_zxy>();
		list=publishcourse.getTeaCourseKB(map);
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping("myteacourseKBExport")
	public Map myteacourseKBExport(HttpServletResponse response,HttpServletRequest request,int couyear,int semester){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teacherservise.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		
		Map map=new HashMap();
		map.put("teaid",teaid);
		map.put("semester",semester);
		map.put("couyear",couyear);
		List<TeacoursePo_zxy> list=new ArrayList<TeacoursePo_zxy>();
		list=publishcourse.getTeaCourseKB(map);
		
		//创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("教师课程表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("教师课程表");
        HSSFCellStyle cellStyle = wb.createCellStyle(); 
        cellStyle.setWrapText(true); 
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		font.setFontName("微软雅黑");  
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中 
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,12));
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(2);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("星期一");
        row2.createCell(1).setCellValue("星期二");
        row2.createCell(2).setCellValue("星期三");
        row2.createCell(3).setCellValue("星期四");
        row2.createCell(4).setCellValue("星期五");
        row2.createCell(5).setCellValue("星期六");
        row2.createCell(6).setCellValue("星期日");
        for (int i = 0; i < 12; i++) {
        	HSSFRow rows = sheet.createRow(i+3);
        	//rows.createCell(0)
        	for(int j=0;j<7;j++){
        		rows.createCell(j).setCellValue("");
        	}
		}
       // System.out.println("总行数"+sheet.getLastRowNum());
        for (int i = 0; i < list.size(); i++) {
			int coutime=list.get(i).getCoutime();
			int coufhour=list.get(i).getCoufhour();
			int couhour=list.get(i).getCouhour();
			String teaname=list.get(i).getTeaname();
			String couname=list.get(i).getCouname();
			String value=teaname+"   "+couname+"   "+"("+coufhour+"——"+(coufhour+couhour-1)+")";
		
			sheet.addMergedRegion(new CellRangeAddress(coufhour+2,(coufhour+2-1+couhour),coutime-1,coutime));
			HSSFRow row=sheet.getRow(coufhour+2);
			row.getCell(coutime-1).setCellValue(value);
		
        }
      //输出Excel文件
        OutputStream output = null;
		try {
			output = response.getOutputStream();
			map.put("mess", "导出成功");
		} catch (IOException e) {
			//e.printStackTrace();
			map.put("mess", "导出失败");
		}
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=myteacourse.xls");
        response.setContentType("application/msexcel");
        try {
			wb.write(output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
