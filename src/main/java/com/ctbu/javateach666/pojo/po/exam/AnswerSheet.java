package com.ctbu.javateach666.pojo.po.exam;

import com.ctbu.javateach666.common.entity.DataEntity;

/**
 * 答题卡
 * @author king
 *
 */
public class AnswerSheet extends DataEntity<AnswerSheet> {

	private static final long serialVersionUID = 1L;
	
	private Integer stuId;				// 学生id
	private Integer teaId;				// 教师id
	private Integer examId;				// 试卷id
	private String singleAnswer;		// 单选答案
	private String multipleAnswer;		// 多选答案
	private String judgmentAnswer;		// 判断答案
	private String completionAnswer;	// 填空答案
	private String completionComment;	// 教师备注
	private String subjectiveAnswer;	// 主观答案
	private String subjectiveComment;	// 教师备注
	private String state;				// 阅卷状态（0未阅览，1已阅览）
	private Integer couyear;			// 学年
	private Integer semester;			// 学期
	
	private String stuName;				// 学生姓名
	private String examName;			// 试卷名
	
	// 查询条件
	
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public String getSingleAnswer() {
		return singleAnswer;
	}
	public void setSingleAnswer(String singleAnswer) {
		this.singleAnswer = singleAnswer;
	}
	public String getMultipleAnswer() {
		return multipleAnswer;
	}
	public void setMultipleAnswer(String multipleAnswer) {
		this.multipleAnswer = multipleAnswer;
	}
	public String getJudgmentAnswer() {
		return judgmentAnswer;
	}
	public void setJudgmentAnswer(String judgmentAnswer) {
		this.judgmentAnswer = judgmentAnswer;
	}
	public String getCompletionAnswer() {
		return completionAnswer;
	}
	public void setCompletionAnswer(String completionAnswer) {
		this.completionAnswer = completionAnswer;
	}
	public String getCompletionComment() {
		return completionComment;
	}
	public void setCompletionComment(String completionComment) {
		this.completionComment = completionComment;
	}
	public String getSubjectiveAnswer() {
		return subjectiveAnswer;
	}
	public void setSubjectiveAnswer(String subjectiveAnswer) {
		this.subjectiveAnswer = subjectiveAnswer;
	}
	public String getSubjectiveComment() {
		return subjectiveComment;
	}
	public void setSubjectiveComment(String subjectiveComment) {
		this.subjectiveComment = subjectiveComment;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public Integer getTeaId() {
		return teaId;
	}
	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
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
