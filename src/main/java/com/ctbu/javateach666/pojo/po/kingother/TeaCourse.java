package com.ctbu.javateach666.pojo.po.kingother;

import com.ctbu.javateach666.common.entity.DataEntity;
import com.ctbu.javateach666.pojo.po.thcpo.THCCoursePO;

/**
 * 教师课程关系表
 * @author king
 *
 */
public class TeaCourse extends DataEntity<TeaCourse>{
	private static final long serialVersionUID = 1L;
	
	private Integer teaid;				// 教师id
	private Integer coutime;			// 上课时间
	private Integer couhour;			// 课时	
	private Integer coufhour;
	private double score;
	private Integer evaluate;
	private String teaname;
	private String couname;
	private THCCoursePO course;			// 课程
	private double rescore;
	
	
	public Integer getTeaid() {
		return teaid;
	}
	public void setTeaid(Integer teaid) {
		this.teaid = teaid;
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
	
}
