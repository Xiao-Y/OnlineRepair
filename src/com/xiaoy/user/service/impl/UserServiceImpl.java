package com.xiaoy.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.User;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.resource.dao.DictionaryDao;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.dao.UserDao;
import com.xiaoy.user.service.UserService;
import com.xiaoy.user.web.form.UserForm;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService
{
	@Resource
	private UserDao userDao;
	
	@Resource
	private DictionaryDao dictionaryDao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UserForm> findUserByConditionWithPage(UserForm userForm)
	{
		Map<String,Object> conditionMap = this.getConditionMap(userForm);
		String hqlWhere = (String) conditionMap.get("hqlWhere");
		Map<String,Object> paramsMapValue = (Map<String, Object>) conditionMap.get("paramMap");
		List<User> list = userDao.findCollectionByConditionWithPage(userForm, hqlWhere, paramsMapValue);
		List<UserForm> formList = this.userListVoToPoList(list);
		return formList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int countUserByCondition(UserForm userForm)
	{
		Map<String,Object> conditionMap = this.getConditionMap(userForm);
		String hqlWhere = (String) conditionMap.get("hqlWhere");
		Map<String,Object> paramsMapValue = (Map<String, Object>) conditionMap.get("paramMap");
		Integer count = userDao.countByCollection(hqlWhere, paramsMapValue);
		return count;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void userSave(UserForm userForm)
	{
		User entity = this.userPoToVo(userForm);
		userDao.saveObject(entity);
	}

	@Override
	public UserForm findUserByUuid(String userUuid)
	{
		UserForm userForm = new UserForm();
		User user = userDao.findObjectById(userUuid);
		
		userForm = this.userVoToPo(userForm, user);
		return userForm;
	}
	

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void userUpdate(UserForm userForm)
	{
		User entity = this.userPoToVo(userForm);
		userDao.updateObject(entity);
	}
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void userDelete(String userUuid)
	{
		userDao.deleteObjectByid(userUuid);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void userDeletes(String[] ids) {
		userDao.deleteUserByIds(ids);
	}
	
	/**
	 * 用户的Vo对象转换成Po对象
	 * @param userForm	PO对象
	 * @param user	VO对象
	 * @return	UserForm PO对象
	 */
	private UserForm userVoToPo(UserForm userForm, User user)
	{
		userForm.setLoginName(user.getLoginName());
		userForm.setAddress(user.getAddress());
		userForm.setMaintainTypeCode(user.getMaintainTypeCode());
		if(!StringUtils.isEmpty(user.getMaintainTypeCode()))
		{
			userForm.setMaintainType(dictionaryDao.findDDLName(user.getMaintainTypeCode(), DictionaryForm.MAINTAIN_TYPE_NAME));
		}else
		{
			userForm.setMaintainType("");
		}
		userForm.setName(user.getName());
		userForm.setPassword(user.getPassword());
		userForm.setPhone(user.getPhone());
		userForm.setRegisterTime(user.getRegisterTime() == null ? "" : DateHelper.dateTimeConverString(user.getRegisterTime()));
		userForm.setRemark(user.getRemark());
		userForm.setSexCode(user.getSexCode());
		if(!StringUtils.isEmpty(user.getSexCode()))
		{
			userForm.setSex(dictionaryDao.findDDLName(user.getSexCode(), DictionaryForm.SEX_NAME));
		}
		userForm.setUserUuid(user.getUserUuid());
		
		return userForm;
	}
	
	/**
	 * 用户的Po对象转换成Vo对象
	 * @param user	Po对象
	 * @return	Vo对象
	 */
	private User userPoToVo(UserForm userForm)
	{
		User user = new User();
		user.setLoginName(userForm.getLoginName());
		user.setAddress(userForm.getAddress());
		user.setMaintainTypeCode(userForm.getMaintainTypeCode());
		user.setName(userForm.getName());
		if(!StringUtils.isEmpty(userForm.getPassword()))
		{
			user.setPassword(userForm.getPassword());
		}else
		{
			user.setPassword("123456");
		}
		user.setPhone(userForm.getPhone());
		if(!StringUtils.isEmpty(userForm.getUserUuid()))
		{
			user.setRegisterTime(!StringUtils.isEmpty(userForm.getRegisterTime()) ? DateHelper.stringConverDateTime(userForm.getRegisterTime()) : null);
		}else
		{
			user.setRegisterTime(new Date());
		}
		user.setRemark(userForm.getRemark());
		user.setSexCode(userForm.getSexCode());
		user.setUserUuid(userForm.getUserUuid());
		
		return user;
	}
	
	/**
	 * 拼接HQL条件查询，添加查询参数
	 * @param userForm	查询条件和参数
	 * @return	Map &ltString, Object&gt 查询条件和参数
	 */
	private Map<String, Object> getConditionMap(UserForm userForm)
	{
		StringBuffer hqlWhere = null;
		Map<String, Object> paramMap = null;
		Map<String,Object> conditionMap = null;
		if(userForm != null)
		{
			hqlWhere = new StringBuffer();
			paramMap = new HashMap<String,Object>();
			conditionMap = new HashMap<String, Object>();
			if(!StringUtils.isEmpty(userForm.getLoginName()))
			{
				hqlWhere.append(" and e.loginName like :loginName");
				paramMap.put("loginName","%" + userForm.getLoginName() + "%");
			}
			
			if(!StringUtils.isEmpty(userForm.getName()))
			{
				hqlWhere.append(" and e.name like :name");
				paramMap.put("name", "%" + userForm.getName() + "%");
			}
			
			if(!StringUtils.isEmpty(userForm.getSexCode()))
			{
				hqlWhere.append(" and e.sexCode = :sexCode");
				paramMap.put("sexCode", userForm.getSexCode());
			}
			
			if(!StringUtils.isEmpty(userForm.getMaintainTypeCode()))
			{
				hqlWhere.append(" and e.maintainTypeCode = :maintainTypeCode");
				paramMap.put("maintainTypeCode", userForm.getMaintainTypeCode());
			}
			hqlWhere.append(" order by e.registerTime desc ");
			conditionMap.put("hqlWhere", hqlWhere.toString());
			conditionMap.put("paramMap", paramMap);
		}
		return conditionMap;
	}
	
	/**
	 * 将用户的实体类对象转换成Po对象
	 * @param list	Vo对象
	 * @return	List &ltUserForm&gt	Po对象
	 */
	private List<UserForm> userListVoToPoList(List<User> list)
	{
		List<UserForm> formList = null;
		if(list != null && list.size() > 0)
		{
			formList = new ArrayList<UserForm>();
			for(User user : list)
			{
				UserForm userForm = new UserForm();
				userForm = this.userVoToPo(userForm, user);
				formList.add(userForm);
			}
		}
		return formList;
	}

	@Override
	public UserForm findUser(UserForm userForm)
	{
		User user = userDao.findUser(userForm);
		if(user != null){
			return this.userVoToPo(userForm, user);
		}
		return null;
	}

	@Override
	public List<UserForm> findUserByMaintainTypeCode(String maintainTypeCode)
	{
		List<User> list = userDao.findUserByMaintainTypeCode(maintainTypeCode);
		List<UserForm> formList = null;
		if(!list.isEmpty())
		{
			formList = new ArrayList<UserForm>();
			
			for(User u : list)
			{
				UserForm form = new UserForm();
				BeanUtils.copyProperties(u, form);
				formList.add(form);
			}
		}
		return formList;
	}
}
