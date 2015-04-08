package com.xiaoy.device.servic;

import java.util.List;

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
	void deviceSave(DeviceInfoForm deviceForm);

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
	void deviceUpdate(DeviceInfoForm deviceForm);

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

}
