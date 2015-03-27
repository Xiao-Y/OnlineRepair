package com.xiaoy.base.entites;

/**
 * 数据字典<br/>
 * 用于存放一些数据的类别
 * 
 * @author XiaoY
 * @explain
 * 
 * @date: 2015年3月27日 下午9:30:15
 */
public class Dictionary
{
	/* 字典id */
	private Integer seqID;
	/* 查询关键字 */
	private String keyWord;
	/* 数据字典Code */
	private Integer ddlCode;
	/* 数据字典Name */
	private String ddlName;

	public Integer getSeqID()
	{
		return seqID;
	}

	public void setSeqID(Integer seqID)
	{
		this.seqID = seqID;
	}

	public String getKeyWord()
	{
		return keyWord;
	}

	public void setKeyWord(String keyWord)
	{
		this.keyWord = keyWord;
	}

	public Integer getDdlCode()
	{
		return ddlCode;
	}

	public void setDdlCode(Integer ddlCode)
	{
		this.ddlCode = ddlCode;
	}

	public String getDdlName()
	{
		return ddlName;
	}

	public void setDdlName(String ddlName)
	{
		this.ddlName = ddlName;
	}
}
