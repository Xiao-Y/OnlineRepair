package com.xiaoy.device.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.action.BaseAction;
import com.xiaoy.device.servic.DeviceService;
import com.xiaoy.device.web.form.DeviceForm;

@SuppressWarnings("serial")
@Controller
public class DeviceAction extends BaseAction implements ModelDriven<DeviceForm>
{
	
	@Resource
	private DeviceService deviceService;
	
	private DeviceForm deviceForm = new DeviceForm();
	
	@Override
	public DeviceForm getModel()
	{
		return deviceForm;
	}
	
	public String deviceInfoList()
	{
		List<DeviceForm> list = deviceService.findDeviceInfoByCondition(deviceForm);
		deviceForm.setRecordCount(deviceService.countDeviceInfoByCondition(deviceForm));
		request.setAttribute("deviceList", list);
		return "deviceInfoList";
	}
	
	/**
	 * 进入添加页面
	 * @return
	 */
	public String toDeviceAdd()
	{
		return "toDeviceAdd";
	}
	
	/**
	 * 保存设备信息
	 * @return
	 */
	public String deviceSave()
	{
		deviceService.deviceSave(deviceForm);
		return "deviceInfoList";
	}

}
