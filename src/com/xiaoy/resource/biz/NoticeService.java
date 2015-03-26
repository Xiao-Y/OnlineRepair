package com.xiaoy.resource.biz;

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
}
