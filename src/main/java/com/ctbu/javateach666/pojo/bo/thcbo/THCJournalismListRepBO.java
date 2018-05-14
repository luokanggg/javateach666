package com.ctbu.javateach666.pojo.bo.thcbo;

public class THCJournalismListRepBO {
	private int page;
	private int rows;
	private String joutitle;
	private String joucontent;
	private int j_type;
	private String dtype;
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public int getJ_type() {
		return j_type;
	}
	public void setJ_type(int j_type) {
		this.j_type = j_type;
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
	public String getJoutitle() {
		return joutitle;
	}
	public void setJoutitle(String joutitle) {
		this.joutitle = joutitle;
	}
	public String getJoucontent() {
		return joucontent;
	}
	public void setJoucontent(String joucontent) {
		this.joucontent = joucontent;
	}
}
