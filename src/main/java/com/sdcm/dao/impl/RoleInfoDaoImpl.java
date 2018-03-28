package com.sdcm.dao.impl;

import java.util.List;

import com.sdcm.common.AbstractBaseDao;
import com.sdcm.dao.RoleInfoDao;
import com.sdcm.po.RoleInfo;
import com.sdcm.utils.Page;

/**
 * RoleInfo数据访问层实现类
 * @author DCM
 *
 */
public class RoleInfoDaoImpl extends AbstractBaseDao<RoleInfo, Long> implements RoleInfoDao {

	/**
	 * 删除
	 */
	@Override
	public void delete(Long id) throws Exception {
		RoleInfo role = this.get(RoleInfo.class,id);
		this.delete(role);
	}

	/**
	 * 主键获取
	 * @param id 编号
	 */
	@Override
	public RoleInfo get(Long id) throws Exception {
		
		return this.get(RoleInfo.class,id);
	}

	/**
	 * 获取列表
	 */
	@Override
	public List<RoleInfo> getList() throws Exception {
		//构建查询语句
		String sql="from RoleInfo";
		List<RoleInfo> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	/**
	 * 条件查询
	 */
	@Override
	public List<RoleInfo> getList(RoleInfo obj) throws Exception {
		//构建查询语句
		String sql="from RoleInfo a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(obj.getRoleName()!=null&&!"".equals(obj.getRoleName())){
				sql+=" AND a.roleName LIKE '%"+obj.getRoleName()+"%'";
			}
		}

		List<RoleInfo> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	@Override
	public Page<RoleInfo> getList(RoleInfo obj, Integer page, Integer limit) throws Exception {
		//构建查询语句
		String sql="from RoleInfo a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(obj.getRoleName()!=null&&!"".equals(obj.getRoleName())){
				sql+=" AND a.roleName LIKE '%"+obj.getRoleName()+"%'";
			}
		}

		Page<RoleInfo> pageObj = searchForPager(sql, page, limit);//调用父类的查询方法拿数据
		return pageObj;
	}


}
