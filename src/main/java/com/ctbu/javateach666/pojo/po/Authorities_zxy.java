package com.ctbu.javateach666.pojo.po;

import java.io.Serializable;

public class Authorities_zxy implements Serializable {
	private int id;
	
	private String username;
	
	private String authorities;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	
}
