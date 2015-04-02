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
		Map<String, Object> paramsMapValue = this.getParamsMap(deviceForm);
		StringBuffer hqlWhere = this.getHqlWhere(deviceForm); 
		return super.findCollectionByConditionWithPage(deviceForm, hqlWhere.toString(), paramsMapValue);
	}

	@Override
	public Integer countDeviceInfoByCondition(DeviceForm deviceForm)
	{
		Map<String, Object> paramsMapValue = this.getParamsMap(deviceForm);
		StringBuffer hqlWhere = this.getHqlWhere(deviceForm); 
		Object count = super.countByCollection(hqlWhere.toString(), paramsMapValue);
		return Integer.parseInt(count.toString());
	}
	
	/**
	 * 添加条件查询的参数
	 * @param deviceForm	查询条件
	 * @return	Map&ltString, Object&gt	参数
	 */
	private Map<String, Object> getParamsMap(DeviceForm deviceForm)
	{
		Map<String, Object> paramsMapValue = null;
		if(deviceForm != null)
		{
			paramsMapValue = new HashMap<String, Object>();
			
			if(!StringUtils.isEmpty(deviceForm.getDeviceName()))
			{
				paramsMapValue.put("deviceName", deviceForm.getDeviceName());
			}
			
			if(!StringUtils.isEmpty(deviceForm.getVersion()))
			{
				paramsMapValue.put("version", deviceForm.getVersion());
			}
		}
		
		return paramsMapValue;
	}
	
	/**
	 * 拼接查询条件
	 * @param deviceForm	查询条件
	 * @return	查询条件
	 */
	private StringBuffer getHqlWhere(DeviceForm deviceForm)
	{
		StringBuffer hqlWhere = null;
		if(deviceForm != null)
		{
			hqlWhere = new StringBuffer();
			
			if(!StringUtils.isEmpty(deviceForm.getDeviceName()))
			{
				hqlWhere.append(" and deviceName = :deviceName ");
			}
			
			if(!StringUtils.isEmpty(deviceForm.getVersion()))
			{
				hqlWhere.append(" and version = :version ");
			}
		}
		
		return hqlWhere;
	}
}
