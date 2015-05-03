package com.xiaoy.resource.web.action;

import java.util.List;

import javax.annotation.Resource;

import com.xiaoy.base.entites.Menu;
import com.xiaoy.base.util.ReadMenuXml;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.menu.service.MenuService;

@SuppressWarnings("serial")
public class MenuInitAction extends BaseAction
{
	@Resource
	private MenuService menuService;
	
	public String menuInit()
	{
		ReadMenuXml menu = new ReadMenuXml();
		List<Menu> list = menu.readXML();
		
		menuService.menuInit(list);
		
		return "success";
	}
}
