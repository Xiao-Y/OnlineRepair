package com.xiaoy.resource.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.Dictionary;
import com.xiaoy.resource.dao.DictionaryDao;

/**
 * 实现类<br>
 * @author XiaoY
 * @explain 
 * 
 * @date: 
 * 2015年3月27日 下午10:10:50
 */
@Repository
public class DictionaryDaoImpl extends CommonImpl<Dictionary> implements DictionaryDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findKeyWord()
	{
		String hql = "select distinct e.keyWord from Dictionary e";
		List<Object> list = (List<Object>) this.getSession().createQuery(hql).list();
		return list;
	}

	@Override
	public String findDDLName(String ddlCode, String keyWord)
	{
		String hql = "select ddlName from Dictionary where keyWord = '" + keyWord + "' and ddlCode = '" + ddlCode + "'";
		String ddlName = (String) this.getSession().createQuery(hql).uniqueResult();
		return ddlName;
	}

	@Override
	public List<Dictionary> findCollectionByConditionNoPage(String keyWord)
	{
		StringBuffer hqlWhere = new StringBuffer();
		hqlWhere.append(" and keyWord = :keyWord");
		hqlWhere.append(" order by e.ddlCode asc");
		
		Map<String, Object> paramsMapValue = new HashMap<String, Object>();
		paramsMapValue.put("keyWord", keyWord);
		
		List<Dictionary> list = this.findCollectionByCondition(hqlWhere.toString(), paramsMapValue);
		return list;
	}

	@Override
	public void deleteObjectByCollectionIds(List<Integer> ids)
	{
		String hql = "delete Dictionary where seqID in(:ids)";
		Query query = this.getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		query.executeUpdate();
	}
}
