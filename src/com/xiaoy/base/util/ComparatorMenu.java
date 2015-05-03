package com.xiaoy.base.util;

import java.util.Comparator;

import com.xiaoy.base.entites.Menu;

public class ComparatorMenu implements Comparator<Menu>
{

	/**
	 * 对菜单的排序
	 */
	@Override
	public int compare(Menu o1, Menu o2)
	{
		return o1.getOrderMenu().compareTo(o2.getOrderMenu());
	}
}
