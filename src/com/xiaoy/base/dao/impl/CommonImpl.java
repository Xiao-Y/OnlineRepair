package com.xiaoy.base.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.util.GenericSuperclass;

/**
 * @author XiaoY
 *
 * @explain 继承HibernateDaoSupport	实现ICommon&ltT&gt
 *
 * @date: 
 * 2014年11月5日 下午11:00:01
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
	public void deleteObjectByCollectionIds(Collection<String> ids,String hqlWhere, Map<String, Object> paramsMapValue)
	{
		StringBuffer hql = null;
		if(ids != null && ids.size() > 0){
			hql = new StringBuffer(" delete " + this.entityClass.getSimpleName() + " where 1 = 1 ");
			hql.append(" and userId in (:id) ");
			
//			//获得条件
//			String hqlWhere = this.getAppendHqlWhere();
			
//			//获得参数
//			Map<String, Object> paramsMap = this.setParamMapValue();
			
			if(paramsMapValue != null && paramsMapValue.size() > 0)
			{
				hql.append(hqlWhere);
			}
			
			Query query = this.getSession().createQuery(hql.toString());
			
			query.setParameterList("id", ids);
			
			
			//设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
			
			query.executeUpdate();
			
		}else 
		{
			System.out.println("集合删除出错---->" + ids);
		}
	}

	@Override
	public void updateObject(T entity)
	{
		this.getSession().update(entity);
	}

	@Override
	public void saveObject(T entity)
	{
		System.out.println(entity);
		this.getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjectById(Serializable id)
	{
		T t = (T)this.getSession().get(entityClass, id);
		return t;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findObject(String hqlWhere)
	{
		String hql = "from " + entityClass.getSimpleName();
		if(!StringUtils.isEmpty(hqlWhere))
		{
			hql = hql + hqlWhere;
		}
		List<T> t = (List<T>)this.getSession().createQuery(hql).list();
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findCollectionByCondition(String hqlWhere,Map<String, Object> paramsMapValue)
	{
		StringBuffer hql = new StringBuffer("from " + entityClass.getSimpleName() + " f where 1 = 1 ");
		
//		//获得条件
//		String hqlWhere = this.getAppendHqlWhere();
		
//		//获得参数
//		Map<String, Object> paramsMap = this.setParamMapValue();
		
		if(paramsMapValue != null && paramsMapValue.size() > 0)
		{
			hql.append(hqlWhere);
		}
		
		Query query = this.getSession().createQuery(hql.toString());
		
		
		//设置参数
		this.settingParam(hqlWhere, paramsMapValue, query);
		
		return query.list();
	}

	@Override
	public void updateObjectCollection(Collection<T> entities)
	{
		if(entities != null && entities.size() > 0)
		{
			for(T t : entities)
			{
				this.getSession().update(t);
			}
		}
		
	}

	@Override
	public void saveObjectCollection(Collection<T> entities)
	{
		if(entities != null && entities.size() > 0)
		{
			for(T t : entities)
			{
				this.getSession().save(t);
			}
		}
		
	}
	
	
	/**
	 * 设置条件查询的参数
	 * @param hqlWhere	条件hql
	 * @param paramsMap	参数集合
	 * @param query	
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月13日 下午7:52:53
	 */
	@SuppressWarnings("rawtypes")
	private void settingParam(String hqlWhere, Map<String, Object> paramsMap, Query query){
		if(!paramsMap.isEmpty() && paramsMap.size() > 0 && hqlWhere != null && hqlWhere.length() > 0)
		{
			for(Map.Entry<String, Object> entry : paramsMap.entrySet())
			{
				if(entry.getValue() instanceof Collection){
					query.setParameterList(entry.getKey(), (Collection) entry.getValue());
				}else
				{
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
	}


//	@SuppressWarnings("unchecked")
//	@Override
//	public List<T> findCollectionByArrayIds(Serializable[] ids)
//	{
//		StringBuffer hql = new StringBuffer("from " + entityClass.getSimpleName() + " f where 1 = 1 ");
//		
//		if(ids != null && ids.length > 0)
//		{
//			hql.append(" and id in (:id)");
//		}
//		
//		//获得条件
//		String hqlWhere = this.getAppendHqlWhere();
//		
//		//拼接条件
//		hql.append(hqlWhere);
//		
//		Query query = this.getSession().createQuery(hql.toString());
//		
//		query.setParameter("id", ids);
//		
//		//获得参数
//		Map<String, Object> paramsMap = this.setParamMapValue();
//		
//		//设置参数
//		this.settingParam(hqlWhere, paramsMap, query);
//		
//		List<T> list = query.list();
//		
//		return list;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<T> findCollectionByCollectionIds(Collection<T> ids)
//	{
//		StringBuffer hql = new StringBuffer("from " + entityClass.getSimpleName() + " f where 1 = 1 ");
//		
//		if(ids != null && ids.size() > 0)
//		{
//			hql.append(" and id in (:id)");
//		}
//		
//		//获得条件
//		String hqlWhere = this.getAppendHqlWhere();
//		
//		//拼接条件
//		hql.append(hqlWhere);
//		
//		Query query = this.getSession().createQuery(hql.toString());
//		
//		query.setParameter("id", ids);
//		
//		//获得参数
//		Map<String, Object> paramsMap = this.setParamMapValue();
//		
//		//设置参数
//		this.settingParam(hqlWhere, paramsMap, query);
//		
//		return query.list();
//	}
	
//	/**
//	 * 用于Hql语句的拼接(需要重写)
//	 * @return	拼接后的Hql语句
//	 *
//	 * @author XiaoY
//	 * @date: 
//	 * 2014年12月13日 下午7:21:34
//	 */
//	protected String getAppendHqlWhere(){
//		
//		return "";
//	}
//	
//	/**
//	 * 获得拼接hql语句的参数(需要重写)
//	 * @return	Map&ltString, Object&gt
//	 *
//	 * @author XiaoY
//	 * @date: 
//	 * 2014年12月13日 下午7:23:25
//	 */
//	protected Map<String, Object> setParamMapValue()
//	{
//		return new HashMap<String, Object>();
//	}

//	@Override
//	public void deleteObjectByArrayIds(Serializable[] ids)
//	{
//		StringBuffer hql = null;
//		if(ids != null && ids.length > 0){
//			hql = new StringBuffer(" delete " + this.entityClass.getSimpleName() + " where 1 = 1 ");
//			hql.append(" id in (:id) ");
//			
//			//获得条件
//			String hqlWhere = this.getAppendHqlWhere();
//			
//			hql.append(hqlWhere);
//			
//			Query query = this.getSession().createQuery(hql.toString());
//			
//			query.setParameter("id", ids);
//			
//			Map<String, Object> paramsMap = this.setParamMapValue();
//			
//			this.settingParam(hqlWhere, paramsMap, query);
//			
//			query.executeUpdate();
//			
//		}else 
//		{
//			System.out.println("数组删除出错---->" + ids);
//		}
//	}

}
