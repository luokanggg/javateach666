package com.ctbu.javateach666.pojo.po.questions;

import java.util.Date;

import com.ctbu.javateach666.common.entity.DataEntity;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;

/**
 * 多选题题库
 * @author king
 *
 */
public class MultipleChoice extends DataEntity<MultipleChoice>{

	private static final long serialVersionUID = 1L;
	
	private THCCoursePO course;		// 课程
	private String courseId;		// 课程id
	private String couname;			// 课程名
	private String multipleTitle;	// 多选题目
	private String answera;			// A选项
	private String answerb;			// B选项
	private String answerc;			// C选项
	private String answerd;			// D选项
	private String multipleAnswer;	// 多选答案
	private String degree;			// 题目难度系数
	private Date createTime;		// 创建时间
	
	// 查询条件
	private Date bTime;				// 查询开始时间
	private Date eTime;				// 查询结束时间 
	private Integer teaId;			// 教师id 
	
	public String getMultipleTitle() {
		return multipleTitle;
	}
	public void setMultipleTitle(String multipleTitle) {
		this.multipleTitle = multipleTitle;
	}
	public String getMultipleAnswer() {
		return multipleAnswer;
	}
	public void setMultipleAnswer(String multipleAnswer) {
		this.multipleAnswer = multipleAnswer;
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
	public String getAnswera() {
		return answera;
	}
	public void setAnswera(String answera) {
		this.answera = answera;
	}
	public String getAnswerb() {
		return answerb;
	}
	public void setAnswerb(String answerb) {
		this.answerb = answerb;
	}
	public String getAnswerc() {
		return answerc;
	}
	public void setAnswerc(String answerc) {
		this.answerc = answerc;
	}
	public String getAnswerd() {
		return answerd;
	}
	public void setAnswerd(String answerd) {
		this.answerd = answerd;
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
