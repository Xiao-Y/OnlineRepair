package com.xiaoy.authority.service;

import java.util.List;

import com.xiaoy.authority.web.form.RoleForm;
import com.xiaoy.base.entites.RolePopedom;
import com.xiaoy.user.web.form.UserForm;


/**
 * 用于角色处理
 * @author XiaoY
 *
 * @explain 
 *
 * @date: 
 * 2014年12月22日 下午9:34:22
 */
public interface RoleService
{

	/**
	 * 使用角色id获得用户信息集合<br/>
	 * @param roleId	用户id
	 * @return	List &ltUserForm&gt
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月26日 下午3:35:35
	 */
	List<UserForm> findUserByRoleId(String roleId);

	/**
	 * 1、保存角色和权限的关联，如果角色存在执行更新操作，否则执行添加操作<br/>
	 * 2、保存角色和用户的关联，一个用户可以有多个角色，一个角色可以拥有多个用户<br/>
	 * @param roleForm	角色的VO对象（权限id数组、用户id数组、角色id）
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月26日 下午7:01:24
	 */
	void saveRole(RoleForm roleForm);

	/**
	 * 通过角色roleCode，查询出该角色的所有权限集合
	 * @param roleCode
	 * @return
	 */
	RolePopedom findPopedomByroleCode(String roleCode);

	/**
	 * 通过用户的uuid查询出现用户的所有的角色
	 * @param userUuid	用户的uuid
	 * @return	含有权限的字符串
	 */
	List<String> findRoleByUserUuid(String userUuid);
	
}
