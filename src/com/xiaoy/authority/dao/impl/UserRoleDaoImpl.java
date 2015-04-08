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
	public List<Object[]> findElecUserByRoleId(String roleId)
	{
		String sql = " select distinct case user_role.roleid "
				+ " when ? then '1' else '0' end as flag, "
				+ " user.userUuid as userUuid, "
				+ " user.name as name, "
				+ " user.loginName as loginName "
				+ " from user"
				+ " left outer join user_role "
				+ " on user.userUuid = user_role.userUuid "
				+ " and user_role.roleid = ? ";
		Query query = this.getSession().createSQLQuery(sql)
						  .addScalar("flag")
						  .addScalar("userUuid")
						  .addScalar("name")
						  .addScalar("loginName");
		query.setParameter(0, roleId);
		query.setParameter(1, roleId);
		List<Object[]> userList = query.list();
		return userList;
	}

	@Override
	public void deleteObjectByCollection(List<UserRole> userRole)
	{
		String hqlWhere = "";
		Map<String, Object> paramsMapValue = null;
		if(userRole != null && userRole.size() > 0){
			paramsMapValue = new HashMap<String, Object>();
			List<Integer> ids = new ArrayList<Integer>();
			hqlWhere = " and seqId in (:seqId)";
			for(UserRole u : userRole){
				ids.add(u.getSeqId());
			}
			paramsMapValue.put("seqId", paramsMapValue);
		}
		super.deleteObjectByCollectionIds(hqlWhere, paramsMapValue);
	}
}
