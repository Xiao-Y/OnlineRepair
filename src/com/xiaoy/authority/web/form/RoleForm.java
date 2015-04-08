package com.xiaoy.authority.web.form;

/**
 * 角色表单
 * @author XiaoY
 *
 * @explain 
 *
 * @date: 
 * 2014年12月22日 下午9:36:44
 */
public class RoleForm
{
	//角色的id
	private String roleId;
	
	//权限的id
	private String[] selectoper;
	
	//用户的id
	private String[] selectuser;

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public String[] getSelectoper()
	{
		return selectoper;
	}

	public void setSelectoper(String[] selectoper)
	{
		this.selectoper = selectoper;
	}

	public String[] getSelectuser()
	{
		return selectuser;
	}

	public void setSelectuser(String[] selectuser)
	{
		this.selectuser = selectuser;
	}
}
