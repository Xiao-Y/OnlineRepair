package com.xiaoy.reporting.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.Reporting;
import com.xiaoy.reporting.web.form.ReportingForm;

public interface ReportingDao extends Common<Reporting>
{

	/**
	 * 带分页的，根据条件查询出现故障申报信息
	 * @param reportingForm	查询条件
	 * @return	List &ltReporting&gt
	 */
	List<Reporting> findReportingBugInfoList(ReportingForm reportingForm);

}
