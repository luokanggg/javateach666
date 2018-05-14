package com.ctbu.javateach666.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ctbu.javateach666.pojo.bo.DemoPassWordReqBo;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.TeacherInfoBo_zxy;
import com.ctbu.javateach666.pojo.po.Account_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.service.interfac.TeacherInfoService_zxy;

@Controller
public class ZXYTeacherInfoController {
	@Autowired
	private TeacherInfoService_zxy teainfoservice;
	
	
	@RequestMapping("/teainfo")
	public String goTeainfo(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		Date date=tea.getJoined_date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date1=sdf.format(date);
		
		HttpSession session=request.getSession();
		session.setAttribute("TeaInfo", tea);
		session.setAttribute("Joined_date", date1);
		return "teacherzxy/teamyinfozxy/teainfo";
	}
	

	@RequestMapping("/teaoperpassword")
	public String goOperPassword(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		HttpSession session=request.getSession();
		session.setAttribute("TeaInfo", tea);
		return "teacherzxy/teamyinfozxy/teaoperpassword";
	}
	
	
	@RequestMapping("/modifyteainfo")
	public String goModifyTeainfo(HttpServletRequest request){
		return "teacherzxy/teamyinfozxy/modifyteainfo";
	}
	@ResponseBody
	@RequestMapping(value = "/updateTeaInfo",method=RequestMethod.GET)
	public Map goUpdateTeaInfo(HttpServletRequest request,TeacherInfoBo_zxy reqBo){
		Map info=new HashMap<String,String>();
		try {
			request.setCharacterEncoding("utf-8");
			//String professional=new String(reqBo.getProfessional().getBytes("ISO-8859-1"),"utf-8");
			String teasex=new String(reqBo.getTeasex().getBytes("ISO-8859-1"),"utf-8");
			String major=new String(reqBo.getMajor().getBytes("ISO-8859-1"),"utf-8");
			String teacollage=new String(reqBo.getTeacollage().getBytes("ISO-8859-1"),"utf-8");
			String political=new String(reqBo.getPolitical().getBytes("ISO-8859-1"),"utf-8");
			String prosonal_statement=new String(reqBo.getProsonal_statement().getBytes("ISO-8859-1"),"utf-8");
			//System.out.println(reqBo.getMajor()+"  "+reqBo.getPolitical()+"  "+reqBo.getProsonal_statement());
			//reqBo.setProfessional(professional);
			reqBo.setTeasex(teasex);
			reqBo.setProsonal_statement(prosonal_statement);
			reqBo.setMajor(major);
			reqBo.setTeacollage(teacollage);
			reqBo.setPolitical(political);
			teainfoservice.updateTeacherInfo(reqBo);
			//System.out.println("=============="+reqBo.getMajor()+"  "+reqBo.getPolitical()+"  "+reqBo.getProsonal_statement());
			info.put("info", "0000");
			
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			//System.out.println("资料更新失败");
			info.put("info", "8888");
		}
		
		return info;
	}
	
	
	
	
	@RequestMapping("/allteainfo")
	public String goAllteainfo(HttpServletRequest request,HttpServletResponse response){
		return "teacherzxy/teamyinfozxy/teainfolist";
	}
	
	@ResponseBody
	@RequestMapping("/updateteainfoimg")
	public void updateStuInfoImg(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		int id=tea.getId();
		String filename=file.getOriginalFilename();//文件名字
		//System.out.println("文件名字："+filename);//co2.png
		//定义文件上传路径
		String savepath = request.getServletContext().getRealPath("/")+"static\\file\\";
		Map map=new HashMap();
		map.put("id", id);
		map.put("teaimage",filename);
		int count=0;
		
		try {
			count=teainfoservice.updateTeaImageById(map);
			if(count>0){
				//System.out.println("成功");
				File newFile=new File(savepath, filename);
				file.transferTo(newFile);
				response.getWriter().print("<script> alert('头像更新成功') </script>");
			}
		} catch (Exception e) {
			try {
				response.getWriter().print("<script> alert('头像更新失败') </script>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	}
	
	@ResponseBody
	@RequestMapping("/getteainfolist")
	public PageInfoBo<TeacherInfo_zxy> goTeainfolist(TeacherInfoBo_zxy teaBo){
		//System.out.println(teaBo.getTeaname()+"   "+teaBo.getProfessional());
		
		PageInfoBo<TeacherInfo_zxy> page=new PageInfoBo<TeacherInfo_zxy>();
		page=teainfoservice.getTeainfolist(teaBo);
		//System.out.println("hhh"+teaBo.getTeaname()+"   "+teaBo.getProfessional());
		return page;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "updatePassword")
	public Map ModifyMyPass(HttpServletResponse response,HttpServletRequest request,DemoPassWordReqBo reqBo){
		response.setCharacterEncoding("UTF-8");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=userDetails.getUsername();
		Account_zxy account=teainfoservice.getAccountByUsername(username);
		int id=-1;
		if(account!=null){
			id=account.getId();
		}
		String pass="";
		pass=reqBo.getPass();
		System.out.println("新密码："+pass);
		Map map=new HashMap<String, String>();
		Map info=new HashMap<String, String>();
		map.put("password", pass);
		map.put("id", id);
		try {
			teainfoservice.updateTeaPasswordById(map);
			info.put("info", "0000");
		} catch (Exception e) {
			info.put("info", "8888");
		}
		return info;
	}
	
	
}
