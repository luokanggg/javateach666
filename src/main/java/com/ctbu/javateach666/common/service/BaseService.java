package com.ctbu.javateach666.common.service;

import java.util.List;

import com.ctbu.javateach666.common.dao.BaseDao;
import com.ctbu.javateach666.common.entity.DataEntity;

public interface BaseService<D extends BaseDao<T>,T extends DataEntity<T>> {
	
	public T get(int id);
	
	public List<T> findList(T entity);
	
	public int insert(T entity);
	
	public int update(T entity);
	
	public int delete(int id);
	
	public int deleteByLogic(T entity);
	
}
