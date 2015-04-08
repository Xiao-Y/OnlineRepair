package com.xiaoy.device.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.Device;
import com.xiaoy.device.web.form.DeviceForm;

public interface DeviceDao extends Common<Device>
{

	/**
	 * 根据条件查询出现设备的信息
	 * @param deviceForm	查询条件
	 * @return
	 */
	List<Device> findDeviceInfoByCondition(DeviceForm deviceForm);

	/**
	 * 根据条件查询出现设备的总记录数
	 * @param deviceForm	查询条件
	 * @return
	 */
	Integer countDeviceInfoByCondition(DeviceForm deviceForm);

	/**
	 * 批量删除设备信息
	 * @param ids	设备uuid
	 */
	void deleteDeviceByIds(String[] ids);

}
