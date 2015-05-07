package com.xiaoy.evaluate.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.evaluate.service.MessageService;
import com.xiaoy.evaluate.web.form.MessageForm;
import com.xiaoy.resource.servic.LogService;

@SuppressWarnings("serial")
public class MessageAction extends BaseAction implements ModelDriven<MessageForm>
{
	// 留言
	@Resource
	private MessageService messageService;
	
	//日志管理
	@Resource
	private LogService logService;

	private MessageForm messageForm = new MessageForm();

	@Override
	public MessageForm getModel()
	{
		return messageForm;
	}

	// 获取输入流
	private InputStream inputStream;

	public InputStream getInputStream()
	{
		return inputStream;
	}

	/**
	 * 进入留言页面
	 * 
	 * @return
	 */
	public String message()
	{
		List<MessageForm> list = messageService.findMessageAll(request);
		request.setAttribute("list", list);
		
		logService.saveLog(request, MessageForm.MODEL_NAME, "进入留言列表页面");
		
		return "message";
	}

	/**
	 * 保存留言信息
	 */
	public String messageSave()
	{
		String messageUuid = request.getParameter("messageUuid");
		String replyMes = request.getParameter("replyMes");
		String flag = request.getParameter("flag");

		messageForm.setMessageUuid(messageUuid);
		messageForm.setReplyMes(replyMes);
		messageForm.setFlag(flag);

		messageService.messageSave(messageForm, request);
		
		logService.saveLog(request, MessageForm.MODEL_NAME, "添加留言信息");
		return "success";
	}

	/**
	 * 根据uuid查询出现回复信息
	 * @throws UnsupportedEncodingException
	 */
	public String findMessageByMessageUuid() throws UnsupportedEncodingException
	{
		String messageUuid = request.getParameter("messageUuid");
		MessageForm messageForm = messageService.findMessageByMessageUuid(messageUuid);
		Gson gson = new Gson();
		String json = gson.toJson(messageForm);
		// 带有中文
		inputStream = new ByteArrayInputStream(json.getBytes("UTF-8"));
		
		logService.saveLog(request, MessageForm.MODEL_NAME, "查看留言信息");
		return "ajax-success";
	}
	
	public String deleteMessageById() throws UnsupportedEncodingException
	{
		String messageUuid = request.getParameter("messageUuid");
		try
		{
			messageService.deleteMessageById(messageUuid);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e)
		{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			e.printStackTrace();
		}
		
		logService.saveLog(request, MessageForm.MODEL_NAME, "删除留言列表页面");
		return "ajax-success";
	}
}
