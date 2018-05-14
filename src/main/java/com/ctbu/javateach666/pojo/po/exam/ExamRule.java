package com.ctbu.javateach666.pojo.po.exam;

import com.ctbu.javateach666.common.entity.DataEntity;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;


/**
 * 试卷规则
 * @author king
 *
 */
public class ExamRule extends DataEntity<ExamRule>{

	private static final long serialVersionUID = 1L;
	
	private THCCoursePO course;				// 课程
	private String couname;					// 课程名
	private String ruleName;				// 规则名
	private String singleRules;				// 单选规则
	private Integer singleNum;				// 单选数量
	private Integer singleScore;			// 单选分数
	private String multipleRules;			// 多选规则
	private Integer multipleNum;			// 多选数量
	private Integer multipleScore;			// 多选分数
	private String judgmentRules;			// 判断规则
	private Integer judgmentNum;			// 判断数量
	private Integer judgmentScore;			// 判断分数
	private String completionRules;			// 填空规则
	private Integer completionNum;			// 填空数量
	private Integer completionScore;		// 填空分数
	private String subjectiveRules;			// 主观规则
	private Integer subjectiveNum;			// 主观数量
	private Integer subjectiveScore;		// 主观分数
	private Integer allScore;				// 总分数
	private Integer ruleType;				// 规则类型(1手动组卷 2自动组卷)
	
	// 查询条件
	private Integer teaId;					// 教师id 
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getSingleRules() {
		return singleRules;
	}
	public void setSingleRules(String singleRules) {
		this.singleRules = singleRules;
	}
	public Integer getSingleNum() {
		return singleNum;
	}
	public void setSingleNum(Integer singleNum) {
		this.singleNum = singleNum;
	}
	public Integer getSingleScore() {
		return singleScore;
	}
	public void setSingleScore(Integer singleScore) {
		this.singleScore = singleScore;
	}
	public String getMultipleRules() {
		return multipleRules;
	}
	public void setMultipleRules(String multipleRules) {
		this.multipleRules = multipleRules;
	}
	public Integer getMultipleNum() {
		return multipleNum;
	}
	public void setMultipleNum(Integer multipleNum) {
		this.multipleNum = multipleNum;
	}
	public Integer getMultipleScore() {
		return multipleScore;
	}
	public void setMultipleScore(Integer multipleScore) {
		this.multipleScore = multipleScore;
	}
	public String getJudgmentRules() {
		return judgmentRules;
	}
	public void setJudgmentRules(String judgmentRules) {
		this.judgmentRules = judgmentRules;
	}
	public Integer getJudgmentNum() {
		return judgmentNum;
	}
	public void setJudgmentNum(Integer judgmentNum) {
		this.judgmentNum = judgmentNum;
	}
	public Integer getJudgmentScore() {
		return judgmentScore;
	}
	public void setJudgmentScore(Integer judgmentScore) {
		this.judgmentScore = judgmentScore;
	}
	public String getCompletionRules() {
		return completionRules;
	}
	public void setCompletionRules(String completionRules) {
		this.completionRules = completionRules;
	}
	public Integer getCompletionNum() {
		return completionNum;
	}
	public void setCompletionNum(Integer completionNum) {
		this.completionNum = completionNum;
	}
	public Integer getCompletionScore() {
		return completionScore;
	}
	public void setCompletionScore(Integer completionScore) {
		this.completionScore = completionScore;
	}
	public String getSubjectiveRules() {
		return subjectiveRules;
	}
	public void setSubjectiveRules(String subjectiveRules) {
		this.subjectiveRules = subjectiveRules;
	}
	public Integer getSubjectiveNum() {
		return subjectiveNum;
	}
	public void setSubjectiveNum(Integer subjectiveNum) {
		this.subjectiveNum = subjectiveNum;
	}
	public Integer getSubjectiveScore() {
		return subjectiveScore;
	}
	public void setSubjectiveScore(Integer subjectiveScore) {
		this.subjectiveScore = subjectiveScore;
	}
	public Integer getAllScore() {
		return allScore;
	}
	public void setAllScore(Integer allScore) {
		this.allScore = allScore;
	}
	public Integer getRuleType() {
		return ruleType;
	}
	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
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
	
}
