package com.xiaoy.audit.web.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.audit.service.AuditService;
import com.xiaoy.audit.web.form.AuditForm;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.device.servic.DeviceStateService;
import com.xiaoy.device.web.form.DeviceStateForm;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.web.form.DictionaryForm;

@SuppressWarnings("serial")
public class AuditInfoAction extends BaseAction implements ModelDriven<AuditForm>
{
	
	@Resource
	private AuditService auditService;
	
	@Resource
	private DictionaryService dictionaryService;
	
	//设备状态信息
	@Resource
	private DeviceStateService deviceStateService;

	private AuditForm auditForm = new AuditForm();
	
	@Override
	public AuditForm getModel()
	{
		return this.auditForm;
	}
	
	/**
	 * 待审核列表
	 * @return
	 */
	public String auditInfoWaitList()
	{
		//在设备状态表中查询出设备的区域
		List<DeviceStateForm> area = deviceStateService.findDeviceArea();
		request.setAttribute("area", area);
		//发送安装位置到页面
		List<DeviceStateForm> installationSite = deviceStateService.findInstallationSiteByArea("");
		request.setAttribute("installationSite",installationSite);
		//设备名
		List<DeviceStateForm> deviceName = deviceStateService.findDeviceNameByinstallationSite("", "");
		request.setAttribute("deviceName", deviceName);
		
		List<AuditForm> list = auditService.findAuditInfoWaitList(auditForm);
		request.setAttribute("auditList", list);
		
		int countAudit = auditService.countAuditInfoWait(auditForm);
		auditForm.setRecordCount(countAudit);
		
		return "auditInfoWaitList";
	}
	
	/**
	 * 待审核中修改审核状态
	 * @return
	 */
	public String auditInfoWaitEdit()
	{
		List<DictionaryForm> diList = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.MAINTAIN_TYPE_NAME);
		request.setAttribute("diList", diList);
		
		AuditForm auForms = auditService.findAuditInfoWaitByAuditUuid(auditForm);
		ActionContext.getContext().getValueStack().push(auForms);
		return "auditInfoWaitEdit";
	}
}
