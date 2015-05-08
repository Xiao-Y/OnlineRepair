package com.xiaoy.reporting.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xiaoy.audit.dao.AuditDao;
import com.xiaoy.authority.service.RoleService;
import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.Reporting;
import com.xiaoy.reporting.dao.ReportingDao;
import com.xiaoy.reporting.web.form.ReportingForm;
import com.xiaoy.resource.web.form.DictionaryForm;

@Repository
public class ReportingDaoImpl extends CommonImpl<Reporting> implements ReportingDao
{

	// 审核信息
	@Resource
	private AuditDao auditDao;

	// 角色
	@Resource
	private RoleService roleService;

	// @Override
	// @SuppressWarnings("unchecked")
	// public List<Object[]> findReportingBugInfoList(ReportingForm reportingForm) {
	// String hqlWhere = "";
	// Map<String, Object> map = this.getHqlMap(reportingForm);
	// String str = (String)map.get("hqlWhere");
	// if(!StringUtils.isEmpty(str)){
	// hqlWhere = str;
	// }
	// Map<String, Object> paramsMapValue = (Map<String, Object>) map.get("paramsMapValue");
	// List<Object[]> list = this.findCollectionByConditionWithPage(reportingForm, hqlWhere, paramsMapValue);
	// return list;
	// }
	//
	// /**
	// * 拼接sql语句，设置参数
	// * @param reportingForm 查询条件
	// * @return Map &ltString,Object&gt
	// */
	// private Map<String,Object> getHqlMap(ReportingForm reportingForm){
	// StringBuffer hqlWhere = null;
	// Map<String, Object> map = null;
	// Map<String, Object> hqlMap = null;
	// if(reportingForm != null){
	// hqlWhere = new StringBuffer();
	// map = new HashMap<String, Object>();
	// if(!StringUtils.isEmpty(reportingForm.getDeviceName()))
	// {
	// hqlWhere.append(" and di.DEVICE_NAME like :deviceName ");
	// map.put("deviceName", "%" + reportingForm.getDeviceName() + "%");
	// }
	// if(!StringUtils.isEmpty(reportingForm.getInstallationSiteCode()))
	// {
	// hqlWhere.append(" and  d.INSTALLATION_SITE_CODE = :installationSiteCode ");
	// map.put("installationSiteCode", reportingForm.getInstallationSiteCode());
	// }
	// if(!StringUtils.isEmpty(reportingForm.getMaintainStatCode()))
	// {
	// hqlWhere.append(" and a.MAINTAIN_STAT_CODE = :maintainStatCode ");
	// map.put("maintainStatCode", reportingForm.getMaintainStatCode());
	// }
	// // if(!StringUtils.isEmpty(reportingForm.getEvaluateStatCode()))
	// // {
	// // hqlWhere.append(" and e.EVALUATE_STAT_CODE = :evaluateStatCode ");
	// // map.put("evaluateStatCode", reportingForm.getEvaluateStatCode());
	// // }
	// if(!StringUtils.isEmpty(reportingForm.getName()))
	// {
	// hqlWhere.append(" and u.NAME like :name");
	// map.put("name", "%" + reportingForm.getName() + "%");
	// }
	// if(!StringUtils.isEmpty(reportingForm.getAuditStatCode()))
	// {
	// hqlWhere.append(" and a.AUDIT_STAT_CODE = :auditStatCode");
	// map.put("auditStatCode", reportingForm.getAuditStatCode());
	// }
	// if(!StringUtils.isEmpty(reportingForm.getMaintainTypeCode()))
	// {
	// hqlWhere.append(" and u.MAINTAIN_TYPE_CODE = :maintainTypeCode");
	// map.put("maintainTypeCode", reportingForm.getMaintainTypeCode());
	// }
	// if(!StringUtils.isEmpty(reportingForm.getAreaCode()))
	// {
	// hqlWhere.append(" and d.AREA_CODE = :areaCode");
	// map.put("areaCode", reportingForm.getAreaCode());
	// }
	//
	// hqlMap = new HashMap<String, Object>();
	// hqlMap.put("hqlWhere", hqlWhere.toString());
	// hqlMap.put("paramsMapValue", map);
	// }
	// return hqlMap;
	// }

