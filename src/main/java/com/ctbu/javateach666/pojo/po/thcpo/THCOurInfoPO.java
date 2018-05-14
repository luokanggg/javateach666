package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCOurInfoPO extends DataEntity<THCOurInfoPO> implements Serializable{

	private static final long serialVersionUID = -2863567703196138857L;
	private Integer id;
	private String mmajor;//专业
	private String mclass;//班级
	private String mname;//姓名
	private String mclassno;//学号
	private String msign;//个性签名
	private String mimg;//头像
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMmajor() {
		return mmajor;
	}
	public void setMmajor(String mmajor) {
		this.mmajor = mmajor;
	}
	public String getMclass() {
		return mclass;
	}
	public void setMclass(String mclass) {
		this.mclass = mclass;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMclassno() {
		return mclassno;
	}
	public void setMclassno(String mclassno) {
		this.mclassno = mclassno;
	}
	public String getMsign() {
		return msign;
	}
	public void setMsign(String msign) {
		this.msign = msign;
	}
	public String getMimg() {
		return mimg;
	}
	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
}
