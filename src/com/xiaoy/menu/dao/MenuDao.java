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

}
