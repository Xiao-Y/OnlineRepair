package com.xiaoy.resource.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.Notice;

public interface NoticeDao extends Common<Notice>
{
	
	/**
	 * 不带分页的查询，查询出所有的公告信息
	 * @return	List &ltNotice&gt
	 */
	List<Notice> findNoticeList();

}
