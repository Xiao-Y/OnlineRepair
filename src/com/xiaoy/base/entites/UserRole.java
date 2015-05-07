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
	private String userUuid; // 用户ID
	private String roleCode; // 角色code
	private String remark; // 备注

	public Integer getSeqId()
	{
		return seqId;
	}

	public void setSeqId(Integer seqId)
	{
		this.seqId = seqId;
	}

	public String getUserUuid()
	{
		return userUuid;
	}

	public void setUserUuid(String userUuid)
	{
		this.userUuid = userUuid;
	}

	public String getRoleCode()
	{
		return roleCode;
	}

	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
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
