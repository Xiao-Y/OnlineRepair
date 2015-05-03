package com.xiaoy.menu.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.entites.Menu;
import com.xiaoy.base.util.ComparatorMenu;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.menu.service.MenuService;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.user.service.UserService;
import com.xiaoy.user.web.form.UserForm;

/**
 * 初始化左侧菜单栏
 * @author XiaoY
 * @explain 
 * 
 * @date: 
 * 2015年3月26日 下午10:05:11
 */
@SuppressWarnings("serial")
@Controller
public class MenuAction extends BaseAction implements ModelDriven<Menu>
{
	
	Menu menu = new Menu();
	
	@Override
	public Menu getModel()
	{
		return menu;
	}
	
	@Resource
	private UserService userService;
	
	@Resource
	private LogService logService;
	
	@Resource
	private MenuService menuService;
	
	private InputStream inputStream;
	
	public InputStream getInputStream()
	{
		return inputStream;
	}
	
	/**
	 * 使用异步验证用户登陆合法性。<br/>
	 * 如果合法，将用户信息存放到Session中key为userInfo,value为UserForm
	 * 如果不合法，返回0，由于ajax提示登陆不合法。
	 * @return	ajax-success
	 * @throws UnsupportedEncodingException
	 */
	public String login() throws UnsupportedEncodingException
	{
		String loginName = request.getParameter("logingName");
		String password = request.getParameter("password");
		
		UserForm user = new UserForm();
		user.setLoginName(loginName);
		user.setPassword(password);
		
		UserForm form = userService.findUser(user);
		if(form != null){
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", form);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			logService.saveLog(request, "【用户登陆】", loginName + " 登陆系统成功");
		}
		else{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			logService.saveLog(request, "【用户登陆】", loginName + " 登陆系统失败");
		}
		return "ajax-success";
	}
	
	/**
	 * 首页加载
	 * @return
	 */
	public String loading()
	{
		return "loading";
	}
	
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
		List<Menu> list = menuService.findParentMenuAll();
		
		//对菜单排序
		ComparatorMenu cm = new ComparatorMenu();
		Collections.sort(list, cm);
		
		request.setAttribute("list", list);
		return "left";
	}
	
	public String top()
	{
		return "top";
	}
}
