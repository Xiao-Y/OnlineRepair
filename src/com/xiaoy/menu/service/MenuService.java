package com.xiaoy.menu.service;

import java.util.List;

import com.xiaoy.base.entites.Menu;

public interface MenuService
{
	/**
	 * 
	 * 初始化菜单
	 * 
	 * @param list
	 */
	void menuInit(List<Menu> list);

	/**
	 * 查询出父类菜单数据
	 * @return
	 */
	List<Menu> findParentMenuAll();

}
