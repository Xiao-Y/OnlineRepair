package com.xiaoy.device.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.DeviceInfo;
import com.xiaoy.device.web.form.DeviceInfoForm;

public interface DeviceInfoDao extends Common<DeviceInfo>
{

	/**
	 * 根据条件查询出现设备的信息
	 * @param deviceForm	查询条件
	 * @return
	 */
	List<DeviceInfo> findDeviceInfoByCondition(DeviceInfoForm deviceForm);

	/**
	 * 根据条件查询出现设备的总记录数
	 * @param deviceForm	查询条件
	 * @return
	 */
	Integer countDeviceInfoByCondition(DeviceInfoForm deviceForm);

	/**
	 * 批量删除设备信息
	 * @param ids	设备uuid
	 */
	void deleteDeviceByIds(String[] ids);

}
