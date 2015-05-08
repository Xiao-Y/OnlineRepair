package com.xiaoy.menu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.Menu;
import com.xiaoy.menu.dao.MenuDao;

@Repository
public class MenuDaoImpl extends CommonImpl<Menu> implements MenuDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findParentMenuAll()
	{
		String hql = "from Menu as mm  where mm.menu is null ";
		Query query = this.getSession().createQuery(hql);
		
		List<Menu> menu = query.list();
		
		return menu;
	}

	@Override
	public void deleteMenuAllParentIsNotNull()
	{
		String hql = " delete from Menu mm where mm.menu is not null ";
		Query query = this.getSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void deleteMenuAllParentIsNull()
	{
		String hql = " delete from Menu mm where mm.menu is null ";
		Query query = this.getSession().createQuery(hql);
		query.executeUpdate();
	}

}
