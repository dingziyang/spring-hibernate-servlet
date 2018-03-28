package com.sdcm.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 工作流信息
 * @author dcm
 *
 */
@Entity
@Table(name = "JOB_INFO")
public class JobInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="bkdex_seq_generator",sequenceName="SEQ_JOB_INFO",allocationSize = 1)
	@GeneratedValue(generator="bkdex_seq_generator",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
	private Long id; 
	
	@Column(name = "JOB_TITLE")
	private String jobTitle; // 工作流标题
	
	@Column(name = "JOB_CONTENT")
	private String jobContent; // 工作流内容
	
	@Column(name = "JOB_TYPE_NO")
	private String jobTypeNo; // 工作流类型编号（0001-9999）
	
	@Column(name = "JOB_HAS_ANNEX")
	private Long jobHasAnnex; // 1没有附件；2有附件
	
	@Column(name = "JOB_ANNEX_URL")
	private String jobAnnexUrl; // 附件地址
	
	@Column(name = "CREATE_BY")
	private Long createBy; // 创建人
	
	@Column(name = "CREATE_TIME")
	private Date createTime; // 创建时间
	
	@Column(name = "UPDATE_BY")
	private Long updateBy; // 修改人
	
	@Column(name = "UPDATE_TIME")
	private Date updateTime; // 修改时间
	
	@Column(name = "MONEY")
	private BigDecimal money; // 金额
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	public String getJobTypeNo() {
		return jobTypeNo;
	}

	public void setJobTypeNo(String jobTypeNo) {
		this.jobTypeNo = jobTypeNo;
	}

	public Long getJobHasAnnex() {
		return jobHasAnnex;
	}

	public void setJobHasAnnex(Long jobHasAnnex) {
		this.jobHasAnnex = jobHasAnnex;
	}

	public String getJobAnnexUrl() {
		return jobAnnexUrl;
	}

	public void setJobAnnexUrl(String jobAnnexUrl) {
		this.jobAnnexUrl = jobAnnexUrl;
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

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "JobInfo [id=" + id + ", jobTitle=" + jobTitle + ", jobContent="
				+ jobContent + ", jobTypeNo=" + jobTypeNo + ", jobHasAnnex="
				+ jobHasAnnex + ", jobAnnexUrl=" + jobAnnexUrl + ", createBy="
				+ createBy + ", createTime=" + createTime + ", updateBy="
				+ updateBy + ", updateTime=" + updateTime + ", money=" + money
				+ "]";
	}
	
	
}
