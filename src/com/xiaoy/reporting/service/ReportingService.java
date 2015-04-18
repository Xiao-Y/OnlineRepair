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

	/**
	 * 通过申报信息的uuid查询出现申报信息及相关信息
	 * @param reportingUuid
	 * @return	ReportingForm
	 */
	ReportingForm findReportingBugInfoByRrUuid(String reportingUuid);

	/**
	 * 删除故障申报信息<br/>
	 * 1、待审核的。只用删除申报信息。<br/>
	 * 2、审核通过。删除审核通过的申报信息，将会删除审核信息和评价信息<br/>
	 * 3、审核未通过。删除审核未通过的申报信息，将会删除审核信息<br/>
	 * 
	 * @param reportingUuid		申报信息的uuid
	 * @param auditStatCode		审核的状态。1表示待审核，2表示已通过，3表示未通过
	 * @param auditUuid			审核信息uuid
	 */
	void deleteReportingBugInfo(String reportingUuid, String auditStatCode,String auditUuid);

}
