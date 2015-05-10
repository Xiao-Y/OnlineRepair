package com.xiaoy.user.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.User;
import com.xiaoy.user.web.form.UserForm;

public interface UserDao extends Common<User>
{

	/**
	 * 通过一组uuid，批量删除用户信息
	 * 
	 * @param ids
	 */
	void deleteUserByIds(String[] ids);

	/**
	 * 根据登陆名和密码查询出用户信息
	 * 
	 * @param userForm
	 *            登陆名和密码
	 * @return 用户信息的VO对象
	 */
	User findUser(UserForm userForm);

	/**
	 * 通过维护类别查出所有的用户
	 * 
	 * @param maintainTypeCode
	 *            维护类别
	 * @return List&ltUser&gt
	 */
	List<User> findUserByMaintainTypeCode(String maintainTypeCode);

	/**
	 * 查询出现所有是维护人员的信息
	 * 
	 * @return
	 */
	List<User> findUserCoditionMaintain();

	/**
	 * 用于检测用户名是否重复<br/>
	 * false:没有重复<br/>
	 * true：已经存在<br/>
	 * 
	 * @param loginName
	 *            登陆名
	 * @return
	 */
	Boolean findUserByLoginName(String loginName);

}
