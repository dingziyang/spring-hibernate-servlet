package com.sdcm.dao.impl;

import java.util.List;

import com.sdcm.common.AbstractBaseDao;
import com.sdcm.dao.JobProcessDao;
import com.sdcm.po.JobProcess;
import com.sdcm.utils.Page;

/**
 * 工作流过程数据访问层实现类
 * @author DCM
 *
 */
public class JobProcessDaoImpl extends AbstractBaseDao<JobProcess, Long> implements JobProcessDao {

	/**
	 * 删除
	 */
	@Override
	public void delete(Long id) throws Exception {
		JobProcess process = this.get(JobProcess.class,id);
		this.delete(process);
	}

	/**
	 * 主键获取
	 * @param id 编号
	 */
	@Override
	public JobProcess get(Long id) throws Exception {
		return this.get(JobProcess.class,id);
	}

	/**
	 * 获取列表
	 */
	@Override
	public List<JobProcess> getList() throws Exception {
		//构建查询语句
		String sql="from JobProcess";
		List<JobProcess> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	/**
	 * 条件查询
	 */
	@Override
	public List<JobProcess> getList(JobProcess obj) throws Exception {
		//构建查询语句
		String sql="from JobProcess a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(obj.getJobId()!=null&&obj.getJobId() != 0){
				sql+=" AND a.jobId="+obj.getJobId();
			}
			
			if(obj.getNext() != null && obj.getNext() != 0){
				sql+=" AND a.next="+obj.getNext();
			}
			sql += "order by a.createTime desc";
		}

		List<JobProcess> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	@Override
	public Page<JobProcess> getList(JobProcess obj, Integer page, Integer limit) throws Exception {
		//构建查询语句
		String sql="from JobProcess a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
		}

		Page<JobProcess> pageObj = searchForPager(sql, page, limit);//调用父类的查询方法拿数据
		return pageObj;
	}

}
