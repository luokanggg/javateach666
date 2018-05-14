package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCCourseIntroducePO extends DataEntity<THCCourseIntroducePO> implements Serializable{

	private static final long serialVersionUID = -5431416603105038186L;
	
	private Integer id;
	private String cimg;//课程图片
	private String cname;//课程名
	private String csign;//课程介绍
	private Integer is_delete;//逻辑删除
	
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCimg() {
		return cimg;
	}
	public void setCimg(String cimg) {
		this.cimg = cimg;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCsign() {
		return csign;
	}
	public void setCsign(String csign) {
		this.csign = csign;
	}
	
}
