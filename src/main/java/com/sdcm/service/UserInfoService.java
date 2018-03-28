package com.sdcm.service;


import com.sdcm.common.BaseService;
import com.sdcm.po.UserInfo;
import com.sdcm.vo.UserVo;

/**
 * 用户信息业务接口
 * @author DCM
 *
 */
public interface UserInfoService extends BaseService<UserInfo, Long>{
	
	public UserVo getUserByUserName(String userName) throws Exception;
	
}
