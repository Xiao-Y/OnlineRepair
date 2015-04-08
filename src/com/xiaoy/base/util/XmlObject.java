package com.xiaoy.base.util;

/**
 * @author XiaoY
 * 
 * @explain 用于存放从Function.xml文件中 获取的权限code、权限名称、父权限code和父权限名称
 * @date: 2014年12月23日 下午7:01:35
 */
@SuppressWarnings("serial")
public class XmlObject implements java.io.Serializable
{
	// 权限code
	private String code;

	// 权限名称
	private String name;

	// 父权限code
	private String parentCode;

	// 父权限的名称
	private String parentName;
	
	/**
	 * 角色编辑页面的复选框是否选种的标识量<br/>
	 * 如果 flag = 0，表示未被选种，没有有该权限<br/>
	 * 如果 flag = 1, 表示被选种，此用户拥有该权限<br/>
	 */
	private String flag;

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getParentCode()
	{
		return parentCode;
	}

	public void setParentCode(String parentCode)
	{
		this.parentCode = parentCode;
	}

	public String getParentName()
	{
		return parentName;
	}

	public void setParentName(String parentName)
	{
		this.parentName = parentName;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}
}
