package com.sdcm.vo;

import java.io.Serializable;
import java.util.List;

import com.sdcm.po.JobProcess;
import com.sdcm.po.JobType;

/**
 * 各种页面需要选项的view object
 * @author XUSANDUO
 *
 */
public class TypesVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 处理选项：打回，通过，拒绝
	private List<JobProcess> processTypes; 
	
	// 工作流类型：借支：0001
	private List<JobType> jobTypes;
	
	// 提交工作流给谁
	private List<UserVo> users;

	public List<JobProcess> getProcessTypes() {
		return processTypes;
	}

	public void setProcessTypes(List<JobProcess> processTypes) {
		this.processTypes = processTypes;
	}

	public List<JobType> getJobTypes() {
		return jobTypes;
	}

	public void setJobTypes(List<JobType> jobTypes) {
		this.jobTypes = jobTypes;
	}

	public List<UserVo> getUsers() {
		return users;
	}

	public void setUsers(List<UserVo> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Types [processTypes=" + processTypes + ", jobTypes=" + jobTypes
				+ ", users=" + users + "]";
	}
	
	

}
