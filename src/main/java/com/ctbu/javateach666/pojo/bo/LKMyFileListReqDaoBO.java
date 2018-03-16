package com.ctbu.javateach666.pojo.bo;

import java.util.Date;

public class LKMyFileListReqDaoBO {
	private int page;
	private int rows;
	private int ownid;
	private String accname;
	private Date beforeuploadtime;
	private Date afteruploadtime;
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
	public Date getBeforeuploadtime() {
		return beforeuploadtime;
	}
	public void setBeforeuploadtime(Date beforeuploadtime) {
		this.beforeuploadtime = beforeuploadtime;
	}
	public Date getAfteruploadtime() {
		return afteruploadtime;
	}
	public void setAfteruploadtime(Date afteruploadtime) {
		this.afteruploadtime = afteruploadtime;
	}
	
}
