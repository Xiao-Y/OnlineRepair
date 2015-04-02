package com.xiaoy.device.servic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
		List<DeviceForm> listForm = new ArrayList<DeviceForm>();
		for(Device d : list)
		{
			DeviceForm form = new DeviceForm();
			form.setDeviceTypeUuid(d.getDeviceTypeUuid());
			form.setDeviceAmount(d.getDeviceAmount().toString());
			form.setDeviceName(d.getDeviceName());
			form.setDevicePicUrl(d.getDevicePicUrl());
			form.setDevicePrice(d.getDevicePrice() + "");
			form.setDeviceTypeUuid(d.getDeviceTypeUuid());
			form.setProducerName(d.getProducerName());
			form.setProducerPhone(d.getProducerPhone());
			form.setRemark(d.getRemark());
			listForm.add(form);
		}
			
		return listForm;
	}

	@Override
	public Integer countDeviceInfoByCondition(DeviceForm deviceForm)
	{
		Integer count = deviceDao.countDeviceInfoByCondition(deviceForm);
		return count;
	}

	@Override
	public void deviceSave(DeviceForm deviceForm)
	{
		Device entity = new Device();
		entity.setDeviceTypeUuid(deviceForm.getDeviceTypeUuid());
		entity.setDeviceAmount(!StringUtils.isEmpty(deviceForm.getDeviceAmount()) ? Integer.parseInt(deviceForm.getDeviceAmount()) : 0);
		entity.setDeviceName(deviceForm.getDeviceName());
		entity.setDevicePicUrl(deviceForm.getDevicePicUrl());
		entity.setDevicePrice(!StringUtils.isEmpty(deviceForm.getDevicePrice()) ? Double.parseDouble(deviceForm.getDevicePrice()) : 0);
		entity.setDeviceTypeUuid(deviceForm.getDeviceTypeUuid());
		entity.setProducerName(deviceForm.getProducerName());
		entity.setProducerPhone(deviceForm.getProducerPhone());
		entity.setRemark(deviceForm.getRemark());
		
		deviceDao.saveObject(entity);
	}
}
