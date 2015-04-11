package com.xiaoy.base.entites;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	private String sexCode;
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
	
	/* 持有申报信息的集合 */
	private Set<Reporting> reporting = new HashSet<Reporting>();
	
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
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
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

	public Set<Reporting> getReporting()
	{
		return reporting;
	}

	public void setReporting(Set<Reporting> reporting)
	{
		this.reporting = reporting;
	}
}
