package com.ctbu.javateach666.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.pojo.bo.NoticeReqBO2_zxy;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.PlanCoutseBo_zxy;
import com.ctbu.javateach666.pojo.bo.StuTeaCourseBo_zxy;
import com.ctbu.javateach666.pojo.bo.TeaCourseReqBo;
import com.ctbu.javateach666.pojo.bo.TeacourseBo_zxy;
import com.ctbu.javateach666.pojo.po.Course_zxy;
import com.ctbu.javateach666.pojo.po.Dictionaries_zxy;
import com.ctbu.javateach666.pojo.po.PlanCoutsePo_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.pojo.po.TeacoursePo_zxy;
import com.ctbu.javateach666.service.interfac.NoticeService_zxy;
import com.ctbu.javateach666.service.interfac.PublishCourseServise_zxy;
import com.ctbu.javateach666.service.interfac.TeacherInfoService_zxy;

@Controller
public class ZXYPublishCourseController {
	
	@Autowired
	PublishCourseServise_zxy publishcourse;
	@Autowired
	TeacherInfoService_zxy teacherservise;
	@Autowired
	private NoticeService_zxy noticeservice;
	
	@RequestMapping("publishcourse")
	public String gopublishcourse(HttpServletRequest request){
		//获取所有的课程信息
				HttpSession session=request.getSession();
				List<Course_zxy> course=publishcourse.getCourseList();
				session.setAttribute("course", course);
				
				//获取所有的教师信息
				List<TeacherInfo_zxy> teacher=publishcourse.getAllTeacher();
				session.setAttribute("teas",teacher);
				
				String dtype="学年";
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
	
	//只有星期几的同一天的开始节数不相同就可以
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
		
		//只判断星期几的某一天 开始节数和课时相同的
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
		String dtype="学年";
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
		String dtype="学年";
		//获取所有的学年信息根据字典表
		List<Dictionaries_zxy> diccouyear=publishcourse.getDictionariesByType(dtype);
		session.setAttribute("diccouyear", diccouyear);
		return "teacherzxy/teaclass/myownteaclassinfo";
	}
	
