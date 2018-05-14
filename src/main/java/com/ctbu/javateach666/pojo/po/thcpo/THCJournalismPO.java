package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;
import java.util.Date;

import com.ctbu.javateach666.common.entity.DataEntity;
import com.ctbu.javateach666.pojo.po.exam.ReleaseExam;

public class THCJournalismPO extends DataEntity<THCJournalismPO> implements Serializable{
	
	private static final long serialVersionUID = 909209112457930372L;
	
	private Integer id;//主页信息id
	private String joutitle;//标题
	private String joucontent;//内容
	private int joutype;//新闻类型
	private Date starttime;//通知创建时间
	private Date endtime;//通知过期时间
	private int pubid;//发布人id
	private int j_type;//1-新闻  2-公告
	private int is_delete;//逻辑删除

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public int getJoutype() {
		return joutype;
	}
	public void setJoutype(int joutype) {
		this.joutype = joutype;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public int getPubid() {
		return pubid;
	}
	public void setPubid(int pubid) {
		this.pubid = pubid;
	}
	public int getJ_type() {
		return j_type;
	}
	public void setJ_type(int j_type) {
		this.j_type = j_type;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
}
