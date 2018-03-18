package com.ctbu.javateach666.pojo.po;

import java.util.Date;

public class LKAccessoryPO {
	private int id;
	private int ownid;
	private String accname;
	private String accurl;
	private int acctype;
	private Date uploadtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOwnid() {
		return ownid;
	}
	public void setOwnid(int ownid) {
		this.ownid = ownid;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
	}
	public String getAccurl() {
		return accurl;
	}
	public void setAccurl(String accurl) {
		this.accurl = accurl;
	}
	public int getAcctype() {
		return acctype;
	}
	public void setAcctype(int acctype) {
		this.acctype = acctype;
	}
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
}
