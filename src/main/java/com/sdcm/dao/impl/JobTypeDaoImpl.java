package com.sdcm.dao.impl;

import java.util.List;

import com.sdcm.common.AbstractBaseDao;
import com.sdcm.dao.JobTypeDao;
import com.sdcm.po.JobType;
import com.sdcm.utils.Page;

/**
 * 工作流类型数据访问层实现类
 * @author DCM
 *
 */
public class JobTypeDaoImpl extends AbstractBaseDao<JobType, Long> implements JobTypeDao {

	/**
	 * 删除
	 */
	@Override
	public void delete(Long id) throws Exception {
		JobType type = this.get(JobType.class,id);
		this.delete(type);
	}

	/**
	 * 主键获取
	 * @param id 编号
	 */
	@Override
	public JobType get(Long id) throws Exception {
		
		return this.get(JobType.class,id);
	}

	/**
	 * 获取列表
	 */
	@Override
	public List<JobType> getList() throws Exception {
		//构建查询语句
		String sql="from JobType";
		List<JobType> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	/**
	 * 条件查询
	 */
	@Override
	public List<JobType> getList(JobType obj) throws Exception {
		//构建查询语句
		String sql="from JobType a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
		}

		List<JobType> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	@Override
	public Page<JobType> getList(JobType obj, Integer page, Integer limit) throws Exception {
		//构建查询语句
		String sql="from JobType a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(obj.getTypeName()!=null&&!"".equals(obj.getTypeName())){
				sql+=" AND a.typeName LIKE '%"+obj.getTypeName()+"%'";
			}
		}

		Page<JobType> pageObj = searchForPager(sql, page, limit);//调用父类的查询方法拿数据
		return pageObj;
	}


}
