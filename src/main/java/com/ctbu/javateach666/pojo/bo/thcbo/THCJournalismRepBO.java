package com.ctbu.javateach666.pojo.bo.thcbo;

import java.util.Date;

public class THCJournalismRepBO {
	private Integer id;//主页信息id
	private String joutitle;//标题
	private String joucontent;//内容
	private String joutype;//新闻类型
	private String dtype;//字典类型
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
	public String getJoutype() {
		return joutype;
	}
	public void setJoutype(String joutype) {
		this.joutype = joutype;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	
}
