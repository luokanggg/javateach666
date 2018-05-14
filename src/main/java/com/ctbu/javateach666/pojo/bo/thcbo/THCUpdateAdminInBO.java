package com.ctbu.javateach666.pojo.bo.thcbo;

import com.ctbu.javateach666.pojo.bo.BaseInfoBO;

public class THCUpdateAdminInBO extends BaseInfoBO{

	private int id;
	private String adminno;
	private String adminname;
	private String adminimg;
	private String adminsex;
	private String adminemail;
	private String admincollage;
	private String adminphone;
	private int isdelete;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminno() {
		return adminno;
	}
	public void setAdminno(String adminno) {
		this.adminno = adminno;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminimg() {
		return adminimg;
	}
	public void setAdminimg(String adminimg) {
		this.adminimg = adminimg;
	}
	public String getAdminsex() {
		return adminsex;
	}
	public void setAdminsex(String adminsex) {
		this.adminsex = adminsex;
	}
	public String getAdminemail() {
		return adminemail;
	}
	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}
	public String getAdmincollage() {
		return admincollage;
	}
	public void setAdmincollage(String admincollage) {
		this.admincollage = admincollage;
	}
	public String getAdminphone() {
		return adminphone;
	}
	public void setAdminphone(String adminphone) {
		this.adminphone = adminphone;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
}
