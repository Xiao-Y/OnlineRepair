package com.xiaoy.authority.service;

import java.util.List;

import com.xiaoy.authority.web.form.RoleForm;
import com.xiaoy.base.util.XmlObject;
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
	 * 从Function.xml中读取权限集合,存放到XmlObject对象中
	 * @return	List &ltXmlObject&gt
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月23日 下午7:10:33
	 */
	List<XmlObject> readXml();

	/**
	 * 通过角色id，查询出该角色的所有权限集合
	 * @param roleId	角色id
	 * @return	List &ltXmlObject&gt
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月25日 下午8:38:59
	 */
	List<XmlObject> readEditXml(String roleId);

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
	
}
