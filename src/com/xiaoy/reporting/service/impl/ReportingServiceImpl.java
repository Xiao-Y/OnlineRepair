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

import com.xiaoy.audit.dao.AuditDao;
import com.xiaoy.audit.web.form.AuditForm;
import com.xiaoy.base.entites.Audit;
import com.xiaoy.base.entites.DeviceState;
import com.xiaoy.base.entites.Reporting;
import com.xiaoy.base.entites.User;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.base.util.UploadImageHelper;
import com.xiaoy.evaluate.dao.EvaluateDao;
import com.xiaoy.reporting.dao.ReportingDao;
import com.xiaoy.reporting.service.ReportingService;
import com.xiaoy.reporting.web.form.ReportingForm;
import com.xiaoy.resource.dao.DictionaryDao;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.dao.UserDao;

@Service
@Transactional(readOnly = true)
public class ReportingServiceImpl implements ReportingService
{
	/**
	 * 故障设备图片
	 */
	private static final String REPORTING_IMAGE_URL = "upload/reportingUploadImages";

	private static final String MENU_MODEL = "【申报故障管理】--【申报故障】";

	// 注入日志
	@Resource
	private LogService logService;

	// //故障审核
	// @Resource
	// private AuditService auditService;
	// 故障审核
	@Resource
	private AuditDao auditDao;

	// 评价信息
	@Resource
	private EvaluateDao evaluateDao;

	// 数据字典
	@Resource
	private DictionaryDao dictionaryDao;

	// 用户信息
	@Resource
	private UserDao userDao;

	@Resource
	private ReportingDao reportingDao;

	/**
	 * 分为两步：<br/>
	 * 1.保存故障申报信息<br/>
	 * 2.添加故障审核信息<br/>
	 */
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void reportingBugInfoSave(ReportingForm reportingForm, HttpServletRequest request)
	{
		if (reportingForm.getImage() != null)
		{
			// 上传图片
			UploadImageHelper.uploadImage(reportingForm, REPORTING_IMAGE_URL);
			logService.saveLog(request, MENU_MODEL, "添加故障设备“" + reportingForm.getDeviceName() + "”图片");
		}
		if (!StringUtils.isEmpty(reportingForm.getNewFileName()))
		{
			reportingForm.setDevicePicUrl(UploadImageHelper.PICURL);
		}
		Reporting entity = this.reportingBugInfoPoToVo(reportingForm);
		// 保存设备申报信息
		reportingDao.saveObject(entity);
		logService.saveLog(request, MENU_MODEL, "添加故障设备");
		// 添加故障审核信息
		Audit audit = this.reportingToAudit(entity);
		auditDao.saveObject(audit);
		logService.saveLog(request,  AuditForm.AUDIT_WAIT, "添加“" + reportingForm.getDeviceName() + "”故障审核");
	}

	/**
	 * 将申报信息的PO对象转换成VO对象
	 * 
	 * @param reportingForm
	 *            PO对象
	 * @return VO对象
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
		String strd = DateHelper.dateConverString(new Date());
		entity.setReportingTime(DateHelper.stringConverDate(strd));
		entity.setReportingUuid(reportingForm.getReportingUuid());

		User user = new User();
		user.setUserUuid(reportingForm.getUserUuid());
		entity.setUser(user);

		return entity;
	}

	/**
	 * 从申报故障信息类中获取申报uuid和用户uuid. 固定系统审核状为1时表示待审核。维护状态为0时表示，还未审核。
	 * 
	 * @param reporting
	 * @return
	 */
	private Audit reportingToAudit(Reporting reporting)
	{
		Audit entity = new Audit();
		entity.setReportingUuid(reporting.getReportingUuid());
		entity.setAuditUserUuid(reporting.getUser().getUserUuid());

		// 1表示未维护
		entity.setMaintainStatCode("1");
		// 1表示未处理
		entity.setAuditStatCode("1");

		return entity;
	}

	@Override
	public List<ReportingForm> findReportingBugInfoList(ReportingForm reportingForm)
	{

		List<Object[]> object = reportingDao.findReportingBugInfoList(reportingForm);

		return this.reportingBugInfoVoToPoList(object);
	}

