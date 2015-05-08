package com.xiaoy.menu.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.Menu;

public interface MenuDao extends Common<Menu>
{

	/**
	 * 查询出父类菜单数据
	 * @return
	 */
	List<Menu> findParentMenuAll();

	/**
	 * 删除所有父级不为空的的菜单
	 */
	void deleteMenuAllParentIsNotNull();

	/**
	 * 删除所有父级为空的的菜单
	 */
	void deleteMenuAllParentIsNull();
}
