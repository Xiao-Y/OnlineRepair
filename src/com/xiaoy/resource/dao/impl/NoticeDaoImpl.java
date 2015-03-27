package com.xiaoy.resource.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.Notice;
import com.xiaoy.resource.dao.NoticeDao;

@Repository
public class NoticeDaoImpl extends CommonImpl<Notice> implements NoticeDao
{

	@Override
	public List<Notice> findNoticeList()
	{
		String hqlWhere = " ORDER BY noticeTime DESC";
		List<Notice> list = this.findObject(hqlWhere);
		
		return list;
	}
}
