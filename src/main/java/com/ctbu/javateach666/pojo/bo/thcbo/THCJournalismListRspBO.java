package com.ctbu.javateach666.pojo.bo.thcbo;

public class THCJournalismListRspBO {
	private int id;//主页信息id
	private String joutitle;//标题
	private String joucontent;//内容
	private String joutype;//新闻类型
	private String starttime;//通知创建时间
	private String endtime;//通知过期时间
	private int pubid;//发布人id
	private int j_type;//1-新闻  2-公告
	private int is_delete;//逻辑删除
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getJoutype() {
		return joutype;
	}
	public void setJoutype(String joutype) {
		this.joutype = joutype;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
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
