package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCAccountPO extends DataEntity<THCAccountPO> implements Serializable{

	private static final long serialVersionUID = -7565870336366851901L;

	private Integer id;
	
	private String username;
	
	private String password;
	
	private Integer enable;
	
	private Integer userdetailid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getUserdetailid() {
		return userdetailid;
	}

	public void setUserdetailid(Integer userdetailid) {
		this.userdetailid = userdetailid;
	}

	
	
}
