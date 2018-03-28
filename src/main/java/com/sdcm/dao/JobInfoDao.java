package com.sdcm.dao;


import java.util.List;

import com.sdcm.common.BaseDao;
import com.sdcm.po.JobInfo;
import com.sdcm.utils.Page;

/**
 * 工作流信息DAO
 * @author DCM
 *
 */
public interface JobInfoDao extends BaseDao <JobInfo , Long> {
	
	/**
	 * 我创建的
	 * @param obj
	 * @param page
	 * @param limit
	 * @param myid
	 * @return
	 * @throws Exception
	 */
	public Page<JobInfo> getICreateList(JobInfo obj, Integer page, Integer limit,Long myid) throws Exception ;
	
	public JobInfo addObj(JobInfo obj) throws Exception;
}
