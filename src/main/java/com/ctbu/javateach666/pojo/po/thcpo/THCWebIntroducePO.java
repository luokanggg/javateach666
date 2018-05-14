package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCWebIntroducePO extends DataEntity<THCWebIntroducePO> implements Serializable{

	private static final long serialVersionUID = -2425238628381046033L;
	
	private Integer id;
	private String wtitle;//标题
	private String wcontent;//内容
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWtitle() {
		return wtitle;
	}
	public void setWtitle(String wtitle) {
		this.wtitle = wtitle;
	}
	public String getWcontent() {
		return wcontent;
	}
	public void setWcontent(String wcontent) {
		this.wcontent = wcontent;
	}

}
