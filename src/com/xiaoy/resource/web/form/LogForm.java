package com.xiaoy.resource.web.form;

import com.xiaoy.base.web.form.BaseForm;


public class LogForm extends BaseForm
{
	/* 日志ID */
	private String logID;
	/* IP地址 */
	private String ipAddress;
	/* 操作人 */
	private String opeName;
	/* 操作时间 */
	private String opeTime;
	/* 操作明细 */
	private String details;
	
	/* 用于删除的id集合*/
	private String[] logId;
	
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

	public String getOpeTime()
	{
		return opeTime;
	}

	public void setOpeTime(String opeTime)
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

	public String[] getLogId()
	{
		return logId;
	}

	public void setLogId(String[] logId)
	{
		this.logId = logId;
	}
}
