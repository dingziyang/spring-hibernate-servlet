package com.sdcm.service.impl;

import java.util.ArrayList;
import java.util.List;




import org.apache.commons.beanutils.BeanUtils;

import com.sdcm.common.AbstractBaseService;
import com.sdcm.dao.JobTypeDao;
import com.sdcm.po.JobType;
import com.sdcm.po.ProcessType;
import com.sdcm.service.JobTypeService;
import com.sdcm.utils.Page;
import com.sdcm.vo.JobTypeVo;
import com.sdcm.vo.ProcessTypeVo;

/**
 * 工作流类型业务实现
 * @author DCM
 *
 */
public class JobTypeServiceImpl extends AbstractBaseService<JobType, Long> implements JobTypeService {
	
	private static JobTypeServiceImpl jobTypeServiceImpl = null;//单例模式
	private JobTypeDao jobTypeDao;
	public JobTypeDao getDao() {
		return jobTypeDao;
	}
	public void setDao(JobTypeDao dao) {
		this.jobTypeDao = dao;
	}
	
	private JobTypeServiceImpl() {
		super();
	}
	
	public static JobTypeServiceImpl getInstance(){
		if(jobTypeServiceImpl == null){
			jobTypeServiceImpl=new JobTypeServiceImpl();
		}
		return jobTypeServiceImpl;
	}
	
	/**
	 * 新增
	 */
	@Override
	public boolean add(JobType obj) throws Exception {
		boolean flag=false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}
	
	/**
	 * 更新
	 */
	@Override
	public boolean update(JobType obj) throws Exception {
		boolean flag=false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}
	
	/**
	 * 删除
	 */
	@Override
	public boolean delete(Long obj) throws Exception {
		boolean flag=false;
		this.getDao().delete(obj);
		flag=true;
		return flag;
	}
	
	/**
	 * 主键查询
	 */
	@Override
	public JobType get(Long obj) throws Exception {
		JobType role=null;
		role = this.getDao().get(obj);
		return role;
	}
	
	/**
	 * 查询总
	 */
	@Override
	public List<JobType> getList() throws Exception {
			
		List<JobType> list=null;
		list = this.getDao().getList();
		return list;
	}
	
	/**
	 * 条件查询
	 */
	@Override
	public List<JobType> getList(JobType obj) throws Exception {
		
		List<JobType> list=null;
		list = this.getDao().getList(obj);
		return list;
	}
	
	/**
	 * 分页条件查询
	 */
	@Override
	public Page<JobType> getList(JobType obj, Integer page, Integer limit)
			throws Exception {
		
		return this.getDao().getList(obj, page, limit);
	}
	
	@Override
	public List<JobTypeVo> getVoList() throws Exception {
		List<JobType> objList = this.getList(new JobType());
		List<JobTypeVo> voList = new ArrayList<>();
		
		for (JobType obj: objList) {
			JobTypeVo vo = new JobTypeVo();
			BeanUtils.copyProperties(vo,obj);
			voList.add(vo);
		}
		return voList;
	}
	

}
