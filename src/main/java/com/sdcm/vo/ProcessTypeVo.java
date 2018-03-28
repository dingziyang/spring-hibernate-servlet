package com.sdcm.vo;

import java.io.Serializable;

/**
 * 工作流处理结果类型VO
 * @author DCM
 *
 */
public class ProcessTypeVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String typeName; // 类型名:通过，打回，拒绝
	private Integer typeValue; // 类型值
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(Integer typeValue) {
		this.typeValue = typeValue;
	}
	@Override
	public String toString() {
		return "ProcessTypeVo [id=" + id + ", typeName=" + typeName
				+ ", typeValue=" + typeValue + "]";
	}
	
}
