package com.sdcm.common;

import java.util.List;

import com.sdcm.utils.Page;

/**
 * 业务逻辑层接口
 * @author XUSANDUO
 *
 * @param <T> 表对象的PO类型
 * @param <K> 表中的主键对应的类型
 */
public interface BaseService<T, K> {
	
	// 添加
	public boolean add(T obj)  throws Exception;

	// 更新
	public boolean update(T obj)  throws Exception;

	// 删除
	public boolean delete(K obj) throws Exception;

	// 通过主键获得一条
	public T get(K obj) throws Exception;

	// 获得列表
	public List<T> getList() throws Exception;

	// 通过条件获得列表
	public List<T> getList(T obj) throws Exception;
	
	public Page<T> getList(T obj, Integer page,Integer limit) throws Exception;
}
