package com.xiaoy.user.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.User;
import com.xiaoy.user.dao.UserDao;

@Repository
public class UserDaoImpl extends CommonImpl<User> implements UserDao
{

	@Override
	public void deleteUserByIds(String[] ids) {
		String hqlWhere = "";
		Map<String, Object> paramsMapValue = null;
		if(ids != null && ids.length > 0){
			//将数组转换成集合
			List<String> list = Arrays.asList(ids);
			paramsMapValue = new HashMap<String, Object>();
			hqlWhere = " and userUuid in(:userUuid)";
			paramsMapValue.put("userUuid", list);
		}
		super.deleteObjectByCollectionIds(hqlWhere, paramsMapValue);
	}
}
