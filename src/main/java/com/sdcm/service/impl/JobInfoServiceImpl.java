package com.sdcm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import java.util.Set;

import javax.transaction.Transactional;








import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;








import com.sdcm.common.AbstractBaseService;
import com.sdcm.common.Constant;
import com.sdcm.controller.content.DownloadController;
import com.sdcm.dao.JobInfoDao;
import com.sdcm.dao.JobProcessDao;
import com.sdcm.dao.JobTypeDao;
import com.sdcm.dao.ProcessTypeDao;
import com.sdcm.dao.RoleInfoDao;
import com.sdcm.dao.UserInfoDao;
import com.sdcm.po.JobInfo;
import com.sdcm.po.JobProcess;
import com.sdcm.po.JobType;
import com.sdcm.po.ProcessType;
import com.sdcm.po.RoleInfo;
import com.sdcm.po.UserInfo;
import com.sdcm.service.JobInfoService;
import com.sdcm.utils.Page;
import com.sdcm.utils.Util;
import com.sdcm.vo.JobInfoVo;

/**
 * 工作流信息业务实现
 * @author DCM
 *
 */
public class JobInfoServiceImpl extends AbstractBaseService<JobInfo, Long> implements JobInfoService {
	
	private static Logger log = LoggerFactory.getLogger(JobInfoServiceImpl.class);
	
	private static JobInfoServiceImpl jobInfoServiceImpl = null;//单例模式
	private JobInfoDao jobInfoDao;
	private JobProcessDao jobProcessDao;
	private ProcessTypeDao processTypeDao;
	private UserInfoDao userInfoDao;
	private RoleInfoDao roleInfoDao;
	private JobTypeDao jobTypeDao;
	public JobInfoDao getDao() {
		return jobInfoDao;
	}
	public void setDao(JobInfoDao dao) {
		this.jobInfoDao = dao;
	}
	
	public JobProcessDao getJobProcessDao() {
		return jobProcessDao;
	}
	public void setJobProcessDao(JobProcessDao jobProcessDao) {
		this.jobProcessDao = jobProcessDao;
	}
	public ProcessTypeDao getProcessTypeDao() {
		return processTypeDao;
	}
	public void setProcessTypeDao(ProcessTypeDao processTypeDao) {
		this.processTypeDao = processTypeDao;
	}
	
	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
	
	public RoleInfoDao getRoleInfoDao() {
		return roleInfoDao;
	}
	public void setRoleInfoDao(RoleInfoDao roleInfoDao) {
		this.roleInfoDao = roleInfoDao;
	}
	public JobTypeDao getJobTypeDao() {
		return jobTypeDao;
	}
	public void setJobTypeDao(JobTypeDao jobTypeDao) {
		this.jobTypeDao = jobTypeDao;
	}
	private JobInfoServiceImpl() {
		super();
	}
	
	public static JobInfoServiceImpl getInstance(){
		if(jobInfoServiceImpl == null){
			jobInfoServiceImpl=new JobInfoServiceImpl();
		}
		return jobInfoServiceImpl;
	}
	
	/**
	 * 新增
	 */
	@Override
	public boolean add(JobInfo obj) throws Exception {
		boolean flag=false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}
	
