package com.xiaoy.device.servic;

import java.util.List;

import com.xiaoy.device.web.form.DeviceForm;

public interface DeviceService
{

	/**
	 * 根据条件查询出设备的所有信息
	 * @param deviceForm	查询条件
	 * @return	List&ltDeviceForm&gt
	 */
	List<DeviceForm> findDeviceInfoByCondition(DeviceForm deviceForm);

	/**
	 * 根据条件查询出现设备的总记录数
	 * @param deviceForm	查询条件
	 * @return	Integer	总记录数
	 */
	Integer countDeviceInfoByCondition(DeviceForm deviceForm);

}
