package com.xiaoy.reporting.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.Reporting;
import com.xiaoy.reporting.dao.ReportingDao;
import com.xiaoy.reporting.web.form.ReportingForm;

@Repository
public class ReportingDaoImpl extends CommonImpl<Reporting> implements ReportingDao
{

	@Override
	public List<Reporting> findReportingBugInfoList(ReportingForm reportingForm) {
		String hqlWhere = "";
		Map<String, Object> paramsMapValue = null;
		List<Reporting> list = this.findCollectionByConditionWithPage(reportingForm, hqlWhere, paramsMapValue);
		return list;
	}
	
	private Map<String,Object> getHqlMap(ReportingForm reportingForm){
		StringBuffer hqlWhere = null; 
		Map<String, Object> map = null;
		if(reportingForm != null){
			hqlWhere = new StringBuffer();
			map = new HashMap<String, Object>();
			if(!StringUtils.isEmpty(reportingForm.getDeviceName())){
				hqlWhere.append(" and deviceName = :deviceName ");
				map.put("deviceName", reportingForm.getDeviceName());
			}
			//if(!StringUtils.isEmpty(reportingForm.get))
		}
		return map;
	}
}
