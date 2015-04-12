package com.xiaoy.device.servic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.DeviceInfo;
import com.xiaoy.base.entites.DeviceState;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.base.util.UploadImageHelper;
import com.xiaoy.device.dao.DeviceStateDao;
import com.xiaoy.device.servic.DeviceStateService;
import com.xiaoy.device.web.form.DeviceStateForm;
import com.xiaoy.resource.dao.DictionaryDao;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.DictionaryForm;

@Service
@Transactional(readOnly=true)
public class DeviceStateServiceImpl implements DeviceStateService {

	private final static String MENU_MODEL = "【设备管理】--【查询设备状态】";
	//保存图片的文件夹
	private static String DEVICE_IMAGE_URL = "deviceStateUploadImages";
	
	/**
	 * 注入日志
	 */
	@Resource
	private LogService logService;
	
	@Resource
	private DeviceStateDao deviceStateDao;
	
	//数据字典
	@Resource
	private DictionaryDao dictionaryDao;
	
	@Override
	public List<DeviceStateForm> findDeviceStateConditionWithPage(DeviceStateForm deviceStateForm) {
		List<DeviceState> list = deviceStateDao.findDeviceStateConditionWithPage(deviceStateForm);
		List<DeviceStateForm> formList = this.deviceStateVoToPoList(list);
		return formList;
	}

	@Override
	public DeviceStateForm findDeviceStateByUuid(String deviceStateUuid) {
		DeviceState deviceState = deviceStateDao.findObjectById(deviceStateUuid);
		DeviceStateForm df = new DeviceStateForm();
		this.deviceStateVoToPo(deviceState, df);
		return df;
	}
	

	@Override
	public int countDeviceStateByCondition(DeviceStateForm deviceStateForm) {
		int count = deviceStateDao.countDeviceStateByCondition(deviceStateForm);
		return count;
	}

	@Override
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	public void deviceStateSave(DeviceStateForm deviceStateForm, HttpServletRequest request)
	{
		if (deviceStateForm.getImage() != null) 
        {
        	//上传图片
        	UploadImageHelper.uploadImage(deviceStateForm, DEVICE_IMAGE_URL);
        	logService.saveLog(request, MENU_MODEL, "添加“"+ deviceStateForm.getDeviceName()+"”图片");
        }
        if(!StringUtils.isEmpty(deviceStateForm.getNewFileName()))
        {
        	deviceStateForm.setDevicePicUrl(UploadImageHelper.PICURL);
        }
		DeviceState entity = this.deviceStatePoToVo(deviceStateForm);
		deviceStateDao.saveObject(entity);;
	}

	@Override
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	public void deviceStateUpdate(DeviceStateForm deviceStateForm, HttpServletRequest request) {
		
		//获取原来的图片路径
		String devicePicUrl = request.getParameter("oldUrl");
		
		if(deviceStateForm != null){
			//如果修改了图片，取新图片的信息
			if(!StringUtils.isEmpty(deviceStateForm.getImageFileName()))
			{	if (deviceStateForm.getImage() != null)
				{
					//上传图片
					UploadImageHelper.uploadImage(deviceStateForm,DEVICE_IMAGE_URL);
					//删除原来的图片
					UploadImageHelper.deleteImage(request, devicePicUrl);
					logService.saveLog(request, MENU_MODEL, "修改“"+ deviceStateForm.getDeviceName()+"”图片");
		        }
			deviceStateForm.setDevicePicUrl(UploadImageHelper.PICURL);
			}else//如果没有修改图片，取原图片的路径
			{
				deviceStateForm.setDevicePicUrl(devicePicUrl);
			}
			DeviceState entity = this.deviceStatePoToVo(deviceStateForm);
			deviceStateDao.updateObject(entity);
		}
	}
	
	/**
	 * 将设备信息的PO转换成VO
	 * @param list	VO
	 * @return	List &ltDeviceStateForm&gt	PO集合
	 */
	private List<DeviceStateForm> deviceStateVoToPoList(List<DeviceState> list) {
		List<DeviceStateForm> formList = null;
		if(list != null && list.size() > 0){
			formList = new ArrayList<DeviceStateForm>();
			for(DeviceState ds : list){
				DeviceStateForm df = new DeviceStateForm();
				this.deviceStateVoToPo(ds,df);
				formList.add(df);
			}
		}
		return formList;
	}

