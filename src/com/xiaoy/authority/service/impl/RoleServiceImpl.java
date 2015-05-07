package com.xiaoy.authority.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.authority.dao.RolePopedomDao;
import com.xiaoy.authority.dao.UserRoleDao;
import com.xiaoy.authority.service.RoleService;
import com.xiaoy.authority.web.form.RoleForm;
import com.xiaoy.base.entites.RolePopedom;
import com.xiaoy.base.entites.UserRole;
import com.xiaoy.user.web.form.UserForm;

/**
 * @author XiaoY
 * @explain 
 * 实现RoleService
 * @date: 
 * 2014年12月22日 下午9:34:22
 */
@Service
@Transactional(readOnly=true)
public class RoleServiceImpl implements RoleService
{
	/**
	 * 注入用户角色
	 */
	@Resource
	private UserRoleDao userRoleDao;
	
	/**
	 * 注入角色权限
	 */
	@Resource
	private RolePopedomDao rolePopedomDao;

	@Override
	public List<UserForm> findUserByRoleId(String roleId)
	{
		List<Object[]> userList = userRoleDao.findElecUserByRoleId(roleId);
		List<UserForm> userForm = this.elecUserRoleObjectToVo(userList);
		return userForm;
	}

	/**
	 * 将Object 类型的用户信息对象转换成	 UserForm 类型的对象
	 * @param userList	List &ltObject[]&gt
	 * @return	List &ltUserForm&gt
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月26日 下午4:20:29
	 */
	private List<UserForm> elecUserRoleObjectToVo(List<Object[]> userList)
	{
		List<UserForm> userForm = null;
		
		if(userList != null)
		{
			userForm = new ArrayList<UserForm>();
			for(Object[] object : userList)
			{
				UserForm user = new UserForm();
				user.setFlag(object[0].toString());
				user.setUserUuid(object[1].toString());
				user.setName(object[2].toString());
				user.setLoginName(object[3].toString());
				
				userForm.add(user);
			}
		}
		return userForm;
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	public void saveRole(RoleForm roleForm)
	{
		if(roleForm != null && !StringUtils.isEmpty(roleForm.getRoleid()))
		{
			//保存角色与权限的关联
			this.saveRolePopedom(roleForm);
			//保存用户与角色的关联
			this.saveUserRole(roleForm);
		}
	}

	/**
	 * 保存角色与权限的关联
	 * @param roleForm	角色VO（角色id、权限id数组）
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月26日 下午7:24:40
	 */
	private void saveRolePopedom(RoleForm roleForm)
	{
		String roleId = roleForm.getRoleid();
		String[] selectoper = roleForm.getPopedomCode();
		
		StringBuffer popedomCode = new StringBuffer("");
		for(int i = 0; selectoper != null && i < selectoper.length; i++)
		{
			if(i == selectoper.length - 1)
			{
				popedomCode.append(selectoper[i]);
			}else
			{
				popedomCode.append(selectoper[i] + ",");
			}
		}
		RolePopedom rolePopedom = rolePopedomDao.findObjectById(roleId);
		//说明数据库中角色与权限的关联表中已经存在
		if(rolePopedom != null)
		{
			rolePopedom.setPopedomCode(popedomCode.toString());
			rolePopedomDao.updateObject(rolePopedom);
		}
		else
		{
			rolePopedom = new RolePopedom();
			rolePopedom.setPopedomCode(popedomCode.toString());
			rolePopedom.setRoleCode(roleId);
			rolePopedomDao.saveObject(rolePopedom);
		}
	}
	
	/**
	 * 保存用户与角色的关联
	 * 1、先删除原来的用户与角色的关联关系
	 * 2、保存新的用户与角色的关联关系
	 * @param roleForm	角色VO（角色id、用户id数组）
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月26日 下午7:26:26
	 */
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	private void saveUserRole(RoleForm roleForm)
	{
		String roleCode = roleForm.getRoleid();
		String[] userIds = roleForm.getUserIds();
		
		StringBuffer hqlWhere = new StringBuffer("");
		hqlWhere.append(" and e.roleCode = :roleCode");
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("roleCode", roleCode);
		
		List<UserRole> userRoles = userRoleDao.findCollectionByCondition(hqlWhere.toString(), paramsMap);
	
		//删除原来的用户与角色的关联关系
		if(userRoles != null)
		{
			userRoleDao.deleteUserRoleByRoleCode(userRoles);
		}
		
		List<UserRole> list = new ArrayList<UserRole>();
		for(int i = 0; userIds != null && i < userIds.length; i++)
		{
			UserRole userRole = new UserRole();
			userRole.setRoleCode(roleCode);
			userRole.setUserUuid(userIds[i]);
			list.add(userRole);
		}
		
		if(list != null && list.size() > 0)
		{
			//保存新的用户与角色的关联关系
			userRoleDao.saveObjectCollection(list);
		}
	}

	@Override
	public RolePopedom findPopedomByroleCode(String roleCode)
	{
		return rolePopedomDao.findObjectById(roleCode);
	}

	@Override
	public List<String> findRoleByUserUuid(String userUuid)
	{
		List<Object> list = userRoleDao.findRoleByUserUuid(userUuid);
		
		List<String> str = null;
		if(list != null && list.size() > 0)
		{
			str = new ArrayList<String>();
			for(Object o : list)
			{
				str.add(o.toString());
			}
		}
		return str;
	}
}















