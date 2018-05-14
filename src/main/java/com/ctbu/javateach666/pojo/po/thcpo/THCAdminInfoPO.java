package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;

public class THCAdminInfoPO implements Serializable{
	
	private static final long serialVersionUID = 7930270998068153910L;
	private Integer id;//管理员id
	private String adminno;//管理员编号
	private String adminname;//姓名
	private String adminimg;//头像
	private String adminsex;//性别
	private String adminemail;//邮箱
	private String admincollage;//学院
	private String adminphone;//电话号码
	private int isdelete;//逻辑删除

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
