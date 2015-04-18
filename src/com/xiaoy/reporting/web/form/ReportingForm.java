package com.xiaoy.reporting.web.form;

import com.xiaoy.base.web.form.BaseForm;

public class ReportingForm extends BaseForm
{
	/* 申报故障信息id */
	private String reportingUuid;
	/* 申报人联系方式 */
	private String reportingPhone;
	/* 预约日期 */
	private String orderTime;
	/* 优先级别id */
	private String priorCode;
	/* 故障设备图片URL */
	private String devicePicUrl;
	/* 故障原因 */
	private String account;
	/* 备注 */
	private String remark;
	/* 申请时间 */
	private String reportingTime;
	/* 设备状态的uuid */
	private String deviceStateUuid;
	/* 用户的uuid */
	private String userUuid;
	/* 标识量：为1时继续添加申报信息 */
	private String flag;
	/* 设备名 */
	private String deviceName;
	/* 安装位置id */
	private String installationSiteCode;
	/* 安装位置名 */
	private String installationSiteName;
	/* 评价状态 */
	private String evaluateStatCode;
	/* 评价状态名 */
	private String evaluateStatName;
	/* 申报人 */
	private String name;
	/* 维护的类别 */
	private String maintainTypeCode;
	/* 维护的类别名 */
	private String maintainTypeName;
	/* 维护状态 */
	private String maintainStatCode;
	/* 维护状态名 */
	private String maintainStatName;
	/* 维护人员 */
	private String maintainName;
	/* 维护人员手机号 */
	private String maintainPhone;
	/* 区域code */
	private String areaCode;
	/* 区域名字 */
	private String areaName;
	/* 审核状态 */
	private String auditStatCode;
	/* 审核状态名 */
	private String auditStatName;
	/* 登陆名 */
	private String loginName;
	/* 设备信息uuid */
	private String deviceTypeUuid;
	/* 审核时间 */
	private String auditTime;
	/* 设备型号 */
	private String version;
	/* 完成时间 */
	private String finishTime;
	/* 维护人员Uuid */
	private String maintainUuid;
	/* 维护人员姓名 */
	private String maintainUserName;
	/*申报信息的uuid*/
	private String auditUuid;

	public String getAuditUuid()
	{
		return auditUuid;
	}

	public void setAuditUuid(String auditUuid)
	{
		this.auditUuid = auditUuid;
	}

	public String getMaintainUserName()
	{
		return maintainUserName;
	}

	public void setMaintainUserName(String maintainUserName)
	{
		this.maintainUserName = maintainUserName;
	}

	public String getAuditTime()
	{
		return auditTime;
	}

	public void setAuditTime(String auditTime)
	{
		this.auditTime = auditTime;
	}

	public String getInstallationSiteName()
	{
		return installationSiteName;
	}

	public void setInstallationSiteName(String installationSiteName)
	{
		this.installationSiteName = installationSiteName;
	}

	public String getEvaluateStatName()
	{
		return evaluateStatName;
	}

	public void setEvaluateStatName(String evaluateStatName)
	{
		this.evaluateStatName = evaluateStatName;
	}

	public String getMaintainTypeName()
	{
		return maintainTypeName;
	}

	public void setMaintainTypeName(String maintainTypeName)
	{
		this.maintainTypeName = maintainTypeName;
	}

	public String getMaintainStatName()
	{
		return maintainStatName;
	}

	public void setMaintainStatName(String maintainStatName)
	{
		this.maintainStatName = maintainStatName;
	}

	public String getAreaName()
	{
		return areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	public String getAuditStatName()
	{
		return auditStatName;
	}

	public void setAuditStatName(String auditStatName)
	{
		this.auditStatName = auditStatName;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	public String getAuditStatCode()
	{
		return auditStatCode;
	}

	public void setAuditStatCode(String auditStatCode)
	{
		this.auditStatCode = auditStatCode;
	}

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getMaintainStatCode()
	{
		return maintainStatCode;
	}

	public String getMaintainName()
	{
		return maintainName;
	}

	public String getMaintainPhone()
	{
		return maintainPhone;
	}

	public void setMaintainPhone(String maintainPhone)
	{
		this.maintainPhone = maintainPhone;
	}

	public void setMaintainName(String maintainName)
	{
		this.maintainName = maintainName;
	}

	public void setMaintainStatCode(String maintainStatCode)
	{
		this.maintainStatCode = maintainStatCode;
	}

	public String getInstallationSiteCode()
	{
		return installationSiteCode;
	}

	public void setInstallationSiteCode(String installationSiteCode)
	{
		this.installationSiteCode = installationSiteCode;
	}

	public String getEvaluateStatCode()
	{
		return evaluateStatCode;
	}

	public void setEvaluateStatCode(String evaluateStatCode)
	{
		this.evaluateStatCode = evaluateStatCode;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMaintainTypeCode()
	{
		return maintainTypeCode;
	}

	public void setMaintainTypeCode(String maintainTypeCode)
	{
		this.maintainTypeCode = maintainTypeCode;
	}

	public String getDeviceName()
	{
		return deviceName;
	}

	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public String getUserUuid()
	{
		return userUuid;
	}

	public void setUserUuid(String userUuid)
	{
		this.userUuid = userUuid;
	}

	public String getDeviceStateUuid()
	{
		return deviceStateUuid;
	}

	public void setDeviceStateUuid(String deviceStateUuid)
	{
		this.deviceStateUuid = deviceStateUuid;
	}

	public String getReportingUuid()
	{
		return reportingUuid;
	}

	public void setReportingUuid(String reportingUuid)
	{
		this.reportingUuid = reportingUuid;
	}

	public String getReportingPhone()
	{
		return reportingPhone;
	}

	public void setReportingPhone(String reportingPhone)
	{
		this.reportingPhone = reportingPhone;
	}

	public String getOrderTime()
	{
		return orderTime;
	}

	public void setOrderTime(String orderTime)
	{
		this.orderTime = orderTime;
	}

	public String getPriorCode()
	{
		return priorCode;
	}

	public void setPriorCode(String priorCode)
	{
		this.priorCode = priorCode;
	}

	public String getDevicePicUrl()
	{
		return devicePicUrl;
	}

	public void setDevicePicUrl(String devicePicUrl)
	{
		this.devicePicUrl = devicePicUrl;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getReportingTime()
	{
		return reportingTime;
	}

	public void setReportingTime(String reportingTime)
	{
		this.reportingTime = reportingTime;
	}

	public String getDeviceTypeUuid()
	{
		return deviceTypeUuid;
	}

	public void setDeviceTypeUuid(String deviceTypeUuid)
	{
		this.deviceTypeUuid = deviceTypeUuid;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getFinishTime()
	{
		return finishTime;
	}

	public void setFinishTime(String finishTime)
	{
		this.finishTime = finishTime;
	}

	public String getMaintainUuid()
	{
		return maintainUuid;
	}

	public void setMaintainUuid(String maintainUuid)
	{
		this.maintainUuid = maintainUuid;
	}
}
