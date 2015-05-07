package com.xiaoy.authority.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xiaoy.authority.dao.UserRoleDao;
import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.UserRole;

/**
 * @author: XiaoY
 * @explain:
 * 实现类<br>
 * <b>extends</b> CommonDaoImpl &ltUserRole&gt<br>
 * <b>implements</b> UserRoleDao<br>
 * 
 * @date: 
 * 2014年12月22日 下午9:20:02
 */
@Repository
public class UserRoleDaoImpl extends CommonImpl<UserRole> implements UserRoleDao
{
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<Object[]> findElecUserByRoleId(String roleCode)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT CASE ur.ROLE_CODE WHEN :roleCode THEN '1' ELSE '0' end as flag,u.USER_UUID,u.NAME,u.LOGIN_NAME ");
		sql.append(" from user u LEFT OUTER JOIN user_role ur on u.USER_UUID = ur.USER_UUID ");
		sql.append(" and ur.ROLE_CODE = :roleCode ");
		Query query = this.getSession().createSQLQuery(sql.toString())
						  .addScalar("flag")
						  .addScalar("USER_UUID")
						  .addScalar("NAME")
						  .addScalar("LOGIN_NAME");
		query.setParameter("roleCode", roleCode);
		query.setParameter("roleCode", roleCode);
		List<Object[]> userList = query.list();
		return userList;
	}

	@Override
	public void deleteUserRoleByRoleCode(List<UserRole> userRoles)
	{
		if(userRoles != null && userRoles.size() > 0)
		{
			List<String> list = new ArrayList<String>();
			for(UserRole u : userRoles)
			{
				list.add(u.getRoleCode());
			}
			
			Map<String, Object> paramsMapValue = new HashMap<String, Object>();
			StringBuffer hqlWhere = new StringBuffer("");
			hqlWhere.append(" and  roleCode in(:roleCode)");
			paramsMapValue.put("roleCode", list);
			
			super.deleteObjectByCollectionIds(hqlWhere.toString(), paramsMapValue);
		}
	}
}
