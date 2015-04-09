package com.xiaoy.device.servic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.DeviceState;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.device.dao.DeviceStateDao;
import com.xiaoy.device.servic.DeviceStateService;
import com.xiaoy.device.web.form.DeviceStateForm;
import com.xiaoy.resource.dao.DictionaryDao;
import com.xiaoy.resource.web.form.DictionaryForm;

@Service
@Transactional(readOnly=true)
public class DeviceStateServiceImpl implements DeviceStateService {

	@Resource
	private DeviceStateDao deviceStateDao;
	
	//数据字典
	@Resource
	private DictionaryDao dictionaryDao;
	
	@Override
	public List<DeviceStateForm> findDeviceStateConditionWithPage(
			DeviceStateForm deviceStateForm) {
		List<DeviceState> list = deviceStateDao.findDeviceStateConditionWithPage(deviceStateForm);
		List<DeviceStateForm> formList = this.DeviceStateVoToPoList(list);
		return formList;
	}

	/**
	 * 将设备信息的PO转换成VO
	 * @param list	VO
	 * @return	List &ltDeviceStateForm&gt	PO集合
	 */
	private List<DeviceStateForm> DeviceStateVoToPoList(List<DeviceState> list) {
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
				
				df.setInstallationTime(ds.getInstallationTime() != null ? DateHelper.dateConverString(ds.getInstallationTime()) : "");
				df.setLastTime(ds.getLastTime() != null ? DateHelper.dateConverString(ds.getLastTime()) : "");
				df.setNextTime(ds.getNextTime() != null ? DateHelper.dateConverString(ds.getNextTime()) : "");
				formList.add(df);
			}
		}
		return formList;
	}

	@Override
	public int countDeviceStateByCondition(DeviceStateForm deviceStateForm) {
		int count = deviceStateDao.countDeviceStateByCondition(deviceStateForm);
		return count;
	}

}
