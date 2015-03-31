package com.xiaoy.resource.web.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.action.BaseAction;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.LogForm;

@SuppressWarnings("serial")
@Controller
public class LogAction extends BaseAction implements ModelDriven<LogForm>
{
	@Resource
	private LogService logService;

	private LogForm logForm = new LogForm();

	@Override
	public LogForm getModel()
	{
		return logForm;
	}

	// 获得输入流
	private InputStream inputStream;

	public InputStream getInputStream()
	{
		return inputStream;
	}
	
	/**
	 * 显示日志信息列表
	 * 
	 * @return
	 */
	public String logIndex()
	{
		List<LogForm> list = logService.findLogByCondition(logForm);
		request.setAttribute("formList", list);
		return "logIndex";
	}

	/**
	 * 删除日志信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String deleteLog()
	{
		String[] ids = logForm.getLogId();
		logService.deleteLogByIds(ids);
		return "logIndex";
	}
}
