package com.xiaoy.evaluate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.Evaluate;
import com.xiaoy.evaluate.dao.EvaluateDao;
import com.xiaoy.evaluate.web.form.EvaluateForm;
import com.xiaoy.resource.web.form.DictionaryForm;

@Repository
public class EvaluateDaoImpl extends CommonImpl<Evaluate> implements EvaluateDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findEvaluateList(EvaluateForm evaluateForm)
	{
		StringBuffer hqlWhere = new StringBuffer("");
		hqlWhere.append(" SELECT d.AREA_CODE, d.INSTALLATION_SITE_CODE,de.DEVICE_NAME,u.NAME,r.REPORTING_PHONE, ");
		hqlWhere.append(" a.FINISH_TIME,e.EVALUATEUUID,e.EVALUATE_STAT_CODE,r.REPORTING_UUID,u.USER_UUID,r.REPORTING_TIME ");
		this.getWhere(hqlWhere);
		
		Map<String, Object> paramsMapValue = this.getMapWhereParam(evaluateForm, hqlWhere);
		
		Query query = this.getSession().createSQLQuery(hqlWhere.toString());
		//从第几条记录开始
		query.setFirstResult((evaluateForm.getPageNo() - 1) * evaluateForm.getPageSize());
		//每页显示的记录数
		query.setMaxResults(evaluateForm.getPageSize());
		
		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null
				&& paramsMapValue.size() > 0)
		{
			// 设置参数
			super.settingParam(hqlWhere.toString(), paramsMapValue, query);
		}
		return query.list();
	}
	
	@Override
	public int countEvaluate(EvaluateForm evaluateForm)
	{
		StringBuffer hqlWhere = new StringBuffer(" select count(*) ");
		this.getWhere(hqlWhere);
		Map<String, Object> paramsMapValue = this.getMapWhereParam(evaluateForm, hqlWhere);
		
		Query query = this.getSession().createSQLQuery(hqlWhere.toString());
		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null
				&& paramsMapValue.size() > 0)
		{
			// 设置参数
			super.settingParam(hqlWhere.toString(), paramsMapValue, query);
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(count.toString());
	}

	/**
	 * 添加查询条件
	 * @param hqlWhere
	 */
	private void getWhere(StringBuffer hqlWhere)
	{
		hqlWhere.append(" FROM reporting r, deviceinfo de, devicestate d,user u,audit a, evaluate e ");
		hqlWhere.append(" where e.REPORTING_UUID = r.REPORTING_UUID ");
		hqlWhere.append(" and r.DEVICE_STATE_UUID = d.DEVICE_STATE_UUID ");
		hqlWhere.append(" and d.DEVICETYPE_UUID = de.DEVICETYPE_UUID ");
		hqlWhere.append(" and r.USER_UUID = u.USER_UUID ");
		hqlWhere.append(" and a.MAINTAIN_STAT_CODE =  " + DictionaryForm.MAINTAIN_STAT_SUCCESS);
	}

	private Map<String, Object> getMapWhereParam(EvaluateForm evaluateForm, StringBuffer hqlWhere)
	{
		Map<String, Object> paramsMapValue = null;
		if(evaluateForm != null)
		{
			paramsMapValue = new HashMap<String, Object>();
			
			String areaCode = evaluateForm.getAreaCode();
			if(!StringUtils.isEmpty(areaCode))
			{
				hqlWhere.append(" and d.AREA_CODE = :areaCode ");
				paramsMapValue.put("areaCode", areaCode);
			}
			
			String installatinoSiteCode = evaluateForm.getInstallationSite();
			if(!StringUtils.isEmpty(installatinoSiteCode))
			{
				hqlWhere.append(" and d.INSTALLATION_SITE_CODE = :installatinoSiteCode ");
				paramsMapValue.put("installatinoSiteCode", installatinoSiteCode);
			}
			
			String deviceName = evaluateForm.getDeviceName();
			if(!StringUtils.isEmpty(deviceName))
			{
				hqlWhere.append(" and de.DEVICE_NAME = :deviceName ");
				paramsMapValue.put("deviceName", deviceName);
			}
			
			String evaluateStatCode = evaluateForm.getEvaluateStatCode();
			if(!StringUtils.isEmpty(evaluateStatCode))
			{
				hqlWhere.append(" and e.EVALUATE_STAT_CODE = :evaluateStatCode ");
				paramsMapValue.put("evaluateStatCode", evaluateStatCode);
			}
			
			String name = evaluateForm.getReportingUserName();
			if(!StringUtils.isEmpty(name))
			{
				hqlWhere.append(" and u.NAME like :name ");
				paramsMapValue.put("name", "%" + name + "%");
			}
			
			String reportingTime = evaluateForm.getReportingTime();
			if(!StringUtils.isEmpty(reportingTime))
			{
				hqlWhere.append(" and r.REPORTING_TIME = timestamp(:reportingTime) ");
				paramsMapValue.put("reportingTime", reportingTime);
			}
			
			String finishTime = evaluateForm.getFinishTime();
			if(!StringUtils.isEmpty(finishTime))
			{
				hqlWhere.append(" and a.FINISH_TIME = timestamp(:finishTime) ");
				paramsMapValue.put("finishTime", finishTime);
			}
		}
		return paramsMapValue;
	}
}
