package com.sdcm.service;


import java.util.List;

import com.sdcm.common.BaseService;
import com.sdcm.po.ProcessType;
import com.sdcm.vo.ProcessTypeVo;

/**
 * 工作流处理结果类型业务接口
 * @author DCM
 *
 */
public interface ProcessTypeService extends BaseService<ProcessType, Long>{
	
	public List<ProcessTypeVo> getVoList() throws Exception;
	
}
