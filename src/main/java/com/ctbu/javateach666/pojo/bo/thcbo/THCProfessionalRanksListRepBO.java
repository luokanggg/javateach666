package com.ctbu.javateach666.pojo.bo.thcbo;

public class THCProfessionalRanksListRepBO {
	private int page;
	private int rows;
	private String s_is_prof;//申请结果
	private String s_prof_person_name;//求职人姓名
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
	public String getS_is_prof() {
		return s_is_prof;
	}
	public void setS_is_prof(String s_is_prof) {
		this.s_is_prof = s_is_prof;
	}
	public String getS_prof_person_name() {
		return s_prof_person_name;
	}
	public void setS_prof_person_name(String s_prof_person_name) {
		this.s_prof_person_name = s_prof_person_name;
	}

}
