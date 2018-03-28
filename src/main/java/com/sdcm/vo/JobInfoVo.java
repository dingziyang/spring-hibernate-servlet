package com.sdcm.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.sdcm.po.ProcessType;

/**
 * 工作流VO
 * @author DCM
 *
 */
public class JobInfoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id; 
	private String jobTitle; // 工作流标题
	private String jobContent; // 工作流内容
	private String jobTypeNo; // 工作流类型编号（0001-9999）
	private Long jobHasAnnex; // 1没有附件；2有附件
	private String jobAnnexUrl; // 附件地址
	private Long createBy; // 创建人
	private Date createTime; // 创建时间
	private Long updateBy; // 修改人
	private Date updateTime; // 修改时间
	private BigDecimal money; // 金额
	private Long targetUserId; // 指向的用户id
	private String createName; // 创建人
	private int needModify; // 0，不需要，1需要
	
	private List<ProcessType> canActionTypes; // 可操作选项
	private String annexName;// 附件名
	private String jobTypeName; // 工作流类型名
	private String createTimeStr; // 创建时间（字符串格式）
	
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
	public Long getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(Long targetUserId) {
		this.targetUserId = targetUserId;
	}
	
	public List<ProcessType> getCanActionTypes() {
		return canActionTypes;
	}

	public void setCanActionTypes(List<ProcessType> canActionTypes) {
		this.canActionTypes = canActionTypes;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public int getNeedModify() {
		return needModify;
	}
	public void setNeedModify(int needModify) {
		this.needModify = needModify;
	}
	public String getAnnexName() {
		return annexName;
	}
	public void setAnnexName(String annexName) {
		this.annexName = annexName;
	}
	public String getJobTypeName() {
		return jobTypeName;
	}
	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	@Override
	public String toString() {
		return "JobInfoVo [id=" + id + ", jobTitle=" + jobTitle
				+ ", jobContent=" + jobContent + ", jobTypeNo=" + jobTypeNo
				+ ", jobHasAnnex=" + jobHasAnnex + ", jobAnnexUrl="
				+ jobAnnexUrl + ", createBy=" + createBy + ", createTime="
				+ createTime + ", updateBy=" + updateBy + ", updateTime="
				+ updateTime + ", money=" + money + ", targetUserId="
				+ targetUserId + ", createName=" + createName + ", needModify="
				+ needModify + ", canActionTypes=" + canActionTypes
				+ ", annexName=" + annexName + ", jobTypeName=" + jobTypeName
				+ ", createTimeStr=" + createTimeStr + "]";
	}
	
}
