package com.sdcm.service.impl;

import java.util.List;


import com.sdcm.common.AbstractBaseService;
import com.sdcm.dao.RoleInfoDao;
import com.sdcm.po.RoleInfo;
import com.sdcm.service.RoleInfoService;
import com.sdcm.utils.Page;

/**
 * 角色信息业务实现
 * @author DCM
 *
 */
public class RoleInfoServiceImpl extends AbstractBaseService<RoleInfo, Long> implements RoleInfoService {
	
	private static RoleInfoServiceImpl roleInfoServiceImpl = null;//单例模式
	private RoleInfoDao roleInfoDao;
	public RoleInfoDao getDao() {
		return roleInfoDao;
	}
	public void setDao(RoleInfoDao dao) {
		this.roleInfoDao = dao;
	}
	
	private RoleInfoServiceImpl() {
		super();
	}
	
	public static RoleInfoServiceImpl getInstance(){
		if(roleInfoServiceImpl == null){
			roleInfoServiceImpl=new RoleInfoServiceImpl();
		}
		return roleInfoServiceImpl;
	}
	
	/**
	 * 新增
	 */
	@Override
	public boolean add(RoleInfo obj) throws Exception {
		boolean flag=false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}
	
	/**
	 * 更新
	 */
	@Override
	public boolean update(RoleInfo obj) throws Exception {
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
	public RoleInfo get(Long obj) throws Exception {
		RoleInfo role=null;
		role = this.getDao().get(obj);
		return role;
	}
	
	/**
	 * 查询总
	 */
	@Override
	public List<RoleInfo> getList() throws Exception {
			
		List<RoleInfo> list=null;
		list = this.getDao().getList();
		return list;
	}
	
	/**
	 * 条件查询
	 */
	@Override
	public List<RoleInfo> getList(RoleInfo obj) throws Exception {
		
		List<RoleInfo> list=null;
		list = this.getDao().getList(obj);
		return list;
	}
	
	/**
	 * 分页条件查询
	 */
	@Override
	public Page<RoleInfo> getList(RoleInfo obj, Integer page, Integer limit)
			throws Exception {
		
		return this.getDao().getList(obj, page, limit);
	}
	

}
