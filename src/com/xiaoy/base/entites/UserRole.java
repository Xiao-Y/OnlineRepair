package com.xiaoy.base.entites;

/**
 * 
 * @author XiaoY
 * 
 * @explain 实体类 对应的表为User_Role(用户角色信息表)
 * 
 * @date: 2014年12月22日 下午8:45:27
 */
public class UserRole
{
	private Integer seqId; // 主键ID
	private String userId; // 用户ID
	private String roleId; // 角色ID
	private String remark; // 备注

	public Integer getSeqId()
	{
		return seqId;
	}

	public void setSeqId(Integer seqId)
	{
		this.seqId = seqId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
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
