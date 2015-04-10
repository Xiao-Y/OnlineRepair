package com.xiaoy.device.servic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xiaoy.device.web.form.DeviceStateForm;

public interface DeviceStateService {

	/**
	 * 根据条件查询出设备状态集合
	 * @param deviceStateForm	查询条件
	 * @return	List &ltDeviceStateForm&gt
	 */
	List<DeviceStateForm> findDeviceStateConditionWithPage(
			DeviceStateForm deviceStateForm);

	/**
	 * 根据条件查询出现记录数
	 * @param deviceStateForm	查询条件
	 * @return	记录数
	 */
	int countDeviceStateByCondition(DeviceStateForm deviceStateForm);

	/**
	 * 保存设备状态信息
	 * @param deviceStateForm	设备状态信息
	 */
	void deviceStateSave(DeviceStateForm deviceStateForm, HttpServletRequest request);

	/**
	 * 通过设备的状态uuid查询出现设备的状态信息
	 * @param deviceTypeUuid	设备状态uuid
	 * @return	DeviceStateForm	设备的状态信息
	 */
	DeviceStateForm findDeviceStateByUuid(String deviceStateUuid);

	/**
	 * 更新设备状态信息
	 * @param deviceStateForm	设备状态信息
	 */
	void deviceStateUpdate(DeviceStateForm deviceStateForm, HttpServletRequest request);

	/**
	 * 通过设备状态信息uuid删除设备状态信息
	 * @param deviceStateUuid	设备状态uuid
	 */
	void deviceStateDeleteByUuid(String deviceStateUuid);

	/**
	 * 通过一组设备状态uuid批量删除设备状态信息
	 * @param ids	设备状态uuid
	 */
	void deviceStateDeleteByIds(String[] ids);

}
