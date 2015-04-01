package com.xiaoy.base.entites;

import java.util.Date;

/**
 * 用户信息实体类<br/>
 * 用于存放用户信息<br>
 *
 * @author XiaoY
 * @date 2015年4月1日
 */
public class User {
	
	/* 用户id */
	private String userUuid;
	/* 登陆名 */
	private String loginName;
	/* 用户姓名 */
	private String name;
	/* 性别id */
	private String SexCode;
	/* 角色id */
	private String RoleCode;
	/* 维护的类别 */
	private String maintainTypeCode;
	/* 联系方式 */
	private String phone;
	/* 地址 */
	private String address;
	/* 备注 */
	private String remark;
	/* 密码 */
	private String password;
	/* 注册时间 */
	private Date registerTime;

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSexCode() {
		return SexCode;
	}

	public void setSexCode(String sexCode) {
		SexCode = sexCode;
	}

	public String getRoleCode() {
		return RoleCode;
	}

	public void setRoleCode(String roleCode) {
		RoleCode = roleCode;
	}

	public String getMaintainTypeCode() {
		return maintainTypeCode;
	}

	public void setMaintainTypeCode(String maintainTypeCode) {
		this.maintainTypeCode = maintainTypeCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
}
