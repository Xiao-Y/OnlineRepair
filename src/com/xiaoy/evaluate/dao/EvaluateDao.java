package com.xiaoy.evaluate.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.Evaluate;
import com.xiaoy.evaluate.web.form.EvaluateForm;

public interface EvaluateDao extends Common<Evaluate>
{

	/**
	 * 查询出来所有的已完成的评价信息
	 * @param evaluateForm
	 * @return
	 */
	List<Object[]> findEvaluateList(EvaluateForm evaluateForm);

	/**
	 * 根据条件统计出数据总记录数
	 * @param evaluateForm	查询记录
	 * @return 总记录数
	 */
	int countEvaluate(EvaluateForm evaluateForm);

}
