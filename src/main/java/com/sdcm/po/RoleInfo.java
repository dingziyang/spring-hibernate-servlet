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
 * 角色信息
 * @author DCM
 *
 */
@Entity
@Table(name = "ROLE_INFO")
public class RoleInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="bkdex_seq_generator",sequenceName="SEQ_ROLE_INFO",allocationSize = 1)
	@GeneratedValue(generator="bkdex_seq_generator",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
	private Long id; 
	
	@Column(name = "ROLE_NAME")
	private String roleName; // 角色名
	
	@Column(name = "ROLE_LEVEL")
	private Integer roleLevel; // 角色等级

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}

	@Override
	public String toString() {
		return "RoleInfo [id=" + id + ", roleName=" + roleName + ", roleLevel="
				+ roleLevel + "]";
	}
	
}
