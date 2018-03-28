package com.sdcm.service;


import java.util.List;

import com.sdcm.common.BaseService;
import com.sdcm.po.JobType;
import com.sdcm.vo.JobTypeVo;

/**
 * 工作流类型业务接口
 * @author DCM
 *
 */
public interface JobTypeService extends BaseService<JobType, Long>{
	
	public List<JobTypeVo> getVoList() throws Exception;
}