	/**
	 * 更新
	 */
	@Override
	public boolean update(JobInfo obj) throws Exception {
		boolean flag=false;
		this.getDao().update(obj);
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
	public JobInfo get(Long obj) throws Exception {
		JobInfo job = null;
		job = this.getDao().get(obj);
		return job;
	}
	
	/**
	 * 查询总
	 */
	@Override
	public List<JobInfo> getList() throws Exception {
			
		List<JobInfo> list=null;
		list = this.getDao().getList();
		return list;
	}
	
	/**
	 * 条件查询
	 */
	@Override
	public List<JobInfo> getList(JobInfo obj) throws Exception {
		
		List<JobInfo> list=null;
		list = this.getDao().getList(obj);
		return list;
	}
	
	/**
	 * 分页条件查询
	 */
	@Override
	public Page<JobInfo> getList(JobInfo obj, Integer page, Integer limit)
			throws Exception {
		
		return this.getDao().getList(obj, page, limit);
	}
	
	@Transactional
	@Override
	public void commitJob(JobInfoVo vo) throws Exception {
		
		JobInfo jobInfo = new JobInfo();
		BeanUtils.copyProperties(jobInfo, vo);
		if(!StringUtils.isEmpty(jobInfo.getJobAnnexUrl())){
			jobInfo.setJobHasAnnex(2L);
		}
		jobInfo.setCreateTime(new Date());
		jobInfo.setUpdateTime(new Date());
		this.getDao().addObj(jobInfo);
		
		JobProcess jobProcess = new JobProcess();
		jobProcess.setPrev(vo.getCreateBy());
		jobProcess.setNext(vo.getTargetUserId());
		jobProcess.setCreateBy(vo.getCreateBy());
		jobProcess.setUpdateBy(vo.getUpdateBy());
		jobProcess.setCreateTime(new Date());
		jobProcess.setUpdateTime(new Date());
		jobProcess.setJobId(jobInfo.getId());
		jobProcess.setStatus(1L);
		this.getJobProcessDao().add(jobProcess);
		
	}
	@Override
	public Page<JobInfoVo> getICreateList(JobInfo obj, Integer page,
			Integer limit, Long myid) throws Exception{
		
		Page<JobInfoVo> infoVo = new Page<>();
		
		Page<JobInfo> info = this.getDao().getICreateList(obj, page, limit, myid);
		BeanUtils.copyProperties(infoVo, info);
		
		List<JobInfoVo> voList = new ArrayList<>();
		for (JobInfo j : info.getResultlist()) {
			JobProcess jobPr = new JobProcess();
			jobPr.setJobId(j.getId());
			List<JobProcess> jobAllPrList = this.jobProcessDao.getList(jobPr);
			
			JobInfoVo vo = new JobInfoVo();
			BeanUtils.copyProperties(vo, j);
			if(!CollectionUtils.isEmpty(jobAllPrList) && jobAllPrList.get(0).getNext() == myid){
				vo.setNeedModify(2);
			}
			
			// 加工附件地址
			if(!StringUtils.isEmpty(j.getJobAnnexUrl())){
				log.info(j.getJobAnnexUrl());
				int idx = j.getJobAnnexUrl().lastIndexOf("/")+1;
				if(idx != -1){
					vo.setAnnexName(j.getJobAnnexUrl().substring(idx, j.getJobAnnexUrl().length()));
				}else{
					vo.setAnnexName("");
				}
			}else{
				vo.setAnnexName("");
			}
			// 工作类型名
			JobType jt = new JobType();
			jt.setTypeNo(vo.getJobTypeNo());
			List<JobType> jtList = this.jobTypeDao.getList(jt);
			if(!CollectionUtils.isEmpty(jtList)){
				vo.setJobTypeName(jtList.get(0).getTypeName());
			}else{
				log.warn("工作类型编码为["+vo.getJobTypeNo()+"]的记录在JOB_TYPE表中不存在");
				vo.setJobTypeName("未知类型");
			}
			
			vo.setCreateTimeStr(Util.formatDate(j.getCreateTime(), Constant.TIME_FORMAT));
			voList.add(vo);
		}
		infoVo.setResultlist(voList);
		
		return infoVo;
	}
	
	@Override
	public Page<JobInfoVo> getICheckList(JobInfo obj, Integer page,
			Integer limit, Set<Long> jobIds, Long myid,int mylevel) throws Exception {
		
		Page<JobInfoVo> pageInfo =  new Page<>();
		// 获取及过滤数据
		List<JobInfo> allJob =  this.getDao().getList(obj); // 拿到所有我参与处理的job
		
		// 如果一条也没有，return
		if(CollectionUtils.isEmpty(allJob)){
			pageInfo.setCurrentPage(1);
			pageInfo.setMaxPage(1);
			pageInfo.setTotal(0);
			pageInfo.setResultlist(new ArrayList<>());
			return pageInfo;
		}
		
		List<JobInfo> noRepeatJobList =  new ArrayList<>(); 
		for (int k = 0; k < allJob.size(); k++) {
			// 去掉那些最新处理人不是我的job ，看最新时间的next是不是我,创建者不是我的
			JobProcess jobPr = new JobProcess();
			jobPr.setJobId(allJob.get(k).getId());
			List<JobProcess> everyJobPrList = this.jobProcessDao.getList(jobPr);
			
			if(!CollectionUtils.isEmpty(everyJobPrList) && everyJobPrList.get(0).getNext() == myid ){
				if(allJob.get(k).getCreateBy() != myid){
					noRepeatJobList.add(allJob.get(k));
				}
			}
		}
		
		List<JobInfo> jList = new ArrayList<>();
		
		noRepeatJobList.forEach(job -> {
			jobIds.forEach(id -> {
				if(id == job.getId()){
					jList.add(job);
				}
			});
		});
		
		pageInfo.setCurrentPage(page);
		pageInfo.setTotal(jList.size());
		
		if(jList.size() <= Constant.PAGE_SIZE && jList.size() >0){
			pageInfo.setMaxPage(1);
		}else{
			pageInfo.setMaxPage((int)Math.floor(jList.size()/Constant.PAGE_SIZE)+1);
		}
		List<JobInfoVo> jobVoList = new ArrayList<>();
		if(jList.size() > Constant.PAGE_SIZE){
			int startIndex = (page-1)*Constant.PAGE_SIZE;
			int endIndex = page*Constant.PAGE_SIZE < jList.size() ? page*Constant.PAGE_SIZE : jList.size();
			List<JobInfo> finallyList = jList;
			finallyList = finallyList.subList(startIndex, endIndex);
			for (int n = 0; n < finallyList.size(); n++) {
				JobInfoVo vo = new JobInfoVo();
				BeanUtils.copyProperties(vo, finallyList.get(n));
				vo.setCreateTimeStr(Util.formatDate(finallyList.get(n).getCreateTime(), Constant.TIME_FORMAT));
				jobVoList.add(vo);
			}
		}else{
			for (int m = 0; m < jList.size(); m++) {
				JobInfoVo vo = new JobInfoVo();
				BeanUtils.copyProperties(vo, jList.get(m));
				vo.setCreateTimeStr(Util.formatDate(jList.get(m).getCreateTime(), Constant.TIME_FORMAT));
				jobVoList.add(vo);
			}
		}
		pageInfo.setResultlist(jobVoList);
		
		// 装入可操作选项
		List<ProcessType> proTypes = this.processTypeDao.getList();
		for (int i = 0; i < pageInfo.getResultlist().size(); i++) {
			List<ProcessType> canActionTypes = new ArrayList<>();
			
			// 如果当前是老板
			if(mylevel == 1){
				proTypes.forEach(pt -> {
					if(pt.getTypeValue() == 1 || pt.getTypeValue() == 5){ // 1创建，5提交给上级
					}else{
						canActionTypes.add(pt);
					}
				});
			}else{//不是老板
				UserInfo createJobUser = this.userInfoDao.get(pageInfo.getResultlist().get(i).getCreateBy());
				RoleInfo createJobUserRole = this.roleInfoDao.get(createJobUser.getRoleId());
				// 员工创建的工作流，并且金额超过3000
				if(createJobUserRole.getRoleLevel() == 3 && pageInfo.getResultlist().get(i).getMoney().compareTo(Constant.NEED_BOSS_PASS) == 1){
					proTypes.forEach(pt -> {
						if(pt.getTypeValue() == 1 || pt.getTypeValue() == 4){ // 1创建，4通过
						}else{
							canActionTypes.add(pt);
						}
					});
				}else{
					proTypes.forEach(ty -> {
						if(ty.getTypeValue() == 1 || ty.getTypeValue() == 5){ // 1创建，5提交给上级
						}else{
							canActionTypes.add(ty);
						}
					});
				}
			}
			pageInfo.getResultlist().get(i).setCanActionTypes(canActionTypes);
			
			// 加工附件地址
			if(!StringUtils.isEmpty(pageInfo.getResultlist().get(i).getJobAnnexUrl())){
				int idx = pageInfo.getResultlist().get(i).getJobAnnexUrl().lastIndexOf("/") +1;
				if(idx != -1){
					pageInfo.getResultlist().get(i).setAnnexName(pageInfo.getResultlist().get(i).getJobAnnexUrl().substring(idx, pageInfo.getResultlist().get(i).getJobAnnexUrl().length()));
				}else{
					pageInfo.getResultlist().get(i).setAnnexName("");
				}
			}else{
				pageInfo.getResultlist().get(i).setAnnexName("");
			}
			// 工作类型名
			JobType jt = new JobType();
			jt.setTypeNo(pageInfo.getResultlist().get(i).getJobTypeNo());
			List<JobType> jtList = this.jobTypeDao.getList(jt);
			if(!CollectionUtils.isEmpty(jtList)){
				pageInfo.getResultlist().get(i).setJobTypeName(jtList.get(0).getTypeName());
			}else{
				log.warn("工作类型编码为["+pageInfo.getResultlist().get(i).getJobTypeNo()+"]的记录在JOB_TYPE表中不存在");
				pageInfo.getResultlist().get(i).setJobTypeName("未知类型");
			}
		}
		return pageInfo;
	}

}