	/**
	 * 故障申报信息集合VO对象转换为PO对象
	 * 
	 * @param form
	 * @return
	 */
	private List<ReportingForm> reportingBugInfoVoToPoList(List<Object[]> object)
	{
		List<ReportingForm> form = null;
		if (object != null && object.size() > 0)
		{
			form = new ArrayList<ReportingForm>();

			for (Object[] o : object)
			{
				ReportingForm r = new ReportingForm();
				r.setAreaCode((String) o[0]);
				if (o[0] != null)
				{
					r.setAreaName(dictionaryDao.findDDLName((String) o[0], DictionaryForm.AREA_NAME));
				}
				r.setInstallationSiteCode((String) o[1]);
				if (o[1] != null)
				{
					r.setInstallationSiteName(dictionaryDao.findDDLName((String) o[1], DictionaryForm.INSTALLATION_SITE_NAME));
				}
				r.setDeviceName((String) o[2]);
				r.setName((String) o[3]);
				r.setReportingPhone((String) o[4]);
				r.setReportingTime(o[5] != null ? DateHelper.dateConverString((Date) o[5]) : "");

				// 以下3个是由于初始化的时候系统添加的
				// 当维护状态为0时则“未维护”
				r.setMaintainStatCode((String) o[6]);
				if (o[6] != null)
				{
					// if(o[6].equals("0")){
					// r.setMaintainStatName("未维护");
					// }else{
					r.setMaintainStatName(dictionaryDao.findDDLName((String) o[6], DictionaryForm.MAINTAIN_STAT));
					// }
				}
				// //当评价状态为0时则“未评价”
				// r.setEvaluateStatCode((String)o[7]);
				// if(o[7] != null){
				// if(o[7].equals("0")){
				// r.setEvaluateStatName("未评价");
				// }else{
				// r.setEvaluateStatName(dictionaryDao.findDDLName((String)o[7], DictionaryForm.EVALUATE_STAT));
				// }
				// }
				// 当审核状态为0时则“待审核”
				r.setAuditStatCode((String) o[7]);
				if (o[7] != null)
				{
					// if(o[7].equals("0")){
					// r.setAuditStatName("待审核");
					// }else {
					r.setAuditStatName(dictionaryDao.findDDLName((String) o[7], DictionaryForm.AUDIT_STAT));
					// }
				}

				r.setMaintainTypeCode((String) o[8]);
				if (o[8] != null)
				{
					r.setMaintainTypeName(dictionaryDao.findDDLName((String) o[8], DictionaryForm.MAINTAIN_TYPE_NAME));
				}
				r.setReportingUuid((String) o[9]);
				r.setDeviceTypeUuid((String) o[10]);
				r.setUserUuid((String) o[11]);
				r.setAuditUuid((String) o[12]);
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

	@Override
	public ReportingForm findReportingBugInfoByRrUuid(String reportingUuid)
	{
		List<Object[]> list = reportingDao.findReportingBugInfoByRrUuid(reportingUuid);
		return this.reportingBugInfoListToPo(list);
	}

	/**
	 * Object对象转换成为PO对象
	 * 
	 * @param object
	 * @return
	 */
	private ReportingForm reportingBugInfoListToPo(List<Object[]> object)
	{
		ReportingForm r = null;

		if (object != null && object.size() > 0)
		{
			for (Object[] o : object)
			{
				r = new ReportingForm();
				r.setAreaCode((String) o[0]);
				if (o[0] != null)
				{
					r.setAreaName(dictionaryDao.findDDLName((String) o[0], DictionaryForm.AREA_NAME));
				}
				r.setInstallationSiteCode((String) o[1]);
				if (o[1] != null)
				{
					r.setInstallationSiteName(dictionaryDao.findDDLName((String) o[1], DictionaryForm.INSTALLATION_SITE_NAME));
				}
				r.setDeviceName((String) o[2]);
				r.setName((String) o[3]);
				r.setReportingPhone((String) o[4]);
				r.setReportingTime(o[5] != null ? DateHelper.dateConverString((Date) o[5]) : "");

				r.setMaintainStatCode((String) o[6]);
				if (o[6] != null)
				{
					r.setMaintainStatName(dictionaryDao.findDDLName((String) o[6], DictionaryForm.MAINTAIN_STAT));
				} else
				{
					r.setMaintainStatName("");
				}
				r.setAuditStatCode((String) o[7]);
				if (o[7] != null)
				{
					r.setAuditStatName(dictionaryDao.findDDLName((String) o[7], DictionaryForm.AUDIT_STAT));
				}

				// r.setMaintainTypeCode((String)o[8]);
				// if(o[8] != null){
				// r.setMaintainTypeName(dictionaryDao.findDDLName((String)o[8], DictionaryForm.MAINTAIN_TYPE_NAME));
				// }else
				// {
				// r.setMaintainTypeName("");
				// }

				r.setVersion((String) o[9]);
				r.setAuditTime(o[10] != null ? DateHelper.dateConverString((Date) o[10]) : "");
				r.setFinishTime(o[11] != null ? DateHelper.dateConverString((Date) o[11]) : "");
				r.setDevicePicUrl((String) o[12]);
				r.setAccount((String) o[13]);
				r.setRemark((String) o[14]);
				if (o[15] != null)
				{
					User user = userDao.findObjectById((String) o[15]);
					r.setMaintainUserName(user.getName());
					r.setMaintainPhone(user.getPhone());
					r.setMaintainTypeName(dictionaryDao.findDDLName(user.getMaintainTypeCode(), DictionaryForm.MAINTAIN_TYPE_NAME));
				} else
				{
					r.setMaintainUserName("");
					r.setMaintainTypeName("");
					r.setMaintainPhone("");
				}
				r.setFailAccount((String) o[16]);
				r.setOrderTime(o[17] != null ? DateHelper.dateConverString((Date) o[17]) : "");
				// r.setMaintainUuid((String)o[15]);
			}
		}
		return r;
	}

	/**
	 * 1、待审核的。只用删除申报信息。<br/>
	 * 2、审核通过。删除审核通过的申报信息，将会删除审核信息和评价信息<br/>
	 * 3、审核未通过。删除审核未通过的申报信息，将会删除审核信息<br/>
	 */
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteReportingBugInfo(String reportingUuid, String auditStatCode, String auditUuid)
	{
		// 待审核的。只用删除申报信息。
		if (auditStatCode.equals("1"))
		{
			// 删除申报信息
			reportingDao.deleteObjectByid(reportingUuid);
		}
		// 审核通过。删除审核通过的申报信息，将会删除审核信息和评价信息
		else if (auditStatCode.equals("2"))
		{
			// 删除审核信息
			auditDao.deleteObjectByid(auditUuid);
			// 删除评价信息
			reportingDao.deleteAuditByReportingUuid(reportingUuid);
			// 删除申报信息
			reportingDao.deleteObjectByid(reportingUuid);
		}
		// 审核未通过。删除审核未通过的申报信息，将会删除审核信息<br/>
		else if (auditStatCode.equals("3"))
		{
			// 删除审核信息
			auditDao.deleteObjectByid(auditUuid);
			// 删除申报信息
			reportingDao.deleteObjectByid(reportingUuid);
		}
	}
}
