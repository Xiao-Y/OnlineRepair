package com.xiaoy.device.web.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.action.BaseAction;
import com.xiaoy.device.servic.DeviceService;
import com.xiaoy.device.web.form.DeviceForm;
import com.xiaoy.resource.servic.LogService;

@SuppressWarnings("serial")
@Controller
public class DeviceAction extends BaseAction implements ModelDriven<DeviceForm>
{
	private static String DEVICE_IMAGE_URL = "deviceUploadImages";
	
	@Resource
	private DeviceService deviceService;
	
	//注入日志
	@Resource
	private LogService logService;
	
	private DeviceForm deviceForm = new DeviceForm();
	
	//获取输入流，用于ajax的删除
	private InputStream inputStream;
	
	@Override
	public DeviceForm getModel()
	{
		return deviceForm;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	/**
	 * 进入设备列表页
	 * @return
	 */
	public String deviceInfoList()
	{
		List<DeviceForm> list = deviceService.findDeviceInfoByCondition(deviceForm);
		deviceForm.setRecordCount(deviceService.countDeviceInfoByCondition(deviceForm));
		request.setAttribute("deviceList", list);
		logService.saveLog(request, "【设备管理】", "查看设备列表");
		return "deviceInfoList";
	}
	
	/**
	 * 进入添加页面
	 * @return
	 */
	public String toDeviceAdd()
	{
		logService.saveLog(request, "【设备管理】", "进入设备添加");
		return "toDeviceAdd";
	}
	
	/**
	 * 保存设备信息
	 * @return
	 */
	public String deviceSave()
	{
        if (deviceForm.getImage() != null) {
            this.uploadImage(deviceForm);
        }
        if(!StringUtils.isEmpty(deviceForm.getImageFileName()))
        {
        	deviceForm.setDevicePicUrl("/"+ DEVICE_IMAGE_URL +"/" + deviceForm.getImageFileName());
        }
		deviceService.deviceSave(deviceForm);
		logService.saveLog(request, "【设备管理】", "完成添加设备");
		return "deviceSave";
	}
	
	/**
	 * 浏览设备信息
	 * @return
	 */
	public String deviceView()
	{
		deviceForm = deviceService.getfindDeviceByUuid(deviceForm.getDeviceTypeUuid());
		request.setAttribute("device", deviceForm);
		logService.saveLog(request, "【设备管理】", "查看 "+ deviceForm.getDeviceName()+" 设备");
		return "deviceView";
	}

	/**
	 * 编辑设备信息
	 * @return
	 */
	public String deviceEdit()
	{
		deviceForm = deviceService.getfindDeviceByUuid(deviceForm.getDeviceTypeUuid());
		request.setAttribute("device", deviceForm);
		logService.saveLog(request, "【设备管理】", "进入 "+ deviceForm.getDeviceName()+" 编辑");
		return "deviceEdit";
	}
	
	/**
	 * 更新设备信息
	 * @return
	 */
	public String deviceUpdate()
	{
		//如果修改了图片，取新图片的信息
		if(!StringUtils.isEmpty(deviceForm.getImageFileName()))
		{	if (deviceForm.getImage() != null) {
				this.uploadImage(deviceForm);
	        }
			deviceForm.setDevicePicUrl("/"+ DEVICE_IMAGE_URL +"/" + deviceForm.getImageFileName());
		}else//如果没有修改图片，取原图片的路径
		{
			String devicePicUrl = request.getParameter("oldUrl");
			deviceForm.setDevicePicUrl(devicePicUrl);
		}
		deviceService.deviceUpdate(deviceForm);
		logService.saveLog(request, "【设备管理】", "更改 "+ deviceForm.getDeviceName()+" 设备");
		return "deviceUpdate";
	}
	
	/**
	 * 使用ajax删除设备信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String deviceDelete() throws UnsupportedEncodingException
	{
		String deviceTypeUuid = (String) request.getAttribute("deviceTypeUuid");
		try {
			deviceService.deviceDeleteByUuid(deviceTypeUuid);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			e.printStackTrace();
		}
		logService.saveLog(request, "【设备管理】", "删除 "+ deviceForm.getDeviceName()+" 设备");
		return "ajax-success";
	}
	
	/**
	 * 上传图片
	 * @param deviceForm
	 */
	private void uploadImage(DeviceForm deviceForm)
	{
		String realpath = ServletActionContext.getServletContext().getRealPath("/" + DEVICE_IMAGE_URL);
        File savefile = new File(new File(realpath), deviceForm.getImageFileName());
        if (!savefile.getParentFile().exists())
            savefile.getParentFile().mkdirs();
        try {
			FileUtils.copyFile(deviceForm.getImage(), savefile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logService.saveLog(request, "【设备管理】", "添加/修改 "+ deviceForm.getDeviceName()+" 图片");
	}
}
