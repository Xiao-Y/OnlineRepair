package com.xiaoy.evaluate.web.form;

import java.util.Date;

/**
 * 留言管理
 * 
 * @author XiaoY
 * @date: 2015年5月5日 上午12:12:52
 */
public class MessageForm
{
	public static final String MODEL_NAME = "【评价留言管理】-【留言管理】";
	
	/* 留言uuid */
	private String messageUuid;
	/* 标题 */
	private String titleMes;
	/* 回复内容 */
	private String replyMes;
	/* 回复状态 */
	private String replySata;
	/* 回复状态名称 */
	private String replySateName;
	/* 留言时间 */
	private Date messageTime;
	/* 留言内容 */
	private String messageMes;

	/* 已回复的标识量 */
	private String flag;

	public String getMessageUuid()
	{
		return messageUuid;
	}

	public void setMessageUuid(String messageUuid)
	{
		this.messageUuid = messageUuid;
	}

	public String getTitleMes()
	{
		return titleMes;
	}

	public void setTitleMes(String titleMes)
	{
		this.titleMes = titleMes;
	}

	public String getReplyMes()
	{
		return replyMes;
	}

	public void setReplyMes(String replyMes)
	{
		this.replyMes = replyMes;
	}

	public String getReplySata()
	{
		return replySata;
	}

	public void setReplySata(String replySata)
	{
		this.replySata = replySata;
	}

	public Date getMessageTime()
	{
		return messageTime;
	}

	public void setMessageTime(Date messageTime)
	{
		this.messageTime = messageTime;
	}

	public String getMessageMes()
	{
		return messageMes;
	}

	public void setMessageMes(String messageMes)
	{
		this.messageMes = messageMes;
	}

	public String getReplySateName()
	{
		return replySateName;
	}

	public void setReplySateName(String replySateName)
	{
		this.replySateName = replySateName;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}
}
