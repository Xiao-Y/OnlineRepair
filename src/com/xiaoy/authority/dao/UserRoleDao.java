package com.xiaoy.authority.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.UserRole;


/**
 * @author XiaoY
 * @explain
 * 接口<p>
 * 继承公有接口 IConmonDao <br>
 * 添加自己的抽象方法<br>
 * @date
 * 2014年12月22日 下午9:18:38
 */
public interface UserRoleDao extends Common<UserRole>
{

	/**
	 * 通过角色id获取用户信息<br/>
	 * 	flag == 0：表示该角色不拥有某个用户<br/>
	 *  flag == 1：表示该角色拥有某个用户<p/>
	 * 注意：此处使用了左外连接，查询了elec_user 和  elec_user_role两张表。<p/>
	 * 使用了高级查询:<br/>
	 * case...<br/>
	 * when...then...else...end	<br/>
	 * 左外连接：<p/>
	 * from 表	<br/>
	 * left outer join 表	<br/>
	 * on 条件	<br/>
	 * and 条件 	<br/>
	 * @param roleId	角色id
	 * @return	List &ltObject[]&gt
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月26日 下午3:42:25
	 */
	List<Object[]> findElecUserByRoleId(String roleId);

	/**
	 * 删除所有属于这个角色的用户
	 * @param userRoles	含有角色Code的
	 */
	void deleteUserRoleByRoleCode(List<UserRole> userRoles);

	/**
	 * 通过用户的uuid查询出现该用户的所有角色
	 * @param userUuid	用户的uuid
	 * @return	角色
	 */
	List<Object> findRoleByUserUuid(String userUuid);
}
