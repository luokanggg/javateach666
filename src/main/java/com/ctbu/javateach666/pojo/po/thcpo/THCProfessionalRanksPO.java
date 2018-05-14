package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCProfessionalRanksPO extends DataEntity<THCProfessionalRanksPO> implements Serializable{
	private static final long serialVersionUID = 3787330992027304405L;
	private Integer id;
	private Integer prof_person_id;//求职人id
	private String prof_person_name;//求职人姓名
	private String prof_saltv;//求职职称
	private String prof_reason;//求职原因
	private String prof_fk_reason;//反馈的原因
	private Integer is_prof;//是否升职成功（0-失败，1-成功，2-未处理）
	private String prof_time;//求职时间
	private Integer approve_id;//审批人id
	private String approve_name;//审批人姓名
	private String approve_time;//审批时间
	private Integer is_approve;//是否审核
	private String now_prof_saltv;//现任职称
	
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
	public String getProf_time() {
		return prof_time;
	}
	public void setProf_time(String prof_time) {
		this.prof_time = prof_time;
	}
	public String getApprove_time() {
		return approve_time;
	}
	public void setApprove_time(String approve_time) {
		this.approve_time = approve_time;
	}
	public String getApprove_name() {
		return approve_name;
	}
	public void setApprove_name(String approve_name) {
		this.approve_name = approve_name;
	}
	public String getNow_prof_saltv() {
		return now_prof_saltv;
	}
	public void setNow_prof_saltv(String now_prof_saltv) {
		this.now_prof_saltv = now_prof_saltv;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProf_person_id() {
		return prof_person_id;
	}
	public void setProf_person_id(Integer prof_person_id) {
		this.prof_person_id = prof_person_id;
	}
	public Integer getIs_prof() {
		return is_prof;
	}
	public void setIs_prof(Integer is_prof) {
		this.is_prof = is_prof;
	}
	public Integer getApprove_id() {
		return approve_id;
	}
	public void setApprove_id(Integer approve_id) {
		this.approve_id = approve_id;
	}
	public Integer getIs_approve() {
		return is_approve;
	}
	public void setIs_approve(Integer is_approve) {
		this.is_approve = is_approve;
	}
	
}
