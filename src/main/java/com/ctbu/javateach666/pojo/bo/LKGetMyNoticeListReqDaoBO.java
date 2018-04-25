package com.ctbu.javateach666.pojo.bo;

import java.util.Date;

public class LKGetMyNoticeListReqDaoBO {
	private int id;
	private int nottype;
	private Date nowtime;
	private int page;
	private int rows;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNottype() {
		return nottype;
	}
	public void setNottype(int nottype) {
		this.nottype = nottype;
	}
	public Date getNowtime() {
		return nowtime;
	}
	public void setNowtime(Date nowtime) {
		this.nowtime = nowtime;
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
