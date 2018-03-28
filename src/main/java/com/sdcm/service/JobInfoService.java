package com.sdcm.service;


import java.util.List;
import java.util.Set;

import com.sdcm.common.BaseService;
import com.sdcm.po.JobInfo;
import com.sdcm.utils.Page;
import com.sdcm.vo.JobInfoVo;

/**
 * 工作流信息业务接口
 * @author DCM
 *
 */
public interface JobInfoService extends BaseService<JobInfo, Long>{
	
	/**
	 * 新建工作流
	 * @param vo
	 * @throws Exception
	 */
	public void commitJob(JobInfoVo vo) throws Exception;
	
	/**
	 * 我创建的
	 * @param obj
	 * @param page
	 * @param limit
	 * @param myid
	 * @return
	 */
	public Page<JobInfoVo> getICreateList(JobInfo obj, Integer page, Integer limit, Long myid) throws Exception;
	
	/**
	 * 要我审核的
	 * @param obj
	 * @param page
	 * @param limit
	 * @param jobIds
	 * @param myid 
	 * @return
	 * @throws Exception
	 */
	public Page<JobInfoVo> getICheckList(JobInfo obj, Integer page, Integer limit, Set<Long> jobIds, Long myid,int mylevel) throws Exception;
}