	@SuppressWarnings("unchecked")
	public List<Object[]> findCollectionByConditionWithPage(ReportingForm reportingForm, String hqlWhere, Map<String, Object> paramsMapValue)
	{
		// StringBuffer hql = new StringBuffer("SELECT d.AREA_CODE,d.INSTALLATION_SITE_CODE,di.DEVICE_NAME,u.NAME,r.REPORTING_PHONE,r.REPORTING_TIME,a.MAINTAIN_STAT_CODE,e.EVALUATE_STAT_CODE,a.AUDIT_STAT_CODE,u.MAINTAIN_TYPE_CODE ");
		StringBuffer hql = new StringBuffer("SELECT d.AREA_CODE,d.INSTALLATION_SITE_CODE,di.DEVICE_NAME,u.NAME,r.REPORTING_PHONE,r.REPORTING_TIME,a.MAINTAIN_STAT_CODE,a.AUDIT_STAT_CODE,u.MAINTAIN_TYPE_CODE,r.REPORTING_UUID,di.DEVICETYPE_UUID,u.USER_UUID,a.AUDIT_UUID ");
		// hql.append(" from audit a,devicestate d,evaluate e,reporting r,deviceinfo di,user u ");
		hql.append(" from audit a,devicestate d,reporting r,deviceinfo di,user u ");
		hql.append(" where a.REPORTING_UUID = r.REPORTING_UUID ");
		// hql.append(" and r.REPORTING_UUID = e.REPORTING_UUID ");
		hql.append(" and d.DEVICE_STATE_UUID = r.DEVICE_STATE_UUID ");
		hql.append(" and d.DEVICETYPE_UUID = di.DEVICETYPE_UUID ");
		hql.append(" and u.USER_UUID = r.USER_UUID ");
		hql.append(hqlWhere);
		hql.append(" ORDER BY r.REPORTING_TIME DESC");

		Query query = this.getSession().createSQLQuery(hql.toString());
		// 从第几条记录开始
		query.setFirstResult((reportingForm.getPageNo() - 1) * reportingForm.getPageSize());
		// 每页显示的记录数
		query.setMaxResults(reportingForm.getPageSize());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null && paramsMapValue.size() > 0)
		{
			// 设置参数
			super.settingParam(hqlWhere, paramsMapValue, query);
		}

		return query.list();
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public int countReportingBugInfo(ReportingForm reportingForm)
	// {
	// String hqlWhere = "";
	// if(!StringUtils.isEmpty((String)this.getHqlMap(reportingForm).get("hqlWhere"))){
	// hqlWhere = (String)this.getHqlMap(reportingForm).get("hqlWhere");
	// }
	// Map<String, Object> paramsMapValue = (Map<String, Object>) this.getHqlMap(reportingForm).get("paramsMapValue");
	//
	//
	// return this.countByCollection(hqlWhere, paramsMapValue);
	// }

