package com.sdcm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sdcm.common.AbstractBaseService;
import com.sdcm.common.Constant;
import com.sdcm.dao.JobInfoDao;
import com.sdcm.dao.JobProcessDao;
import com.sdcm.dao.ProcessTypeDao;
import com.sdcm.dao.UserInfoDao;
import com.sdcm.po.JobInfo;
import com.sdcm.po.JobProcess;
import com.sdcm.po.ProcessType;
import com.sdcm.po.UserInfo;
import com.sdcm.service.JobProcessService;
import com.sdcm.utils.Page;
import com.sdcm.utils.Util;
import com.sdcm.vo.JobProcessVo;
import com.sdcm.vo.UserVo;

/**
 * 工作流过程业务实现
 * @author DCM
 *
 */
public class JobProcessServiceImpl extends AbstractBaseService<JobProcess, Long> implements JobProcessService {
	
	private static JobProcessServiceImpl jobProcessServiceImpl = null;//单例模式
	private JobProcessDao jobProcessDao;
	private JobInfoDao jobInfoDao;
	private UserInfoDao userInfoDao;
	private ProcessTypeDao processTypeDao;
	public JobProcessDao getDao() {
		return jobProcessDao;
	}
	public void setDao(JobProcessDao dao) {
		this.jobProcessDao = dao;
	}
	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}
	public ProcessTypeDao getProcessTypeDao() {
		return processTypeDao;
	}
	public void setProcessTypeDao(ProcessTypeDao processTypeDao) {
		this.processTypeDao = processTypeDao;
	}
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
	public JobInfoDao getJobInfoDao() {
		return jobInfoDao;
	}
	public void setJobInfoDao(JobInfoDao jobInfoDao) {
		this.jobInfoDao = jobInfoDao;
	}
	private JobProcessServiceImpl() {
		super();
	}
	
	public static JobProcessServiceImpl getInstance(){
		if(jobProcessServiceImpl == null){
			jobProcessServiceImpl=new JobProcessServiceImpl();
		}
		return jobProcessServiceImpl;
	}
	
	/**
	 * 新增
	 */
	@Override
	public boolean add(JobProcess obj) throws Exception {
		boolean flag=false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}
	
	/**
	 * 更新
	 */
	@Override
	public boolean update(JobProcess obj) throws Exception {
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
	public JobProcess get(Long obj) throws Exception {
		JobProcess process = null;
		process = this.getDao().get(obj);
		return process;
	}
	
	/**
	 * 查询总
	 */
	@Override
	public List<JobProcess> getList() throws Exception {
			
		List<JobProcess> list=null;
		list = this.getDao().getList();
		return list;
	}
	
	/**
	 * 条件查询
	 */
	@Override
	public List<JobProcess> getList(JobProcess obj) throws Exception {
		
		List<JobProcess> list=null;
		list = this.getDao().getList(obj);
		return list;
	}
	
	/**
	 * 分页条件查询
	 */
	@Override
	public Page<JobProcess> getList(JobProcess obj, Integer page, Integer limit)
			throws Exception {
		
		return this.getDao().getList(obj, page, limit);
	}
	@Override
	public List<JobProcessVo> getVoList(JobProcess obj) throws Exception {
		List<JobProcess> list=null;
		list = this.getDao().getList(obj);
		
		List<JobProcessVo> voList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			JobProcessVo vo = new JobProcessVo();
			BeanUtils.copyProperties(vo, list.get(i));
			UserInfo prevUser = this.userInfoDao.get(list.get(i).getPrev());
			vo.setPrevNickName(prevUser.getUserNickName());
			
			if(StringUtils.isEmpty(list.get(i).getNext())){
				vo.setNextNickName("流程已关闭");
			}else{
				UserInfo nextUser = this.userInfoDao.get(list.get(i).getNext());
				vo.setNextNickName(nextUser.getUserNickName());
			}
			
			ProcessType pt = this.processTypeDao.get(vo.getStatus());
			vo.setProcessName(pt.getTypeName());
			
			vo.setCreateTimeStr(Util.formatDate(list.get(i).getCreateTime(), Constant.TIME_FORMAT));
			
			voList.add(vo);
		};
		return voList;
	}
	
	@Transactional
	@Override
	public void handleJob(UserVo user, Long jobId,String handleType) throws Exception {
		
		JobProcess jp = new JobProcess();
		jp.setJobId(jobId);
		jp.setCreateBy(user.getId());
		jp.setCreateTime(new Date());
		
		if("2".equals(handleType)){//拒绝
			jp.setPrev(user.getId());
			jp.setNext(null);
			jp.setStatus(2L);
		}
		if("3".equals(handleType)){//打回
			jp.setPrev(user.getId());
			
			JobProcess p = new JobProcess();
			p.setJobId(jobId);
			
			//如果当前是老板--》next：当前工作流的最新pross记录的prev；
			if(user.getRoleLevel() == 1){
				List<JobProcess> jpList = this.jobProcessDao.getList(p);
				if(!CollectionUtils.isEmpty(jpList)){
					jp.setNext(jpList.get(0).getPrev());
				}
			}else{//如果当前是经理--》next：创建者id (员工没有此功能)
				JobInfo job = this.jobInfoDao.get(jobId);
				jp.setNext(job.getCreateBy());
			}
			jp.setStatus(3L);
		}
		if("5".equals(handleType)){// 提交上级(重新上报)
			jp.setPrev(user.getId());
			jp.setNext(user.getHighUserId());
			jp.setStatus(5L);
		}
		if("4".equals(handleType)){//通过
			jp.setPrev(user.getId());
			jp.setNext(null);
			jp.setStatus(4L);
		}
		this.jobProcessDao.add(jp);
	}
	

}
