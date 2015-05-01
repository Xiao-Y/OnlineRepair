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

	/**
	 * 在设备状态表中查询出现设备的区域
	 * @return	List &ltDeviceStateForm&gt
	 */
	List<DeviceStateForm> findDeviceArea();

	/**
	 * 在设备状态表中通过区域找到所有不重复的安装位置
	 * @param areaCode	区域code
	 * @return	List &ltDeviceStateForm&gt
	 */
	List<DeviceStateForm> findInstallationSiteByArea(String areaCode);

	/**
	 * 通过区域和安置位置在设备状态信息表中查询出现所有的设备名
	 * @param areaCode	区域
	 * @param installationSiteCode	安置位置
	 * @return	List &ltDeviceStateForm&gt	含有设备名的设备状态PO
	 */
	List<DeviceStateForm> findDeviceNameByinstallationSite(String areaCode,String installationSiteCode);

	/**
	 * 通过区域和安置位置和设备名，在设备状态信息表中查询出现所有的设备型号
	 * @param areaCode	区域
	 * @param installationSiteCode	安置位置
	 * @param deviceName	设备名
	 * @return	List &ltDeviceStateForm&gt 含有设备型号的设备状态PO对象
	 */
	List<DeviceStateForm> findVersionBydeviceNamee(String areaCode,String installationSiteCode, String deviceName);

	/**
	 * 根据区域、安装位置、设备信息uuid查询设备状态信息是否存在
	 * @param form	含有区域code、安装位置code、设备信息uuid
	 * @return	Boolean ture表示存在。false表示不存在
	 */
	Boolean findDeviceStateCondition(DeviceStateForm form);
}
