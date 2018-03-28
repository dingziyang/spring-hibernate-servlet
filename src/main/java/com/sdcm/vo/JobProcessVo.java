package com.sdcm.vo;

import java.io.Serializable;
import java.sql.Date;

/**
 * 工作流过程VO
 * @author DCM
 *
 */
public class JobProcessVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; 
	private Long prev; // 上一位人员
	private Long next; // 下一位人员
	private Long status; // 未查看，打回，通过，拒绝等状态， 1-99
	private Long createBy; // 创建人
	private Date createTime; // 创建时间
	private Long updateBy; // 修改人
	private Date updateTime; // 修改时间
	private Long jobId; // 工作id
	
	private String prevNickName;
	private String nextNickName;
	private String processName;
	private String createTimeStr; // 创建时间（字符串格式）
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrev() {
		return prev;
	}

	public void setPrev(Long prev) {
		this.prev = prev;
	}

	public Long getNext() {
		return next;
	}

	public void setNext(Long next) {
		this.next = next;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getPrevNickName() {
		return prevNickName;
	}

	public void setPrevNickName(String prevNickName) {
		this.prevNickName = prevNickName;
	}

	public String getNextNickName() {
		return nextNickName;
	}

	public void setNextNickName(String nextNickName) {
		this.nextNickName = nextNickName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	@Override
	public String toString() {
		return "JobProcessVo [id=" + id + ", prev=" + prev + ", next=" + next
				+ ", status=" + status + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", updateBy=" + updateBy
				+ ", updateTime=" + updateTime + ", jobId=" + jobId
				+ ", prevNickName=" + prevNickName + ", nextNickName="
				+ nextNickName + ", processName=" + processName
				+ ", createTimeStr=" + createTimeStr + "]";
	}
	

}
