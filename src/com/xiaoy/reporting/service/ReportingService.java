package com.xiaoy.reporting.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xiaoy.reporting.web.form.ReportingForm;

public interface ReportingService
{

	/**
	 * 保存申报信息
	 * @param reportingForm		申报信息
	 */
	void reportingBugInfoSave(ReportingForm reportingForm, HttpServletRequest request);

	/**
	 * 根据条件查询出申报信息列表
	 * @param reportingForm	查询条件
	 * @return	List &ltReportingForm&gt
	 */
	List<ReportingForm> findReportingBugInfoList(ReportingForm reportingForm);

	/**
	 * 根据条件查询统计出现数据总量
	 * @param reportingForm	查询条件
	 * @return	int 数据总量
	 */
	int countReportingBugInfo(ReportingForm reportingForm);

}
