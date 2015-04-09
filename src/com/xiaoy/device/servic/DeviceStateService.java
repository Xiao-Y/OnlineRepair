package com.xiaoy.device.servic;

import java.util.List;

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

}
