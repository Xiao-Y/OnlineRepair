package com.xiaoy.evaluate.web.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.evaluate.service.EvaluateService;
import com.xiaoy.evaluate.web.form.EvaluateForm;

@SuppressWarnings("serial")
public class EvaluateAction extends BaseAction implements ModelDriven<EvaluateForm>
{
	@Resource
	private EvaluateService evaluateService;
	
	EvaluateForm evaluateForm = new EvaluateForm();

	@Override
	public EvaluateForm getModel()
	{
		return evaluateForm;
	}

	/**
	 * 评价列表
	 * @return
	 */
	public String evaluateList()
	{
		
		return "evaluateList";
	}
}
