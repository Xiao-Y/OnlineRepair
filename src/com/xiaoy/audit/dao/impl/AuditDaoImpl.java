package com.xiaoy.audit.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xiaoy.audit.dao.AuditDao;
import com.xiaoy.audit.web.form.AuditForm;
import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.Audit;
import com.xiaoy.resource.web.form.DictionaryForm;

@Repository
public class AuditDaoImpl extends CommonImpl<Audit> implements AuditDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAuditInfoWaitList(AuditForm auditForm)
	{
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT de.AREA_CODE,de.INSTALLATION_SITE_CODE,d.DEVICE_NAME,u.NAME,r.REPORTING_PHONE,r.REPORTING_TIME,a.REPORTING_UUID,u.USER_UUID,a.AUDIT_UUID ");
		this.appendSQLWhere(sql);
		sql.append(" and a.AUDIT_STAT_CODE= " + DictionaryForm.AUDITSTAT_WAIT);

		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.getMapWhereParam(auditForm, hqlWhere);

		sql.append(hqlWhere.toString());

		Query query = this.getSession().createSQLQuery(sql.toString());
		// 从第几条记录开始
		query.setFirstResult((auditForm.getPageNo() - 1) * auditForm.getPageSize());
		// 每页显示的记录数
		query.setMaxResults(auditForm.getPageSize());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null && paramsMapValue.size() > 0)
		{
			// 设置参数
			super.settingParam(hqlWhere.toString(), paramsMapValue, query);
		}

		List<Object[]> list = query.list();
		return list;
	}

	@Override
	public int countAuditInfoWait(AuditForm auditForm)
	{
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT count(*) ");
		this.appendSQLWhere(sql);
		sql.append(" and a.AUDIT_STAT_CODE= " + DictionaryForm.AUDITSTAT_WAIT);

		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.getMapWhereParam(auditForm, hqlWhere);

		sql.append(hqlWhere.toString());

		Query query = this.getSession().createSQLQuery(sql.toString());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null && paramsMapValue.size() > 0)
		{
			// 设置参数
			super.settingParam(hqlWhere.toString(), paramsMapValue, query);
		}

		Object count = query.uniqueResult();
		return Integer.parseInt(count.toString());
	}

	@Override
	public Object[] findAuditInfoWaitByAuditUuid(AuditForm auditForm)
	{
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT de.AREA_CODE,de.INSTALLATION_SITE_CODE,d.DEVICE_NAME,u.NAME,r.REPORTING_PHONE,r.REPORTING_TIME, ");
		sql.append(" d.VERSION,r.ACCOUNT,r.REMARK,de.DEVICE_STATE_UUID,r.REPORTING_UUID,u.USER_UUID,r.DEVICE_PIC_URL,r.ORDER_TIME,r.PRIOR_CODE ");
		this.appendSQLWhere(sql);
		sql.append(" and a.AUDIT_STAT_CODE= " + DictionaryForm.AUDITSTAT_WAIT);
		sql.append(" and a.AUDIT_UUID = :auditUuid");
		
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setParameter("auditUuid", auditForm.getAuditUuid());
		
		Object[] object = (Object[]) query.uniqueResult();
		
		return object;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAuditInfoPassList(AuditForm auditForm)
	{
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT de.AREA_CODE,de.INSTALLATION_SITE_CODE,d.DEVICE_NAME,u.NAME,r.REPORTING_PHONE,r.REPORTING_TIME,a.REPORTING_UUID,u.USER_UUID,a.AUDIT_UUID,a.MAINTAIN_UUID,a.AUDIT_TIME ");
		this.appendSQLWhere(sql);
		sql.append(" and a.AUDIT_STAT_CODE= " + DictionaryForm.AUDITSTAT_SUCCESS);

		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.getMapWhereParam(auditForm, hqlWhere);

		sql.append(hqlWhere.toString());

		Query query = this.getSession().createSQLQuery(sql.toString());
		// 从第几条记录开始
		query.setFirstResult((auditForm.getPageNo() - 1) * auditForm.getPageSize());
		// 每页显示的记录数
		query.setMaxResults(auditForm.getPageSize());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null && paramsMapValue.size() > 0)
		{
			// 设置参数
			super.settingParam(hqlWhere.toString(), paramsMapValue, query);
		}

		List<Object[]> list = query.list();
		return list;
	}
	
	/**
	 * 拼接SQL语句
	 * 
	 * @param sql
	 */
	private void appendSQLWhere(StringBuffer sql)
	{
		sql.append(" from reporting r,user u,deviceinfo d,devicestate de,audit a ");
		sql.append(" where a.REPORTING_UUID = r.REPORTING_UUID ");
		sql.append(" and r.DEVICE_STATE_UUID = de.DEVICE_STATE_UUID ");
		sql.append(" and r.USER_UUID = u.USER_UUID ");
		sql.append(" and de.DEVICETYPE_UUID = d.DEVICETYPE_UUID ");
	}

	/**
	 * 设置条件查询语句和参数
	 * 
	 * @param auditForm
	 *            查询条件
	 * @param hqlWhere
	 *            查询语句
	 * @return Map&ltString, Object&gt 设置的参数
	 */
	private Map<String, Object> getMapWhereParam(AuditForm auditForm, StringBuffer hqlWhere)
	{
		Map<String, Object> param = null;
		if (auditForm != null)
		{
			param = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(auditForm.getAreaCode()))
			{
				hqlWhere.append(" and de.AREA_CODE = :areaCode ");
				param.put("areaCode", auditForm.getAreaCode());
			}

			if (!StringUtils.isEmpty(auditForm.getInstallationSiteCode()))
			{
				hqlWhere.append(" and de.INSTALLATION_SITE_CODE = :installationSiteCode");
				param.put("installationSiteCode", auditForm.getInstallationSiteCode());
			}

			if (!StringUtils.isEmpty(auditForm.getDeviceName()))
			{
				hqlWhere.append(" and d.DEVICE_NAME = :deviceName ");
				param.put("deviceName", auditForm.getDeviceName());
			}

			if (!StringUtils.isEmpty(auditForm.getName()))
			{
				hqlWhere.append(" and u.NAME like :name ");
				param.put("name", "%" + auditForm.getName() + "%");
			}

			if (!StringUtils.isEmpty(auditForm.getReportingTime()))
			{
				hqlWhere.append(" and r.REPORTING_TIME = timestamp(:reportingTime)");
				param.put("reportingTime", auditForm.getReportingTime());
			}
			
			if(!StringUtils.isEmpty(auditForm.getAuditTime()))
			{
				hqlWhere.append(" and  a.AUDIT_TIME = timestamp(:auditTime)");
				param.put("auditTime", auditForm.getAuditTime());
			}
		}
		return param;
	}

	@Override
	public int countAuditInfoPass(AuditForm auditForm)
	{
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT count(*) ");
		this.appendSQLWhere(sql);
		sql.append(" and a.AUDIT_STAT_CODE= " + DictionaryForm.AUDITSTAT_SUCCESS);

		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.getMapWhereParam(auditForm, hqlWhere);

		sql.append(hqlWhere.toString());

		Query query = this.getSession().createSQLQuery(sql.toString());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null && paramsMapValue.size() > 0)
		{
			// 设置参数
			super.settingParam(hqlWhere.toString(), paramsMapValue, query);
		}

		Object count = query.uniqueResult();
		return Integer.parseInt(count.toString());
	}

	@Override
	public Object[] findAuditInfoPassByAuditUuid(AuditForm auditForm)
	{
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT de.AREA_CODE,de.INSTALLATION_SITE_CODE,d.DEVICE_NAME,u.NAME,r.REPORTING_PHONE,r.REPORTING_TIME, ");
		sql.append(" d.VERSION,r.ACCOUNT,r.REMARK,de.DEVICE_STATE_UUID,r.REPORTING_UUID,u.USER_UUID,r.DEVICE_PIC_URL, ");
		sql.append(" r.ORDER_TIME,r.PRIOR_CODE,a.MAINTAIN_UUID,a.AUDIT_TIME,a.MAINTAIN_STAT_CODE,a.FINISH_TIME,e.EVALUATEUUID ");
		sql.append(" from reporting r,user u,deviceinfo d,devicestate de,audit a, evaluate e ");
		sql.append(" where a.REPORTING_UUID = r.REPORTING_UUID ");
		sql.append(" and r.DEVICE_STATE_UUID = de.DEVICE_STATE_UUID ");
		sql.append(" and r.USER_UUID = u.USER_UUID ");
		sql.append(" and de.DEVICETYPE_UUID = d.DEVICETYPE_UUID ");
		sql.append(" and r.REPORTING_UUID = e.REPORTING_UUID ");
		sql.append(" and a.AUDIT_STAT_CODE= " + DictionaryForm.AUDITSTAT_SUCCESS);
		sql.append(" and a.AUDIT_UUID = :auditUuid");
		
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setParameter("auditUuid", auditForm.getAuditUuid());
		
		Object[] object = (Object[]) query.uniqueResult();
		
		return object;
	}
}
