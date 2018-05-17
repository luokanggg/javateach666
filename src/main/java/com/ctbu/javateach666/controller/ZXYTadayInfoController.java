package com.ctbu.javateach666.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.TeacourseBo_zxy;
import com.ctbu.javateach666.pojo.bo.ToNoticeBoRsp_zxy;
import com.ctbu.javateach666.pojo.bo.ToNoticeReqBo_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.pojo.po.TeacoursePo_zxy;
import com.ctbu.javateach666.service.interfac.NoticeService_zxy;
import com.ctbu.javateach666.service.interfac.TeacherInfoService_zxy;
import com.ctbu.javateach666.service.interfac.TodayInfoService;
import com.sun.tools.hat.internal.server.HttpReader;

@Controller
public class ZXYTadayInfoController {
	@Autowired
	private TeacherInfoService_zxy teainfoservice;
	@Autowired
	private NoticeService_zxy noticeservice;
	@Autowired
	private TodayInfoService totayservice;
	//查看今日的课程信息
	@RequestMapping("lookmycouse")
	public String TodayCourse(HttpServletRequest request){
		Date today = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date2=sdf.format(today);
        Calendar c=Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        weekday=(weekday+6)%7;
        
        int semester=1;
        int years=Integer.parseInt(year);
       // System.out.println(years+"年份");
        int month=Calendar.MONTH+1;
        if(month>=9){
        	semester=1;
        }else{
        	semester=2;
        }
        TeacourseBo_zxy tc=new TeacourseBo_zxy();
        tc.setCouyear(years);
        tc.setSemester(semester);
        tc.setCoutime(1);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		tc.setTeaid(teaid);
		System.out.println(tc);
		List<TeacoursePo_zxy> list=totayservice.getTodayTeaCourse(tc);
		HttpSession session=request.getSession();
		session.setAttribute("todaycourse", list);
		session.setAttribute("date", date2);
		session.setAttribute("weekday", weekday);
		return "teacherzxy/tadayinfozxy/todaycourse";
	}
	
	@RequestMapping("lookmynotice")
	public String getlookmynotice(){
		return "teacherzxy/tadayinfozxy/todaynotice";
	}
	//查看今日收到的的信息
	@ResponseBody
	@RequestMapping("todaynoticeinfolists")
	public PageInfoBo<ToNoticeBoRsp_zxy> getTodayNotice(HttpServletRequest request,ToNoticeReqBo_zxy toReq){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		toReq.setTonotid(teaid);
		Date today = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(today);
		toReq.setStarttime(time);
		PageInfoBo<ToNoticeBoRsp_zxy> page=new PageInfoBo<ToNoticeBoRsp_zxy>();
		page=totayservice.getTodayNoticeByPage(toReq);
	
		return page;
	}
	
	
	@RequestMapping("goerror")
	public String goerror(){
		return "teacherzxy/error";
	}
}
