package com.sdcm.dao.impl;

import java.util.List;

import com.sdcm.common.AbstractBaseDao;
import com.sdcm.dao.UserInfoDao;
import com.sdcm.po.UserInfo;
import com.sdcm.utils.Page;

/**
 * UserInfo数据访问层实现类
 * @author DCM
 *
 */
public class UserInfoDaoImpl extends AbstractBaseDao<UserInfo, Long> implements UserInfoDao {

	/**
	 * 删除
	 */
	@Override
	public void delete(Long id) throws Exception {
		UserInfo users=this.get(UserInfo.class,id);
		this.delete(users);
	}

	/**
	 * 主键查询
	 * @param id 编号
	 */
	@Override
	public UserInfo get(Long id) throws Exception {
		
		return this.get(UserInfo.class,id);
	}

	/**
	 * 获取列表
	 */
	@Override
	public List<UserInfo> getList() throws Exception {
		//构建查询语句
		String sql="from UserInfo";
		List<UserInfo> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}

	/**
	 * 条件查询
	 */
	@Override
	public List<UserInfo> getList(UserInfo obj) throws Exception {
		//构建查询语句
		String sql="from UserInfo a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(obj.getUserName()!=null&&!"".equals(obj.getUserName())){
				sql+=" AND a.userName LIKE '%"+obj.getUserName()+"%'";
			}
		}

		List<UserInfo> list = query(sql, new Object[]{});//调用父类的查询方法拿数据
		return list;
	}
	
	
	/**
	 * 用户名查询
	 * @param userName
	 * @return
	 */
	public UserInfo getUsersByUserName(String userName)throws Exception{
		//构建查询语句
		String sql="from UserInfo a WHERE a.userName=?";
		UserInfo users = uniqueQuery(sql, new Object[]{userName});
		return users;	
	}

	@Override
	public Page<UserInfo> getList(UserInfo obj, Integer page, Integer limit) throws Exception {
		//构建查询语句
		String sql="from UserInfo a WHERE 1=1";
		if(obj!=null){//条件构造
			
			if(obj.getId()!=null&&obj.getId()!=0){
				sql+=" AND a.id="+obj.getId();
			}
			
			if(obj.getUserName()!=null&&!"".equals(obj.getUserName())){
				sql+=" AND a.userName LIKE '%"+obj.getUserName()+"%'";
			}
		}

		Page<UserInfo> pageObj = searchForPager(sql, page, limit);//调用父类的查询方法拿数据
		return pageObj;
	}

	@Override
	public UserInfo getUserByUserName(String userName) throws Exception {
		//构建查询语句
		String sql="from UserInfo a WHERE a.userName=?";
		UserInfo user = this.uniqueQuery(sql, new Object[]{userName});//调用父类的查询方法拿数据
		return user;
	}


}
