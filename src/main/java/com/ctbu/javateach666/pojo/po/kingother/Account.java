package com.ctbu.javateach666.pojo.po.kingother;

import com.ctbu.javateach666.common.entity.DataEntity;

/**
 * 账号实体 
 * @author king
 *
 */
public class Account extends DataEntity<Account> {
	private static final long serialVersionUID = 1L;

	private String username;		// 用户名
	private String password;		// 密码
	private Integer enable;			// 是否启用
	private Integer userdetailid;	// 用户类别


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
