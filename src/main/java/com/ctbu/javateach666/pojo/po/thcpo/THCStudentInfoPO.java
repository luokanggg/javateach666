package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCStudentInfoPO extends DataEntity<THCStudentInfoPO> implements Serializable{

	private static final long serialVersionUID = -6593775917708688391L;
	
	private Integer id;
	private String stuno;//学生编号
	private String stuname;//学生姓名
	private String stusex;//性别
	private int stuage;//年龄
	private String stuimage;//头像
	private int classyear;//学年
	private int classid;//班级id
	private String major;//专业
	private String college;//学院
	private String stuphone;//电话
	private String political;//政治面貌
	private String nation;//民族
	private String classname;//班级名称
	private int isdelete;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getStusex() {
		return stusex;
	}
	public void setStusex(String stusex) {
		this.stusex = stusex;
	}
	public int getStuage() {
		return stuage;
	}
	public void setStuage(int stuage) {
		this.stuage = stuage;
	}
	public String getStuimage() {
		return stuimage;
	}
	public void setStuimage(String stuimage) {
		this.stuimage = stuimage;
	}
	public int getClassyear() {
		return classyear;
	}
	public void setClassyear(int classyear) {
		this.classyear = classyear;
	}
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getStuphone() {
		return stuphone;
	}
	public void setStuphone(String stuphone) {
		this.stuphone = stuphone;
	}
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	
}
