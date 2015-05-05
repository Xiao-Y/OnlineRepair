package com.xiaoy.evaluate.web.form;

import java.util.Date;

import com.xiaoy.base.web.form.BaseForm;

public class EvaluateForm extends BaseForm
{
	/* 评价信息uuid */
	private String evaluateUuid;
	/* 评价状态 */
	private String evaluateStatCode;
	/* 评价状态名称 */
	private String evaluateStatName;
	/* 评价时间 */
	private Date evaluateTime;
	/* 满意度 */
	private String rankCode;
	/* 申报用户的uuid */
	private String reportingUserUuid;
	/* 申报故障信息uuid */
	private String reportingUuid;
	/* 安装位置 */
	private String installationSite;
	/* 安装位置名 */
	private String installationSiteName;
	/* 区域 */
	private String areaCode;
	/* 区域名 */
	private String areaName;
	/* 设备名 */
	private String deviceName;
	/* 申报人 */
	private String reportingUserName;
	/* 申报时间 */
	private String reportingTime;
	/* 完成时间 */
	private String finishTime;
	/* 申报人手机号 */
	private String reportingPhone;
	/* 用户id */
	private String userUuid;

	public String getEvaluateStatName()
	{
		return evaluateStatName;
	}

	public void setEvaluateStatName(String evaluateStatName)
	{
		this.evaluateStatName = evaluateStatName;
	}

	public String getInstallationSiteName()
	{
		return installationSiteName;
	}

	public void setInstallationSiteName(String installationSiteName)
	{
		this.installationSiteName = installationSiteName;
	}

	public String getAreaName()
	{
		return areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

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

	public String getUserUuid()
	{
		return userUuid;
	}

	public void setUserUuid(String userUuid)
	{
		this.userUuid = userUuid;
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

	public String getReportingPhone()
	{
		return reportingPhone;
	}

	public void setReportingPhone(String reportingPhone)
	{
		this.reportingPhone = reportingPhone;
	}

	public String getReportingUuid()
	{
		return reportingUuid;
	}

	public void setReportingUuid(String reportingUuid)
	{
		this.reportingUuid = reportingUuid;
	}

	public String getInstallationSite()
	{
		return installationSite;
	}

	public void setInstallationSite(String installationSite)
	{
		this.installationSite = installationSite;
	}

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getDeviceName()
	{
		return deviceName;
	}

	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}

	public String getReportingUserName()
	{
		return reportingUserName;
	}

	public void setReportingUserName(String reportingUserName)
	{
		this.reportingUserName = reportingUserName;
	}

	public String getReportingTime()
	{
		return reportingTime;
	}

	public void setReportingTime(String reportingTime)
	{
		this.reportingTime = reportingTime;
	}

	public String getFinishTime()
	{
		return finishTime;
	}

	public void setFinishTime(String finishTime)
	{
		this.finishTime = finishTime;
	}

}
