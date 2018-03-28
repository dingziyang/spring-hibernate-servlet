package com.sdcm.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdcm.common.AbstractBaseService;
import com.sdcm.dao.RoleInfoDao;
import com.sdcm.dao.UserInfoDao;
import com.sdcm.po.RoleInfo;
import com.sdcm.po.UserInfo;
import com.sdcm.service.UserInfoService;
import com.sdcm.utils.Page;
import com.sdcm.vo.UserVo;

/**
 * 用户信息业务实现
 * @author DCM
 *
 */
public class UserInfoServiceImpl extends AbstractBaseService<UserInfo, Long> implements UserInfoService {
	
	//private Logger log = Logger.getLogger(this.getClass());
	private static Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	private static UserInfoServiceImpl userInfoServiceImpl = null;//单例模式
	private UserInfoDao userInfoDao;
	private RoleInfoDao roleInfoDao;
	public UserInfoDao getDao() {
		return userInfoDao;
	}
	public void setDao(UserInfoDao dao) {
		this.userInfoDao = dao;
	}
	public RoleInfoDao getRoleInfoDao() {
		return roleInfoDao;
	}
	public void setRoleInfoDao(RoleInfoDao roleInfoDao) {
		this.roleInfoDao = roleInfoDao;
	}
	
	private UserInfoServiceImpl() {
		super();
	}
	
	public static UserInfoServiceImpl getInstance(){
		if(userInfoServiceImpl == null){
			userInfoServiceImpl=new UserInfoServiceImpl();
		}
		return userInfoServiceImpl;
	}
	
	/**
	 * 新增
	 */
	@Override
	public boolean add(UserInfo obj) throws Exception {
		boolean flag=false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}
	
	/**
	 * 更新
	 */
	@Override
	public boolean update(UserInfo obj) throws Exception {
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
	public UserInfo get(Long obj) throws Exception {
		UserInfo user=null;
		user = this.getDao().get(obj);
		return user;
	}
	
	/**
	 * 查询总
	 */
	@Override
	public List<UserInfo> getList() throws Exception {
			
		List<UserInfo> list=null;
		list = this.getDao().getList();
		return list;
	}
	
	/**
	 * 条件查询
	 */
	@Override
	public List<UserInfo> getList(UserInfo obj) throws Exception {
		
		List<UserInfo> list=null;
		list = this.getDao().getList(obj);
		return list;
	}
	
	/**
	 * 分页条件查询
	 */
	@Override
	public Page<UserInfo> getList(UserInfo obj, Integer page, Integer limit)
			throws Exception {
		
		return this.getDao().getList(obj, page, limit);
	}
	
	/**
	 * 用户名查询
	 */
	@Override
	public UserVo getUserByUserName(String userName) throws Exception {
		
		UserInfo user=null;
		user = this.getDao().getUserByUserName(userName);
		
		UserVo vo = new UserVo();
		if(user != null){
			BeanUtils.copyProperties(vo,user);
		}else{
			log.warn("查询出的用户不存在");
		}
		
		RoleInfo role = this.getRoleInfoDao().get(vo.getRoleId());
		vo.setRoleLevel(role.getRoleLevel());
		vo.setRoleName(role.getRoleName());
		
		if(user.getHighUserId() > 0){
			UserInfo highUser = this.get(user.getHighUserId());
			vo.setHighUserId(user.getHighUserId());
			vo.setHighUserName(highUser.getUserName());
			vo.setHighUserNickName(highUser.getUserNickName());
		}
		
		return vo;
	}

}
