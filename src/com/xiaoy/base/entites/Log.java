package com.xiaoy.base.entites;

import java.util.Date;

/**
 * 用户操作日志
 * 
 * @author XiaoY
 * @explain
 * 
 * @date: 2015年3月28日 下午4:12:14
 */
public class Log
{
	/* 日志ID */
	private String logID;
	/* IP地址 */
	private String ipAddress;
	/* 操作人 */
	private String opeName;
	/* 操作时间 */
	private Date opeTime;
	/* 操作明细 */
	private String details;

	public String getLogID()
	{
		return logID;
	}

	public void setLogID(String logID)
	{
		this.logID = logID;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	public String getOpeName()
	{
		return opeName;
	}

	public void setOpeName(String opeName)
	{
		this.opeName = opeName;
	}

	public Date getOpeTime()
	{
		return opeTime;
	}

	public void setOpeTime(Date opeTime)
	{
		this.opeTime = opeTime;
	}

	public String getDetails()
	{
		return details;
	}

	public void setDetails(String details)
	{
		this.details = details;
	}
}
