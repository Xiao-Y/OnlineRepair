package com.xiaoy.user.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.User;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.dao.UserDao;
import com.xiaoy.user.web.form.UserForm;

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

	@Override
	public User findUser(UserForm userForm)
	{
		String hql = "from User where 1 = 1 and loginName = :loginName and password = :password";
		Query query = this.getSession().createQuery(hql)
				.setString("loginName", userForm.getLoginName())
				.setString("password", userForm.getPassword());
		User user = (User) query.uniqueResult();
		return user;
	}

	@Override
	public List<User> findUserByMaintainTypeCode(String maintainTypeCode)
	{
		String hqlWhere = "";
		Map<String, Object> paramsMapValue = null;
		
		if(!StringUtils.isEmpty(maintainTypeCode))
		{
			paramsMapValue = new HashMap<String, Object>();
			
			hqlWhere = " and  maintainTypeCode = :maintainTypeCode ";
			paramsMapValue.put("maintainTypeCode", maintainTypeCode);
		}
		List<User> user = super.findCollectionByCondition(hqlWhere, paramsMapValue);
		
		return user;
	}

	@Override
	public List<User> findUserCoditionMaintain()
	{
		String hqlWhere = "";
		Map<String, Object> paramsMapValue = new HashMap<String, Object>();
			
		hqlWhere = " and  maintainTypeCode != :maintainTypeCode ";
		paramsMapValue.put("maintainTypeCode", DictionaryForm.MAINTAIN_TYPE_NORMAL);
	
		List<User> user = super.findCollectionByCondition(hqlWhere, paramsMapValue);
		
		return user;
	}
}
