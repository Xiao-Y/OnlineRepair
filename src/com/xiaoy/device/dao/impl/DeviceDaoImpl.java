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

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> findDeviceInfoByCondition(DeviceForm deviceForm)
	{
		Map<String, Object> paramsMapValue = (Map<String, Object>) this.getHqlMap(deviceForm).get("paramsMapValue");
		StringBuffer hqlWhere = (StringBuffer) this.getHqlMap(deviceForm).get("hqlWhere"); 
		return super.findCollectionByConditionWithPage(deviceForm, hqlWhere.toString(), paramsMapValue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer countDeviceInfoByCondition(DeviceForm deviceForm)
	{
		Map<String, Object> paramsMapValue = (Map<String, Object>) this.getHqlMap(deviceForm).get("paramsMapValue");
		StringBuffer hqlWhere = (StringBuffer) this.getHqlMap(deviceForm).get("hqlWhere"); 
		Object count = super.countByCollection(hqlWhere.toString(), paramsMapValue);
		return Integer.parseInt(count.toString());
	}
	
	/**
	 * 添加条件查询的参数和查询条件
	 * @param deviceForm	查询条件
	 * @return	Map&ltString, Object&gt	参数和查询条件
	 */
	private Map<String, Object> getHqlMap(DeviceForm deviceForm)
	{
		Map<String,Object> map = null;
		Map<String, Object> paramsMapValue = null;
		StringBuffer hqlWhere = null;
		if(deviceForm != null)
		{
			paramsMapValue = new HashMap<String, Object>();
			hqlWhere = new StringBuffer();
			
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
			
			map = new HashMap<String, Object>();
			map.put("hqlWhere", hqlWhere);
			map.put("paramsMapValue", paramsMapValue);
		}
		
		return map;
	}
}
