package com.sdcm.po;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 用户信息
 * @author DCM
 *
 */
@Entity
@Table(name = "USER_INFO")
//@NamedQueries({
//@NamedQuery(name = "UserInfo.findAll", query = "SELECT u FROM USER_INFO u")})
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="bkdex_seq_generator",sequenceName="SEQ_USER_INFO",allocationSize = 1)
	@GeneratedValue(generator="bkdex_seq_generator",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
	private Long id; 
	
	@Column(name = "USER_NAME")
	private String userName; // 用户名
	
	@Column(name = "USER_PWD")
	private String userPwd; // 密码
	
	@Column(name = "USER_NICK_NAME")
	private String userNickName; // 昵称
	
	@Column(name = "CREATE_TIME")
	private Date createTime; // 创建时间
	
	@Column(name = "UPDATE_TIME")
	private Date updateTime; // 修改时间
	
	@Column(name = "ROLE_ID")
	private Long roleId; // 角色id
	
	@Column(name = "HIGH_USER_ID")
	private Long highUserId; // 领导id
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getHighUserId() {
		return highUserId;
	}
	public void setHighUserId(Long highUserId) {
		this.highUserId = highUserId;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userName=" + userName + ", userPwd="
				+ userPwd + ", userNickName=" + userNickName + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", roleId="
				+ roleId + ", highUserId=" + highUserId + "]";
	}
	
	

}
