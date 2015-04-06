package com.xiaoy.device.web.form;

import java.io.File;

import com.xiaoy.base.web.form.BaseForm;

/**
 * 设备信息
 * 
 * @author XiaoY
 * @explain
 * 
 * @date: 2015年4月1日 下午10:39:55
 */
public class DeviceForm extends BaseForm {
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
	private String devicePrice;
	/* 设备数量 */
	private String deviceAmount;
	/* 设备图片URL */
	private String devicePicUrl;
	/* 备注 */
	private String remark;

	// 上传的文件
	private File image; 
	// 文件名称
	private String imageFileName; 
	// 文件类型
	private String imageContentType; 
	
	//文件的新名字
	private String newFileName;

	public String getDeviceTypeUuid() {
		return deviceTypeUuid;
	}

	public void setDeviceTypeUuid(String deviceTypeUuid) {
		this.deviceTypeUuid = deviceTypeUuid;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getProducerPhone() {
		return producerPhone;
	}

	public void setProducerPhone(String producerPhone) {
		this.producerPhone = producerPhone;
	}

	public String getDevicePrice() {
		return devicePrice;
	}

	public void setDevicePrice(String devicePrice) {
		this.devicePrice = devicePrice;
	}

	public String getDeviceAmount() {
		return deviceAmount;
	}

	public void setDeviceAmount(String deviceAmount) {
		this.deviceAmount = deviceAmount;
	}

	public String getDevicePicUrl() {
		return devicePicUrl;
	}

	public void setDevicePicUrl(String devicePicUrl) {
		this.devicePicUrl = devicePicUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getNewFileName()
	{
		return newFileName;
	}

	public void setNewFileName(String newFileName)
	{
		this.newFileName = newFileName;
	}
}
