package com.xiaoy.audit.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.audit.service.AuditService;
import com.xiaoy.audit.web.form.AuditForm;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.device.servic.DeviceStateService;
import com.xiaoy.device.web.form.DeviceStateForm;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.service.UserService;
import com.xiaoy.user.web.form.UserForm;

@SuppressWarnings("serial")
public class AuditInfoAction extends BaseAction implements ModelDriven<AuditForm>
{
	public final static String AUDIT_WAIT = "【故障申报审核】--【待审核】";

	@Resource
	private AuditService auditService;

	@Resource
	private DictionaryService dictionaryService;

	// 设备状态信息
	@Resource
	private DeviceStateService deviceStateService;

	// 维护类别
	@Resource
	private UserService userService;

	// 日志
	@Resource
	private LogService logService;

	// 获取输入流
	private InputStream inputStream;

	public InputStream getInputStream()
	{
		return inputStream;
	}

	private AuditForm auditForm = new AuditForm();

	@Override
	public AuditForm getModel()
	{
		return this.auditForm;
	}

	/**
	 * 待审核列表
	 * 
	 * @return
	 */
	public String auditInfoWaitList()
	{
		// 在设备状态表中查询出设备的区域
		List<DeviceStateForm> area = deviceStateService.findDeviceArea();
		request.setAttribute("area", area);
		// 发送安装位置到页面
		List<DeviceStateForm> installationSite = deviceStateService.findInstallationSiteByArea("");
		request.setAttribute("installationSite", installationSite);
		// 设备名
		List<DeviceStateForm> deviceName = deviceStateService.findDeviceNameByinstallationSite("", "");
		request.setAttribute("deviceName", deviceName);

		List<AuditForm> list = auditService.findAuditInfoWaitList(auditForm);
		request.setAttribute("auditList", list);

		int countAudit = auditService.countAuditInfoWait(auditForm);
		auditForm.setRecordCount(countAudit);
		logService.saveLog(request, AUDIT_WAIT, "进入待审核列表");
		return "auditInfoWaitList";
	}

	/**
	 * 待审核中修改审核状态
	 * 
	 * @return
	 */
	public String auditInfoWaitEdit()
	{
		// 查询出来维护类别
		List<DictionaryForm> diList = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.MAINTAIN_TYPE_NAME);
		request.setAttribute("diList", diList);
		// 查询出来审核状态
		List<DictionaryForm> auditStat = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.AUDIT_STAT);
		request.setAttribute("auditStat", auditStat);

		AuditForm auForms = auditService.findAuditInfoWaitByAuditUuid(auditForm);
		ActionContext.getContext().getValueStack().push(auForms);
		logService.saveLog(request, AUDIT_WAIT, "修改“ " + auForms.getDeviceName() + " ”的待审核状态");
		return "auditInfoWaitEdit";
	}

	/**
	 * 通过维护类型查找出现用户信息，发送到页面上
	 * 
	 * @return
	 */
	public String auditInfoWaitUser()
	{
		String maintainTypeCode = request.getParameter("maintainTypeCode");
		if (!StringUtils.isEmpty(maintainTypeCode))
		{
			List<UserForm> userForm = userService.findUserByMaintainTypeCode(maintainTypeCode);
			Gson gson = new Gson();
			String json = gson.toJson(userForm);
			try
			{
				// 带有中文
				inputStream = new ByteArrayInputStream(json.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		return "ajax-success";
	}

	/**
	 * 保存编辑后的待审核信息<br/>
	 * 1.修改设备状态信息为异常<br/>
	 * 2.添加评论信息。<br/>
	 * 3.修改审核信息，添加审核时间、审核人。如果驳回，添加驳回信息<br/>
	 * 
	 * @return
	 */
	public String auditInfoWaitSave()
	{
		auditService.auditInfoWaitSave(auditForm, request);
		logService.saveLog(request, AUDIT_WAIT, "保存“ " + auditForm.getDeviceName() + " ”待审核列表");
		return "successWait";
	}

	/**
	 * 审核通过的列表
	 * 
	 * @return
	 */
	public String auditInfoPassList()
	{
		// 在设备状态表中查询出设备的区域
		List<DeviceStateForm> area = deviceStateService.findDeviceArea();
		request.setAttribute("area", area);
		// 发送安装位置到页面
		List<DeviceStateForm> installationSite = deviceStateService.findInstallationSiteByArea("");
		request.setAttribute("installationSite", installationSite);
		// 设备名
		List<DeviceStateForm> deviceName = deviceStateService.findDeviceNameByinstallationSite("", "");
		request.setAttribute("deviceName", deviceName);

		List<AuditForm> list = auditService.findAuditInfoPassList(auditForm);
		request.setAttribute("auditList", list);

		int countAudit = auditService.countAuditInfoPass(auditForm);
		auditForm.setRecordCount(countAudit);
		
		return "auditInfoPassList";
	}

	/**
	 * 进入审核通过的编辑页面
	 * @return
	 */
	public String auditInfoPassEdit()
	{
		// 查询出来维护类别
		List<DictionaryForm> diList = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.MAINTAIN_TYPE_NAME);
		request.setAttribute("diList", diList);
		// 查询出来审核状态
		List<DictionaryForm> auditStat = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.AUDIT_STAT);
		request.setAttribute("auditStat", auditStat);
		//查询出用户名
		//List<UserForm> userForm = userService.findUserByMaintainTypeCode(maintainTypeCode);

//		AuditForm auForms = auditService.findAuditInfoWaitByAuditUuid(auditForm);
//		ActionContext.getContext().getValueStack().push(auForms);
		return "auditInfoPassEdit";
	}
	
	/**
	 * 审核未通过的列表
	 * 
	 * @return
	 */
	public String auditInfoRefuseList()
	{
		return "auditInfoRefuseList";
	}
}
