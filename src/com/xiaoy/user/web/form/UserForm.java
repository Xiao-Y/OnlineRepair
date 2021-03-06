package com.xiaoy.user.web.form;

import com.xiaoy.base.web.form.BaseForm;

public class UserForm extends BaseForm
{
	/* 用户id */
	private String userUuid;
	/* 登陆名 */
	private String loginName;
	/* 用户姓名 */
	private String name;
	/* 性别id */
	private String sexCode;
	/*性别*/
	private String sex;
	/* 维护类别 */
	private String maintainTypeCode;
	/* 维护类别名称*/
	private String maintainTypeName;
	/* 联系方式 */
	private String phone;
	/* 地址 */
	private String address;
	/* 备注 */
	private String remark;
	/* 密码 */
	private String password;
	/* 注册时间 */
	private String registerTime;
	
	/**
	 * 为1时标识为用户个人信息
	 * 为2时标识为更新个人信息
	 */
	private String falg;
	
	/**
	 * 判断该角色是否拥有某个用户：<br/>
	 * flag == 0: 表示该角色不拥有某个用户<br/>
	 * flag == 1: 表示该角色拥有某个用户<br/>
	 */
	private String flag;
	
	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public String getUserUuid()
	{
		return userUuid;
	}

	public void setUserUuid(String userUuid)
	{
		this.userUuid = userUuid;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSexCode()
	{
		return sexCode;
	}

	public void setSexCode(String sexCode)
	{
		this.sexCode = sexCode;
	}

	public String getMaintainTypeCode()
	{
		return maintainTypeCode;
	}

	public void setMaintainTypeCode(String maintainTypeCode)
	{
		this.maintainTypeCode = maintainTypeCode;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRegisterTime()
	{
		return registerTime;
	}

	public void setRegisterTime(String registerTime)
	{
		this.registerTime = registerTime;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getMaintainTypeName()
	{
		return maintainTypeName;
	}

	public void setMaintainTypeName(String maintainTypeName)
	{
		this.maintainTypeName = maintainTypeName;
	}

	public String getFalg() {
		return falg;
	}

	public void setFalg(String falg) {
		this.falg = falg;
	}
}
