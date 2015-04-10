package com.xiaoy.device.servic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xiaoy.device.web.form.DeviceInfoForm;

public interface DeviceInfoService
{

	/**
	 * 根据条件查询出设备的所有信息
	 * @param deviceForm	查询条件
	 * @return	List&ltDeviceForm&gt
	 */
	List<DeviceInfoForm> findDeviceInfoByCondition(DeviceInfoForm deviceForm);

	/**
	 * 根据条件查询出现设备的总记录数
	 * @param deviceForm	查询条件
	 * @return	Integer	总记录数
	 */
	Integer countDeviceInfoByCondition(DeviceInfoForm deviceForm);

	/**
	 * 保存设备信息
	 * @param deviceForm	设备信息
	 */
	void deviceSave(DeviceInfoForm deviceForm, HttpServletRequest request);

	/**
	 * 通过设备的Uuid获取设备信息
	 * @param deviceTypeUuid	设备Uuid
	 * @return	DeviceForm 设备信息
	 */
	DeviceInfoForm getfindDeviceByUuid(String deviceTypeUuid);

	/**
	 * 更新设备信息
	 * @param deviceForm	设备信息
	 */
	void deviceUpdate(DeviceInfoForm deviceForm, HttpServletRequest request);

	/**
	 * 根据设备的uuid删除设备信息
	 * @param deviceTypeUuid	设备uuid
	 */
	void deviceDeleteByUuid(String deviceTypeUuid);

	/**
	 * 批量删除设备信息
	 * @param ids	设备uuid
	 */
	void deviceDeleteByIds(String[] ids);

	/**
	 * 获取所有设备的名称
	 * @return	List &ltDeviceStateForm&gt	设备名称集合
	 */
	List<DeviceInfoForm> findDeviceName();

	/**
	 * 通过设备名称查找出现设备的所有型号
	 * @param deviceName	设备名称
	 * @return	List &ltDeviceInfoForm&gt	含有设备型号的集合
	 */
	List<DeviceInfoForm> findDeviceVersionByName(String deviceName);

}
