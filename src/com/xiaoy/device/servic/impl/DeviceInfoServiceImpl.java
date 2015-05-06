package com.xiaoy.device.servic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.DeviceInfo;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.base.util.UploadImageHelper;
import com.xiaoy.device.dao.DeviceInfoDao;
import com.xiaoy.device.servic.DeviceInfoService;
import com.xiaoy.device.web.form.DeviceInfoForm;
import com.xiaoy.resource.servic.LogService;

@Service
@Transactional(readOnly=true)
public class DeviceInfoServiceImpl implements DeviceInfoService
{
	private final static String MENU_MODEL = "【设备管理】--【设备信息管理】";
	//保存图片的文件夹
	private static String DEVICE_IMAGE_URL = "upload/deviceUploadImages";
	
	@Resource
	private DeviceInfoDao deviceDao;
	
	//注入日志
	@Resource
	private LogService logService;
	
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
			//设备的Vo对象转换成Po对象
			DeviceInfoForm form = this.deviceVoToPo(d);
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
	public void deviceSave(DeviceInfoForm deviceForm, HttpServletRequest request)
	{
		if (deviceForm.getImage() != null) 
        {
        	//上传图片
        	UploadImageHelper.uploadImage(deviceForm, DEVICE_IMAGE_URL);
        	logService.saveLog(request, MENU_MODEL, "添加“"+ deviceForm.getDeviceName()+"”图片");
        }
        if(!StringUtils.isEmpty(deviceForm.getNewFileName()))
        {
        	deviceForm.setDevicePicUrl(UploadImageHelper.PICURL);
        }
        //设备的Po对象转换成Vo对象
		DeviceInfo entity = this.deviceFormPoToVo(deviceForm);
		deviceDao.saveObject(entity);
	}

	@Override
	public DeviceInfoForm getfindDeviceByUuid(String deviceTypeUuid) {
		
		DeviceInfo device = deviceDao.findObjectById(deviceTypeUuid);
		
		//设备的Po对象转换成Vo对象
		DeviceInfoForm deviceForm = this.deviceVoToPo(device);
		return deviceForm;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void deviceUpdate(DeviceInfoForm deviceForm, HttpServletRequest request) {
		
		//获取原来的图片路径
		String devicePicUrl = request.getParameter("oldUrl");
		
		//如果修改了图片，取新图片的信息
		if(!StringUtils.isEmpty(deviceForm.getImageFileName()))
		{	if (deviceForm.getImage() != null)
			{
				//上传图片
				UploadImageHelper.uploadImage(deviceForm, DEVICE_IMAGE_URL);
				//删除原来的图片
				UploadImageHelper.deleteImage(request, devicePicUrl);
				logService.saveLog(request, MENU_MODEL, "修改“"+ deviceForm.getDeviceName()+"”图片");
	        }
			deviceForm.setDevicePicUrl(UploadImageHelper.PICURL);
		}else//如果没有修改图片，取原图片的路径
		{
			deviceForm.setDevicePicUrl(devicePicUrl);
		}
		//设备的Po对象转换成Vo对象
		DeviceInfo entity = this.deviceFormPoToVo(deviceForm);
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
	private DeviceInfoForm deviceVoToPo(DeviceInfo d)
	{
		DeviceInfoForm form = new DeviceInfoForm();
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
		form.setCreatTime(d.getCreatTime() != null ? DateHelper.dateConverString(d.getCreatTime()) : "");
		return form;
	}
	
	/**
	 * 设备的Po对象转换成Vo对象
	 * @param deviceForm	Po对象
	 * @param entity	Vo对象
	 * @return	Device	Vo对象
	 */
	private DeviceInfo deviceFormPoToVo(DeviceInfoForm deviceForm)
	{
		DeviceInfo entity = new DeviceInfo();
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
		entity.setCreatTime(new Date());
		entity.setDeviceNum(0);
		return entity;
	}
}
