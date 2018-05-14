package com.ctbu.javateach666.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbu.javateach666.dao.NoticeDao_zxy;
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
@Service
public class NoticeServiceImpl_zxy implements NoticeService_zxy{
	@Autowired
	NoticeDao_zxy noticedao;
	public StudentInfo_zxy getStudentById(int id) {
		// TODO Auto-generated method stub
		return noticedao.getStudentById(id);
	}
	public TeacherInfo_zxy getTeacherById(int id) {
		// TODO Auto-generated method stub
		return noticedao.getTeacherById(id);
	}
	public int insertNotice(Notice_zxy notice) {
		// TODO Auto-generated method stub
		return noticedao.insertNotice(notice);
	}
	
	public PageInfoBo<NoticeResBo_zxy> getAllNoticeInfo(NoticeBo_zxy notice){
		//定义出参
		PageInfoBo<NoticeResBo_zxy> rsp=new PageInfoBo<NoticeResBo_zxy>();
		//设置page为下标
		int page = 0;
		page=(notice.getPage()-1)*notice.getRows();
		notice.setPage(page);
		
		if(notice.getNotname()!=null&&!"".equals(notice.getNotname())){
			String notname="";
			notname="%"+notice.getNotname()+"%";
			notice.setNotname(notname);
		}
		//System.out.println(notice.getTonotname()+"被通知人姓名==");
		if(notice.getTonotname()!=null&&!"".equals(notice.getTonotname())){
			String tonotname="";
			tonotname="%"+notice.getTonotname()+"%";
			notice.setTonotname(tonotname);
			//System.out.println(notice.getTonotname()+"被通知人姓名不为空==");
		}
		int nottypt1=Integer.parseInt(notice.getNottype());//通知的类型
		int total=0;
		if(nottypt1==1){
			total=TotalALlNoticeinfoByStu(notice);
			if(total < 1){
				return rsp;
			}else{
				List<NoticeResBo_zxy> list=noticedao.getAllNOticeBySeachAndPageStu(notice);
				rsp.setRows(list);
				rsp.setTotal(total);
				return rsp;
			}
		}else{
			total=TotalALlNoticeinfoByTea(notice);
			if(total < 1){
				return rsp;
			}else{
				List<NoticeResBo_zxy> list=noticedao.getAllNOticeBySeachAndPageTea(notice);
				rsp.setRows(list);
				rsp.setTotal(total);
				return rsp;
			}
		}	
	}
	

	public Notice_zxy getNoticeById(int id) {
		// TODO Auto-generated method stub
		return noticedao.getNoticeById(id);
	}
	
	//根基id删除通知信息
	public int deleteNoticeById(int id){
		return noticedao.deleteNoticeById(id);
	}
	public StudentInfo_zxy getStudentInfoById(int id) {
		// TODO Auto-generated method stub
		return noticedao.getStudentById(id);
	}
	public TeacherInfo_zxy getTeacherInfoById(int id) {
		// TODO Auto-generated method stub
		return noticedao.getTeacherById(id);
	}
	

	public int getNoLookNoticeById(int id) {
		// TODO Auto-generated method stub
		return noticedao.getNoLookNoticeById(id);
	}
	public int insertToNotice(Notice_zxy notice) {
		// TODO Auto-generated method stub
		return noticedao.insertToNotice(notice);
	}
	
	public int TotalALlNoticeinfoByStu(NoticeBo_zxy notice) {
		// TODO Auto-generated method stub
		return noticedao.TotalALlNoticeinfoByStu(notice);
	}
	public int TotalALlNoticeinfoByTea(NoticeBo_zxy notice) {
		// TODO Auto-generated method stub
		return noticedao.TotalALlNoticeinfoByTea(notice);
	}
	public List<NoticeResBo_zxy> getAllNOticeBySeachAndPageStu(NoticeBo_zxy notice) {
		// TODO Auto-generated method stub
		return noticedao.getAllNOticeBySeachAndPageStu(notice);
	}
	public List<NoticeResBo_zxy> getAllNOticeBySeachAndPageTea(NoticeBo_zxy notice) {
		// TODO Auto-generated method stub
		return noticedao.getAllNOticeBySeachAndPageTea(notice);
	}
	public int updateNotice(NoticeBo_zxy reqBO) {
		// TODO Auto-generated method stub
		return noticedao.updateNotice(reqBO);
	}
	public int totalToNoticeinfo(ToNoticeReqBo_zxy toReq) {
		// TODO Auto-generated method stub
		return noticedao.totalToNoticeinfo(toReq);
	}
	public List<ToNoticeBoRsp_zxy> getNOReadNotice(ToNoticeReqBo_zxy toReq) {
		// TODO Auto-generated method stub
		return noticedao.getNOReadNotice(toReq);
	}
	public PageInfoBo<ToNoticeBoRsp_zxy> getAllNoReadnoticelistByPage(ToNoticeReqBo_zxy toReq) {
		// TODO Auto-generated method stub
		PageInfoBo<ToNoticeBoRsp_zxy> rsp=new PageInfoBo<ToNoticeBoRsp_zxy>();
		int page=0;
		page=(toReq.getPage()-1)*toReq.getRows();
		toReq.setPage(page);
		int total=noticedao.totalToNoticeinfo(toReq);
		if(total < 1){
			return rsp;
		}else{
			List<ToNoticeBoRsp_zxy> list=noticedao.getNOReadNotice(toReq);
			rsp.setRows(list);
			rsp.setTotal(total);
			return rsp;
		}
	}
	public int ReadNotice(int id) {
		// TODO Auto-generated method stub
		return noticedao.ReadNotice(id);
	}
	public Tonotice_zxy getToNoticeById(int id) {
		// TODO Auto-generated method stub
		return noticedao.getToNoticeById(id);
	}
	public int deleteToNotice(int id) {
		// TODO Auto-generated method stub
		return noticedao.deleteToNotice(id);
	}
	public List<ToNoticeBoRsp_zxy> getAllTonoticelist(ToNoticeReqBo_zxy toReq) {
		// TODO Auto-generated method stub
		return noticedao.getAllTonoticelist(toReq);
	}
	public int TotalTonotice(ToNoticeReqBo_zxy toReq) {
		// TODO Auto-generated method stub
		return noticedao.TotalTonotice(toReq);
	}
	public PageInfoBo<ToNoticeBoRsp_zxy> getAllTonoticelistByPage(ToNoticeReqBo_zxy toReq) {
		// TODO Auto-generated method stub
		PageInfoBo<ToNoticeBoRsp_zxy> rsp=new PageInfoBo<ToNoticeBoRsp_zxy>();
		int page=0;
		page=(toReq.getPage()-1)*toReq.getRows();
		toReq.setPage(page);
		int total=noticedao.TotalTonotice(toReq);
		if(total < 1){
			return rsp;
		}else{
			List<ToNoticeBoRsp_zxy> list=noticedao.getAllTonoticelist(toReq);
			rsp.setRows(list);
			rsp.setTotal(total);
			return rsp;
		}
	}
	public List<NoticeReqBO2_zxy> getTeaClassSJByTeaid(int id) {
		// TODO Auto-generated method stub
		return noticedao.getTeaClassSJByTeaid(id);
	}
	@Override
	public List<StudentInfoPO_zxy> getAllStuByClassId(int classid) {
		// TODO Auto-generated method stub
		return noticedao.getAllStuByClassId(classid);
	}
	
}