	/**
	 * 重写按条件统计数据
	 */
	public Integer countByCollection(String hqlWhere, Map<String, Object> paramsMapValue)
	{
		StringBuffer sql = new StringBuffer("SELECT COUNT(*) ");
		sql.append(" from audit a,devicestate d,reporting r,deviceinfo di,user u ");
		sql.append(" where a.REPORTING_UUID = r.REPORTING_UUID ");
		sql.append(" and d.DEVICE_STATE_UUID = r.DEVICE_STATE_UUID ");
		sql.append(" and d.DEVICETYPE_UUID = di.DEVICETYPE_UUID ");
		sql.append(" and u.USER_UUID = r.USER_UUID ");
		sql.append(hqlWhere);

		Query query = this.getSession().createSQLQuery(sql.toString());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null && paramsMapValue.size() > 0)
		{
			// 设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(count.toString());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> findReportingBugInfoByRrUuid(String reportingUuid)
	{
		StringBuffer hql = new StringBuffer("SELECT d.AREA_CODE,d.INSTALLATION_SITE_CODE,di.DEVICE_NAME,u.NAME,r.REPORTING_PHONE,r.REPORTING_TIME,a.MAINTAIN_STAT_CODE,a.AUDIT_STAT_CODE,u.MAINTAIN_TYPE_CODE,di.VERSION,a.AUDIT_TIME,a.FINISH_TIME,r.DEVICE_PIC_URL,r.ACCOUNT,r.REMARK,a.MAINTAIN_UUID,a.FAIL_ACCOUNT,r.ORDER_TIME ");
		hql.append(" from audit a,devicestate d,reporting r,deviceinfo di,user u ");
		hql.append(" where r.REPORTING_UUID = a.REPORTING_UUID ");
		hql.append(" and r.DEVICE_STATE_UUID = d.DEVICE_STATE_UUID ");
		hql.append(" and d.DEVICETYPE_UUID = di.DEVICETYPE_UUID ");
		hql.append(" and r.USER_UUID = u.USER_UUID ");
		hql.append(" and r.REPORTING_UUID = :reportingUuid");

		Query query = this.getSession().createSQLQuery(hql.toString());
		query.setParameter("reportingUuid", reportingUuid);
		List<Object[]> obj = query.list();
		return obj;
	}

	@Override
	public void deleteAuditByReportingUuid(String reportingUuid)
	{
		String hqlWhere = " and reportingUuid = :reportingUuid";
		Map<String, Object> paramsMapValue = new HashMap<String, Object>();
		paramsMapValue.put("reportingUuid", reportingUuid);
		auditDao.deleteObjectByCollectionIds(hqlWhere, paramsMapValue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findReportingBugInfoList(ReportingForm reportingForm)
	{
		StringBuffer hql = new StringBuffer("SELECT d.AREA_CODE,d.INSTALLATION_SITE_CODE,di.DEVICE_NAME,u.NAME,r.REPORTING_PHONE,r.REPORTING_TIME, ");
		hql.append(" a.MAINTAIN_STAT_CODE,a.AUDIT_STAT_CODE,a.MAINTAIN_UUID,r.REPORTING_UUID,di.DEVICETYPE_UUID,u.USER_UUID,a.AUDIT_UUID ");
		hql.append(" from audit a,devicestate d,reporting r,deviceinfo di,user u ");
		hql.append(" where r.REPORTING_UUID = a.REPORTING_UUID ");
		hql.append(" and r.DEVICE_STATE_UUID = d.DEVICE_STATE_UUID ");
		hql.append(" and d.DEVICETYPE_UUID = di.DEVICETYPE_UUID ");
		hql.append(" and r.USER_UUID = u.USER_UUID ");
		// 添加条件查询
		Map<String, Object> paramsMap = this.getHqlWhere(hql, reportingForm);

		Query query = this.getSession().createSQLQuery(hql.toString());
		super.settingParam(hql.toString(), paramsMap, query);

		List<Object[]> obj = query.list();
		return obj;
	}

	/**
	 * 添加查询条件
	 * 
	 * @param reportingForm
	 *            查询条件
	 * @return
	 */
	private Map<String, Object> getHqlWhere(StringBuffer hql, ReportingForm reportingForm)
	{
		Map<String, Object> paramsMap = null;
		if (reportingForm != null)
		{
			paramsMap = new HashMap<String, Object>();
			String areaCode = reportingForm.getAreaCode();
			if (!StringUtils.isEmpty(areaCode))
			{
				hql.append(" and d.AREA_CODE = :areaCode ");
				paramsMap.put("areaCode", areaCode);
			}

			String installationSiteCode = reportingForm.getInstallationSiteCode();
			if (!StringUtils.isEmpty(installationSiteCode))
			{
				paramsMap.put("installationSiteCode", installationSiteCode);
				hql.append(" and d.INSTALLATION_SITE_CODE = :installationSiteCode ");
			}

			String deviceName = reportingForm.getDeviceName();
			if (!StringUtils.isEmpty(deviceName))
			{
				paramsMap.put("deviceName", deviceName);
				hql.append(" and di.DEVICETYPE_UUID = :deviceUuid ");
			}

			String maintainStatCode = reportingForm.getMaintainStatCode();
			if (!StringUtils.isEmpty(maintainStatCode))
			{
				paramsMap.put("maintainStatCode", maintainStatCode);
				hql.append(" and  a.MAINTAIN_STAT_CODE = :maintainStatCode ");
			}

			String maintainUuid = reportingForm.getMaintainUuid();
			if (!StringUtils.isEmpty(maintainUuid))
			{
				paramsMap.put("maintainUuid", maintainUuid);
				hql.append(" and  a.MAINTAIN_UUID = :maintainUuid");
			}

			String name = reportingForm.getName();
			if (!StringUtils.isEmpty(name))
			{
				paramsMap.put("name", "%" + name + "%");
				hql.append(" and  u.NAME like :name");
			}

			String auditStatCode = reportingForm.getAuditStatCode();
			if (!StringUtils.isEmpty(auditStatCode))
			{
				paramsMap.put("auditStatCode", auditStatCode);
				hql.append(" and a.AUDIT_STAT_CODE = :auditStatCode ");
			}

			List<String> list = roleService.findRoleByUserUuid(reportingForm.getUserUuid());
			// 普通用户
			if (list != null && list.size() > 0 && list.size() == 1 && list.contains(DictionaryForm.ROLE_TYPE_COMMON))
			{
				hql.append(" and r.USER_UUID = :userUuid ");
				paramsMap.put("userUuid", reportingForm.getUserUuid());
			}

			if (list != null && list.size() > 0 && !list.contains(DictionaryForm.ROLE_TYPE_ADMIN) && !list.contains(DictionaryForm.ROLE_TYPE_SA) && list.contains(DictionaryForm.ROLE_TYPE_MAINTAIN))
			{
				hql.append(" and (r.USER_UUID = :userUuid ");
				hql.append(" or  a.MAINTAIN_UUID = :maintainUuid) ");
				paramsMap.put("userUuid", reportingForm.getUserUuid());
				paramsMap.put("maintainUuid", reportingForm.getUserUuid());
			}

		}
		return paramsMap;
	}

	@Override
	public int countReportingBugInfo(ReportingForm reportingForm)
	{
		StringBuffer hql = new StringBuffer(" select count(*)  ");
		hql.append(" from audit a,devicestate d,reporting r,deviceinfo di,user u ");
		hql.append(" where r.REPORTING_UUID = a.REPORTING_UUID ");
		hql.append(" and r.DEVICE_STATE_UUID = d.DEVICE_STATE_UUID ");
		hql.append(" and d.DEVICETYPE_UUID = di.DEVICETYPE_UUID ");
		hql.append(" and r.USER_UUID = u.USER_UUID ");
		// 添加条件查询
		Map<String, Object> paramsMap = this.getHqlWhere(hql, reportingForm);

		Query query = this.getSession().createSQLQuery(hql.toString());
		super.settingParam(hql.toString(), paramsMap, query);
		Object count = query.uniqueResult();
		return Integer.parseInt(count.toString());
	}
}
