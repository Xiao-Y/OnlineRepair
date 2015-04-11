package com.xiaoy.reporting.web.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.action.BaseAction;
import com.xiaoy.reporting.web.form.ReportingForm;

@Controller
@SuppressWarnings("serial")
public class ReportingAction extends BaseAction implements ModelDriven<ReportingForm>
{

	private ReportingForm reportingForm = new ReportingForm();
	
	@Override
	public ReportingForm getModel()
	{
		return reportingForm;
	}

	/**
	 * 申报故障列表页面
	 * @return
	 */
	public String reportingBugInfoList()
	{
		return "reportingBugInfoList";
	}
	
	/**
	 * 申报故障添加页面
	 * @return
	 */
	public String reportingBugInfoAdd()
	{
		return "reportingBugInfoAdd";
	}
	
	/**
	 * 申报故障编辑页面
	 * @return
	 */
	public String reportingBugInfoEdit()
	{
		return "reportingBugInfoEdit";
	}
}
