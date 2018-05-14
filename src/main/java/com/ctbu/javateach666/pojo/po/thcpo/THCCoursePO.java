package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCCoursePO extends DataEntity<THCCoursePO> implements Serializable{
	
	private static final long serialVersionUID = -5891552672907938306L;
	private Integer id;
	private String couname;//课程名称
	private Integer ctype;//课程类型（0-必修课，1-选修课）
	private double credit;//学分
	private int is_delete;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public String getCouname() {
		return couname;
	}
	public void setCouname(String couname) {
		this.couname = couname;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
}
