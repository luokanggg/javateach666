package com.ctbu.javateach666.pojo.po.exam;

import com.ctbu.javateach666.common.entity.DataEntity;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;

/**
 * 试卷信息
 * @author king
 *
 */
public class ExamPaper extends DataEntity<ExamPaper>{

	private static final long serialVersionUID = 1L;
	
	private THCCoursePO course;			// 课程
	private String couname;				// 课程名
	private String examPaperName;		// 试卷名
	private ExamRule examRule;			// 规则
	private String examRuleName;		// 规则名
	private String examScore;			// 试卷分数
	
	//查询条件
	private Integer teaId;				// 教师id 
	
	public THCCoursePO getCourse() {
		return course;
	}
	public void setCourse(THCCoursePO course) {
		this.course = course;
	}
	public String getCoursename() {
		return couname;
	}
	public void setCoursename(String couname) {
		this.couname = couname;
	}
	public String getExamPaperName() {
		return examPaperName;
	}
	public void setExamPaperName(String examPaperName) {
		this.examPaperName = examPaperName;
	}
	public String getCouname() {
		return couname;
	}
	public void setCouname(String couname) {
		this.couname = couname;
	}
	public ExamRule getExamRule() {
		return examRule;
	}
	public void setExamRule(ExamRule examRule) {
		this.examRule = examRule;
	}
	public String getExamRuleName() {
		return examRuleName;
	}
	public void setExamRuleName(String examRuleName) {
		this.examRuleName = examRuleName;
	}
	public String getExamScore() {
		return examScore;
	}
	public void setExamScore(String examScore) {
		this.examScore = examScore;
	}
	public Integer getTeaId() {
		return teaId;
	}
	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
	}
	
	
}
