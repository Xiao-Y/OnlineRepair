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

	/**
	 * 根据uuid查询出来评价信息及相关信息
	 * @param evaluateForm	含有评价uuid的
	 * @return
	 */
	EvaluateForm findEvaluateByUuid(EvaluateForm evaluateForm);

	/**
	 * 保存用户信息<br/>
	 * 保存满意度、评价时间、修改评价状态
	 * @param evaluateForm
	 */
	void evaluateInfoSave(EvaluateForm evaluateForm);

	/**
	 * 删除单个评价
	 * 1。将评价设备为不可以看见
	 */
	void updateEvaluateInfo(String evaluateUuid);

	/**
	 * 批量删除评论
	 * @param ids
	 */
	void deletesEvaluateInfo(String[] ids);

}
