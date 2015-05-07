package com.xiaoy.authority.web.action;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.authority.service.RoleService;
import com.xiaoy.authority.web.form.RoleForm;
import com.xiaoy.base.entites.Menu;
import com.xiaoy.base.entites.RolePopedom;
import com.xiaoy.base.util.ComparatorMenu;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.menu.service.MenuService;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.web.form.UserForm;

/**
 * @author XiaoY
 * 
 * @explain
 * 
 * @date: 2014年11月13日 下午11:09:05
 */
@SuppressWarnings("serial")
@Controller
public class RoleAction extends BaseAction implements ModelDriven<RoleForm>
{
	@Resource
	private RoleService roleService;

	// 数据字典
	@Resource
	private DictionaryService dictionaryService;

	// 菜单
	@Resource
	private MenuService menuService;

	// 日志处理
	@Resource
	private LogService logService;

	private RoleForm roleForm = new RoleForm();

	private InputStream inputStream;

	@Override
	public RoleForm getModel()
	{
		return roleForm;
	}

	public InputStream getInputStream()
	{
		return inputStream;
	}

	/**
	 * 查询出所有的角色权限（在数据字典中）<br/>
	 * 在founction.xml文件中查询出系统所有的功能权限<br/>
	 * 
	 * @return 跳转到roleIndex.jsp
	 * 
	 * @author XiaoY
	 * @date: 2014年12月23日 下午6:32:30
	 */
	public String home()
	{
		// 获取所有的角色类型
		List<DictionaryForm> roleList = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.ROLE_TYPE);
		request.setAttribute("roleList", roleList);

		// 读取所有的菜单和权限
		List<Menu> list = menuService.findParentMenuAll();
		// 对菜单排序
		ComparatorMenu cm = new ComparatorMenu();
		Collections.sort(list, cm);
		request.setAttribute("xmlList", list);

		logService.saveLog(request, RoleForm.MODE_NAME, "进入角色和权限列表");
		return "home";
	}

	/**
	 * 跳转到角色权限编辑的页面<br/>
	 * 1.使用角色Code获得角色的权限, 并与系统下的所有权限进行匹配<br/>
	 * 2.使用角色Code获得用户信息集合，并与系统中的所有用户进行匹配<br/>
	 * 
	 * @return roleEdit.jsp
	 * 
	 * @author XiaoY
	 * @date: 2014年12月25日 下午7:24:00
	 */
	public String edit()
	{
		//1. 读取所有的菜单和权限
		List<Menu> list = menuService.findParentMenuAll();
		// 对菜单排序
		ComparatorMenu cm = new ComparatorMenu();
		Collections.sort(list, cm);
		request.setAttribute("xmlList", list);

		//2.角色拥有的权限
		String roleCode = request.getParameter("roleCode");
		// 通过角色roleCode，查询出该角色的所有权限集合
		RolePopedom rolePopedom = roleService.findPopedomByroleCode(roleCode);
		
		request.setAttribute("popedomCode", rolePopedom != null ? rolePopedom.getPopedomCode() : null);

		// 使用角色id获得用户信息集合
		List<UserForm> userList = roleService.findUserByRoleId(roleCode);
		request.setAttribute("userList", userList);
		logService.saveLog(request, RoleForm.MODE_NAME, "进入编辑角色和权限");
		return "edit";
	}

	/**
	 * 保存角色权限和角色拥有的用户<br/>
	 * 1、保存角色和权限的关联，如果角色存在执行更新操作，否则执行添加操作<br/>
	 * 2、保存角色和用户的关联，一个用户可以有多个角色，一个角色可以拥有多个用户<br/>
	 * 
	 * @return 权限列表
	 * 
	 * @author XiaoY
	 * @date: 2014年12月26日 下午6:35:18
	 */
	public String save()
	{
		roleService.saveRole(roleForm);
		logService.saveLog(request, RoleForm.MODE_NAME, "保存角色和权限");
		return "list";
	}
}
