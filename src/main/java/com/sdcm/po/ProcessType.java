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
 * 工作流处理结果类型
 * @author DCM
 *
 */
@Entity
@Table(name = "PROCESS_TYPE")
public class ProcessType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="bkdex_seq_generator",sequenceName="SEQ_PROCESS_TYPE",allocationSize = 1)
	@GeneratedValue(generator="bkdex_seq_generator",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
	private Long id;
	
	@Column(name = "TYPE_NAME")
	private String typeName; // 类型名:通过，打回，拒绝,创建，转交老板
	
	@Column(name = "TYPE_VALUE")
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
		return "ProcessType [id=" + id + ", typeName=" + typeName
				+ ", typeValue=" + typeValue + "]";
	}
	

}