	/**
	 *	设备状态信息Vo转Po对象
	 * @param ds	VO对象
	 * @param df	PO对象
	 */
	private void deviceStateVoToPo(DeviceState ds, DeviceStateForm df) {
		df.setAreaCode(ds.getAreaCode());
		if(!StringUtils.isEmpty(ds.getAreaCode())){
			df.setAreaName(dictionaryDao.findDDLName(ds.getAreaCode(), DictionaryForm.AREA_NAME));
		}else{
			df.setAreaName("");
		}
		df.setInstallationSiteCode(ds.getInstallationSiteCode());
		if(!StringUtils.isEmpty(ds.getInstallationSiteCode())){
			df.setInstallationSiteName(dictionaryDao.findDDLName(ds.getInstallationSiteCode(), DictionaryForm.INSTALLATION_SITE_NAME));
		}else{
			df.setInstallationSiteName("");
		}
		df.setStateCode(ds.getStateCode());
		if(!StringUtils.isEmpty(ds.getStateCode())){
			df.setStateName(dictionaryDao.findDDLName(ds.getStateCode(), DictionaryForm.STATE_NAME));
		}
		df.setDevicePicUrl(ds.getDevicePicUrl());
		df.setDeviceStateUuid(ds.getDeviceStateUuid());
		df.setDeviceTypeUuid(ds.getDeviceInfo().getDeviceTypeUuid());
		df.setDeviceName(ds.getDeviceInfo().getDeviceName());
		df.setVersion(ds.getDeviceInfo().getVersion());
		df.setRemark(ds.getRemark());
		
		df.setInstallationTime(ds.getInstallationTime() != null ? DateHelper.dateConverString(ds.getInstallationTime()) : "");
		df.setLastTime(ds.getLastTime() != null ? DateHelper.dateConverString(ds.getLastTime()) : "");
		df.setNextTime(ds.getNextTime() != null ? DateHelper.dateConverString(ds.getNextTime()) : "");
	}

	/**
	 * 设备信息PO转换成VO
	 * @param deviceStateForm	PO对象
	 * @return	DeviceState	VO对象
	 */
	private DeviceState deviceStatePoToVo(DeviceStateForm deviceStateForm)
	{
		DeviceState deviceState = new DeviceState();
		deviceState.setDeviceStateUuid(deviceStateForm.getDeviceStateUuid());
		deviceState.setAreaCode(deviceStateForm.getAreaCode());
		
		DeviceInfo deviceInfo = deviceState.getDeviceInfo();
		deviceInfo.setDeviceTypeUuid(deviceStateForm.getDeviceTypeUuid());
		deviceState.setDeviceInfo(deviceInfo);
		
		deviceState.setDevicePicUrl(deviceStateForm.getDevicePicUrl());
		deviceState.setInstallationSiteCode(deviceStateForm.getInstallationSiteCode());
		deviceState.setInstallationTime(!StringUtils.isEmpty(deviceStateForm.getInstallationTime()) ? DateHelper.stringConverDate(deviceStateForm.getInstallationTime()) : null);
		deviceState.setLastTime(!StringUtils.isEmpty(deviceStateForm.getLastTime()) ? DateHelper.stringConverDate(deviceStateForm.getLastTime()) : null);
		deviceState.setNextTime(!StringUtils.isEmpty(deviceStateForm.getNextTime()) ? DateHelper.stringConverDate(deviceStateForm.getNextTime()) : null);
		deviceState.setStateCode(deviceStateForm.getStateCode());
		deviceState.setRemark(deviceStateForm.getRemark());
		
		return deviceState;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,readOnly=false, propagation=Propagation.REQUIRED)
	public void deviceStateDeleteByUuid(String deviceStateUuid) {
		deviceStateDao.deleteObjectByid(deviceStateUuid);
	}

	@Override
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	public void deviceStateDeleteByIds(String[] ids) {
		deviceStateDao.deviceStateDeleteByIds(ids);
	}

	@Override
	public List<DeviceStateForm> findDeviceArea()
	{
		List<DeviceState> list = deviceStateDao.findDeviceStateArea();
		//查询出来的含有不重复区域的VO转换成PO对象
		return this.deviceStateAreaVoToPoList(list);
	}

