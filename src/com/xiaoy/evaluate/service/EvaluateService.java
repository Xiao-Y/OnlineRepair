package com.xiaoy.evaluate.service;

import java.util.List;

import com.xiaoy.evaluate.web.form.EvaluateForm;

public interface EvaluateService
{
	/**
	 * 保存评价信息
	 * @param eForm
	 */
	void createEvaluate(EvaluateForm eForm);

	/**
	 * 查询出来所有评价的信息<br/>
	 * 1、维护状态已完成<br/>
	 * 2、
	 * @param evaluateForm
	 * @return
	 */
	List<EvaluateForm> findEvaluateList(EvaluateForm evaluateForm);

	/**
	 * 根据条件统计出数据总记录数
	 * @param evaluateForm	查询记录
	 * @return 总记录数
	 */
	int countEvaluate(EvaluateForm evaluateForm);

}
