package com.xiaoy.evaluate.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xiaoy.evaluate.web.form.MessageForm;

public interface MessageService
{

	/**
	 * 保存留言信息
	 * @param messageForm	用户的uuid、标题、内容
	 * @param request 用于获取用户信息
	 */
	void messageSave(MessageForm messageForm, HttpServletRequest request);

	/**
	 * 查询出所有的留言信息
	 * @param request	用于获取用户信息
	 * @return	List&ltMessageForm&gt
	 */
	List<MessageForm> findMessageAll(HttpServletRequest request);

	/**
	 * 通过uuid查询出现数据
	 * @param messageUuid
	 * @return
	 */
	MessageForm findMessageByMessageUuid(String messageUuid);

	/**
	 * 根据uuid删除留言信息
	 * @param messageUuid
	 */
	void deleteMessageById(String messageUuid);

}
