package com.sdcm.vo;

import java.io.Serializable;

/**
 * 工作流类型VO
 * @author DCM
 *
 */
public class JobTypeVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; 
	private String typeNo; // 工作流类型编号(0001-9999)
	private String typeName; // 工作流类型名称

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "JobType [id=" + id + ", typeNo=" + typeNo + ", typeName="
				+ typeName + "]";
	}
	
}
