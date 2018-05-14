package com.ctbu.javateach666.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbu.javateach666.pojo.bo.NoticeBo_zxy;
import com.ctbu.javateach666.pojo.bo.NoticeReqBO2_zxy;
import com.ctbu.javateach666.pojo.bo.NoticeResBo_zxy;
import com.ctbu.javateach666.pojo.bo.PageInfoBo;
import com.ctbu.javateach666.pojo.bo.ToNoticeBoRsp_zxy;
import com.ctbu.javateach666.pojo.bo.ToNoticeReqBo_zxy;
import com.ctbu.javateach666.pojo.po.Notice_zxy;
import com.ctbu.javateach666.pojo.po.StudentInfoPO_zxy;
import com.ctbu.javateach666.pojo.po.StudentInfo_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.pojo.po.Tonotice_zxy;
import com.ctbu.javateach666.service.interfac.NoticeService_zxy;
import com.ctbu.javateach666.service.interfac.TeacherInfoService_zxy;
@Controller
public class ZXYNoticeController {
	@Autowired
	private TeacherInfoService_zxy teainfoservice;
	
	@Autowired
	private NoticeService_zxy noticeservice;
	@RequestMapping("/noticeinfo")
	public String goNoticePublish(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{
		response.setCharacterEncoding("utf-8");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		HttpSession session=request.getSession();
		session.setAttribute("TeaInfo", tea);
		//查询islook表，判断登录的用户有没有未查看的通知
		int id=tea.getId();
		//根据id判断有多少条未查看的通知记录
		int count=0;
		count=noticeservice.getNoLookNoticeById(id);
		request.setAttribute("nolook",count);
		int teaid=id;
		List<NoticeReqBO2_zxy> classlist=noticeservice.getTeaClassSJByTeaid(teaid);
		request.setAttribute("classlist",classlist);
		return "teacherzxy/teanoticezxy/noticeinfo";
	}
	
	@RequestMapping("/lookNoReadNotice")
	public String golookNoReadNotice(){
		return "teacherzxy/teanoticezxy/mynoreadnoticeinfo";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/publishnotice",method=RequestMethod.POST)
	public Map goPublishNotice(HttpServletRequest request,String notcontent,String tonotid,String endtime,String noturl,String nottitle,String nottypt,String type) throws ParseException, UnsupportedEncodingException{
		//System.out.println("------------------"+type+notcontent+"  "+tonotid+"  "+endtime+" "+noturl+" "+nottitle+" "+nottypt);
		Map map=new HashMap();
		int classid=Integer.parseInt(nottypt);
		HttpSession session=request.getSession();
		TeacherInfo_zxy tea=(TeacherInfo_zxy) session.getAttribute("TeaInfo");
		String notname=tea.getTeaname();//通知人姓名
		int notid=tea.getId();//通知人ID
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String starttime=dateFormater.format(date);//创建的时间
		Date starttime1=dateFormater.parse(starttime);
		Date endtime1=dateFormater.parse(endtime);
		Notice_zxy notice=new Notice_zxy();
		notice.setNotid(notid);
		notice.setStarttime(starttime1);
		notice.setEndtime(endtime1);
		notice.setIs_delete(1);
		notice.setNottitle(nottitle);
		notice.setNotcontent(notcontent);
		notice.setNoturl(noturl);
		notice.setNotname(notname);
		if(type.equals("simple")){
			int tonotid1=Integer.parseInt(tonotid);//被通知人ID
			int nottypt1=Integer.parseInt(nottypt);//通知的类型
			if(notid==tonotid1&&nottypt1==4){
				map.put("mess", "不能通知自己！");
				return map;
			}else{
				//判断被通知人的ID是否存在
				if(nottypt1==1){
					//老师通知学生查看学生表判断信息是否存在
					StudentInfo_zxy stu=null;
					stu=noticeservice.getStudentById(tonotid1);	
					if(stu==null){
						map.put("mess", "被通知人ID不存在，通知失败");
						return map;
					}
				}else{
					TeacherInfo_zxy teacher=null;
					teacher=noticeservice.getTeacherById(tonotid1);
					if(teacher==null){
						map.put("mess", "被通知人ID不存在，通知失败");
						return map;
					}
				}
				notice.setTonotid(tonotid1);
				notice.setNottype(nottypt1);
				
				try {
					noticeservice.insertNotice(notice);
					noticeservice.insertToNotice(notice);
					map.put("mess", "通知成功");
					return map;
				} catch (Exception e) {
					map.put("mess", "通知失败");
					return map;
				}
			}
		}else{
			if(type.equals("class")){
				List<StudentInfoPO_zxy> stus=new ArrayList<>();
				stus=noticeservice.getAllStuByClassId(classid);
				int tag=0;
				if(stus.size()>0){
					for (int i = 0; i < stus.size(); i++) {
						int stuid=stus.get(i).getId();
						notice.setTonotid(stuid);
						notice.setNottype(1);
						noticeservice.insertNotice(notice);
						noticeservice.insertToNotice(notice);
						tag++;
					}
				}
				if(tag==stus.size()){
					map.put("mess", "通知成功");
					
				}else{
					map.put("mess", "通知失败");
					
				}
			}
			return map;
		}
	}
	
	@RequestMapping("noticeinfolist")
	public String goNoticeinfolist(HttpServletRequest request){
		return "teacherzxy/teanoticezxy/noticelist";
	}
	@ResponseBody
	@RequestMapping("/getAllnoticelist")
	public PageInfoBo<NoticeResBo_zxy> getAllNoticeinfos(HttpServletResponse response,HttpServletRequest request,NoticeBo_zxy notice) throws UnsupportedEncodingException, ParseException{
		
		response.setCharacterEncoding("utf-8");
		PageInfoBo<NoticeResBo_zxy> page=new PageInfoBo<NoticeResBo_zxy>();
		page=noticeservice.getAllNoticeInfo(notice);
		return page;
	}
	
	
	@ResponseBody
	@RequestMapping("/getAllNoReadnoticelist")
	public PageInfoBo<ToNoticeBoRsp_zxy> getAllNoReadnoticelist(HttpServletRequest request,HttpServletResponse response,ToNoticeReqBo_zxy toReq){
		response.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		TeacherInfo_zxy tea=(TeacherInfo_zxy) session.getAttribute("TeaInfo");
		int id=tea.getId();
		toReq.setId(id);
		//System.out.println(toReq.getId()+"  "+toReq.getPage()+"  "+toReq.getRows());
		PageInfoBo<ToNoticeBoRsp_zxy> page=new PageInfoBo<ToNoticeBoRsp_zxy>();
		page=noticeservice.getAllNoReadnoticelistByPage(toReq);
		return page;
	}
	
	@RequestMapping("/getAllTonotice")
	public String getAllTonotice(){
		return "teacherzxy/teanoticezxy/myalltonoticeinfo";
	}
	@ResponseBody
	@RequestMapping("/getAllTonoticelist")
	public PageInfoBo<ToNoticeBoRsp_zxy> getAllTonoticelist(HttpServletRequest request,HttpServletResponse response,ToNoticeReqBo_zxy toReq){
		response.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		TeacherInfo_zxy tea=(TeacherInfo_zxy) session.getAttribute("TeaInfo");
		int id=tea.getId();
		toReq.setId(id);
	
		PageInfoBo<ToNoticeBoRsp_zxy> page=new PageInfoBo<ToNoticeBoRsp_zxy>();
		page=noticeservice.getAllTonoticelistByPage(toReq);
		return page;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/notice_delete")
	public Map goNotice_delete(HttpServletRequest request,int id){
		Map map=new HashMap<String, String>();
		Notice_zxy notice=noticeservice.getNoticeById(id);
		int notid=0;//通知人Id
		int is_delete=0;//是否可以删除
		if(notice!=null){
			notid=notice.getNotid();
			is_delete=notice.getIs_delete();
		}
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		if(teaid!=notid){
			map.put("mess", "该通知不是您发布，不可以删除");
			return map;
		}else{
			if(is_delete==1){
				try {
					noticeservice.deleteNoticeById(id);
					map.put("mess", "删除成功");
				} catch (Exception e) {
					// TODO: handle exception
					map.put("mess", "删除失败");
				}
				return map;
			}else{
				map.put("mess", "该通知不可以执行逻辑删除！");
				return map;
			}
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/notice_modify")
	public Map goNotice_modify(HttpServletRequest request,int id){
		//System.out.println("编辑Id:"+id);
		Map map=new HashMap<String, String>();
		Notice_zxy notice=noticeservice.getNoticeById(id);
		int notid=0;//通知人Id
		if(notice!=null){
			notid=notice.getNotid();
		}
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TeacherInfo_zxy tea=teainfoservice.getTeacherInfo(userDetails.getUsername());
		int teaid=tea.getId();
		if(teaid!=notid){
			map.put("mess", "该通知不是您发布，不可以编辑");
			return map;
		}else{
			NoticeResBo_zxy res=new NoticeResBo_zxy();
			res.setId(id);
			res.setNotcontent(notice.getNotcontent());
			res.setEndtime(notice.getEndtime());
			res.setNotname(notice.notname);
			res.setNottitle(notice.getNottitle());
			res.setNoturl(notice.getNoturl());
			res.setNottype(notice.getNottype()+"");
			res.setTonotid(notice.getTonotid()+"");
			if(notice.getNottype()==1){
				StudentInfo_zxy stu=noticeservice.getStudentById(notice.getTonotid());
				String totname=stu.getStuname();
				res.setTonotname(totname);
			}else if(notice.getNottype()==4){
				TeacherInfo_zxy teacher=noticeservice.getTeacherById(notice.getTonotid());
				String totname=teacher.getTeaname();
				res.setTonotname(totname);
			}
			HttpSession session=request.getSession();
			session.setAttribute("resnotice",res);
			map.put("mess", "modifynotice");
			return map;
		}
	}
	
	@RequestMapping(value="/modifynotice")
	public String goNoticemodify(){
		return "teacherzxy/teanoticezxy/modifynotice";
	}
	@ResponseBody
	@RequestMapping(value="/updatenotice")
	public Map updateNotice(HttpServletRequest request,NoticeBo_zxy notice){
		Map map=new HashMap();
		//System.out.println("传入的参数："+notice.getId()+"  "+notice.getEndtime()+"  "+notice.getNotcontent()+"  "+notice.getNottitle());
		//id，notcontent,nottitle,noturl,endtime,satrttime,is_delete=1,
		Notice_zxy tonot=new Notice_zxy();
		int is_delete=1;
		tonot.setIs_delete(is_delete);
		HttpSession session=request.getSession();
		NoticeResBo_zxy res=(NoticeResBo_zxy) session.getAttribute("resnotice");
		tonot.setNotid(res.getNotid());
		tonot.setTonotid(Integer.parseInt(res.getTonotid()));
		tonot.setNottype(Integer.parseInt(res.getNottype()));
		tonot.setNotname(res.getNotname());
		
		if("".equals(notice.getNotcontent())){
			notice.setNotcontent(res.getNotcontent());
			tonot.setNotcontent(res.getNotcontent());
		}else{
			tonot.setNotcontent(notice.getNotcontent());
		}
		if("".equals(notice.getNottitle())){
			notice.setNottitle(res.getNottitle());
			tonot.setNottitle(res.getNottitle());
		}else{
			tonot.setNottitle(notice.getNottitle());
		}
		if("".equals(notice.getNoturl())){
			notice.setNoturl(res.getNoturl());
			tonot.setNoturl(res.getNoturl());
		}else{
			tonot.setNoturl(notice.getNoturl());
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date=res.getEndtime();
		
		String startdate=format.format(new Date());
		//System.out.println("被通知的开始时间："+startdate+"    "+new Date());
		tonot.setStarttime(new Date());
		if("".equals(notice.getEndtime())){
			notice.setEndtime(format.format(date));
			tonot.setEndtime(date);
			//System.out.println(notice.getEndtime()+"======");
		}else{
			try {
				tonot.setEndtime(format.parse(startdate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			int flag=0;
			flag=noticeservice.updateNotice(notice);
			noticeservice.insertToNotice(tonot);
			if(flag==1){
				map.put("mess", "更新成功！");
				return map;
			}else{
				map.put("mess", "更新失败！");
				return map;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("mess", "更新失败！");
			//System.out.println(e);
			return map;
		}
		
	}
	@ResponseBody
	@RequestMapping("/read_modify")
	public Map goReadNotice(int id){
		Map map=new HashMap<String,String>();
		Tonotice_zxy tonotice=noticeservice.getToNoticeById(id);
		int flag=-1;
		if(tonotice!=null){
			flag=tonotice.getIs_look(); 
		}
		if(flag==1){
			map.put("mess","该通知标志已是已读，不能再标志");
			return map;
		}else{
			try {
				noticeservice.ReadNotice(id);
				map.put("mess", "该记录已标志为已读");
				return map;
			} catch (Exception e) {
				// TODO: handle exception
				map.put("mess", "标志失败");
				return map;
			}
		}
	}
	
	//批量标志
	@ResponseBody
	@RequestMapping("/read_modify_pl")
	public Map goRead_modify_pl(String  _list){
		Map map=new HashMap();
		String list[]=_list.split(",");
		int id=0;
		boolean tag=true;
		for (int i = 0; i < list.length; i++) {
			id=Integer.parseInt(list[i]);
			try {
				noticeservice.ReadNotice(id);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				tag=false;
			
			}
		}
		if(tag==true){
			map.put("mess", "该记录已标志为已读");
			return map;
		}else{
			map.put("mess", "标志失败");
			return map;
		}
		
	}
	
	
	//批量删除
	@ResponseBody
	@RequestMapping("/deletetonoticepl")
	public Map deletetonoticepl(String  _list){
		Map map=new HashMap();
		String list[]=_list.split(",");
		int id=0;
		boolean tag=true;
		for (int i = 0; i < list.length; i++) {
			id=Integer.parseInt(list[i]);
			try {
				//逐条删除
				noticeservice.deleteToNotice(id);
			} catch (Exception e) {
				// TODO: handle exception
				tag=false;
			
			}
		}
		if(tag==true){
			map.put("mess", "批量删除成功");
			return map;
		}else{
			map.put("mess", "批量删除失败");
			return map;
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/ToNoReadnotice_delete")
	public Map goToNoReadnotice_delete(int id){
		Map map=new HashMap<String,String>();
		//System.out.println("id====="+id);
		try {
			int flag=noticeservice.deleteToNotice(id);
			//System.out.println(flag);
			if(flag==1){
				map.put("mess", "删除成功！");
				//System.out.println("成功");
				return map;
			}else{
				map.put("mess", "删除失败！");
				//System.out.println("sssss");
				return map;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("mess", "删除失败！");
		//	System.out.println(e);
			return map;
		}
		
	}
}
