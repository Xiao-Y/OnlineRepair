package com.xiaoy.resource.servic;

import java.util.List;

import com.xiaoy.resource.web.form.NoticeForm;

public interface NoticeService
{
	/**
	 * 保存管理员发布的公告<br/>
	 * @param noticeForm	公告页面数据
	 */
	public void saveNotice(NoticeForm noticeForm);

	/**
	 * 查询出所有的公告信息
	 * @return	List &ltNoticeForm&gt
	 */
	public List<NoticeForm> getNoticeList();

	/**
	 * 根据id删除公告信息
	 * @param id	公告的id
	 */
	public void deleteNotice(String id);
}
