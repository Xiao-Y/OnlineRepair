package com.xiaoy.device.servic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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
			//设备的Vo对象转换成Po对象
			form = this.DeviceVoToPo(form, d);
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
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void deviceSave(DeviceForm deviceForm)
	{
		Device entity = new Device();
		//设备的Po对象转换成Vo对象
		entity = this.DeviceFormPoToVo(deviceForm, entity);
		deviceDao.saveObject(entity);
	}

	@Override
	public DeviceForm getfindDeviceByUuid(String deviceTypeUuid) {
		
		Device device = deviceDao.findObjectById(deviceTypeUuid);
		
		DeviceForm deviceForm = new DeviceForm();
		//设备的Po对象转换成Vo对象
		deviceForm = this.DeviceVoToPo(deviceForm, device);
		return deviceForm;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void deviceUpdate(DeviceForm deviceForm) {
		Device entity = new Device();
		//设备的Po对象转换成Vo对象
		entity = this.DeviceFormPoToVo(deviceForm, entity);
		deviceDao.updateObject(entity);
	}
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void deviceDeleteByUuid(String deviceTypeUuid) {
		deviceDao.deleteObjectByid(deviceTypeUuid);
	}
	

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void deviceDeleteByIds(String[] ids) {
		deviceDao.deleteDeviceByIds(ids);
	}
	
	/**
	 * 设备的Vo对象转换成Po对象
	 * @param form	Po对象
	 * @param d	Vo对象
	 * @return	DeviceForm Po对象
	 */
	private DeviceForm DeviceVoToPo(DeviceForm form, Device d)
	{
		form.setDeviceTypeUuid(d.getDeviceTypeUuid());
		form.setDeviceAmount(d.getDeviceAmount().toString());
		form.setDeviceName(d.getDeviceName());
		form.setDevicePicUrl(d.getDevicePicUrl());
		form.setDevicePrice(d.getDevicePrice() + "");
		form.setDeviceTypeUuid(d.getDeviceTypeUuid());
		form.setProducerName(d.getProducerName());
		form.setProducerPhone(d.getProducerPhone());
		form.setVersion(d.getVersion());
		form.setRemark(d.getRemark());
		return form;
	}
	
	/**
	 * 设备的Po对象转换成Vo对象
	 * @param deviceForm	Po对象
	 * @param entity	Vo对象
	 * @return	Device	Vo对象
	 */
	private Device DeviceFormPoToVo(DeviceForm deviceForm, Device entity)
	{
		entity.setDeviceTypeUuid(deviceForm.getDeviceTypeUuid());
		entity.setDeviceAmount(!StringUtils.isEmpty(deviceForm.getDeviceAmount()) ? Integer.parseInt(deviceForm.getDeviceAmount()) : 0);
		entity.setDeviceName(deviceForm.getDeviceName());
		entity.setDevicePicUrl(deviceForm.getDevicePicUrl());
		entity.setDevicePrice(!StringUtils.isEmpty(deviceForm.getDevicePrice()) ? Double.parseDouble(deviceForm.getDevicePrice()) : 0);
		entity.setDeviceTypeUuid(deviceForm.getDeviceTypeUuid());
		entity.setProducerName(deviceForm.getProducerName());
		entity.setProducerPhone(deviceForm.getProducerPhone());
		entity.setVersion(deviceForm.getVersion());
		entity.setRemark(deviceForm.getRemark());
		
		return entity;
	}
}
