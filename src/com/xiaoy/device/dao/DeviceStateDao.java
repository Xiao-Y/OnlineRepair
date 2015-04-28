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

	/**
	 * 通过一组设备状态uuid删除设备状态信息
	 * @param ids	设备状态uuid
	 */
	void deviceStateDeleteByIds(String[] ids);

	/**
	 * 查询出现设备状态中不重复的设备的区域
	 * @return	List &ltDeviceState&gt
	 */
	List<DeviceState> findDeviceStateArea();

	/**
	 * 在设备状态表中通过区域找到所有不重复的安装位置
	 * @param areaCode	区域code
	 * @return	List &ltDeviceState&gt
	 */
	List<DeviceState> findInstallationSiteByArea(String areaCode);

	/**
	 * 通过区域和安置位置在设备状态信息表中查询出现所有的设备名
	 * @param areaCode	区域
	 * @param installationSiteCode	安置位置
	 * @return	List &ltDeviceState&gt	含有设备名的设备状态PO
	 */
	List<DeviceState> findDeviceNameByinstallationSite(String areaCode,String installationSiteCode);

	/**
	 * 通过区域和安置位置和设备名，在设备状态信息表中查询出现所有的设备型号
	 * @param areaCode	区域
	 * @param installationSiteCode	安置位置
	 * @param deviceName	设备名
	 * @return	List &ltDeviceStateForm&gt 含有设备型号的设备状态PO对象
	 */
	List<DeviceState> findVersionBydeviceName(String areaCode,String installationSiteCode, String deviceName);

	/**
	 * 根据区域、安装位置、设备信息uuid查询设备状态信息是否存在
	 * @param form	含有区域code、安装位置code、设备信息uuid
	 * @return	Integer 存在的个数
	 */
	Integer findDeviceStateCondition(DeviceStateForm form);

	/**
	 * 修改设备运行状态
	 * @param statException		设备运行状态
	 * @param deviceStateUuid	设备状态的uuid
	 */
	void deviceStateUpdateSatae(String deviceStateUuid, String statException);

}
