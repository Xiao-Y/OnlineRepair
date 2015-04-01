package com.xiaoy.device.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.Device;
import com.xiaoy.device.dao.DeviceDao;
import com.xiaoy.device.web.form.DeviceForm;

@Repository
public class DeviceDaoImpl extends CommonImpl<Device> implements DeviceDao
{

	@Override
	public List<Device> findDeviceInfoByCondition(DeviceForm deviceForm)
	{
		StringBuffer hqlWhere = null;
		Map<String, Object> paramsMapValue = null;
		if(deviceForm != null)
		{
			hqlWhere = new StringBuffer();
			paramsMapValue = new HashMap<String, Object>();
			
			if(!StringUtils.isEmpty(deviceForm.getDeviceName()))
			{
				hqlWhere.append(" and deviceName = :deviceName ");
				paramsMapValue.put("deviceName", deviceForm.getDeviceName());
			}
			
			if(!StringUtils.isEmpty(deviceForm.getVersion()))
			{
				hqlWhere.append(" and version = :version ");
				paramsMapValue.put("version", deviceForm.getVersion());
			}
			
			//if(!StringUtils.isEmpty(deviceForm.))
		}
		
		
		List<Device> list = super.findCollectionByCondition(hqlWhere.toString(), paramsMapValue);
		return null;
	}

	@Override
	public Integer countDeviceInfoByCondition(DeviceForm deviceForm)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
