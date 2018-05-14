package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCClassPO extends DataEntity<THCClassPO> implements Serializable{

	private static final long serialVersionUID = -7944489728519277942L;
	private Integer id;
	private String claname;
	private String college;
	private String major;
	private int classyear;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClaname() {
		return claname;
	}
	public void setClaname(String claname) {
		this.claname = claname;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getClassyear() {
		return classyear;
	}
	public void setClassyear(int classyear) {
		this.classyear = classyear;
	}
	
}
