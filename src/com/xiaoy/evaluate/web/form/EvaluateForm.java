package com.xiaoy.evaluate.web.form;

import java.util.Date;

public class EvaluateForm
{
	/* 评价信息uuid */
	private String evaluateUuid;
	/* 评价状态 */
	private String evaluateStatCode;
	/* 评价时间 */
	private Date evaluateTime;
	/* 满意度 */
	private String rankCode;
	/* 申报用户的uuid */
	private String reportingUserUuid;
	/* 申报故障信息uuid */
	private String reportingUuid;
	
	public String getReportingUserUuid()
	{
		return reportingUserUuid;
	}

	public void setReportingUserUuid(String reportingUserUuid)
	{
		this.reportingUserUuid = reportingUserUuid;
	}

	public String getEvaluateUuid()
	{
		return evaluateUuid;
	}

	public void setEvaluateUuid(String evaluateUuid)
	{
		this.evaluateUuid = evaluateUuid;
	}

	public String getEvaluateStatCode()
	{
		return evaluateStatCode;
	}

	public void setEvaluateStatCode(String evaluateStatCode)
	{
		this.evaluateStatCode = evaluateStatCode;
	}

	public Date getEvaluateTime()
	{
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime)
	{
		this.evaluateTime = evaluateTime;
	}

	public String getRankCode()
	{
		return rankCode;
	}

	public void setRankCode(String rankCode)
	{
		this.rankCode = rankCode;
	}

	public String getReportingUuid()
	{
		return reportingUuid;
	}

	public void setReportingUuid(String reportingUuid)
	{
		this.reportingUuid = reportingUuid;
	}
}