	@ResponseBody
	@RequestMapping("myteacourseKBinfo")
	public List<TeacoursePo_zxy> getMyTeaKB(HttpServletRequest request){
		HttpSession session=request.getSession();
		String dtype="学年";
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
	
	@SuppressWarnings("deprecation")
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
		list=publishcourse.getTeaCourseKBforExcel(map);
		
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
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,6));
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
        	for(int j=0;j<7;j++){
        		rows.createCell(j).setCellValue("     ");
        	}
		}
       
       
        for (int i = 0; i < list.size(); i++) {
			int coutime=list.get(i).getCoutime();
			
			int coufhour=list.get(i).getCoufhour();
			int couhour=list.get(i).getCouhour();
			String teaname=list.get(i).getTeaname();
			String couname=list.get(i).getCouname();
			String value=teaname+"   "+couname+"   "+"("+coufhour+"——"+(coufhour+couhour-1)+")";
			//sheet.addMergedRegion(new CellRangeAddress((coufhour+2),(coufhour+1+couhour),(coutime-1),(coutime-1)));
			HSSFRow row=sheet.getRow((int)(coufhour+2));
			//HSSFCell cells=row.getCell(coutime-1);
			
			row.createCell(coutime-1).setCellValue(value);
		
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
	@RequestMapping("mycourseinfos")
	public String myclassinfos(HttpServletRequest request){
		HttpSession session=request.getSession();
		String dtype="学年";
		//获取所有的学年信息根据字典表
		List<Dictionaries_zxy> diccouyear=publishcourse.getDictionariesByType(dtype);
		session.setAttribute("diccouyear", diccouyear);
		return "teacherzxy/teaclass/mycourse";
	}
	
	@ResponseBody
	@RequestMapping("getMycourselist")
	public PageInfoBo<TeacoursePo_zxy> getmyclassinfolist(PlanCoutseBo_zxy pcBo){
		PageInfoBo<TeacoursePo_zxy> page = new PageInfoBo<TeacoursePo_zxy>();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teacherservise.getTeacherInfo(userDetails.getUsername());
		int id=tea.getId();
		pcBo.setTeaid(id);
		page=publishcourse.getTeaCourse(pcBo);
		return page;
	}
	@ResponseBody
	@RequestMapping(value="plancorse",method=RequestMethod.GET)
	public void planCorse(String teaid1,String couname,String couyear,String semester,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//response.setCharacterEncoding("utf-8");
		couname= new String(couname.getBytes("iso8859-1"),"UTF-8");
		PlanCoutseBo_zxy pcBo=new PlanCoutseBo_zxy();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teacherservise.getTeacherInfo(userDetails.getUsername());
		HttpSession session=request.getSession();
		session.setAttribute("TeaInfo", tea);
		int teaid=Integer.parseInt(teaid1);
		
		//获取该教师所教授的班级信息
		List<NoticeReqBO2_zxy> classlist=noticeservice.getTeaClassSJByTeaid(teaid);
		session.setAttribute("classlist",classlist);
		
		pcBo.setCouyear(Integer.parseInt(couyear));
		pcBo.setSemester(Integer.parseInt(semester));
		pcBo.setTeaid(teaid);
		pcBo.setCouname(couname);
		session.setAttribute("teacourse",pcBo);
		
	}
	@RequestMapping("mycourseplan")
	public String goMyCoursePlan()
	{
		
		return "teacherzxy/teaclass/courseplan";
	}
	@ResponseBody
	@RequestMapping(value="MakePlanCourse",method=RequestMethod.GET)
	public Map goMakePlanCourse(String plantime1,String plantime2,String plancontent,String plangoal,String plantitle,String couyear,String semester,String planclass,String couname){
		Map map=new HashMap();
		try {
			
			plancontent=new String(plancontent.getBytes("iso8859-1"),"UTF-8");
			plangoal=new String(plangoal.getBytes("iso8859-1"),"UTF-8");
			plantitle=new String(plantitle.getBytes("iso8859-1"),"UTF-8");
			planclass=new String(planclass.getBytes("iso8859-1"),"UTF-8");
			couname=new String(couname.getBytes("iso8859-1"),"UTF-8");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
			Date start=sdf.parse(plantime1);
			Date end=sdf.parse(plantime2);
			int seme=Integer.parseInt(semester);
			int couy=Integer.parseInt(couyear);
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			TeacherInfo_zxy tea=teacherservise.getTeacherInfo(userDetails.getUsername());
			int teaid=tea.getId();
			PlanCoutseBo_zxy pcBo=new PlanCoutseBo_zxy();
			pcBo.setCouname(couname);
			pcBo.setCouyear(couy);
			pcBo.setSemester(seme);
			pcBo.setPlanclass(planclass);
			pcBo.setPlantime1(start);
			pcBo.setPlantime2(end);
			pcBo.setPlantitle(plantitle);
			pcBo.setPlancontent(plancontent);
			pcBo.setPlangoal(plangoal);
			pcBo.setTeaid(teaid);
			
			try {
				publishcourse.insertPlanCOurse(pcBo);
				map.put("mess","0000");
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("mess","8888");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("mess","8888");
		}
		return map;
	}
	@RequestMapping("mycourseplandetail")
	public String gomycourseplan(HttpServletRequest request){
		HttpSession session=request.getSession();
		String dtype="学年";
		//获取所有的学年信息根据字典表
		List<Dictionaries_zxy> diccouyear=publishcourse.getDictionariesByType(dtype);
		session.setAttribute("diccouyear", diccouyear);
		return "teacherzxy/teaclass/mycourseplandetail";
	}
	
	@ResponseBody
	@RequestMapping(value="/getMycoursePlan")
	public PageInfoBo<PlanCoutsePo_zxy> getmycourseplan(HttpServletResponse res,PlanCoutseBo_zxy pcBo){
		// res.setCharacterEncoding("UTF-8");
		PageInfoBo<PlanCoutsePo_zxy> page=new PageInfoBo<PlanCoutsePo_zxy>();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teacherservise.getTeacherInfo(userDetails.getUsername());
		int id=tea.getId();
		pcBo.setTeaid(id);
		page=publishcourse.getmycourseplanBySearch(pcBo);
		return page;
	}
	
	@ResponseBody
	@RequestMapping(value="/lookdetailfile",method=RequestMethod.GET)
	public Map goLookPlanContent(int id,HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		PlanCoutsePo_zxy pc=null;
		Map map=new HashMap<>();
		HttpSession session=request.getSession();
		pc=publishcourse.getPlanCoutseById(id);
	    if(pc!=null){
	    	if("".equals(pc.getPlanfile())){
	    		map.put("mess","8888");
	    	}else{
	    		map.put("mess", pc.getPlanfile());
	    		session.setAttribute("planfile",pc.getPlanfile());
	    		//System.out.println(pc.getPlanfile()+"地址");
	    	}
	    	
	    }else{
	    	map.put("mess","8888");
	    }
	    return map;
	}
	
	//文件名
	private String makeFileName(String filename){    
			   //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名  
		 return UUID.randomUUID().toString() + "_" + filename;  
	}  
	@RequestMapping("/gomyplanfile")
	public String gomyplanfile(HttpServletRequest request){
		HttpSession session=request.getSession();
		String planfile=(String) session.getAttribute("planfile");
		 File file = new File(planfile);  
		 BufferedReader rd=null;
		 String str = "";
		try {
			//rd = new BufferedReader(new FileReader(file),"UTF-8");
			rd = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
	        String s = rd.readLine(); 
	      
	       
	        while (null != s) {  
	             str += s;  
	             s = rd.readLine();  
	            
	         }  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    
			session.setAttribute("detailplan",str);
		
		//读取文件的内容显示在页面上
		return "teacherzxy/teaclass/myplanaccessy";
	}
	

	@RequestMapping("/uploadmyplan")
	public void updateStuInfoImg(@RequestParam("file") CommonsMultipartFile file, @RequestParam("id") String id,HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		String filename=file.getOriginalFilename();
		filename=makeFileName(filename);
		//String savepath = request.getServletContext().getRealPath("/")+"static\\file\\";
		String savepath ="D:/planfile";
		File file1=new File(savepath,filename);
		PlanCoutseBo_zxy pcBo=new PlanCoutseBo_zxy();
		pcBo.setId(Integer.parseInt(id));
		pcBo.setPlanfile(savepath+"/"+filename);
		int tag=0;
		try {
			file.transferTo(file1);
			tag=publishcourse.updatePlanById(pcBo);
			if(tag==1){
				response.getWriter().print("<script> alert('附件上传成功') </script>");
			}else{
				response.getWriter().print("<script> alert('附件上传失败') </script>");
			}
			
		} catch (IllegalStateException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			try {
				response.getWriter().print("<script> alert('附件上传失败') </script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	//下载教学方案
	@ResponseBody
	@RequestMapping(value="downplanfile",method=RequestMethod.GET)
	public void  downFile(HttpServletRequest request,HttpServletResponse response,int id){
		PlanCoutsePo_zxy pc=null;
		pc=publishcourse.getPlanCoutseById(id);
		response.setCharacterEncoding("UTF-8");
		String filename="";
		if(pc!=null){
			filename=pc.getPlanfile();
			if(filename==null||"".equals(filename)){
				try {
					response.getWriter().print("<script> alert('该课程还么有上传教学计划文件！') </script>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					 String filename2=filename;
			         String prefix=filename2.substring(filename2.lastIndexOf(".")+1);
			         String contentType = new MimetypesFileTypeMap().getContentType(filename);
			         String contentDisposition = "attachment;filename=planfile"+"."+prefix;
			         FileInputStream input = new FileInputStream(filename);
			         response.setHeader("Content-Type",contentType);
			         response.setHeader("Content-Disposition",contentDisposition);
			         ServletOutputStream output = response.getOutputStream();
			         // 把输入流中的数据写入到输出流中
			         IOUtils.copy(input,output);
			         input.close();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else{
			try {
				response.getWriter().print("<script> alert('文件下载失败！') </script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
