package com.xiaoy.resource.web.action;

import java.util.List;

import javax.annotation.Resource;

import com.xiaoy.base.entites.Menu;
import com.xiaoy.base.util.ReadMenuXml;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.menu.service.MenuService;
import com.xiaoy.resource.servic.LogService;

@SuppressWarnings("serial")
public class MenuInitAction extends BaseAction
{
	@Resource
	private MenuService menuService;
	
	// 日志信息
	@Resource
	private LogService logService;

	public String menuInit()
	{
		ReadMenuXml menu = new ReadMenuXml();
		List<Menu> list = menu.readXML();

		menuService.menuInit(list);
		logService.saveLog(request, "【系统管理】-【初始化菜单】", "初始化菜单");
		return "success";
	}
}
