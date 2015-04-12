package com.xiaoy.reporting.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class ReportingImpl implements ReportingService
{

	/**
	 * 故障设备图片
	 */
	private static final String REPORTING_IMAGE_URL = "reportingUploadImages";
	
	private static final String MENU_MODEL = "【申报故障管理】--【申报故障】";
	
	//注入日志
	@Resource
	private LogService logService;
	
	@Resource
	private ReportingDao reportingDao;
	
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
		reportingDao.saveObject(entity);;
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
	
}
