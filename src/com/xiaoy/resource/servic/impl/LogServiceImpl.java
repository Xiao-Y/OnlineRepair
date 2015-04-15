package com.xiaoy.resource.servic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.Log;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.resource.dao.LogDao;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.LogForm;
import com.xiaoy.user.web.form.UserForm;

@Service
@Transactional(readOnly = true)
public class LogServiceImpl implements LogService
{
	@Resource
	private LogDao logDao;
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void saveLog(HttpServletRequest request, String model, String details)
	{
		HttpSession session = request.getSession();
		UserForm userInfo = (UserForm) session.getAttribute("userInfo");
		Log elecLog = new Log();
		elecLog.setIpAddress(request.getRemoteAddr());//远程IP地址
		//ElecUser elecUser = (ElecUser) request.getSession().getAttribute("globle_user");
		//登陆人
		if(userInfo != null)
		{
			elecLog.setOpeName(userInfo.getLoginName());
		}
		elecLog.setOpeTime(new Date());
		//elecLog.setDetails(model + "用户名：【"+ elecUser.getUserName() +"】"+ details);
		elecLog.setDetails(model + "--"+ details);
		logDao.saveObject(elecLog);
	}

	@Override
	public List<LogForm> findLogByCondition(LogForm logForm)
	{
		List<Log> listLog = logDao.findCollectionByCondition(logForm);
		List<LogForm> list = this.logPoListToVoList(listLog);
		return list;
	}
	
	@Override
	public Integer countByCollection(LogForm logForm) {
		Integer count = logDao.countByCollection(logForm);
		return count;
	}
	
	/**
	 * 日志文件的po对象转换成vo对象
	 * @param elecLog	po对象
	 * @return	List &ltElecLogForm&gt
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月30日 下午4:13:12
	 */
	private List<LogForm> logPoListToVoList(List<Log> log)
	{
		List<LogForm> list = null;
		
		if(log != null && log.size() > 0)
		{
			list = new ArrayList<LogForm>();
			for(Log e : log)
			{
				LogForm logForm = new LogForm();
				logForm.setLogID(e.getLogID().toString());
				logForm.setIpAddress(e.getIpAddress());
				logForm.setOpeName(e.getOpeName());
				logForm.setOpeTime(e.getOpeTime() != null ? DateHelper.dateTimeConverString(e.getOpeTime()) : "");
				logForm.setDetails(e.getDetails());
				
				list.add(logForm);
			}
		}
		return list;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void deleteLogByIds(String[] ids)
	{
		logDao.deleteLogByIds(ids);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void deleteLogAll()
	{
		logDao.deleteObjectByCollectionIds("", null);
	}
}
