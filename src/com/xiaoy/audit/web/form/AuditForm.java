package com.xiaoy.audit.web.form;

import com.xiaoy.base.web.form.BaseForm;

public class AuditForm extends BaseForm
{
	/**
	 * 【故障申报审核】--【待审核】
	 */
	public final static String AUDIT_WAIT = "【故障申报审核】--【待审核】";
	/**
	 * 【故障申报审核】--【审核通过】
	 */
	public final static String AUDIT_PASS = "【故障申报审核】--【审核通过】";

	/* 审核uuid */
	private String auditUuid;
	/* 审核状态 */
	private String auditStatCode;
	/* 审核时间 */
	private String auditTime;
	/* 维护状态 */
	private String maintainStatCode;
	/* 完成时间 */
	private String finishTime;
	/* 驳回原因 */
	private String failAccount;
	/* 申报故障信息uuid */
	private String reportingUuid;
	/* 维护人员Uuid */
	private String maintainUuid;
	/* 维护人员名字 */
	private String maintainName;
	/* 维护人员手机号 */
	private String maintainPhone;
	/* 申报用户uuid */
	private String reportingUserUuid;
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
	/* 设备状态uuid */
	private String deviceStateUuid;
	/* 设备状态的图片的url */
	private String devicePicUrl;
	/* 预约日期 */
	private String orderTime;
	/* 优先级别Name */
	private String priorName;
	/* 评价信息uuid */
	private String evaluateUuid;

	public String getEvaluateUuid()
	{
		return evaluateUuid;
	}

	public void setEvaluateUuid(String evaluateUuid)
	{
		this.evaluateUuid = evaluateUuid;
	}

	public String getOrderTime()
	{
		return orderTime;
	}

	public void setOrderTime(String orderTime)
	{
		this.orderTime = orderTime;
	}

	public String getPriorName()
	{
		return priorName;
	}

	public void setPriorName(String priorName)
	{
		this.priorName = priorName;
	}

	public String getMaintainName()
	{
		return maintainName;
	}

	public void setMaintainName(String maintainName)
	{
		this.maintainName = maintainName;
	}

	public String getMaintainPhone()
	{
		return maintainPhone;
	}

	public void setMaintainPhone(String maintainPhone)
	{
		this.maintainPhone = maintainPhone;
	}

	public String getDevicePicUrl()
	{
		return devicePicUrl;
	}

	public void setDevicePicUrl(String devicePicUrl)
	{
		this.devicePicUrl = devicePicUrl;
	}

	public String getDeviceStateUuid()
	{
		return deviceStateUuid;
	}

	public void setDeviceStateUuid(String deviceStateUuid)
	{
		this.deviceStateUuid = deviceStateUuid;
	}

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

	public String getAuditTime()
	{
		return auditTime;
	}

	public void setAuditTime(String auditTime)
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

	public String getFinishTime()
	{
		return finishTime;
	}

	public void setFinishTime(String finishTime)
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

	public String getReportingUserUuid()
	{
		return reportingUserUuid;
	}

	public void setReportingUserUuid(String reportingUserUuid)
	{
		this.reportingUserUuid = reportingUserUuid;
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
