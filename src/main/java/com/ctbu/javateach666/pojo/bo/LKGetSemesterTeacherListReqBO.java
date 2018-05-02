package com.ctbu.javateach666.pojo.bo;

public class LKGetSemesterTeacherListReqBO {
	private int id;
	private int teaid;
	private int stuid;
	private int couyear;
	private int semester;
	private int page;
	private int rows;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeaid() {
		return teaid;
	}
	public void setTeaid(int teaid) {
		this.teaid = teaid;
	}
	public int getStuid() {
		return stuid;
	}
	public void setStuid(int stuid) {
		this.stuid = stuid;
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
