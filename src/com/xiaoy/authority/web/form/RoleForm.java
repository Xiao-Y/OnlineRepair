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
	
	public static final String MODE_NAME = "【权限管理】-【角色权限管理】";
	
	//角色的id
	private String roleCode;
	
	//权限的id
	private String[] popedomCode;
	
	//用户的id
	private String[] userIds;
	
	//角色id
	private String roleid;

	public String getRoleCode()
	{
		return roleCode;
	}

	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
	}

	public String[] getPopedomCode()
	{
		return popedomCode;
	}

	public void setPopedomCode(String[] popedomCode)
	{
		this.popedomCode = popedomCode;
	}

	public String[] getUserIds()
	{
		return userIds;
	}

	public void setUserIds(String[] userIds)
	{
		this.userIds = userIds;
	}

	public String getRoleid()
	{
		return roleid;
	}

	public void setRoleid(String roleid)
	{
		this.roleid = roleid;
	}
}
