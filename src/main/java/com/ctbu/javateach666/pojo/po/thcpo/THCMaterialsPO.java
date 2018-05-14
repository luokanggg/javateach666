package com.ctbu.javateach666.pojo.po.thcpo;

import java.io.Serializable;
import java.util.Date;

import com.ctbu.javateach666.common.entity.DataEntity;

public class THCMaterialsPO extends DataEntity<THCMaterialsPO> implements Serializable{

	private static final long serialVersionUID = 5467069658283386566L;
	
	private Integer id;
	private String mtitle;//学习资料名称
	private String murl;//文件地址
	private Date mtime;//上传时间
	private Integer is_delete;//逻辑删除

	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMtitle() {
		return mtitle;
	}
	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}
	public String getMurl() {
		return murl;
	}
	public void setMurl(String murl) {
		this.murl = murl;
	}
	
}
