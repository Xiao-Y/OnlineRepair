package com.xiaoy.resource.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.Notice;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.resource.biz.NoticeService;
import com.xiaoy.resource.dao.NoticeDao;
import com.xiaoy.resource.web.form.NoticeForm;

@Service
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService
{
	@Resource
	private NoticeDao noticeDao;
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	public void saveNotice(NoticeForm noticeForm)
	{
		//PO转换成VO
		Notice entity = this.noticePoToVo(noticeForm);
		//保存公告信息
		noticeDao.saveObject(entity);
	}
	
	
	/**
	 * NoticeForm转换成Notice对象
	 * @param noticeForm	PO对象，页面数据
	 * @return	Notice VO对象，实体类
	 */
	private Notice noticePoToVo(NoticeForm noticeForm)
	{
		Notice entity = new Notice();
		entity.setNoticeTit(noticeForm.getNoticeTit());
		entity.setNotice(noticeForm.getNotice());
		entity.setNoticeName(noticeForm.getNoticeName());
		entity.setNoticeTime(new Date());
		
		return entity;
	}


	@Override
	public List<NoticeForm> getNoticeList()
	{
		List<Notice> noticeList = noticeDao.findNoticeList();
		List<NoticeForm> list = this.NoticeFormListVoToPo(noticeList);
		return list;
	}
	
	/**
	 * 将VO对象转换成PO对象
	 * @param noticeList	VO对象，公告对象
	 * @return	List &ltNoticeForm&gt
	 */
	private List<NoticeForm> NoticeFormListVoToPo(List<Notice> noticeList)
	{
		List<NoticeForm> list = null;
		if(noticeList != null && noticeList.size() > 0)
		{
			list = new ArrayList<NoticeForm>();
			for(Notice n : noticeList)
			{
				NoticeForm nf = new NoticeForm();
				nf.setNoticeUuid(n.getNoticeUuid());
				nf.setNoticeTit(n.getNoticeTit());
				nf.setNotice(n.getNotice());
				nf.setNoticeName(n.getNoticeName());
				nf.setNoticeTime(DateHelper.dateTimeConverString(n.getNoticeTime()));
				
				list.add(nf);
			}
		}
		return list;
	}
}
