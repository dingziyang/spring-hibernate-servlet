package com.sdcm.service.impl;

import java.util.ArrayList;
import java.util.List;



import org.apache.commons.beanutils.BeanUtils;

import com.sdcm.common.AbstractBaseService;
import com.sdcm.dao.ProcessTypeDao;
import com.sdcm.po.ProcessType;
import com.sdcm.service.ProcessTypeService;
import com.sdcm.utils.Page;
import com.sdcm.vo.ProcessTypeVo;

/**
 * 工作流处理结果类型业务实现
 * @author DCM
 *
 */
public class ProcessTypeServiceImpl extends AbstractBaseService<ProcessType, Long> implements ProcessTypeService {
	
	private static ProcessTypeServiceImpl processTypeServiceImpl = null;//单例模式
	private ProcessTypeDao processTypeDao;
	public ProcessTypeDao getDao() {
		return processTypeDao;
	}
	public void setDao(ProcessTypeDao dao) {
		this.processTypeDao = dao;
	}
	
	private ProcessTypeServiceImpl() {
		super();
	}
	
	public static ProcessTypeServiceImpl getInstance(){
		if(processTypeServiceImpl == null){
			processTypeServiceImpl=new ProcessTypeServiceImpl();
		}
		return processTypeServiceImpl;
	}
	
	/**
	 * 新增
	 */
	@Override
	public boolean add(ProcessType obj) throws Exception {
		boolean flag=false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}
	
	/**
	 * 更新
	 */
	@Override
	public boolean update(ProcessType obj) throws Exception {
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
	public ProcessType get(Long obj) throws Exception {
		ProcessType role=null;
		role = this.getDao().get(obj);
		return role;
	}
	
	/**
	 * 查询总
	 */
	@Override
	public List<ProcessType> getList() throws Exception {
			
		List<ProcessType> list=null;
		list = this.getDao().getList();
		return list;
	}
	
	/**
	 * 条件查询
	 */
	@Override
	public List<ProcessType> getList(ProcessType obj) throws Exception {
		
		List<ProcessType> list=null;
		list = this.getDao().getList(obj);
		return list;
	}
	
	/**
	 * 分页条件查询
	 */
	@Override
	public Page<ProcessType> getList(ProcessType obj, Integer page, Integer limit)
			throws Exception {
		
		return this.getDao().getList(obj, page, limit);
	}
	
	@Override
	public List<ProcessTypeVo> getVoList() throws Exception{
		
		List<ProcessType> objList = this.getList(new ProcessType());
		List<ProcessTypeVo> voList = new ArrayList<>();
		
		for (ProcessType obj: objList) {
			ProcessTypeVo vo = new ProcessTypeVo();
			BeanUtils.copyProperties(vo,obj);
			voList.add(vo);
		}
		return voList;
	}
	

}
