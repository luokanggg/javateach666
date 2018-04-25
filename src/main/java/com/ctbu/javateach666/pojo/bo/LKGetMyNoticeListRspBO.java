package com.ctbu.javateach666.pojo.bo;

import java.util.Date;

public class LKGetMyNoticeListRspBO {
	private String notname;
	private String nottitle;
	private String noturl;
	private String notcontent;
	private Date starttime;
	private Date endtime;
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
	public String getNoturl() {
		return noturl;
	}
	public void setNoturl(String noturl) {
		this.noturl = noturl;
	}
	public String getNotcontent() {
		return notcontent;
	}
	public void setNotcontent(String notcontent) {
		this.notcontent = notcontent;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
}
