package com.xiaoy.audit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.audit.dao.AuditDao;
import com.xiaoy.audit.service.AuditService;
import com.xiaoy.audit.web.form.AuditForm;
import com.xiaoy.base.entites.Audit;
import com.xiaoy.base.entites.DeviceInfo;
import com.xiaoy.base.entites.DeviceState;
import com.xiaoy.base.entites.Evaluate;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.device.dao.DeviceInfoDao;
import com.xiaoy.device.dao.DeviceStateDao;
import com.xiaoy.evaluate.dao.EvaluateDao;
import com.xiaoy.resource.dao.DictionaryDao;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.service.UserService;
import com.xiaoy.user.web.form.UserForm;

@Service
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService
{

	@Resource
	private AuditDao auditDao;

	// 数据字典
	@Resource
	private DictionaryDao dictionaryDao;

	//设置状态dao
	@Resource
	private DeviceStateDao deviceStateDao;

	// 用户信息
	@Resource
	private UserService userService;

	// 评价信息
	@Resource
	private EvaluateDao evaluateDao;
	
	//设备信息
	@Resource
	private DeviceInfoDao deviceInfoDao;

	@Override
	public List<AuditForm> findAuditInfoWaitList(AuditForm auditForm)
	{
		List<Object[]> list = auditDao.findAuditInfoWaitList(auditForm);
		List<AuditForm> formList = this.auditWaitVoToPoList(list);
		return formList;
	}

	/**
	 * 将查询出来的待审核VO转换成PO对象
	 * 
	 * @param list
	 *            待转换的VO集合
	 * @return List&ltAuditForm&gt
	 */
	private List<AuditForm> auditWaitVoToPoList(List<Object[]> list)
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

				// 与审核未通过的重用，多一个字段
				if (o.length > 9)
				{
					auditForm.setAuditTime(o[9] != null ? DateHelper.dateConverString((Date) o[9]) : "");
				}
				formList.add(auditForm);
			}
		}

		return formList;
	}

	@Override
	public int countAuditInfoWait(AuditForm auditForm)
	{
		int count = auditDao.countAuditInfo(auditForm);
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
		auditForm.setOrderTime(o[13] != null ? DateHelper.dateConverString((Date) o[13]) : "");
		if (o[14] != null)
		{
			auditForm.setPriorName(dictionaryDao.findDDLName((String) o[14], DictionaryForm.PRIOR));
		}
		
		auditForm.setDeviceTypeUuid((String)o[15]);

		// 与审核通过的复用
		if (o.length > 16)
		{
			if (!StringUtils.isEmpty((String) o[16]))
			{
				UserForm userForm = userService.findUserByUuid((String) o[16]);
				auditForm.setMaintainUuid(userForm.getUserUuid());
				auditForm.setMaintainName(userForm.getName());
				auditForm.setMaintainTypeCode(userForm.getMaintainTypeCode());
			}

			auditForm.setAuditTime(o[17] != null ? DateHelper.dateConverString((Date) o[17]) : "");
			auditForm.setMaintainStatCode((String) o[18]);
			auditForm.setFailAccount((String) o[19]);
			auditForm.setFinishTime(o[20] != null ? DateHelper.dateConverString((Date) o[20]) : "");
			if (o.length > 21)
			{
				auditForm.setEvaluateUuid((String) o[21]);
			}
		}
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
		//String sd = DateHelper.dateConverString(new Date());
		audit.setAuditTime(new Date());
		// 审核人uuid
		audit.setAuditUserUuid(userInfo.getUserUuid());
		String auditStatCode = auditForm.getAuditStatCode();
		audit.setAuditStatCode(auditStatCode);
		audit.setFailAccount(auditForm.getFailAccount());
		
		DeviceState deviceState = deviceStateDao.findObjectById(auditForm.getDeviceStateUuid());
		deviceState.setStateCode(DictionaryForm.DEVICE_STAT_OK);

		// 当审核通过的时候，添加评价信息，修改设备状态并添加评价记录、修改设备信息故障次数
		if (auditStatCode.equals(DictionaryForm.AUDITSTAT_SUCCESS))
		{
			// 1.修改设备状态信息为异常
			deviceState.setStateCode(DictionaryForm.DEVICE_STAT_EXCEPTION);

			// 2.添加评论信息
			Evaluate evaluate = new Evaluate();
			evaluate.setReportingUuid(auditForm.getReportingUuid());
			evaluate.setReportingUserUuid(auditForm.getReportingUserUuid());
			evaluate.setEvaluateStatCode(DictionaryForm.EVALUATE_STAT_NO);
			evaluate.setDeleteFlag(DictionaryForm.DELETE_FLAG_TRUE);
			
			//3.修改设备信息故障
			DeviceInfo deviceInfo = deviceInfoDao.findObjectById(auditForm.getDeviceTypeUuid());
			deviceInfo.setDeviceNum(deviceInfo.getDeviceNum() == null ? 0 : deviceInfo.getDeviceNum() + 1);

			evaluateDao.saveObject(evaluate);

			// 当审核通过的时候添加维护人员uuid
			audit.setMaintainUuid(auditForm.getMaintainUuid());
			// 当审核通过的时候修改维护状态为待维护
			audit.setMaintainStatCode(DictionaryForm.MAINTAIN_STAT_WAIT);
		}

	}

	@Override
	public List<AuditForm> findAuditInfoPassList(AuditForm auditForm)
	{
		List<Object[]> list = auditDao.findAuditInfoPassList(auditForm);
		List<AuditForm> formList = this.auditPassVoToPoList(list);
		return formList;
	}

	private List<AuditForm> auditPassVoToPoList(List<Object[]> list)
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

				auditForm.setMaintainUuid((String) o[9]);
				if (o[9] != null)
				{
					UserForm userForm = userService.findUserByUuid((String) o[9]);
					auditForm.setMaintainName(userForm.getName());
					auditForm.setMaintainPhone(userForm.getPhone());
				}
				auditForm.setAuditTime(o[10] != null ? DateHelper.dateConverString((Date) o[10]) : "");
				formList.add(auditForm);
			}
		}

		return formList;
	}

	@Override
	public int countAuditInfoPass(AuditForm auditForm)
	{
		int count = auditDao.countAuditInfo(auditForm);
		return count;
	}

	@Override
	public AuditForm findAuditInfoPassByAuditUuid(AuditForm auditForm)
	{
		Object[] object = auditDao.findAuditInfoPassByAuditUuid(auditForm);
		AuditForm aForm = this.auditVoToPo(object, auditForm);
		return aForm;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void auditInfoPassSave(AuditForm auditForm, HttpServletRequest request)
	{
		// 从session中获取登陆用户的信息
		HttpSession session = request.getSession();
		UserForm userInfo = (UserForm) session.getAttribute("userInfo");

		// 审核状态
		String auditStatCode = auditForm.getAuditStatCode();
		// 修改维护人员的uuid、维护状态为未处理、完成时间为空
		Audit audit = auditDao.findObjectById(auditForm.getAuditUuid());
		audit.setAuditUserUuid(userInfo.getUserUuid());

		// 审核通过的
		if (auditStatCode.equals(DictionaryForm.AUDITSTAT_SUCCESS))
		{
			String maintainUuid = auditForm.getMaintainUuid();
			if(!StringUtils.isEmpty(maintainUuid))
			{
				audit.setMaintainUuid(maintainUuid);
			}
			// 维护状态
			String maintainSataCode = auditForm.getMaintainStatCode();
			audit.setMaintainStatCode(maintainSataCode);
			if (DictionaryForm.MAINTAIN_STAT_SUCCESS.equals(maintainSataCode))
			{
				//String str = DateHelper.dateTimeConverString(new Date());
				//添加维护时间和修改设备状态
				audit.setFinishTime(new Date());
				DeviceState deviceState = deviceStateDao.findObjectById(auditForm.getDeviceStateUuid());
				deviceState.setStateCode(DictionaryForm.DEVICE_STAT_OK);
				
			} else
			{
				audit.setFinishTime(null);
			}

			audit.setFailAccount("");
		} else
		// 待审核和驳回
		{
			// ①评价表：删除评价信息
			evaluateDao.deleteObjectByid(auditForm.getEvaluateUuid());
			// ②设备状态表：修改设备状态为正常运行
			DeviceState deviceState = deviceStateDao.findObjectById(auditForm.getDeviceStateUuid());
			deviceState.setStateCode(DictionaryForm.DEVICE_STAT_OK);
			
			// ③修改维护人员的uuid、维护状态、完成时间都为空,修改审核状态
			audit.setMaintainUuid("");
			audit.setMaintainStatCode(DictionaryForm.MAINTAIN_STAT_NO);
			audit.setFinishTime(null);
			audit.setAuditStatCode(auditForm.getAuditStatCode());
			if (auditStatCode.equals(DictionaryForm.AUDITSTAT_FAIL))
			{
				audit.setFailAccount(auditForm.getFailAccount());
			} else
			{
				audit.setFailAccount("");
			}
			
			//3.修改设备信息故障
			DeviceInfo deviceInfo = deviceInfoDao.findObjectById(auditForm.getDeviceTypeUuid());
			int i = deviceInfo.getDeviceNum();
			deviceInfo.setDeviceNum( i == 0 ? 0 : i - 1);

		}
	}

	@Override
	public List<AuditForm> auditInfoRefuseList(AuditForm auditForm)
	{
		List<Object[]> auObjects = auditDao.findAuditInfoRefuseList(auditForm);
		List<AuditForm> auList = this.auditWaitVoToPoList(auObjects);
		return auList;
	}

	@Override
	public int countAuditInfoRefuse(AuditForm auditForm)
	{
		int count = auditDao.countAuditInfo(auditForm);
		return count;
	}

	@Override
	public AuditForm findAuditInfoRefuseByAuditUuid(AuditForm auditForm)
	{
		Object[] object = auditDao.findAuditInfoRefuseByAuditUuid(auditForm);
		AuditForm aForm = this.auditVoToPo(object, auditForm);
		return aForm;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void auditRefuseSave(AuditForm auditForm, HttpServletRequest request)
	{
		// 从session中获取登陆用户的信息
		HttpSession session = request.getSession();
		UserForm userInfo = (UserForm) session.getAttribute("userInfo");

		// 审核状态
		String auditStatCode = auditForm.getAuditStatCode();
		Audit audit = auditDao.findObjectById(auditForm.getAuditUuid());
		audit.setAuditUserUuid(userInfo.getUserUuid());
		audit.setAuditTime(new Date());

		// 审核通过的
		if (auditStatCode.equals(DictionaryForm.AUDITSTAT_SUCCESS))
		{
			//修改为审核通过
			audit.setAuditStatCode(auditStatCode);
			// 添加评价记录
			Evaluate evaluate = new Evaluate();
			evaluate.setReportingUserUuid(auditForm.getReportingUserUuid());
			evaluate.setReportingUuid(auditForm.getReportingUuid());
			evaluateDao.saveObject(evaluate);
			
			//更改设备状态
			DeviceState deviceState = deviceStateDao.findObjectById(auditForm.getDeviceStateUuid());
			deviceState.setStateCode(DictionaryForm.DEVICE_STAT_EXCEPTION);

			audit.setFailAccount("");
			audit.setMaintainStatCode(DictionaryForm.MAINTAIN_STAT_WAIT);
			audit.setMaintainUuid(auditForm.getMaintainUuid());
			
			//3.修改设备信息故障
			DeviceInfo deviceInfo = deviceInfoDao.findObjectById(auditForm.getDeviceTypeUuid());
			int i = deviceInfo.getDeviceNum();
			deviceInfo.setDeviceNum( i + 1);
			
		} else if (auditStatCode.equals(DictionaryForm.AUDITSTAT_WAIT))
		// 待审核
		{
			audit.setAuditStatCode(DictionaryForm.AUDITSTAT_WAIT);

		} else
		// 驳回
		{
			audit.setFailAccount(auditForm.getFailAccount());
		}
	}
}
