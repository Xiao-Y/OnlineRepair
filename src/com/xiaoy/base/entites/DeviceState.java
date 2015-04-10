package com.xiaoy.base.entites;

import java.util.Date;

/**
 * 设备运行状态管理
 * 
 * @author XiaoY
 * @date 2015年4月8日
 */
public class DeviceState {
	/* 设备状态uuid */
	private String deviceStateUuid;
	/* 安装的区域id */
	private String areaCode;
	/* 安装位置id */
	private String installationSiteCode;

	/* 一种类型设备对应多种状态（因为有多个设备） */
	private DeviceInfo deviceInfo = new DeviceInfo();
	/* 上次检修日期 */
	private Date lastTime;
	/* 下次检修日期 */
	private Date nextTime;
	/* 安装日期 */
	private Date installationTime;
	/* 运行状态id */
	private String stateCode;
	/* 设备图片URL */
	private String devicePicUrl;
	/* 备注 */
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeviceStateUuid() {
		return deviceStateUuid;
	}

	public void setDeviceStateUuid(String deviceStateUuid) {
		this.deviceStateUuid = deviceStateUuid;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getInstallationSiteCode() {
		return installationSiteCode;
	}

	public void setInstallationSiteCode(String installationSiteCode) {
		this.installationSiteCode = installationSiteCode;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public Date getInstallationTime() {
		return installationTime;
	}

	public void setInstallationTime(Date installationTime) {
		this.installationTime = installationTime;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDevicePicUrl() {
		return devicePicUrl;
	}

	public void setDevicePicUrl(String devicePicUrl) {
		this.devicePicUrl = devicePicUrl;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
}