	/**
	 * 查询出来的含有不重复区域的VO转换成PO对象
	 * @param list	含有不重复区域的VO
	 * @return	List &ltDeviceStateForm&gt	含有不重复区域的PO
	 */
	private List<DeviceStateForm> deviceStateAreaVoToPoList(List<DeviceState> list)
	{
		List<DeviceStateForm> formList = null;
		if(list != null && list.size() > 0){
			formList = new ArrayList<DeviceStateForm>();
			for(DeviceState ds : list){
				DeviceStateForm df = new DeviceStateForm();
				df.setAreaCode(ds.getAreaCode());
				if(!StringUtils.isEmpty(ds.getAreaCode())){
					df.setAreaName(dictionaryDao.findDDLName(ds.getAreaCode(), DictionaryForm.AREA_NAME));
				}else{
					df.setAreaName("");
				}
				formList.add(df);
			}
		}
		return formList;
	}

	@Override
	public List<DeviceStateForm> findInstallationSiteByArea(String areaCode)
	{
		List<DeviceState> list = deviceStateDao.findInstallationSiteByArea(areaCode);
		//查询出来的含有不重复安装位置的VO转换成PO对象
		return this.deviceStateInstallationSiteVoToPoList(list);
	}

	/**
	 * 查询出来的含有不重复安装位置的VO转换成PO对象
	 * @param list	含有不重复安装位置的VO
	 * @return	List &ltDeviceStateForm&gt	含有不重复安装位置的PO
	 */
	private List<DeviceStateForm> deviceStateInstallationSiteVoToPoList(List<DeviceState> list)
	{
		List<DeviceStateForm> formList = null;
		if(list != null && list.size() > 0){
			formList = new ArrayList<DeviceStateForm>();
			for(DeviceState ds : list){
				DeviceStateForm df = new DeviceStateForm();
				df.setInstallationSiteCode(ds.getInstallationSiteCode());
				if(!StringUtils.isEmpty(ds.getInstallationSiteCode())){
					df.setInstallationSiteName(dictionaryDao.findDDLName(ds.getInstallationSiteCode(), DictionaryForm.INSTALLATION_SITE_NAME));
				}else{
					df.setInstallationSiteName("");
				}
				formList.add(df);
			}
		}
		return formList;
	}

	@Override
	public List<DeviceStateForm> findDeviceNameByinstallationSite(String areaCode, String installationSiteCode)
	{
		List<DeviceState> list = deviceStateDao.findDeviceNameByinstallationSite(areaCode, installationSiteCode);
		
		return this.deviceStateDeviceNameVoToPoList(list);
	}

	/**
	 * 查询出来的含有不重复设备名的VO转换成PO对象
	 * @param list	含有不重复设备名的VO
	 * @return	List &ltDeviceStateForm&gt	含有不重复设备名的PO
	 */
	private List<DeviceStateForm> deviceStateDeviceNameVoToPoList(List<DeviceState> list)
	{
		List<DeviceStateForm> formList = null;
		if(list != null && list.size() > 0){
			formList = new ArrayList<DeviceStateForm>();
			for(DeviceState ds : list){
				DeviceStateForm df = new DeviceStateForm();
				if(ds.getDeviceInfo() != null && !StringUtils.isEmpty(ds.getDeviceInfo().getDeviceName())){
					df.setDeviceName(ds.getDeviceInfo().getDeviceName());
				}else{
					df.setDeviceName("");
				}
				formList.add(df);
			}
		}
		return formList;
	}

	@Override
	public List<DeviceStateForm> findVersionBydeviceNamee(String areaCode,String installationSiteCode, String deviceName)
	{
		List<DeviceState> list = deviceStateDao.findVersionBydeviceNamee(areaCode, installationSiteCode, deviceName);
		return this.deviceStateVersionVoToPoList(list);
	}

	/**
	 * 将含有设备状态uuid和设备型号的VO对象转为PO对象
	 * @param list	含有设备状态uuid和设备型号的VO
	 * @return	List &ltDeviceStateForm&gt	含有设备状态uuid和设备型号的PO
	 */
	private List<DeviceStateForm> deviceStateVersionVoToPoList(List<DeviceState> list)
	{
		List<DeviceStateForm> formList = null;
		if(list != null && list.size() > 0){
			formList = new ArrayList<DeviceStateForm>();
			for(DeviceState ds : list){
				DeviceStateForm df = new DeviceStateForm();
				df.setDeviceStateUuid(ds.getDeviceStateUuid());
				
				if(ds.getDeviceInfo() != null && !StringUtils.isEmpty(ds.getDeviceInfo().getVersion())){
					df.setVersion(ds.getDeviceInfo().getVersion());
				}else{
					df.setVersion("");
				}
				
				formList.add(df);
			}
		}
		return formList;
	}
}
