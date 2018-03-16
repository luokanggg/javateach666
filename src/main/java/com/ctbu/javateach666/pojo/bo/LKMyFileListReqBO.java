package com.ctbu.javateach666.pojo.bo;



public class LKMyFileListReqBO {
	private int page;
	private int rows;
	private int ownid;
	private String accname;
	private String beforeuploadtime;
	private String afteruploadtime;
	
	public int getOwnid() {
		return ownid;
	}
	public void setOwnid(int ownid) {
		this.ownid = ownid;
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
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
	}
	public String getBeforeuploadtime() {
		return beforeuploadtime;
	}
	public void setBeforeuploadtime(String beforeuploadtime) {
		this.beforeuploadtime = beforeuploadtime;
	}
	public String getAfteruploadtime() {
		return afteruploadtime;
	}
	public void setAfteruploadtime(String afteruploadtime) {
		this.afteruploadtime = afteruploadtime;
	}
}
