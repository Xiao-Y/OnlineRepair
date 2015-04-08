package com.xiaoy.device.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.action.BaseAction;
import com.xiaoy.base.util.UploadImageHelper;
import com.xiaoy.device.servic.DeviceInfoService;
import com.xiaoy.device.web.form.DeviceInfoForm;
import com.xiaoy.resource.servic.LogService;

@SuppressWarnings("serial")
@Controller
public class DeviceInfoAction extends BaseAction implements ModelDriven<DeviceInfoForm>
{
	@Resource
	private DeviceInfoService deviceService;
	
	//注入日志
	@Resource
	private LogService logService;
	
	private DeviceInfoForm deviceForm = new DeviceInfoForm();
	
	//获取输入流，用于ajax的删除
	private InputStream inputStream;
	
	@Override
	public DeviceInfoForm getModel()
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
		List<DeviceInfoForm> list = deviceService.findDeviceInfoByCondition(deviceForm);
		deviceForm.setRecordCount(deviceService.countDeviceInfoByCondition(deviceForm));
		request.setAttribute("deviceList", list);
		logService.saveLog(request, "【设备管理】--【设备信息管理】", "查看设备列表");
		return "deviceInfoList";
	}
	
	/**
	 * 进入添加页面
	 * @return
	 */
	public String toDeviceAdd()
	{
		logService.saveLog(request, "【设备管理】--【设备信息管理】", "进入设备添加");
		return "toDeviceAdd";
	}
	
	/**
	 * 保存设备信息
	 * @return
	 */
	public String deviceSave()
	{
        if (deviceForm.getImage() != null) 
        {
        	//上传图片
        	UploadImageHelper.uploadImage(deviceForm);
        	logService.saveLog(request, "【设备管理】--【设备信息管理】", "添加“"+ deviceForm.getDeviceName()+"”图片");
        }
        if(!StringUtils.isEmpty(deviceForm.getNewFileName()))
        {
        	deviceForm.setDevicePicUrl(UploadImageHelper.PICURL);
        }
		deviceService.deviceSave(deviceForm);
		logService.saveLog(request, "【设备管理】--【设备信息管理】", "完成添加设备");
		return "success";
	}
	
	/**
	 * 浏览设备信息
	 * @return
	 */
	public String deviceView()
	{
		deviceForm = deviceService.getfindDeviceByUuid(deviceForm.getDeviceTypeUuid());
		request.setAttribute("device", deviceForm);
		logService.saveLog(request, "【设备管理】--【设备信息管理】", "查看“"+ deviceForm.getDeviceName()+"”设备");
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
		logService.saveLog(request, "【设备管理】--【设备信息管理】", "进入“"+ deviceForm.getDeviceName()+"”编辑");
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
		{	if (deviceForm.getImage() != null)
			{
				//上传图片
				UploadImageHelper.uploadImage(deviceForm);
				logService.saveLog(request, "【设备管理】--【设备信息管理】", "修改“"+ deviceForm.getDeviceName()+"”图片");
	        }
			deviceForm.setDevicePicUrl(UploadImageHelper.PICURL);
		}else//如果没有修改图片，取原图片的路径
		{
			String devicePicUrl = request.getParameter("oldUrl");
			deviceForm.setDevicePicUrl(devicePicUrl);
		}
		deviceService.deviceUpdate(deviceForm);
		logService.saveLog(request, "【设备管理】--【设备信息管理】", "更改“"+ deviceForm.getDeviceName()+"”设备");
		return "success";
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
		logService.saveLog(request, "【设备管理】--【设备信息管理】", "删除“"+ deviceForm.getDeviceName()+"”设备");
		return "ajax-success";
	}
	
	public String deviceDeletes()
	{
		String[] ids = deviceForm.getIds();
		if(ids != null && ids.length > 0){
			for(int i = 0; i < ids.length; i++){
				DeviceInfoForm deviceForm = deviceService.getfindDeviceByUuid(ids[i]); 
				logService.saveLog(request, "【设备管理】--【设备信息管理】", "批量删除“"+ deviceForm.getDeviceName()+"”设备");
			}
			deviceService.deviceDeleteByIds(ids);
		}
		return "success";
	}
}
