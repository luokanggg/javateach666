package com.ctbu.javateach666.pojo.bo;

import java.util.Date;

public class LKGetChooseActivityListReqBO {
	private int pubownid;
	private String pubownname;
	private String pubname;
	private int stuid;
	private Date nowtime;
	private int page;
	private int rows;
	
	
	public int getPubownid() {
		return pubownid;
	}
	public void setPubownid(int pubownid) {
		this.pubownid = pubownid;
	}
	public int getStuid() {
		return stuid;
	}
	public void setStuid(int stuid) {
		this.stuid = stuid;
	}
	public String getPubownname() {
		return pubownname;
	}
	public void setPubownname(String pubownname) {
		this.pubownname = pubownname;
	}
	public String getPubname() {
		return pubname;
	}
	public void setPubname(String pubname) {
		this.pubname = pubname;
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
	public Date getNowtime() {
		return nowtime;
	}
	public void setNowtime(Date nowtime) {
		this.nowtime = nowtime;
	}
	
	
}
