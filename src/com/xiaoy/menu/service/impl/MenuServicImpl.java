package com.xiaoy.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.Menu;
import com.xiaoy.menu.dao.MenuDao;
import com.xiaoy.menu.service.MenuService;

@Service
@Transactional(readOnly = true)
public class MenuServicImpl implements MenuService
{

	@Resource
	private MenuDao menuDao;
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void menuInit(List<Menu> entities)
	{

		if (entities != null && entities.size() > 0)
		{
			for (Menu t : entities)
			{
				menuDao.saveOrUpdate(t);
			}
		}
	}

	@Override
	public List<Menu> findParentMenuAll()
	{
		return menuDao.findParentMenuAll();
	}
}
