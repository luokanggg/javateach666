package com.ctbu.javateach666.pojo.po;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfessionalRanks_zxy implements Serializable {
	
	public int id;
	
	public String prof_person_id="";//申请人编号
	
	public String prof_person_name="";//申请人姓名
	
	public String prof_saltv="";//申请的职称
	
	public String prof_reason="";//申请理由
	
	public String prof_fk_reason="";//反馈
	
	public int is_prof;//是否申请成功  0失败 1成功2未知
	
	public Date prof_time;//申请的时间
	
	public String approve_id="";//审批人编号
	
	public String approve_name="";//审批人姓名
	
	//Date date=new Date("9999-01-01");
	//SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	
	public Date approve_time;
	
	public int is_approve;//是否审批 0未审批

    public String now_prof_saltv="";
	
	public ProfessionalRanks_zxy(){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		approve_time=date;
	}
	
	public String getNow_prof_saltv() {
		return now_prof_saltv;
	}

	public void setNow_prof_saltv(String now_prof_saltv) {
		this.now_prof_saltv = now_prof_saltv;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProf_person_id() {
		return prof_person_id;
	}

	public void setProf_person_id(String prof_person_id) {
		this.prof_person_id = prof_person_id;
	}

	public String getProf_person_name() {
		return prof_person_name;
	}

	public void setProf_person_name(String prof_person_name) {
		this.prof_person_name = prof_person_name;
	}

	public String getProf_saltv() {
		return prof_saltv;
	}

	public void setProf_saltv(String prof_saltv) {
		this.prof_saltv = prof_saltv;
	}

	public String getProf_reason() {
		return prof_reason;
	}

	public void setProf_reason(String prof_reason) {
		this.prof_reason = prof_reason;
	}

	public String getProf_fk_reason() {
		return prof_fk_reason;
	}

	public void setProf_fk_reason(String prof_fk_reason) {
		this.prof_fk_reason = prof_fk_reason;
	}

	public int getIs_prof() {
		return is_prof;
	}

	public void setIs_prof(int is_prof) {
		this.is_prof = is_prof;
	}

	public Date getProf_time() {
		return prof_time;
	}

	public void setProf_time(Date prof_time) {
		this.prof_time = prof_time;
	}

	public String getApprove_id() {
		return approve_id;
	}

	public void setApprove_id(String approve_id) {
		this.approve_id = approve_id;
	}

	public String getApprove_name() {
		return approve_name;
	}

	public void setApprove_name(String approve_name) {
		this.approve_name = approve_name;
	}

	public Date getApprove_time() {
		return approve_time;
	}

	public void setApprove_time(Date approve_time) {
		this.approve_time = approve_time;
	}

	public int getIs_approve() {
		return is_approve;
	}

	public void setIs_approve(int is_approve) {
		this.is_approve = is_approve;
	}
	
}
