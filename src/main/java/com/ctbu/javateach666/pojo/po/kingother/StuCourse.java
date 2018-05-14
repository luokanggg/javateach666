package com.ctbu.javateach666.pojo.po.kingother;

import com.ctbu.javateach666.common.entity.DataEntity;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;

/**
 * 学生课程关系表
 * @author king
 *
 */
public class StuCourse extends DataEntity<StuCourse>{
	private static final long serialVersionUID = 1L;
	
	private Integer stuid;				// 学生id
	private Integer teacourseid;
	private Integer coutime;
	private Integer couhour;
	private Integer coufhour;
	private double score;
	private Integer evaluate;
	private String teaname;
	private String couname;
	private THCCoursePO course;			// 课程
	private double rescore;
	
	// 查询条件
	private Integer couyear;			// 学年
	private Integer semester;			// 学期 
	
	public Integer getStuid() {
		return stuid;
	}
	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}
	public Integer getTeacourseid() {
		return teacourseid;
	}
	public void setTeacourseid(Integer teacourseid) {
		this.teacourseid = teacourseid;
	}
	public Integer getCoutime() {
		return coutime;
	}
	public void setCoutime(Integer coutime) {
		this.coutime = coutime;
	}
	public Integer getCouhour() {
		return couhour;
	}
	public void setCouhour(Integer couhour) {
		this.couhour = couhour;
	}
	public Integer getCoufhour() {
		return coufhour;
	}
	public void setCoufhour(Integer coufhour) {
		this.coufhour = coufhour;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public Integer getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}
	public String getTeaname() {
		return teaname;
	}
	public void setTeaname(String teaname) {
		this.teaname = teaname;
	}
	public String getCouname() {
		return couname;
	}
	public void setCouname(String couname) {
		this.couname = couname;
	}
	public double getRescore() {
		return rescore;
	}
	public void setRescore(double rescore) {
		this.rescore = rescore;
	}
	public THCCoursePO getCourse() {
		return course;
	}
	public void setCourse(THCCoursePO course) {
		this.course = course;
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
