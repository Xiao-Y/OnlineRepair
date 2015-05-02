package com.xiaoy.count.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.BaseDao;
import com.xiaoy.count.dao.CountDao;

@Repository
public class CountDaoImpl extends BaseDao implements CountDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> userSexCount()
	{
		String hql = "select sexCode, count(*) from User group by sexCode";
		Query query = this.getSession().createQuery(hql);
		List<Object[]> list = query.list();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> userTypeCount()
	{

		String hql = "select maintainTypeCode, count(*) from User group by maintainTypeCode";
		Query query = this.getSession().createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> deviceBreakdownCount()
	{
		String hql = "SELECT d.deviceName,sum(d.deviceNum) FROM DeviceInfo d GROUP BY d.deviceName";
		Query query = this.getSession().createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> deviceSum()
	{
		String hql = "SELECT d.deviceName,count(*) FROM DeviceInfo d GROUP BY d.deviceName";
		Query query = this.getSession().createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
}
