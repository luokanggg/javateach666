package com.ctbu.javateach666.pojo.po.exam;

import com.ctbu.javateach666.common.entity.DataEntity;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;


/**
 * 学生成绩表
 * @author king
 *
 */
public class Achievement extends DataEntity<Achievement>{

	private static final long serialVersionUID = 1L;
	
	private Integer stuId;				// 学生id
	private String stuName;				// 学生姓名
	private Integer teaId;				// 教师id
	private Integer score;				// 总成绩
	private Integer paperId;			// 试卷id
	private String paperName;			// 试卷名
	private Integer singleScore;		// 单选分数
	private Integer multipleScore;		// 多选分数
	private Integer judgmentScore;		// 判断分数
	private Integer completionScore;	// 填空分数
	private Integer subjectiveScore;	// 主观分数
	private Integer objectiveScore;		// 客观分数
	private THCCoursePO course;			// 课程信息
	private String coursename;			// 课程名
	private Integer couyear;			// 学年
	private Integer semester;			// 学期
	
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Integer getTeaId() {
		return teaId;
	}
	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Integer getSingleScore() {
		return singleScore;
	}
	public void setSingleScore(Integer singleScore) {
		this.singleScore = singleScore;
	}
	public Integer getMultipleScore() {
		return multipleScore;
	}
	public void setMultipleScore(Integer multipleScore) {
		this.multipleScore = multipleScore;
	}
	public Integer getJudgmentScore() {
		return judgmentScore;
	}
	public void setJudgmentScore(Integer judgmentScore) {
		this.judgmentScore = judgmentScore;
	}
	public Integer getCompletionScore() {
		return completionScore;
	}
	public void setCompletionScore(Integer completionScore) {
		this.completionScore = completionScore;
	}
	public Integer getSubjectiveScore() {
		return subjectiveScore;
	}
	public void setSubjectiveScore(Integer subjectiveScore) {
		this.subjectiveScore = subjectiveScore;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Integer getObjectiveScore() {
		return objectiveScore;
	}
	public void setObjectiveScore(Integer objectiveScore) {
		this.objectiveScore = objectiveScore;
	}
	public THCCoursePO getCourse() {
		return course;
	}
	public void setCourse(THCCoursePO course) {
		this.course = course;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
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
