package com.ctbu.javateach666.common.dao;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * dao基础接口
 * @author Administrator
 *
 * @param <T>
 */
@Component
public interface BaseDao<T> {
	
	/**
	 * 根据id获取单条数据
	 * @param id
	 * @return entity
	 */
	public T get(int id);
	
	/**
	 * 根据条件获取对象集合
	 * @param entity
	 * @return List
	 */
	public List<T> findList(T entity);
	
	/**
	 * 插入数据
	 * 
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * 
	 * @param id
	 * @return
	 */
	public int delete(int id);
	
	/**
	 * 删除数据（逻辑删除）
	 * 
	 * @param id
	 * @return
	 */
	public int deleteByLogic(T entity);
	
	
}
