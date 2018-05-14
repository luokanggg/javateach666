package com.ctbu.javateach666.pojo.bo.thcbo;

public class THCCourseListRepBO {
	private int page;
	private int rows;
	private String couname;
	private String ctype;
	public int getPage() {
		return page;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
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
	public String getCouname() {
		return couname;
	}
	public void setCouname(String couname) {
		this.couname = couname;
	}
}
