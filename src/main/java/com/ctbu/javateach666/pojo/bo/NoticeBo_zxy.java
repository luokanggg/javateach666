package com.ctbu.javateach666.pojo.bo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class NoticeBo_zxy {
	public int id;
	
	public int notid;
	
	public String tonotname;//被通知人姓名
	
	private int page;
	private int rows;
	
	public String tonotid;
	
	public String nottype;
	public String notname;
	
	public String nottitle;
	
	public String notcontent;
	
	public String noturl;
	//@JSONField(format = "yyyy-MM-dd")
	public String starttime;
	
	//@JSONField(format = "yyyy-MM-dd")
	public String endtime;

	
	public int is_delete;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public int getNotid() {
		return notid;
	}

	public void setNotid(int notid) {
		this.notid = notid;
	}

	public String getTonotname() {
		return tonotname;
	}

	public void setTonotname(String tonotname) {
		this.tonotname = tonotname;
	}
	public String getTonotid() {
		return tonotid;
	}

	public void setTonotid(String tonotid) {
		this.tonotid = tonotid;
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

	
	
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
}
