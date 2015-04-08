package com.xiaoy.user.dao;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.User;

public interface UserDao extends Common<User>
{

	/**
	 * 通过一组uuid，批量删除用户信息
	 * @param ids
	 */
	void deleteUserByIds(String[] ids);

}
