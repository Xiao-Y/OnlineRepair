package com.xiaoy.device.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.DeviceInfo;
import com.xiaoy.device.dao.DeviceInfoDao;
import com.xiaoy.device.web.form.DeviceInfoForm;

@Repository
public class DeviceInfoDaoImpl extends CommonImpl<DeviceInfo> implements DeviceInfoDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceInfo> findDeviceInfoByCondition(DeviceInfoForm deviceForm)
	{
		Map<String, Object> paramsMapValue = null;
		StringBuffer hqlWhere = null;
		
		if(this.getHqlMap(deviceForm) != null)
		{
			paramsMapValue = (Map<String, Object>) this.getHqlMap(deviceForm).get("paramsMapValue");
			hqlWhere = (StringBuffer) this.getHqlMap(deviceForm).get("hqlWhere"); 
		}
		return super.findCollectionByConditionWithPage(deviceForm, hqlWhere.toString(), paramsMapValue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer countDeviceInfoByCondition(DeviceInfoForm deviceForm)
	{
		Map<String, Object> paramsMapValue = (Map<String, Object>) this.getHqlMap(deviceForm).get("paramsMapValue");
		StringBuffer hqlWhere = (StringBuffer) this.getHqlMap(deviceForm).get("hqlWhere"); 
		Object count = super.countByCollection(hqlWhere.toString(), paramsMapValue);
		return Integer.parseInt(count.toString());
	}
	

	@Override
	public void deleteDeviceByIds(String[] ids) {
		String hqlWhere = "";
		Map<String, Object> paramsMapValue = null;
		if(ids != null && ids.length > 0){
			List<String> list = Arrays.asList(ids);
			paramsMapValue = new HashMap<String, Object>();
			hqlWhere = " and deviceTypeUuid in(:deviceTypeUuid)";
			paramsMapValue.put("deviceTypeUuid", list);
			super.deleteObjectByCollectionIds(hqlWhere, paramsMapValue);
		}
	}
	
	/**
	 * 添加条件查询的参数和查询条件
	 * @param deviceForm	查询条件
	 * @return	Map&ltString, Object&gt	参数和查询条件
	 */
	private Map<String, Object> getHqlMap(DeviceInfoForm deviceForm)
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
				hqlWhere.append(" and deviceName like :deviceName ");
				paramsMapValue.put("deviceName", "%" + deviceForm.getDeviceName() + "%");
			}
			
			if(!StringUtils.isEmpty(deviceForm.getVersion()))
			{
				hqlWhere.append(" and version = :version ");
				paramsMapValue.put("version", deviceForm.getVersion());
			}
			
			if(!StringUtils.isEmpty(deviceForm.getProducerName()))
			{
				hqlWhere.append(" and producerName like :producerName ");
				paramsMapValue.put("producerName", "%" + deviceForm.getProducerName() + "%");
			}
			
			hqlWhere.append(" order by creatTime desc");
			
			map = new HashMap<String, Object>();
			map.put("hqlWhere", hqlWhere);
			map.put("paramsMapValue", paramsMapValue);
		}
		
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DeviceInfoForm> findDeviceName() {
		String hql = "select distinct deviceName from DeviceInfo";
		Query query = this.getSession().createQuery(hql);
		List<Object[]> dn = query.list();
		List<DeviceInfoForm> list = null;
		if(dn != null && dn.size() > 0){
			list = new ArrayList<DeviceInfoForm>();
			for(Object o : dn){
				DeviceInfoForm df = new DeviceInfoForm();
				df.setDeviceName((String) o);
				list.add(df);
			}
		}
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DeviceInfoForm> findDeviceVersionByName(String deviceName) {
		String hql = "select new DeviceInfo(deviceTypeUuid, version) from DeviceInfo where 1 = 1 and deviceName = :deviceName";
		Query query = this.getSession().createQuery(hql).setString("deviceName", deviceName);
		List<DeviceInfoForm> list = query.list();
		return list;
	}
}
