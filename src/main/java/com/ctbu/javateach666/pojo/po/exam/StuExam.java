package com.ctbu.javateach666.pojo.po.exam;

import java.util.Date;

import com.ctbu.javateach666.common.entity.DataEntity;
import com.ctbu.javateach666.pojo.po.LKStucoursePO;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;

/**
 * 学生考试实体
 * @author king
 *
 */
public class StuExam extends DataEntity<StuExam>{
	
	private static final long serialVersionUID = 1L;
	
	private THCCoursePO course;			// 课程信息
	private String couname;				// 课程名
	private ReleaseExam release;		// 发布的考试
	private String examPaperName;		// 试卷名
	private Integer examTime;			// 考试时长
	private Date beginTime;				// 考试时间
	private LKStucoursePO stucourse;	// 学生课程信息
	private Integer couyear;			// 学年
	private Integer semester;			// 学期
	
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
	public ReleaseExam getRelease() {
		return release;
	}
	public void setRelease(ReleaseExam release) {
		this.release = release;
	}
	public LKStucoursePO getStucourse() {
		return stucourse;
	}
	public void setStucourse(LKStucoursePO stucourse) {
		this.stucourse = stucourse;
	}
	public String getExamPaperName() {
		return examPaperName;
	}
	public void setExamPaperName(String examPaperName) {
		this.examPaperName = examPaperName;
	}
	public Integer getExamTime() {
		return examTime;
	}
	public void setExamTime(Integer examTime) {
		this.examTime = examTime;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Integer getCouyear() {
		return couyear;
	}
	public void setCouyear(Integer couyear) {
		this.couyear = couyear;
	}
	public Integer getSemester() {
		return semester;
	}
	public void setSemester(Integer semester) {
		this.semester = semester;
	}
}
