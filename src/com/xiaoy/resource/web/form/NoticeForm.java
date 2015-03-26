package com.xiaoy.resource.web.form;


/**
 * 公告信息的实体类<br/>
 * 用于存放管理员发布的公告<br/>
 * 
 * @author XiaoY
 * @explain
 * 
 * @date: 2015年3月25日 下午10:09:09
 */
public class NoticeForm
{
	/* 公告uuid */
	private String noticeUuid;
	
	/* 公告标题 */
	private String noticeTit;
	
	/* 公告信息 */
	private String notice;
	
	/* 公告时间 */
	private String noticeTime;
	
	/* 公告人 */
	private String noticeName;

	
	public String getNoticeUuid()
	{
		return noticeUuid;
	}

	public void setNoticeUuid(String noticeUuid)
	{
		this.noticeUuid = noticeUuid;
	}

	public String getNoticeTit()
	{
		return noticeTit;
	}

	public void setNoticeTit(String noticeTit)
	{
		this.noticeTit = noticeTit;
	}

	public String getNotice()
	{
		return notice;
	}

	public void setNotice(String notice)
	{
		this.notice = notice;
	}

	public String getNoticeTime()
	{
		return noticeTime;
	}

	public void setNoticeTime(String noticeTime)
	{
		this.noticeTime = noticeTime;
	}

	public String getNoticeName()
	{
		return noticeName;
	}

	public void setNoticeName(String noticeName)
	{
		this.noticeName = noticeName;
	}

}
