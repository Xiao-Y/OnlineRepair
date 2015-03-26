package com.xiaoy.resource.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.action.BaseAction;
import com.xiaoy.resource.biz.NoticeService;
import com.xiaoy.resource.web.form.NoticeForm;

@SuppressWarnings("serial")
@Controller
public class NoticeAction extends BaseAction implements ModelDriven<NoticeForm>
{

	@Resource
	private NoticeService noticeService;

	private NoticeForm noticeForm = new NoticeForm();

	@Override
	public NoticeForm getModel()
	{
		return noticeForm;
	}

	public String noticeIndex()
	{
		List<NoticeForm> list = noticeService.getNoticeList();
		request.setAttribute("commonList", list);
		return "noticeIndex";
	}
	
	public String saveNotice()
	{
		noticeService.saveNotice(noticeForm);
		return "saveNotice";
	}

}
