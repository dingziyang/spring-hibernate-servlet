package com.sdcm.service;


import java.util.List;

import com.sdcm.common.BaseService;
import com.sdcm.po.JobProcess;
import com.sdcm.vo.JobProcessVo;
import com.sdcm.vo.UserVo;

/**
 * 工作流过程业务接口
 * @author DCM
 *
 */
public interface JobProcessService extends BaseService<JobProcess, Long>{
	
	
	public List<JobProcessVo> getVoList(JobProcess obj) throws Exception;
	
	
	public void handleJob(UserVo user,Long jobId,String handleType) throws Exception;
}
