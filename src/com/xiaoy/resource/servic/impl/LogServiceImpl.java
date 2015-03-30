package com.xiaoy.resource.servic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.Log;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.resource.dao.LogDao;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.LogForm;

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
		Log elecLog = new Log();
		elecLog.setIpAddress(request.getRemoteAddr());//远程IP地址
		//ElecUser elecUser = (ElecUser) request.getSession().getAttribute("globle_user");
		//登陆人
		elecLog.setOpeName("");
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
	
//	@Override
//	public List<ElecLogForm> findElecLogByCondition(ElecLogForm elecLogForm,HttpServletRequest request)
//	{
//		StringBuffer hqlWhere = new StringBuffer("");
//		Map<String, Object> paramsMap = new HashMap<String, Object>();
//		if(elecLogForm != null && !StringUtils.isEmpty(elecLogForm.getOpeName()))
//		{
//			hqlWhere.append(" and e.opeName like :opeName ");
//			paramsMap.put("opeName", "%" + elecLogForm.getOpeName() + "%");
//		}
//		hqlWhere.append(" order by e.opeTime desc ");
//		//List<ElecLog> elecLog = elecLogDao.findCollectionByConditionNoPage(hqlWhere, paramsMap);
//		PageInfo pageInfo = new PageInfo(request);
//		List<ElecLog> elecLog = elecLogDao.findCollectionByConditionWithPage(hqlWhere, paramsMap, pageInfo);
//		request.setAttribute("page", pageInfo.getPageBean());
//		
//		List<ElecLogForm> list = this.elecLogPoListToVoList(elecLog);
//		return list;
//	}

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
		
		if(log != null)
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
		Map<String, Object> mapIds = new HashMap<String, Object>();
		mapIds.put("logID", ids);
		
		logDao.deleteObjectByCollectionIds(ids);
	}
}
