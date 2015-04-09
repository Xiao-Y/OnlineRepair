package com.xiaoy.device.servic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.DeviceInfo;
import com.xiaoy.device.dao.DeviceInfoDao;
import com.xiaoy.device.servic.DeviceInfoService;
import com.xiaoy.device.web.form.DeviceInfoForm;

@Service
@Transactional(readOnly=true)
public class DeviceInfoServiceImpl implements DeviceInfoService
{

	@Resource
	private DeviceInfoDao deviceDao;
	
	@Override
	public List<DeviceInfoForm> findDeviceInfoByCondition(DeviceInfoForm deviceForm)
	{
		List<DeviceInfo> list = deviceDao.findDeviceInfoByCondition(deviceForm);
		List<DeviceInfoForm> listForm = this.deviceListVOToListPo(list);
		return listForm;
	}

	private List<DeviceInfoForm> deviceListVOToListPo(List<DeviceInfo> list)
	{
		List<DeviceInfoForm> listForm = new ArrayList<DeviceInfoForm>();
		for(DeviceInfo d : list)
		{
			DeviceInfoForm form = new DeviceInfoForm();
			//设备的Vo对象转换成Po对象
			form = this.DeviceVoToPo(form, d);
			listForm.add(form);
		}
			
		return listForm;
	}

	@Override
	public Integer countDeviceInfoByCondition(DeviceInfoForm deviceForm)
	{
		Integer count = deviceDao.countDeviceInfoByCondition(deviceForm);
		return count;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void deviceSave(DeviceInfoForm deviceForm)
	{
		DeviceInfo entity = new DeviceInfo();
		//设备的Po对象转换成Vo对象
		entity = this.DeviceFormPoToVo(deviceForm, entity);
		deviceDao.saveObject(entity);
	}

	@Override
	public DeviceInfoForm getfindDeviceByUuid(String deviceTypeUuid) {
		
		DeviceInfo device = deviceDao.findObjectById(deviceTypeUuid);
		
		DeviceInfoForm deviceForm = new DeviceInfoForm();
		//设备的Po对象转换成Vo对象
		deviceForm = this.DeviceVoToPo(deviceForm, device);
		return deviceForm;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void deviceUpdate(DeviceInfoForm deviceForm) {
		DeviceInfo entity = new DeviceInfo();
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
	
	@Override
	public List<DeviceInfoForm> findDeviceName() {
		List<DeviceInfoForm> dss = deviceDao.findDeviceName();
		return dss;
	}

	@Override
	public List<DeviceInfoForm> findDeviceVersionByName(String deviceName) {
		List<DeviceInfoForm> list = deviceDao.findDeviceVersionByName(deviceName);
		return list;
	}
	
	/**
	 * 设备的Vo对象转换成Po对象
	 * @param form	Po对象
	 * @param d	Vo对象
	 * @return	DeviceForm Po对象
	 */
	private DeviceInfoForm DeviceVoToPo(DeviceInfoForm form, DeviceInfo d)
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
	private DeviceInfo DeviceFormPoToVo(DeviceInfoForm deviceForm, DeviceInfo entity)
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
