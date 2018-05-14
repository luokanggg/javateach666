package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCAuthoritiesPO extends DataEntity<THCAuthoritiesPO> implements Serializable{

	private static final long serialVersionUID = 1801745274996825707L;
	private Integer id;
	private String username;
	private String authorities;
	private int is_delete;
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
	public String getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

}
