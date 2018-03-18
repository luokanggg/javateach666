package com.ctbu.javateach666.pojo.bo;

public class LKChooseClassOnlineListReqBO {
	private int page;
	private int rows;
	private int couyear;
	private int semester;
	private String couname;
	private String teaname;
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
	public int getCouyear() {
		return couyear;
	}
	public void setCouyear(int couyear) {
		this.couyear = couyear;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getCouname() {
		return couname;
	}
	public void setCouname(String couname) {
		this.couname = couname;
	}
	public String getTeaname() {
		return teaname;
	}
	public void setTeaname(String teaname) {
		this.teaname = teaname;
	}

}
