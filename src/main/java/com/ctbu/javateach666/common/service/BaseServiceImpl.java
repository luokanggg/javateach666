package com.ctbu.javateach666.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ctbu.javateach666.common.dao.BaseDao;
import com.ctbu.javateach666.common.entity.DataEntity;

/**
 * service的基础方法
 * @author king
 *
 */
public abstract class BaseServiceImpl<D extends BaseDao<T>,T extends DataEntity<T>> implements BaseService<D,T> {
	
	/**
	 * 持久化对象层
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public T get(int id) {
		return dao.get(id);
	}
	
	/**
	 * 查询列表数据
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 添加一条数据
	 * 
	 * @param entity
	 * @return
	 */
	public int insert(T entity) {
		return dao.insert(entity);
	}
	
	/**
	 * 修改一条数据
	 * 
	 * @param entity
	 * @return
	 */
	public int update(T entity) {
		return dao.update(entity);
	}
	
	/**
	 * 物理删除数据
	 * 
	 * @param id
	 * @return
	 */
	public int delete(int id) {
		return dao.delete(id);
	}
	
	/**
	 * 逻辑删除数据
	 * 
	 * @param entitys
	 * @return 
	 */
	public int deleteByLogic(T entity) {
		entity.setIsDelete(1);
		return dao.deleteByLogic(entity);
	}
}
