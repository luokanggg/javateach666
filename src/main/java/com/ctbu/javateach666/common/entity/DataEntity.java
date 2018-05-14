package com.ctbu.javateach666.common.entity;

import java.io.Serializable;

public class DataEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 实体唯一编号
	 */
	protected Integer id;
	/**
	 * 逻辑删除（0未删除，1已删除）
	 */
	protected int isDelete;
	
	
	
	public DataEntity() {
		super();
		this.isDelete = 0;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	

}
