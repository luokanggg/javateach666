package com.ctbu.javateach666.pojo.po.questions;

import java.util.Date;

import com.ctbu.javateach666.common.entity.DataEntity;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;

/**
 * 填空题题库
 * @author king
 *
 */
public class Completion extends DataEntity<Completion>{

	private static final long serialVersionUID = 1L;
	
	private THCCoursePO course;			// 课程
	private String courseId;			// 课程id
	private String couname;				// 课程名
	private String completionTitle;		// 填空题目
	private String completionAnswer;	// 填空答案
	private String degree;				// 题目难度系数
	private Date createTime;			// 创建时间
	
	// 查询条件
	private Date bTime;				// 查询开始时间
	private Date eTime;				// 查询结束时间 
	private Integer teaId;			// 教师id 
	public String getCompletionTitle() {
		return completionTitle;
	}
	public void setCompletionTitle(String completionTitle) {
		this.completionTitle = completionTitle;
	}
	public String getCompletionAnswer() {
		return completionAnswer;
	}
	public void setCompletionAnswer(String completionAnswer) {
		this.completionAnswer = completionAnswer;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getbTime() {
		return bTime;
	}
	public void setbTime(Date bTime) {
		this.bTime = bTime;
	}
	public Date geteTime() {
		return eTime;
	}
	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}
	public THCCoursePO getCourse() {
		return course;
	}
	public void setCourse(THCCoursePO course) {
		this.course = course;
	}
	public String getCouname() {
		return couname;
	}
	public void setCouname(String couname) {
		this.couname = couname;
	}
	public Integer getTeaId() {
		return teaId;
	}
	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
}
