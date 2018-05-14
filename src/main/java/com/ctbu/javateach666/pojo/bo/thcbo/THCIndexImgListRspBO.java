package com.ctbu.javateach666.pojo.bo.thcbo;

public class THCIndexImgListRspBO {
	private int id;//图片id
	private String imgno;//图片编号
	private String imgname;//图片名称
	private String imgurl;//图片地址
	private String create_time;//上传时间
	private int is_pub;//是否发布
	public int getIs_pub() {
		return is_pub;
	}
	public void setIs_pub(int is_pub) {
		this.is_pub = is_pub;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImgno() {
		return imgno;
	}
	public void setImgno(String imgno) {
		this.imgno = imgno;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
