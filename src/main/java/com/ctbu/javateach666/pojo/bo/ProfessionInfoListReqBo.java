package com.ctbu.javateach666.pojo.bo;

public class ProfessionInfoListReqBo {
	private int page;
	private int rows;
	public String prof_saltv;//申请职位
	public String approve_id;//审批人编号
	public String prof_person_id;//求职人编号。当前登录人ID
	
	public String getProf_person_id() {
		return prof_person_id;
	}
	public void setProf_person_id(String prof_person_id) {
		this.prof_person_id = prof_person_id;
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

	public String getProf_saltv() {
		return prof_saltv;
	}
	public void setProf_saltv(String prof_saltv) {
		this.prof_saltv = prof_saltv;
	}
	public String getApprove_id() {
		return approve_id;
	}
	public void setApprove_id(String approve_id) {
		this.approve_id = approve_id;
	}
	
}
