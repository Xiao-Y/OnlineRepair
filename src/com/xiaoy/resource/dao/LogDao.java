package com.xiaoy.resource.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.Log;
import com.xiaoy.resource.web.form.LogForm;

public interface LogDao extends Common<Log>
{
	/**
	 * 通过条件查询出现操作日志
	 * @param log	查询条件
	 * @return
	 */
	public List<Log> findCollectionByCondition(LogForm logForm);

	/**
	 * 根据条件查询出现记录数
	 * @param logForm	查询条件
	 * @return	记录数
	 */
	public Integer countByCollection(LogForm logForm);
	
}
