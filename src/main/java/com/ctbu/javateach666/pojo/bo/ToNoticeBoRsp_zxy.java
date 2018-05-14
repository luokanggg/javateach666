package com.ctbu.javateach666.pojo.bo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ToNoticeBoRsp_zxy {
	public int id;
	public String tonotname;//被通知人姓名
	public String endtime;
	public String starttime;
	public int is_look;
	public String nottype;
	public String notname;
	public String nottitle;
	public String notcontent;
	public String noturl;
	public int is_delete;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTonotname() {
		return tonotname;
	}
	public void setTonotname(String tonotname) {
		this.tonotname = tonotname;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public int getIs_look() {
		return is_look;
	}
	public void setIs_look(int is_look) {
		this.is_look = is_look;
	}
	public String getNottype() {
		return nottype;
	}
	public void setNottype(String nottype) {
		this.nottype = nottype;
	}
	public String getNotname() {
		return notname;
	}
	public void setNotname(String notname) {
		this.notname = notname;
	}
	public String getNottitle() {
		return nottitle;
	}
	public void setNottitle(String nottitle) {
		this.nottitle = nottitle;
	}
	public String getNotcontent() {
		return notcontent;
	}
	public void setNotcontent(String notcontent) {
		this.notcontent = notcontent;
	}
	public String getNoturl() {
		return noturl;
	}
	public void setNoturl(String noturl) {
		this.noturl = noturl;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
	
}
