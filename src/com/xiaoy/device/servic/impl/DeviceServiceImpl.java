package com.xiaoy.device.servic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.Device;
import com.xiaoy.device.dao.DeviceDao;
import com.xiaoy.device.servic.DeviceService;
import com.xiaoy.device.web.form.DeviceForm;

@Service
@Transactional(readOnly=true)
public class DeviceServiceImpl implements DeviceService
{

	@Resource
	private DeviceDao deviceDao;
	
	@Override
	public List<DeviceForm> findDeviceInfoByCondition(DeviceForm deviceForm)
	{
		List<Device> list = deviceDao.findDeviceInfoByCondition(deviceForm);
		List<DeviceForm> listForm = this.deviceListVOToListPo(list);
		return listForm;
	}

	private List<DeviceForm> deviceListVOToListPo(List<Device> list)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countDeviceInfoByCondition(DeviceForm deviceForm)
	{
		Integer count = deviceDao.countDeviceInfoByCondition(deviceForm);
		return count;
	}

}
