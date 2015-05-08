package com.xiaoy.audit.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
	@Resource
	private AuditService auditService;

	//数据字典
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
		// 发送查询条件到页面
		this.sendSelectPage();

		List<AuditForm> list = auditService.findAuditInfoWaitList(auditForm);
		request.setAttribute("auditList", list);

		// 标识为待审核
		auditForm.setAuditStatCode(DictionaryForm.AUDITSTAT_WAIT);
		int countAudit = auditService.countAuditInfoWait(auditForm);
		auditForm.setRecordCount(countAudit);

		logService.saveLog(request, AuditForm.AUDIT_WAIT, "进入待审核列表");
		return "auditInfoWaitList";
	}

	/**
	 * 待审核中修改审核状态
	 * 
	 * @return
	 */
	public String auditInfoWaitEdit()
	{
		Map<String, String> map = new HashMap<String, String>();
		// 查询出来维护类别
		map.put("diList", DictionaryForm.MAINTAIN_TYPE_NAME);
		// 查询出来审核状态
		map.put("auditStat", DictionaryForm.AUDIT_STAT);

		Map<String, List<DictionaryForm>> ddlList = dictionaryService.findDictionaryMapKeyWord(map);
		for (Entry<String, List<DictionaryForm>> ddl : ddlList.entrySet())
		{
			request.setAttribute(ddl.getKey(), ddl.getValue());
		}

		AuditForm auForms = auditService.findAuditInfoWaitByAuditUuid(auditForm);
		ActionContext.getContext().getValueStack().push(auForms);

		logService.saveLog(request, AuditForm.AUDIT_WAIT, "修改“ " + auForms.getDeviceName() + " ”的待审核状态");
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
		logService.saveLog(request, AuditForm.AUDIT_WAIT, "保存“ " + auditForm.getDeviceName() + " ”待审核信息");
		return "successWait";
	}

	/**
	 * 审核通过的列表
	 * 
	 * @return
	 */
	public String auditInfoPassList()
	{
		
		HttpSession session = request.getSession();
		UserForm userInfo = (UserForm) session.getAttribute("userInfo");
		auditForm.setMaintainUuid(userInfo.getUserUuid());
		
		// 发送查询条件到页面
		this.sendSelectPage();

		List<AuditForm> list = auditService.findAuditInfoPassList(auditForm);
		request.setAttribute("auditList", list);

		// 标识为审核通过
		auditForm.setAuditStatCode(DictionaryForm.AUDITSTAT_SUCCESS);
		int countAudit = auditService.countAuditInfoPass(auditForm);
		auditForm.setRecordCount(countAudit);

		logService.saveLog(request, AuditForm.AUDIT_PASS, "进入审核通过列表");
		return "auditInfoPassList";
	}

	/**
	 * 发送查询条件到页面
	 * <p/>
	 * 1.在设备状态表中查询出设备的区域<br/>
	 * 2.发送安装位置到页面<br/>
	 * 3.设备名<br/>
	 */
	private void sendSelectPage()
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
	}

	/**
	 * 进入审核通过的编辑页面
	 * 
	 * @return
	 */
	public String auditInfoPassEdit()
	{
		Map<String, String> map = new HashMap<String, String>();

		// 查询出来维护类别
		map.put("diList", DictionaryForm.MAINTAIN_TYPE_NAME);
		// 设备状态
		map.put("auditStat", DictionaryForm.AUDIT_STAT);
		// 维护状态
		map.put("maintainStat", DictionaryForm.MAINTAIN_STAT);

		Map<String, List<DictionaryForm>> ddlList = dictionaryService.findDictionaryMapKeyWord(map);
		for (Entry<String, List<DictionaryForm>> ddl : ddlList.entrySet())
		{
			request.setAttribute(ddl.getKey(), ddl.getValue());
		}

		auditForm = auditService.findAuditInfoPassByAuditUuid(auditForm);
		auditForm.setAuditStatCode(DictionaryForm.AUDITSTAT_SUCCESS);
		ActionContext.getContext().getValueStack().push(auditForm);

		logService.saveLog(request, AuditForm.AUDIT_PASS, "进入“" + auditForm.getDeviceName() + "”的审核信息编辑页");
		return "auditInfoPassEdit";
	}

	/**
	 * 保存修改审核通过的数据
	 * <p/>
	 * 1.修改为待审核状态：<br/>
	 * ①评价表：删除评价信息<br/>
	 * ②设备状态表：修改设备状态为正常运行<br/>
	 * ③审核表：修改维护人员的uuid、维护状态、完成时间都为空、审核状态为待审核
	 * <p/>
	 * 
	 * 2.修改为驳回：<br/>
	 * ①评价表：删除评价信息<br/>
	 * ②设备状态表：修改设备状态为正常运行<br/>
	 * ③审核表：修改维护人员的uuid、维护状态、完成时间都为空，审核状态为驳回
	 * <p/>
	 * 
	 * 3.没有对审核进行修改<br/>
	 * 审核表：修改维护人员的uuid、维护状态为未处理、完成时间为空
	 * <p/>
	 * 
	 * @return
	 */
	public String auditInfoPassSave()
	{
		auditService.auditInfoPassSave(auditForm, request);
		logService.saveLog(request, AuditForm.AUDIT_PASS, "保存“" + auditForm.getDeviceName() + "”的审核信息");
		return "successPass";
	}

	/**
	 * 审核未通过的列表
	 * 
	 * @return
	 */
	public String auditInfoRefuseList()
	{
		// 发送查询条件到页面
		this.sendSelectPage();

		List<AuditForm> auditForms = auditService.auditInfoRefuseList(auditForm);
		request.setAttribute("auditList", auditForms);

		auditForm.setAuditStatCode(DictionaryForm.AUDITSTAT_FAIL);
		int countAudit = auditService.countAuditInfoRefuse(auditForm);
		auditForm.setRecordCount(countAudit);

		logService.saveLog(request, AuditForm.AUDIT_REFUSE, "进入的审核未通过列表");
		return "auditInfoRefuseList";
	}

	/**
	 * 审核未通过中修改审核状态
	 * 
	 * @return
	 */
	public String auditInfoRefuseEdit()
	{
		Map<String, String> map = new HashMap<String, String>();
		// 查询出来维护类别
		map.put("diList", DictionaryForm.MAINTAIN_TYPE_NAME);
		// 查询出来审核状态
		map.put("auditStat", DictionaryForm.AUDIT_STAT);
		// 维护状态
		map.put("maintainStat", DictionaryForm.MAINTAIN_STAT);
		
		Map<String, List<DictionaryForm>> ddlList = dictionaryService.findDictionaryMapKeyWord(map);
		for (Entry<String, List<DictionaryForm>> ddl : ddlList.entrySet())
		{
			request.setAttribute(ddl.getKey(), ddl.getValue());
		}

		AuditForm auForms = auditService.findAuditInfoRefuseByAuditUuid(auditForm);
		//标识审核状态为驳回
		auForms.setAuditStatCode(DictionaryForm.AUDITSTAT_FAIL);
		ActionContext.getContext().getValueStack().push(auForms);

		logService.saveLog(request, AuditForm.AUDIT_REFUSE, "修改“ " + auForms.getDeviceName() + " ”的待审核状态");
		return "auditInfoRefuseEdit";
	}
	
	/**
	 * 保存审核未通过的修改
	 * @return
	 */
	public String auditRefuseSave()
	{
		auditService.auditRefuseSave(auditForm, request);
		logService.saveLog(request, AuditForm.AUDIT_REFUSE, "保存“" + auditForm.getDeviceName() + "”的审核信息");
		return "successRefuse";
	}
}
