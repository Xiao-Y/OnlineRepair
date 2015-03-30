package com.xiaoy.resource.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.Log;
import com.xiaoy.resource.dao.LogDao;
import com.xiaoy.resource.web.form.LogForm;

@Repository
public class LogDaoImpl extends CommonImpl<Log> implements LogDao
{
	@Override
	public List<Log> findCollectionByCondition(LogForm logForm)
	{
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (logForm != null && !StringUtils.isEmpty(logForm.getOpeName()))
		{
			hqlWhere.append(" and e.opeName like :opeName ");
			paramsMap.put("opeName", "%" + logForm.getOpeName() + "%");
		}
		hqlWhere.append(" order by e.opeTime desc ");
		return super.findCollectionByCondition(hqlWhere.toString(), paramsMap);
	}

	@Override
	public void deleteObjectByCollectionIds(String[] ids)
	{
		String hqlWhere = " and logID in(:logID)";
		 Map<String, Object> paramsMapValue = new HashMap<String, Object>();
		 paramsMapValue.put("logID", ids);
		this.deleteObjectByCollectionIds(hqlWhere, paramsMapValue);
	}
}
