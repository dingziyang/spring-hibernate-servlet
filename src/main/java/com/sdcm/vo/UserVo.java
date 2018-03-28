package com.sdcm.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;

/**
 * 用户vo
 * @author XUSANDUO
 *
 */
public class UserVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id; 
	
	private String userName; // 用户名
	
	private String userPwd; // 密码
	
	private String userNickName; // 昵称
	
	private Date createTime; // 创建时间
	
	private Date updateTime; // 修改时间
	
	private Long roleId; // 角色id
	
	private Integer roleLevel; // 角色等级
	
	private String roleName; // 角色名称
	
	private Long highUserId; // 领导id
	
	private String highUserName; // 领导账号名
	
	private String highUserNickName; // 领导姓名

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

	public Integer getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getHighUserId() {
		return highUserId;
	}

	public void setHighUserId(Long highUserId) {
		this.highUserId = highUserId;
	}

	public String getHighUserName() {
		return highUserName;
	}

	public void setHighUserName(String highUserName) {
		this.highUserName = highUserName;
	}

	public String getHighUserNickName() {
		return highUserNickName;
	}

	public void setHighUserNickName(String highUserNickName) {
		this.highUserNickName = highUserNickName;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", userName=" + userName + ", userPwd="
				+ userPwd + ", userNickName=" + userNickName + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", roleId="
				+ roleId + ", roleLevel=" + roleLevel + ", roleName="
				+ roleName + ", highUserId=" + highUserId + ", highUserName="
				+ highUserName + ", highUserNickName=" + highUserNickName + "]";
	}

}
