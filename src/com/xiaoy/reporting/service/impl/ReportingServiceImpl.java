package com.xiaoy.reporting.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.audit.service.AuditService;
import com.xiaoy.base.entites.DeviceState;
import com.xiaoy.base.entites.Reporting;
import com.xiaoy.base.entites.User;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.base.util.UploadImageHelper;
import com.xiaoy.reporting.dao.ReportingDao;
import com.xiaoy.reporting.service.ReportingService;
import com.xiaoy.reporting.web.form.ReportingForm;
import com.xiaoy.resource.servic.LogService;

@Service
@Transactional(readOnly=true)
public class ReportingServiceImpl implements ReportingService
{

	/**
	 * 故障设备图片
	 */
	private static final String REPORTING_IMAGE_URL = "upload/reportingUploadImages";
	
	private static final String MENU_MODEL = "【申报故障管理】--【申报故障】";
	
	//注入日志
	@Resource
	private LogService logService;
	
	//故障审核
	@Resource
	private AuditService auditService;
	
	@Resource
	private ReportingDao reportingDao;
	
	/**
	 * 分为两步：<br/>
	 * 1.保存故障申报信息<br/>
	 * 2.添加故障审核信息<br/>
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void reportingBugInfoSave(ReportingForm reportingForm, HttpServletRequest request)
	{
		if (reportingForm.getImage() != null) 
        {
        	//上传图片
        	UploadImageHelper.uploadImage(reportingForm, REPORTING_IMAGE_URL);
        	logService.saveLog(request, MENU_MODEL, "添加故障设备“"+ reportingForm.getDeviceName()+"”图片");
        }
        if(!StringUtils.isEmpty(reportingForm.getNewFileName()))
        {
        	reportingForm.setDevicePicUrl(UploadImageHelper.PICURL);
        }
		Reporting entity = this.reportingBugInfoPoToVo(reportingForm);
		//保存设备申报信息
		reportingDao.saveObject(entity);
		//添加故障审核信息
		auditService.saveAudit(entity);
	}

	/**
	 * 将申报信息的PO对象转换成VO对象
	 * @param reportingForm		PO对象
	 * @return	VO对象
	 */
	private Reporting reportingBugInfoPoToVo(ReportingForm reportingForm)
	{
		Reporting entity = new Reporting();
		entity.setAccount(reportingForm.getAccount());
		entity.setDevicePicUrl(reportingForm.getDevicePicUrl());
		
		DeviceState deviceState = new DeviceState();
		deviceState.setDeviceStateUuid(reportingForm.getDeviceStateUuid());
		entity.setDeviceState(deviceState);
		
		entity.setOrderTime(!StringUtils.isEmpty(reportingForm.getOrderTime()) ? DateHelper.stringConverDate(reportingForm.getOrderTime()) : null);
		entity.setPriorCode(reportingForm.getPriorCode());
		entity.setRemark(reportingForm.getRemark());
		entity.setReportingPhone(reportingForm.getReportingPhone());
		entity.setReportingTime(new Date());
		entity.setReportingUuid(reportingForm.getReportingUuid());
		
		User user = new User();
		//TODO
		//user.setUserUuid(reportingForm.getUserUuid());
		user.setUserUuid("40283f824c8e0f10014c8e10a0020000");
		entity.setUser(user);
		
		return entity;
	}

	@Override
	public List<ReportingForm> findReportingBugInfoList(ReportingForm reportingForm) {
		
		List<Object[]> object = reportingDao.findReportingBugInfoList(reportingForm);
		
		return this.reportingBugInfoVoToPoList(object);
	}

	/**
	 * 故障申报信息集合VO对象转换为PO对象
	 * @param form
	 * @return
	 */
	private List<ReportingForm> reportingBugInfoVoToPoList(List<Object[]> object) {
		List<ReportingForm> form = null;
		if(object != null && object.size() > 0)
		{
			form = new ArrayList<ReportingForm>();
			
			for(Object[] o : object)
			{
				ReportingForm r = new ReportingForm();
				r.setAreaCode((String)o[0]);
				r.setInstallationSiteCode((String)o[1]);
				r.setDeviceName((String)o[2]);
				r.setName((String)o[3]);
				r.setReportingPhone((String)o[4]);
				r.setReportingTime((String)o[5]);
				r.setMaintainStatCode((String)o[6]);
				r.setEvaluateStatCode((String)o[7]);
				r.setAuditStatCode((String)o[8]);
				r.setMaintainTypeCode((String)o[9]);
				
				form.add(r);
			}
		}
		return form;
	}

	@Override
	public int countReportingBugInfo(ReportingForm reportingForm)
	{
		int count = reportingDao.countReportingBugInfo(reportingForm);
		return count;
	}
}
