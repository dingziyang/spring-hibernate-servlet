package com.sdcm.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.util.CollectionUtils;

import com.sdcm.common.AbstractBaseDao;
import com.sdcm.dao.JobInfoDao;
import com.sdcm.po.JobInfo;
import com.sdcm.utils.Page;

/**
 * 工作流信息数据访问层实现类
 * @author DCM
 *
 */
public class JobInfoDaoImpl extends AbstractBaseDao<JobInfo, Long> implements JobInfoDao {
	
	/**
	 * 删除
	 */
	@Override
	public void delete(Long id) throws Exception {
		JobInfo job = this.get(JobInfo.class,id);
		this.delete(job);
	}

	/**
	 * 主键查询
	 * @param id 编号
	 */
	@Override
	public JobInfo get(Long id) throws Exception {
		
		return this.get(JobInfo.class,id);
	}

	/**
	 * 获取列表
	 */
	@Override
	public List<JobInfo> getList() throws Exception {
		//构建查询语句
		String sql="from JobInfo";
		List<JobInfo> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	/**
	 * 条件查询
	 */
	@Override
	public List<JobInfo> getList(JobInfo obj) throws Exception {
		//构建查询语句
		String sql="from JobInfo a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(obj.getJobTitle()!=null&&!"".equals(obj.getJobTitle())){
				sql+=" AND a.jobTitle LIKE '%"+obj.getJobTitle()+"%'";
			}
		}

		List<JobInfo> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	@Override
	public Page<JobInfo> getList(JobInfo obj, Integer page, Integer limit) throws Exception {
		//构建查询语句
		String sql="from JobInfo a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(obj.getJobTitle()!=null&&!"".equals(obj.getJobTitle())){
				sql+=" AND a.jobTitle LIKE '%"+obj.getJobTitle()+"%'";
			}
		}

		Page<JobInfo> pageObj = searchForPager(sql, page, limit);//调用父类的查询方法拿数据
		return pageObj;
	}

	@Override
	public Page<JobInfo> getICreateList(JobInfo obj, Integer page, Integer limit,
			Long myid) throws Exception {
		//构建查询语句
		String sql="from JobInfo a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(myid != null){
				sql+=" AND a.createBy="+myid;
			}
			
			if(obj.getJobTitle()!=null&&!"".equals(obj.getJobTitle())){
				sql+=" AND a.jobTitle LIKE '%"+obj.getJobTitle()+"%'";
			}
		}

		Page<JobInfo> pageObj = searchForPager(sql, page, limit);//调用父类的查询方法拿数据
		return pageObj;
	}

	@Override
	public JobInfo addObj(JobInfo obj) throws Exception {
		this.getSession().save(obj);
		return obj;
	}

}
