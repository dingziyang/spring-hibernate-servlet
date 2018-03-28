package com.sdcm.common;

import java.io.Serializable;
import java.util.List;

import com.sdcm.utils.Page;

/**
 * 数据访问层接口
 * @author XUSANDUO
 *
 * @param <T> 表对象的po类型
 * @param <K> 表中的主键对应的类型
 */
public interface BaseDao<T, K extends Serializable> {
	
	// 添加
	public void add(T obj) throws Exception;
	
	// 更新
	public void update(T obj) throws Exception;
	
	// 删除
	public void delete(K obj) throws Exception;
	
	public void delete(String sql) throws Exception;
	
	//通过主键获得一条
	public T get(K obj) throws Exception;
	
	//获得列表
	public List<T> getList() throws Exception;
	
	//通过条件获得列表
	public List<T> getList(T obj) throws Exception;

	public Page<T> getList(T obj, Integer page,Integer limit) throws Exception;
	
	List<T> findEntity(String hql) throws Exception;

	List<T> findEntity(String hql, Integer start, Integer limit) throws Exception;
	
}
