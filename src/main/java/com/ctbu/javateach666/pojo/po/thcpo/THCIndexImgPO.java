package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCIndexImgPO extends DataEntity<THCIndexImgPO> implements Serializable{

	private static final long serialVersionUID = 2929070710959003129L;

	private Integer id;//图片id
	private String imgno;//图片编号
	private String imgname;//图片名称
	private String imgurl;//图片地址
	private String create_time;//上传时间
	private Integer is_pub;//是否发布

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIs_pub() {
		return is_pub;
	}
	public void setIs_pub(Integer is_pub) {
		this.is_pub = is_pub;
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
