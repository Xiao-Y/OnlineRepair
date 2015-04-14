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
	 * @return	List &ltObject[]&gt
	 */
	List<Object[]> findReportingBugInfoList(ReportingForm reportingForm);

	/**
	 * 根据条件查询统计出现数据总量
	 * @param reportingForm	查询条件
	 * @return	int 数据总量
	 */
	int countReportingBugInfo(ReportingForm reportingForm);

}
