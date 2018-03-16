package com.ctbu.javateach666.pojo.bo;

import java.util.List;

public class PageInfoBo<T> {
	private List<T> rows;
	private int total;
	//private String responseCode;  //返回编码
	//private String responseDesc;  //返回描述
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
