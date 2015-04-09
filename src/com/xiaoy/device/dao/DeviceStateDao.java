package com.xiaoy.device.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.DeviceState;
import com.xiaoy.device.web.form.DeviceStateForm;

public interface DeviceStateDao extends Common<DeviceState> {

	/**
	 * 带分页的，根据条件查询出设备状态信息
	 * @param deviceStateForm	查询条件
	 * @return	List &ltDeviceState&gt
	 */
	List<DeviceState> findDeviceStateConditionWithPage(DeviceStateForm deviceStateForm);

	/**
	 * 根据条件查询出现总记录数
	 * @param deviceStateForm	查询条件
	 * @return	总记录数
	 */
	int countDeviceStateByCondition(DeviceStateForm deviceStateForm);

}
