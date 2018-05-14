package com.ctbu.javateach666.dao;

import java.util.List;

import com.ctbu.javateach666.pojo.bo.NoticeBo_zxy;
import com.ctbu.javateach666.pojo.bo.NoticeReqBO2_zxy;
import com.ctbu.javateach666.pojo.bo.NoticeResBo_zxy;
import com.ctbu.javateach666.pojo.bo.ToNoticeBoRsp_zxy;
import com.ctbu.javateach666.pojo.bo.ToNoticeReqBo_zxy;
import com.ctbu.javateach666.pojo.po.Notice_zxy;
import com.ctbu.javateach666.pojo.po.StudentInfoPO_zxy;
import com.ctbu.javateach666.pojo.po.StudentInfo_zxy;
import com.ctbu.javateach666.pojo.po.TeacherInfo_zxy;
import com.ctbu.javateach666.pojo.po.Tonotice_zxy;

public interface NoticeDao_zxy {
	//根据Id查找学生信息
	public StudentInfo_zxy getStudentById(int id);
	//根据ID查找教师的信息
	public TeacherInfo_zxy getTeacherById(int id);
	
	//插入通知信息
	public int insertNotice(Notice_zxy notice);
	//插入被通知人的信息
	public int insertToNotice(Notice_zxy notice);
	
	//获取通知信息的总数（条件搜索）通知学生
	public int TotalALlNoticeinfoByStu(NoticeBo_zxy notice);
	//获取通知信息的总数（条件搜索）通知教师
	public int TotalALlNoticeinfoByTea(NoticeBo_zxy notice);
	
	//分页获取通知信息(被通知人是学生)
	public List<NoticeResBo_zxy> getAllNOticeBySeachAndPageStu(NoticeBo_zxy notice);
	
	//分页获取通知信息(被通知人是教师)
	public List<NoticeResBo_zxy> getAllNOticeBySeachAndPageTea(NoticeBo_zxy notice);
	
	//根据id获取通知信息
	public Notice_zxy getNoticeById(int id);
	
	//根基id删除通知信息
	public int deleteNoticeById(int id);
	
	//根据id获取被通知人信息(Stu)
	public StudentInfo_zxy getStudentInfoById(int id);
	
	//根据id获取被通知人信息(Tea)
	public TeacherInfo_zxy getTeacherInfoById(int id);
	
	//更新通知
	public int updateNotice(NoticeBo_zxy reqBO);
	//根据登录人的id查看有多少条未查看的通知记录
	public int getNoLookNoticeById(int id);
	//获取未查看信息的数量
	public int totalToNoticeinfo(ToNoticeReqBo_zxy toReq);
	//获取未阅读的所有通知信息
	public List<ToNoticeBoRsp_zxy> getNOReadNotice(ToNoticeReqBo_zxy toReq);
	//标志该记录已读
	public int ReadNotice(int id);
	//根据id查询被通知记录是否是已读，已读时不可以在标志
	public Tonotice_zxy getToNoticeById(int id);
	//根据id删除通知记录
	public int deleteToNotice(int id);
	//查询所有ToNOtice
	public List<ToNoticeBoRsp_zxy> getAllTonoticelist(ToNoticeReqBo_zxy toReq);
	//查询所有Tonotice的总数
	public int TotalTonotice(ToNoticeReqBo_zxy toReq);
	
	//查询登录人所教的班级
	public List<NoticeReqBO2_zxy> getTeaClassSJByTeaid(int id);
	
	//根据班级id查询所有的该班级的学生信息
	public List<StudentInfoPO_zxy> getAllStuByClassId(int classid);
}
