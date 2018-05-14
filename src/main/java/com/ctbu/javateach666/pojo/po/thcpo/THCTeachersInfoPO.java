package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;
import java.util.Date;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCTeachersInfoPO extends DataEntity<THCTeachersInfoPO> implements Serializable{

	private static final long serialVersionUID = -8163739882225639971L;
	private Integer id;
	private String teaname;//教师姓名
	private String email;//邮箱
	private Integer teaage;//年龄
	private String teano;//教师编号
	private String joined_date;//入职时间
	private String professional;//职称
	private String prosonal_statement;//个人说明
	private String teaphone;//电话
	private String teaimage;//头像
	private String teasex;//性别
	private String teacollage;//学院
	private String teanation;//民族
	private String political;//政治面貌
	private int isdelete;//逻辑删除

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTeaage() {
		return teaage;
	}
	public void setTeaage(Integer teaage) {
		this.teaage = teaage;
	}
	public String getTeaname() {
		return teaname;
	}
	public void setTeaname(String teaname) {
		this.teaname = teaname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTeano() {
		return teano;
	}
	public void setTeano(String teano) {
		this.teano = teano;
	}
	public String getJoined_date() {
		return joined_date;
	}
	public void setJoined_date(String joined_date) {
		this.joined_date = joined_date;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getProsonal_statement() {
		return prosonal_statement;
	}
	public void setProsonal_statement(String prosonal_statement) {
		this.prosonal_statement = prosonal_statement;
	}
	public String getTeaphone() {
		return teaphone;
	}
	public void setTeaphone(String teaphone) {
		this.teaphone = teaphone;
	}
	public String getTeaimage() {
		return teaimage;
	}
	public void setTeaimage(String teaimage) {
		this.teaimage = teaimage;
	}
	public String getTeasex() {
		return teasex;
	}
	public void setTeasex(String teasex) {
		this.teasex = teasex;
	}
	public String getteacollage() {
		return teacollage;
	}
	public void setteacollage(String teacollage) {
		this.teacollage = teacollage;
	}
	public String getTeanation() {
		return teanation;
	}
	public void setTeanation(String teanation) {
		this.teanation = teanation;
	}
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	
	
}
