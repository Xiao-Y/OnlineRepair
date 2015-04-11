package com.xiaoy.base.entites;

import java.util.Date;

/**
 * 申报故障信息实体类
 * 
 * @author XiaoY
 * @explain
 * 
 * @date: 2015年4月11日 下午11:16:39
 */
public class Reporting
{
	/* 申报故障信息id */
	private String reportingUuid;
	/* 申报人联系方式 */
	private String reportingPhone;
	/* 预约日期 */
	private Date orderTime;
	/* 优先级别id */
	private String priorCode;
	/* 设备图片URL */
	private String devicePicUrl;
	/* 故障原因 */
	private String account;
	/* 备注 */
	private String remark;
	/* 申请时间 */
	private Date reportingTime;
	/* 一个用户可以申请多个申报，一个申报只能对应一个用户 */
	private User user = new User();
	/* 一个申报信息对应一个设备状态，一个设备状态可能对应多个申报信息 */
	private DeviceState deviceState = new DeviceState();

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

	public Date getOrderTime()
	{
		return orderTime;
	}

	public void setOrderTime(Date orderTime)
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

	public Date getReportingTime()
	{
		return reportingTime;
	}

	public void setReportingTime(Date reportingTime)
	{
		this.reportingTime = reportingTime;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public DeviceState getDeviceState()
	{
		return deviceState;
	}

	public void setDeviceState(DeviceState deviceState)
	{
		this.deviceState = deviceState;
	}
}
