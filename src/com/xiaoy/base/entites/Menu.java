package com.xiaoy.base.entites;

import java.util.Set;
import java.util.TreeSet;

/**
 * 菜单表
 * 
 * @author XiaoY
 * @date: 2015年5月3日 下午12:53:35
 */
public class Menu
{
	/* 菜单code */
	private String code;
	/* 菜单名称 */
	private String codeName;
	/* 所在位置 */
	private String orderMenu;
	/* 菜单URL */
	private String url;

	private Menu menu;

	private Set<Menu> menus = new TreeSet<Menu>();

	/**
	 * private String parentCode;
	 */
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getCodeName()
	{
		return codeName;
	}

	public void setCodeName(String codeName)
	{
		this.codeName = codeName;
	}

	public String getOrderMenu()
	{
		return orderMenu;
	}

	public void setOrderMenu(String orderMenu)
	{
		this.orderMenu = orderMenu;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Menu getMenu()
	{
		return menu;
	}

	public void setMenu(Menu menu)
	{
		this.menu = menu;
	}

	public Set<Menu> getMenus()
	{
		return menus;
	}

	public void setMenus(Set<Menu> menus)
	{
		this.menus = menus;
	}

}
