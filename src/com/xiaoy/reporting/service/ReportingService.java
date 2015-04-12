package com.xiaoy.reporting.service;

import javax.servlet.http.HttpServletRequest;

import com.xiaoy.reporting.web.form.ReportingForm;

public interface ReportingService
{

	/**
	 * 保存申报信息
	 * @param reportingForm		申报信息
	 */
	void reportingBugInfoSave(ReportingForm reportingForm, HttpServletRequest request);

}
