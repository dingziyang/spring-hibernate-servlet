package com.sdcm.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 工作流过程
 * @author DCM
 *
 */
@Entity
@Table(name = "JOB_PROCESS")
public class JobProcess implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="bkdex_seq_generator",sequenceName="SEQ_JOB_PROCESS",allocationSize = 1)
	@GeneratedValue(generator="bkdex_seq_generator",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
	private Long id; 
	
	@Column(name = "PREV")
	private Long prev; // 上一位人员
	
	@Column(name = "NEXT")
	private Long next; // 下一位人员
	
	@Column(name = "STATUS")
	private Long status; // 打回，通过，拒绝等状态， 1-99
	
	@Column(name = "CREATE_BY")
	private Long createBy; // 创建人
	
	@Column(name = "CREATE_TIME")
	private Date createTime; // 创建时间
	
	@Column(name = "UPDATE_BY")
	private Long updateBy; // 修改人
	
	@Column(name = "UPDATE_TIME")
	private Date updateTime; // 修改时间
	
	@Column(name = "JOB_ID")
	private Long jobId; // 工作流id

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

	@Override
	public String toString() {
		return "JobProcess [id=" + id + ", prev=" + prev + ", next=" + next
				+ ", status=" + status + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", updateBy=" + updateBy
				+ ", updateTime=" + updateTime + ", jobId=" + jobId + "]";
	}

}
