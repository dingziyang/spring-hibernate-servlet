package com.sdcm.dao;

import com.sdcm.common.BaseDao;
import com.sdcm.po.UserInfo;

/**
 * 用户信息DAO
 * @author DCM
 *
 */
public interface UserInfoDao extends BaseDao <UserInfo , Long> {
	
	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public UserInfo getUserByUserName(String userName) throws Exception;
	
}
