package com.xiaoy.base.entites;

/**
 * 
 * @author XiaoY
 * 
 * @explain 实体类 对应的表为Elec_Role_Popedom(角色权限信息表)
 * 
 * @date: 2014年12月22日 下午8:52:29
 */
public class RolePopedom
{
	private String roleCode; // 主键Code
	private String popedomCode; // 配置web文件中权限的编码code的字符串连接
	private String remark; // 备注

	public String getRoleCode()
	{
		return roleCode;
	}

	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
	}

	public String getPopedomCode()
	{
		return popedomCode;
	}

	public void setPopedomCode(String popedomCode)
	{
		this.popedomCode = popedomCode;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}
}
