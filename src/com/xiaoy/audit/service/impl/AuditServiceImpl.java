package com.xiaoy.audit.service.impl;

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

import com.xiaoy.audit.dao.AuditDao;
import com.xiaoy.audit.service.AuditService;
import com.xiaoy.audit.web.form.AuditForm;
import com.xiaoy.base.entites.Audit;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.device.servic.DeviceStateService;
import com.xiaoy.evaluate.service.EvaluateService;
import com.xiaoy.evaluate.web.EvaluateForm;
import com.xiaoy.resource.dao.DictionaryDao;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.web.form.UserForm;

@Service
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService
{

	public static final String MENU_MODEL = "【故障申报审核】--【添加故障审核】";

	/**
	 * 设备状态为异常
	 */
	public static final String STAT_EXCEPTION = "2";

	/**
	 * 审核：通过
	 */
	public static final String AUDITSTAT_SUCCESS = "2";

	@Resource
	private AuditDao auditDao;

	// 数据字典
	@Resource
	private DictionaryDao dictionaryDao;

	// 设备状态
	@Resource
	private DeviceStateService deviceStateService;

	// 评价信息
	@Resource
	private EvaluateService evaluateService;

	@Override
	public List<AuditForm> findAuditInfoWaitList(AuditForm auditForm)
	{
		List<Object[]> list = auditDao.findAuditInfoWaitList(auditForm);
		List<AuditForm> formList = this.auditVoToPoList(list);
		return formList;
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	private List<AuditForm> auditVoToPoList(List<Object[]> list)
	{
		List<AuditForm> formList = null;
		if (!list.isEmpty())
		{
			formList = new ArrayList<AuditForm>();
			for (Object[] o : list)
			{
				AuditForm auditForm = new AuditForm();
				auditForm.setAreaCode((String) o[0]);
				if (o[0] != null)
				{
					auditForm.setAreaName(dictionaryDao.findDDLName((String) o[0], DictionaryForm.AREA_NAME));
				}
				auditForm.setInstallationSiteCode((String) o[1]);
				if (o[1] != null)
				{
					auditForm.setInstallationSiteName(dictionaryDao.findDDLName((String) o[1], DictionaryForm.INSTALLATION_SITE_NAME));
				}
				auditForm.setDeviceName((String) o[2]);
				auditForm.setName((String) o[3]);
				auditForm.setReportingPhone((String) o[4]);
				auditForm.setReportingTime(o[5] != null ? DateHelper.dateConverString((Date) o[5]) : "");
				auditForm.setReportingUuid((String) o[6]);
				auditForm.setReportingUserUuid((String) o[7]);
				auditForm.setAuditUuid((String) o[8]);
				formList.add(auditForm);
			}
		}

		return formList;
	}

	@Override
	public int countAuditInfoWait(AuditForm auditForm)
	{
		int count = auditDao.countAuditInfoWait(auditForm);
		return count;
	}

	@Override
	public AuditForm findAuditInfoWaitByAuditUuid(AuditForm auditForm)
	{
		Object[] object = auditDao.findAuditInfoWaitByAuditUuid(auditForm);
		AuditForm aForm = this.auditVoToPo(object, auditForm);
		return aForm;
	}

	/**
	 * 将查询出来的Vo对象转换成Po对象
	 * 
	 * @param object
	 *            Vo对象
	 * @return Po对象
	 */
	private AuditForm auditVoToPo(Object[] o, AuditForm auditForm)
	{
		auditForm.setAreaCode((String) o[0]);
		if (o[0] != null)
		{
			auditForm.setAreaName(dictionaryDao.findDDLName((String) o[0], DictionaryForm.AREA_NAME));
		}
		auditForm.setInstallationSiteCode((String) o[1]);
		if (o[1] != null)
		{
			auditForm.setInstallationSiteName(dictionaryDao.findDDLName((String) o[1], DictionaryForm.INSTALLATION_SITE_NAME));
		}
		auditForm.setDeviceName((String) o[2]);
		auditForm.setName((String) o[3]);
		auditForm.setReportingPhone((String) o[4]);
		auditForm.setReportingTime(o[5] != null ? DateHelper.dateConverString((Date) o[5]) : "");
		auditForm.setVersion((String) o[6]);
		auditForm.setAccount((String) o[7]);
		auditForm.setRemark((String) o[8]);
		auditForm.setDeviceStateUuid((String) o[9]);
		auditForm.setReportingUuid((String) o[10]);
		auditForm.setReportingUserUuid((String) o[11]);
		auditForm.setDevicePicUrl((String) o[12]);

		return auditForm;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void auditInfoWaitSave(AuditForm auditForm, HttpServletRequest request)
	{
		// 从session中获取登陆用户的信息
		HttpSession session = request.getSession();
		UserForm userInfo = (UserForm) session.getAttribute("userInfo");

		// 3.修改审核信息，添加审核时间、审核人。如果驳回，添加驳回信息
		// 在同一个事务中，直接查询出来修改，提交就更新了
		Audit audit = auditDao.findObjectById(auditForm.getAuditUuid());
		String sd = DateHelper.dateConverString(new Date());
		audit.setAuditTime(DateHelper.stringConverDate(sd));
		// 审核人uuid
		audit.setAuditUserUuid(userInfo.getUserUuid());
		String auditStatCode = auditForm.getAuditStatCode();
		audit.setAuditStatCode(auditStatCode);
		audit.setFailAccount(auditForm.getFailAccount());

		// 当审核通过的时候，添加评价信息，修改设备状态并添加评价记录
		if (auditStatCode.equals(AUDITSTAT_SUCCESS))
		{
			// 1.修改设备状态信息为异常
			deviceStateService.deviceStateUpdateSatae(auditForm.getDeviceStateUuid(), STAT_EXCEPTION);

			// 2.添加评论信息
			EvaluateForm eForm = new EvaluateForm();
			eForm.setReportingUuid(auditForm.getReportingUuid());
			eForm.setReportingUserUuid(auditForm.getReportingUserUuid());

			evaluateService.createEvaluate(eForm);

			// 当审核通过的时候添加维护人员uuid
			audit.setMaintainUuid(auditForm.getMaintainTypeUuid());
		}

	}

	// @Resource
	// private AuditDao auditDao;
	//
	// @Override
	// @Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	// public void saveAudit(Reporting reporting) {
	// Audit entity = this.reportingToAudit(reporting);
	// auditDao.saveObject(entity);
	// }
	//
	// /**
	// * 从申报故障信息类中获取申报uuid和用户uuid.
	// * 固定系统审核状为1时表示待审核。维护状态为0时表示，还未审核。
	// * @param reporting
	// * @return
	// */
	// private Audit reportingToAudit(Reporting reporting) {
	// Audit entity = new Audit();
	// entity.setReportingUuid(reporting.getReportingUuid());
	// entity.setUserUuid(reporting.getUser().getUserUuid());
	//
	// //1表示未维护
	// entity.setMaintainStatCode("1");
	// //1表示未处理
	// entity.setAuditStatCode("1");
	//
	// return entity;
	// }

}
