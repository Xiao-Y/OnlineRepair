package com.xiaoy.base.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.util.GenericSuperclass;
import com.xiaoy.base.web.form.BaseForm;

/**
 * @author XiaoY
 * 
 * @explain 继承HibernateDaoSupport 实现ICommon&ltT&gt
 * 
 * @date: 2014年11月5日 下午11:00:01
 */
public class CommonImpl<T> extends BaseDao implements Common<T>
{
	// 获取当前类的父类的类型(泛型的转换)
	@SuppressWarnings("rawtypes")
	Class entityClass = GenericSuperclass.getClass(this.getClass());

	@Override
	public void deleteObjectByid(Serializable id)
	{
		this.getSession().delete(this.getSession().get(entityClass, id));
	}

	@Override
	public void deleteObjectByCollectionIds(String hqlWhere,
			Map<String, Object> paramsMapValue)
	{
		StringBuffer hql = new StringBuffer(" delete from "
				+ this.entityClass.getSimpleName() + " where 1 = 1 ");

		hql.append(hqlWhere);

		Query query = this.getSession().createQuery(hql.toString());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null
				&& paramsMapValue.size() > 0)
		{
			// 设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
		}

		query.executeUpdate();
	}

	@Override
	public void updateObject(T entity)
	{
		this.getSession().update(entity);
	}

	@Override
	public void saveObject(T entity)
	{
		this.getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjectById(Serializable id)
	{
		T t = (T) this.getSession().get(entityClass, id);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findCollectionByCondition(String hqlWhere,
			Map<String, Object> paramsMapValue)
	{
		StringBuffer hql = new StringBuffer("from "
				+ entityClass.getSimpleName() + " e where 1 = 1 ");

		hql.append(hqlWhere);

		Query query = this.getSession().createQuery(hql.toString());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null
				&& paramsMapValue.size() > 0)
		{
			// 设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findCollectionByConditionWithPage(BaseForm baseForm,String hqlWhere,
			Map<String, Object> paramsMapValue) {
		StringBuffer hql = new StringBuffer("from "
				+ entityClass.getSimpleName() + " e where 1 = 1 ");

		hql.append(hqlWhere);

		Query query = this.getSession().createQuery(hql.toString());
		//从第几条记录开始
		query.setFirstResult((baseForm.getPageNo() - 1) * baseForm.getPageSize());
		//每页显示的记录数
		query.setMaxResults(baseForm.getPageSize());
		
		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null
				&& paramsMapValue.size() > 0)
		{
			// 设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
		}
		return query.list();
	}
	
	@Override
	public Integer countByCollection(String hqlWhere,
			Map<String, Object> paramsMapValue) {
		StringBuffer sql = new StringBuffer(" select count(*) from "
				+ entityClass.getSimpleName() + " e where 1 = 1 ");

		sql.append(hqlWhere);

		Query query = this.getSession().createQuery(sql.toString());
		
		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null
				&& paramsMapValue.size() > 0)
		{
			// 设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(count.toString());
	}
	
	@Override
	public void updateObjectCollection(Collection<T> entities)
	{
		if (entities != null && entities.size() > 0)
		{
			for (T t : entities)
			{
				this.getSession().update(t);
			}
		}

	}

	@Override
	public void saveObjectCollection(Collection<T> entities)
	{
		if (entities != null && entities.size() > 0)
		{
			for (T t : entities)
			{
				this.getSession().saveOrUpdate(t);
			}
		}

	}
	
	public void saveOrUpdate(T t)
	{
		this.getSession().saveOrUpdate(t);
	}

	/**
	 * 设置条件查询的参数
	 * 
	 * @param hqlWhere
	 *            条件hql
	 * @param paramsMap
	 *            参数集合
	 * @param query
	 * 
	 * @author XiaoY
	 * @date: 2014年12月13日 下午7:52:53
	 */
	@SuppressWarnings("rawtypes")
	protected void settingParam(String hqlWhere, Map<String, Object> paramsMap,
			Query query)
	{
		if (!paramsMap.isEmpty() && paramsMap.size() > 0 && hqlWhere != null
				&& hqlWhere.length() > 0)
		{
			for (Map.Entry<String, Object> entry : paramsMap.entrySet())
			{
				if (entry.getValue() instanceof Collection)
				{
					query.setParameterList(entry.getKey(),
							(Collection) entry.getValue());
				} else
				{
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
	}
}
