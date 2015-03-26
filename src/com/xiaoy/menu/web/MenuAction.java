package com.xiaoy.menu.web;

import org.springframework.stereotype.Controller;

/**
 * 初始化左侧菜单栏
 * @author XiaoY
 * @explain 
 * 
 * @date: 
 * 2015年3月26日 下午10:05:11
 */
@Controller
public class MenuAction
{
	public String home()
	{
		return "home";
	}
	
	public String buttom()
	{
		return "buttom";
	}
	
	public String left()
	{
		return "left";
	}
	
	public String top()
	{
		return "top";
	}
}
