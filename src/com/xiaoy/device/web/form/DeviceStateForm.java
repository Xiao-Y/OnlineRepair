package com.xiaoy.device.web.form;

import com.xiaoy.base.web.form.BaseForm;

/**
 * 设备运行状态
 * @author XiaoY
 * @date 2015年4月8日
 */
public class DeviceStateForm  extends BaseForm {

	/* 设备状态uuid */
	private String deviceStateUuid;
	/* 安装的区域id */
	private String areaCode;
	/*安装的区域name*/
	private String areaName;
	/* 安装位置id */
	private String installationSiteCode;
	/* 安装位置name */
	private String installationSiteName;
	/* 设备类型信息uuid */
	private String deviceTypeUuid;
	/* 上次检修日期 */
	private String lastTime;
	/* 下次检修日期 */
	private String nextTime;
	/* 安装日期 */
	private String installationTime;
	/* 运行状态id */
	private String stateCode;
	/*运行状态name*/
	private String stateName;
	/* 设备图片URL */
	private String devicePicUrl;
	
	/* 设备名*/
	private String deviceName;
	
	/* 设备型号*/
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public String getDeviceTypeUuid() {
		return deviceTypeUuid;
	}

	public void setDeviceTypeUuid(String deviceTypeUuid) {
		this.deviceTypeUuid = deviceTypeUuid;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getNextTime() {
		return nextTime;
	}

	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}

	public String getInstallationTime() {
		return installationTime;
	}

	public void setInstallationTime(String installationTime) {
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getInstallationSiteName() {
		return installationSiteName;
	}

	public void setInstallationSiteName(String installationSiteName) {
		this.installationSiteName = installationSiteName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
