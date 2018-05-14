package com.ctbu.javateach666.pojo.po;

import java.io.Serializable;
import java.util.Date;

public class TeacherInfo_zxy implements Serializable{
	public int id;
	
	public String teaname;//教师姓名
	
	public int teaage;
	
	public String email;
	
	public String teano;//教师编号
	
	public Date joined_date;//入职日期
	
	public String professional;//职称
	
	public String prosonal_statement;//个人简介
	
	public String teaphone;//电话
	
	public String teaimage;//头像
	
	public String teasex;
	
	public String teacollage;//学院
	
	public String teanation;//名族
	
	public String political;//政治面貌
	
	public String major;
	

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeaname() {
		return teaname;
	}

	public void setTeaname(String teaname) {
		this.teaname = teaname;
	}

	public int getTeaage() {
		return teaage;
	}

	public void setTeaage(int teaage) {
		this.teaage = teaage;
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


	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
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

	public String getTeacollage() {
		return teacollage;
	}

	public void setTeacollage(String teacollage) {
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

	public Date getJoined_date() {
		return joined_date;
	}

	public void setJoined_date(Date joined_date) {
		this.joined_date = joined_date;
	}

	public String getProsonal_statement() {
		return prosonal_statement;
	}

	public void setProsonal_statement(String prosonal_statement) {
		this.prosonal_statement = prosonal_statement;
	}
	
	
}
