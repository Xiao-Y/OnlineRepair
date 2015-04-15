package com.xiaoy.user.dao;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.User;
import com.xiaoy.user.web.form.UserForm;

public interface UserDao extends Common<User>
{

	/**
	 * 通过一组uuid，批量删除用户信息
	 * @param ids
	 */
	void deleteUserByIds(String[] ids);

	/**
	 * 根据登陆名和密码查询出用户信息
	 * @param userForm	登陆名和密码
	 * @return	用户信息的VO对象
	 */
	User findUser(UserForm userForm);

}
