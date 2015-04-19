package com.xiaoy.resource.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.servic.NoticeService;
import com.xiaoy.resource.web.form.NoticeForm;
import com.xiaoy.user.web.form.UserForm;

@SuppressWarnings("serial")
@Controller
public class NoticeAction extends BaseAction implements ModelDriven<NoticeForm>
{

	private final static String MENU_MODEL = "【公告管理】";
	
	@Resource
	private NoticeService noticeService;

	// 日志信息
	@Resource
	private LogService logService;

	private NoticeForm noticeForm = new NoticeForm();

	@Override
	public NoticeForm getModel()
	{
		return noticeForm;
	}

	// 要删除的公告id
	private String id;

	// 获得输入流
	private InputStream inputStream;

	/**
	 * 显示公告信息列表，及编辑页面
	 * 
	 * @return
	 */
	public String noticeIndex()
	{
		List<NoticeForm> list = noticeService.getNoticeList();
		request.setAttribute("commonList", list);
		logService.saveLog(request, MENU_MODEL, "查看公告列表");
		return "noticeIndex";
	}

	/**
	 * 保存公告信息
	 * 
	 * @return
	 */
	public String saveNotice()
	{
		HttpSession session = request.getSession();
		UserForm userInfo = (UserForm) session.getAttribute("userInfo");
		noticeForm.setNoticeName(userInfo.getName());
		noticeService.saveNotice(noticeForm);
		logService.saveLog(request, MENU_MODEL, "添加公告信息");
		return "saveNotice";
	}

	/**
	 * 根据id删除公告信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String deleteNotice() throws UnsupportedEncodingException
	{
		try
		{
			noticeService.deleteNotice(id);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e)
		{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			e.printStackTrace();
		}
		logService.saveLog(request, MENU_MODEL, "删除公告信息");
		return "ajax-success";
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public InputStream getInputStream()
	{
		return inputStream;
	}
}
