package com.xiaoy.evaluate.service;

import com.xiaoy.evaluate.web.EvaluateForm;

public interface EvaluateService
{
	/**
	 * 保存评价信息
	 * @param eForm
	 */
	void createEvaluate(EvaluateForm eForm);

}
