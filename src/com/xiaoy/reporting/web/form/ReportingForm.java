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
	/* 设备图片URL */
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
}
