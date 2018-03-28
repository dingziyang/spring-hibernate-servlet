package com.sdcm.po;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 工作流类型
 * @author DCM
 *
 */
@Entity
@Table(name = "JOB_TYPE")
public class JobType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="bkdex_seq_generator",sequenceName="SEQ_JOB_TYPE",allocationSize = 1)
	@GeneratedValue(generator="bkdex_seq_generator",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
	private Long id; 
	
	@Column(name = "TYPE_NO")
	private String typeNo; // 工作流类型编号(0001-9999)
	
	@Column(name = "TYPE_NAME")
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
