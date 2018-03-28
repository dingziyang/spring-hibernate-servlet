package com.sdcm.dao.impl;

import java.util.List;

import com.sdcm.common.AbstractBaseDao;
import com.sdcm.dao.ProcessTypeDao;
import com.sdcm.po.ProcessType;
import com.sdcm.utils.Page;

/**
 * 工作流处理结果类型数据访问层实现类
 * @author DCM
 *
 */
public class ProcessTypeDaoImpl extends AbstractBaseDao<ProcessType, Long> implements ProcessTypeDao {

	/**
	 * 删除
	 */
	@Override
	public void delete(Long id) throws Exception {
		ProcessType pType = this.get(ProcessType.class,id);
		this.delete(pType);
	}

	/**
	 * 主键获取
	 * @param id 编号
	 */
	@Override
	public ProcessType get(Long id) throws Exception {
		
		return this.get(ProcessType.class,id);
	}

	/**
	 * 获取列表
	 */
	@Override
	public List<ProcessType> getList() throws Exception {
		//构建查询语句
		String sql="from ProcessType";
		List<ProcessType> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	/**
	 * 条件查询
	 */
	@Override
	public List<ProcessType> getList(ProcessType obj) throws Exception {
		//构建查询语句
		String sql="from ProcessType a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(obj.getTypeName()!=null&&!"".equals(obj.getTypeName())){
				sql+=" AND a.typeName LIKE '%"+obj.getTypeName()+"%'";
			}
		}

		List<ProcessType> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	@Override
	public Page<ProcessType> getList(ProcessType obj, Integer page, Integer limit) throws Exception {
		//构建查询语句
		String sql="from ProcessType a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(obj.getTypeName()!=null&&!"".equals(obj.getTypeName())){
				sql+=" AND a.typeName LIKE '%"+obj.getTypeName()+"%'";
			}
		}

		Page<ProcessType> pageObj = searchForPager(sql, page, limit);//调用父类的查询方法拿数据
		return pageObj;
	}


}
