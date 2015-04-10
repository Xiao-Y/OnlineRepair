package com.xiaoy.device.dao.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.DeviceState;
import com.xiaoy.device.dao.DeviceStateDao;
import com.xiaoy.device.web.form.DeviceStateForm;

@Repository
public class DeviceStateDaoImpl extends CommonImpl<DeviceState> implements DeviceStateDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<DeviceState> findDeviceStateConditionWithPage(DeviceStateForm deviceStateForm) {
		String hqlWhere = null;
		Map<String, Object> paramsMapValue = null;
		Map<String, Object> map = this.getHqlMap(deviceStateForm);
		if(map != null && map.size() > 0){
			hqlWhere = (String) map.get("hqlWhere");
			paramsMapValue = (Map<String, Object>) map.get("paramsMapValue");
		}
		List<DeviceState> list = this.findCollectionByConditionWithPage(deviceStateForm, hqlWhere, paramsMapValue);
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int countDeviceStateByCondition(DeviceStateForm deviceStateForm) {
		String hqlWhere = null;
		Map<String, Object> paramsMapValue = null;
		Map<String, Object> map = this.getHqlMap(deviceStateForm);
		if(map != null && map.size() > 0){
			hqlWhere = (String) map.get("hqlWhere");
			paramsMapValue = (Map<String, Object>) map.get("paramsMapValue");
		}
		int count = super.countByCollection(hqlWhere, paramsMapValue);
		return count;
	}
	
	/**
	 * 添加条件查询的参数和查询条件
	 * @param deviceForm	查询条件
	 * @return	Map&ltString, Object&gt	参数和查询条件
	 */
	private Map<String,Object> getHqlMap(DeviceStateForm deviceStateForm) {
		StringBuffer hqlWhere = null;
		Map<String, Object> paramsMapValue = null;
		Map<String,Object> map = null;
		
		if(deviceStateForm != null){
			hqlWhere = new StringBuffer();
			paramsMapValue = new HashMap<String, Object>();
			
			if(!StringUtils.isEmpty(deviceStateForm.getAreaCode())){
				hqlWhere.append(" and e.areaCode = :areaCode");
				paramsMapValue.put("areaCode", deviceStateForm.getAreaCode());
			}
			
			if(!StringUtils.isEmpty(deviceStateForm.getInstallationSiteCode())){
				hqlWhere.append(" and e.installationSiteCode = :installationSiteCode");
				paramsMapValue.put("installationSiteCode", deviceStateForm.getInstallationSiteCode());
			}
			
			if(!StringUtils.isEmpty(deviceStateForm.getDeviceName())){
				hqlWhere.append(" and e.deviceInfo.deviceName like :deviceName");
				paramsMapValue.put("deviceName", "%" + deviceStateForm.getDeviceName() + "%");
			}
			
			if(!StringUtils.isEmpty(deviceStateForm.getVersion())){
				hqlWhere.append(" and e.deviceInfo.version like :version");
				paramsMapValue.put("version", "%" + deviceStateForm.getVersion() + "%");
			}
			
			if(!StringUtils.isEmpty(deviceStateForm.getInstallationTime())){
				hqlWhere.append(" and e.installationTime = timestamp(:installationTime)");
				paramsMapValue.put("installationTime", deviceStateForm.getInstallationTime());
			}
			
			if(!StringUtils.isEmpty(deviceStateForm.getStateCode())){
				hqlWhere.append(" and e.stateCode = :stateCode");
				paramsMapValue.put("stateCode", deviceStateForm.getStateCode());
			}
			
			map = new HashMap<String, Object>();
			map.put("hqlWhere", hqlWhere.toString());
			map.put("paramsMapValue", paramsMapValue);
		}
		return map;
	}

	@SuppressWarnings({ "unchecked"})
	private List<DeviceState> findCollectionByConditionWithPage(DeviceStateForm deviceStateForm,String hqlWhere,
			Map<String, Object> paramsMapValue) {
		StringBuffer hql = new StringBuffer("from DeviceState e left join fetch e.deviceInfo where 1 = 1 ");

		hql.append(hqlWhere);

		Query query = this.getSession().createQuery(hql.toString());
		//从第几条记录开始
		query.setFirstResult((deviceStateForm.getPageNo() - 1) * deviceStateForm.getPageSize());
		//每页显示的记录数
		query.setMaxResults(deviceStateForm.getPageSize());
		
		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null
				&& paramsMapValue.size() > 0)
		{
			// 设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
		}
		return query.list();
	} 
	
	/**
	 * 设置条件查询的参数
	 * 
	 * @param hqlWhere
	 *            条件hql
	 * @param paramsMap
	 *            参数集合
	 * @param query
	 * 
	 * @author XiaoY
	 * @date: 2014年12月13日 下午7:52:53
	 */
	@SuppressWarnings("rawtypes")
	private void settingParam(String hqlWhere, Map<String, Object> paramsMap,
			Query query)
	{
		if (!paramsMap.isEmpty() && paramsMap.size() > 0 && hqlWhere != null
				&& hqlWhere.length() > 0)
		{
			for (Map.Entry<String, Object> entry : paramsMap.entrySet())
			{
				if (entry.getValue() instanceof Collection)
				{
					query.setParameterList(entry.getKey(),
							(Collection) entry.getValue());
				}else
				{
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
	}

	@Override
	public void deviceStateDeleteByIds(String[] ids) {
		String hqlWhere = "";
		Map<String, Object> paramsMapValue = null;
		if(ids != null && ids.length > 0){
			List<String> list = Arrays.asList(ids);
			paramsMapValue = new HashMap<String, Object>();
			hqlWhere = " and deviceStateUuid in(:deviceStateUuid)";
			paramsMapValue.put("deviceStateUuid", list);
			super.deleteObjectByCollectionIds(hqlWhere, paramsMapValue);
		}
	}
}

