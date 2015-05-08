package com.xiaoy.evaluate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.authority.service.RoleService;
import com.xiaoy.base.entites.Message;
import com.xiaoy.base.entites.User;
import com.xiaoy.evaluate.dao.MessageDao;
import com.xiaoy.evaluate.service.MessageService;
import com.xiaoy.evaluate.web.form.MessageForm;
import com.xiaoy.resource.dao.DictionaryDao;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.web.form.UserForm;

@Service
@Transactional(readOnly=true)
public class MessageServieImpl implements MessageService
{

	@Resource
	private MessageDao messageDao;
	
	@Resource
	private DictionaryDao dictionaryDao;
	
	//角色
	@Resource
	private RoleService roleService;
	
	@Override
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	public void messageSave(MessageForm messageForm, HttpServletRequest request)
	{
		Message message = null;
		if("1".equals(messageForm.getFlag()))
		{
			message = messageDao.findObjectById(messageForm.getMessageUuid());
			message.setReplyMes(messageForm.getReplyMes());
			message.setReplySata(DictionaryForm.REPLY_SATA_OK);
		}else
		{
			message = new Message();
			BeanUtils.copyProperties(messageForm, message);
			HttpSession session = request.getSession();
			UserForm userInfo = (UserForm) session.getAttribute("userInfo");
			User user = new User();
			user.setUserUuid(userInfo.getUserUuid());
			message.setUser(user);
			message.setMessageTime(new Date());
			message.setReplySata(DictionaryForm.REPLY_SATA_NO);
			messageDao.saveObject(message);
		}
	}

	@Override
	public List<MessageForm> findMessageAll(HttpServletRequest request)
	{
		//获取用户的信息
		HttpSession session = request.getSession();
		UserForm userInfo = (UserForm) session.getAttribute("userInfo");
		
		List<String> roles = roleService.findRoleByUserUuid(userInfo.getUserUuid());
		
		Map<String, Object> map = null;
		String hql = "";
		//除系统管理员和高级管理员外，其它的都只能看见自己的留言
		if(roles != null && roles.size() > 0  && !roles.contains(DictionaryForm.ROLE_TYPE_ADMIN) && !roles.contains(DictionaryForm.ROLE_TYPE_SA))
		{
			hql = hql + " and e.user.userUuid = :userUuid ";
			map = new HashMap<String, Object>();
			map.put("userUuid", userInfo.getUserUuid());
		}
		
		List<Message> list = messageDao.findCollectionByCondition(hql, map);
		
		List<MessageForm> mForm = null;
		if(list != null && list.size() > 0)
		{
			mForm = new ArrayList<MessageForm>();
			
			for(Message m : list)
			{
				MessageForm form = new MessageForm();
				BeanUtils.copyProperties(m, form);
				if(!StringUtils.isEmpty(m.getReplySata()))
				{
					form.setReplySateName(dictionaryDao.findDDLName(m.getReplySata(), DictionaryForm.REPLY_SATA));
				}
				
				mForm.add(form);
			}
		}
		return mForm;
	}

	@Override
	public MessageForm findMessageByMessageUuid(String messageUuid)
	{
		Message message = messageDao.findObjectById(messageUuid);
		MessageForm form = new MessageForm();
		BeanUtils.copyProperties(message, form);
		return form;
	}

	@Override
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	public void deleteMessageById(String messageUuid)
	{
		messageDao.deleteObjectByid(messageUuid);
	}
}
