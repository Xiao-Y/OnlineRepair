package com.xiaoy.base.entites;

import java.util.HashSet;
import java.util.Set;

/**
 * 设备信息实体类
 * 
 * @author XiaoY
 * @explain 用于存放设备的信息
 * @date: 2015年3月31日 下午11:25:24
 */
public class DeviceInfo
{
	/* 设备信息uuid */
	private String deviceTypeUuid;
	/* 设备名 */
	private String deviceName;
	/* 型号 */
	private String version;
	/* 生产商 */
	private String producerName;
	/* 生产商联系方式 */
	private String producerPhone;
	/* 设备价格 */
	private Double devicePrice;
	/* 设备数量 */
	private Integer deviceAmount;
	/* 设备图片URL */
	private String devicePicUrl;
	/* 备注 */
	private String remark;
	
	Set<DeviceState> deviceState = new HashSet<DeviceState>();

	public DeviceInfo() {
	}

	public DeviceInfo(String deviceName) {
		this.deviceName = deviceName;
	}

	public DeviceInfo(String deviceTypeUuid, String version) {
		this.deviceTypeUuid = deviceTypeUuid;
		this.version = version;
	}

	public String getDeviceTypeUuid()
	{
		return deviceTypeUuid;
	}

	public void setDeviceTypeUuid(String deviceTypeUuid)
	{
		this.deviceTypeUuid = deviceTypeUuid;
	}

	public String getDeviceName()
	{
		return deviceName;
	}

	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getProducerName()
	{
		return producerName;
	}

	public void setProducerName(String producerName)
	{
		this.producerName = producerName;
	}

	public String getProducerPhone()
	{
		return producerPhone;
	}

	public void setProducerPhone(String producerPhone)
	{
		this.producerPhone = producerPhone;
	}

	public double getDevicePrice()
	{
		return devicePrice;
	}

	public void setDevicePrice(Double devicePrice)
	{
		this.devicePrice = devicePrice;
	}

	public Integer getDeviceAmount()
	{
		return deviceAmount;
	}

	public void setDeviceAmount(Integer deviceAmount)
	{
		this.deviceAmount = deviceAmount;
	}

	public String getDevicePicUrl()
	{
		return devicePicUrl;
	}

	public void setDevicePicUrl(String devicePicUrl)
	{
		this.devicePicUrl = devicePicUrl;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public Set<DeviceState> getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(Set<DeviceState> deviceState) {
		this.deviceState = deviceState;
	}
}
