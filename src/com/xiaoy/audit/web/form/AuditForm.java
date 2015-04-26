package com.xiaoy.audit.web.form;

import java.util.Date;

import com.xiaoy.base.web.form.BaseForm;

public class AuditForm extends BaseForm
{
	/* 审核uuid */
	private String auditUuid;
	/* 审核状态 */
	private String auditStatCode;
	/* 审核时间 */
	private Date auditTime;
	/* 维护状态 */
	private String maintainStatCode;
	/* 完成时间 */
	private Date finishTime;
	/* 驳回原因 */
	private String failAccount;
	/* 申报故障信息uuid */
	private String reportingUuid;
	/* 维护人员Uuid */
	private String maintainUuid;
	/* 用户uuid */
	private String userUuid;
	
	/* 设备名 */
	private String deviceName;
	/* 安装位置id */
	private String installationSiteCode;
	/* 安装位置名 */
	private String installationSiteName;
	/* 区域code */
	private String areaCode;
	/* 区域名字 */
	private String areaName;
	/* 申报人 */
	private String name;
	/* 申请时间 */
	private String reportingTime;
	/* 申报人联系方式 */
	private String reportingPhone;
	/* 设备型号 */
	private String version;
	/* 故障原因 */
	private String account;
	/* 备注 */
	private String remark;
	/* 维护的类别 */
	private String maintainTypeCode;

	public String getMaintainTypeCode()
	{
		return maintainTypeCode;
	}

	public void setMaintainTypeCode(String maintainTypeCode)
	{
		this.maintainTypeCode = maintainTypeCode;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getAuditUuid()
	{
		return auditUuid;
	}

	public void setAuditUuid(String auditUuid)
	{
		this.auditUuid = auditUuid;
	}

	public String getAuditStatCode()
	{
		return auditStatCode;
	}

	public void setAuditStatCode(String auditStatCode)
	{
		this.auditStatCode = auditStatCode;
	}

	public Date getAuditTime()
	{
		return auditTime;
	}

	public void setAuditTime(Date auditTime)
	{
		this.auditTime = auditTime;
	}

	public String getMaintainStatCode()
	{
		return maintainStatCode;
	}

	public void setMaintainStatCode(String maintainStatCode)
	{
		this.maintainStatCode = maintainStatCode;
	}

	public Date getFinishTime()
	{
		return finishTime;
	}

	public void setFinishTime(Date finishTime)
	{
		this.finishTime = finishTime;
	}

	public String getFailAccount()
	{
		return failAccount;
	}

	public void setFailAccount(String failAccount)
	{
		this.failAccount = failAccount;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getReportingUuid()
	{
		return reportingUuid;
	}

	public void setReportingUuid(String reportingUuid)
	{
		this.reportingUuid = reportingUuid;
	}

	public String getMaintainUuid()
	{
		return maintainUuid;
	}

	public void setMaintainUuid(String maintainUuid)
	{
		this.maintainUuid = maintainUuid;
	}

	public String getUserUuid()
	{
		return userUuid;
	}

	public void setUserUuid(String userUuid)
	{
		this.userUuid = userUuid;
	}

	public String getDeviceName()
	{
		return deviceName;
	}

	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}

	public String getInstallationSiteCode()
	{
		return installationSiteCode;
	}

	public void setInstallationSiteCode(String installationSiteCode)
	{
		this.installationSiteCode = installationSiteCode;
	}

	public String getInstallationSiteName()
	{
		return installationSiteName;
	}

	public void setInstallationSiteName(String installationSiteName)
	{
		this.installationSiteName = installationSiteName;
	}

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getAreaName()
	{
		return areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getReportingTime()
	{
		return reportingTime;
	}

	public void setReportingTime(String reportingTime)
	{
		this.reportingTime = reportingTime;
	}

	public String getReportingPhone()
	{
		return reportingPhone;
	}

	public void setReportingPhone(String reportingPhone)
	{
		this.reportingPhone = reportingPhone;
	}
}
