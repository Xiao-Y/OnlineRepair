package com.xiaoy.user.service;

import java.util.List;

import com.xiaoy.user.web.form.UserForm;

public interface UserService
{
	/**
	 * 带分页的条件查询
	 * @param userForm 查询条件
	 * @return	List &ltUserForm&gt
	 */
	public List<UserForm> findUserByConditionWithPage(UserForm userForm);
	
	/**
	 * 带条件的用户数量统计
	 * @param userForm	查询条件
	 * @return	int 统计数量
	 */
	public int countUserByCondition(UserForm userForm);

	/**
	 * 保存用户信息
	 * @param userForm
	 */
	public void userSave(UserForm userForm);

	/**
	 * 通过用户的uuid获取用户信息
	 * @param userUuid	用户uuid
	 * @return	UserForm	用户信息
	 */
	public UserForm findUserByUuid(String userUuid);

	/**
	 * 更新用户信息
	 * @param userForm	用户信息
	 */
	public void userUpdate(UserForm userForm);

	/**
	 * 通过主键uuid删除用户信息
	 * @param userUuid	用户uuid
	 */
	public void userDelete(String userUuid);

	/**
	 * 通过一组uuid数组，批量删除用户信息
	 * @param ids	用户uuid数组
	 */
	public void userDeletes(String[] ids);

	/**
	 * 根据登陆名和密码查询出用户信息
	 * @param userForm	登陆名和密码
	 * @return	用户信息的PO对象
	 */
	public UserForm findUser(UserForm userForm);

	/**
	 * 通过维护类别查出所有的用户
	 * @param maintainTypeCode	维护类别
	 * @return	List&ltUserForm&gt
	 */
	public List<UserForm> findUserByMaintainTypeCode(String maintainTypeCode);
}
