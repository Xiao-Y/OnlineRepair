package com.xiaoy.device.web.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.action.BaseAction;
import com.xiaoy.base.util.UploadImageHelper;
import com.xiaoy.device.servic.DeviceInfoService;
import com.xiaoy.device.servic.DeviceStateService;
import com.xiaoy.device.web.form.DeviceInfoForm;
import com.xiaoy.device.web.form.DeviceStateForm;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.DictionaryForm;

@SuppressWarnings("serial")
@Controller
public class DeviceStateAction extends BaseAction implements ModelDriven<DeviceStateForm>
{
	
	//保存图片的文件夹
	private static String DEVICE_IMAGE_URL = "deviceStateUploadImages";
	
	/**
	 * 注入日志
	 */
	@Resource
	private LogService logService;
	
	/**
	 *数据字典 
	 */
	@Resource
	private DictionaryService dictionaryService;
	
	/**
	 * 设备信息
	 */
	@Resource
	private DeviceInfoService deviceInfoService;
	
	@Resource
	private DeviceStateService deviceStateService;
	
	private DeviceStateForm deviceStateForm = new DeviceStateForm();
	
	//获取输入流，用于ajax的删除
	private InputStream inputStream;
	
	@Override
	public DeviceStateForm getModel()
	{
		return deviceStateForm;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	/**
	 * 进入设备状态列表页
	 * @return
	 */
	public String deviceStateList()
	{
		List<DeviceStateForm> list = deviceStateService.findDeviceStateConditionWithPage(deviceStateForm);
		int count = deviceStateService.countDeviceStateByCondition(deviceStateForm);
		deviceStateForm.setRecordCount(count);
		request.setAttribute("formList", list);
		//向页面上发送类型数据
		this.sendPageData();
		logService.saveLog(request, "【设备管理】--【查询设备状态】", "查看状态列表");
		return "deviceStateList";
	}

	/**
	 * 进入添加页面
	 * @return
	 */
	public String toDeviceStateAdd()
	{
		//向页面上发送类型数据
		this.sendPageData();
		logService.saveLog(request, "【设备管理】--【设备信息管理】", "进入设备添加");
		return "toDeviceStateAdd";
	}

	/**
	 * 用户异步请求显示设备型号<br/>
	 * 使用了谷歌的集合转json的Gson.jar
	 */
	public void deviceVersion(){
		List<DeviceInfoForm> list = deviceInfoService.findDeviceVersionByName(deviceStateForm.getDeviceName());
		Gson gson = new Gson();
		String json = gson.toJson(list);
		try {
			response.getOutputStream().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向页面上发送类型数据
	 */
	private void sendPageData()
	{
		//数据类型发送到页面
		List<DictionaryForm> area = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.AREA_NAME);
		List<DictionaryForm> installationSite = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.INSTALLATION_SITE_NAME);
		List<DictionaryForm> state = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.STATE_NAME);
		//获取所有设备的名称
		List<DeviceInfoForm> deviceName = deviceInfoService.findDeviceName();
		request.setAttribute("deviceName", deviceName);
		request.setAttribute("area", area);
		request.setAttribute("installationSite", installationSite);
		request.setAttribute("state", state);
	}
	
	/**
	 * 保存设备状态信息
	 * @return
	 */
	public String deviceStateSave()
	{
        if (deviceStateForm.getImage() != null) 
        {
        	//上传图片
        	UploadImageHelper.uploadImage(deviceStateForm, DEVICE_IMAGE_URL);
        	logService.saveLog(request, "【设备管理】--【查询设备状态管理】", "添加“"+ deviceStateForm.getDeviceName()+"”图片");
        }
        if(!StringUtils.isEmpty(deviceStateForm.getNewFileName()))
        {
        	deviceStateForm.setDevicePicUrl(UploadImageHelper.PICURL);
        }
        deviceStateService.deviceStateSave(deviceStateForm);
		logService.saveLog(request, "【设备管理】--【查询设备状态管理】", "完成添加设备状态");
		return "success";
	}
	
	/**
	 * 浏览设备信息
	 * @return
	 */
	public String deviceView()
	{
		//deviceStateForm = deviceService.getfindDeviceByUuid(deviceForm.getDeviceTypeUuid());
		//request.setAttribute("device", deviceForm);
		///logService.saveLog(request, "【设备管理】--【设备信息管理】", "查看“"+ deviceForm.getDeviceName()+"”设备");
		return "deviceView";
	}

	/**
	 * 编辑设备信息
	 * @return
	 */
	public String deviceEdit()
	{
		//deviceForm = deviceService.getfindDeviceByUuid(deviceForm.getDeviceTypeUuid());
		//request.setAttribute("device", deviceForm);
		//logService.saveLog(request, "【设备管理】--【设备信息管理】", "进入“"+ deviceForm.getDeviceName()+"”编辑");
		return "deviceEdit";
	}
	
	/**
	 * 更新设备信息
	 * @return
	 */
	public String deviceUpdate()
	{
//		//如果修改了图片，取新图片的信息
//		if(!StringUtils.isEmpty(deviceForm.getImageFileName()))
//		{	if (deviceForm.getImage() != null)
//			{
//				//上传图片
//				UploadImageHelper.uploadImage(deviceForm);
//				logService.saveLog(request, "【设备管理】--【设备信息管理】", "修改“"+ deviceForm.getDeviceName()+"”图片");
//	        }
//			deviceForm.setDevicePicUrl(UploadImageHelper.PICURL);
//		}else//如果没有修改图片，取原图片的路径
//		{
//			String devicePicUrl = request.getParameter("oldUrl");
//			deviceForm.setDevicePicUrl(devicePicUrl);
//		}
//		deviceService.deviceUpdate(deviceForm);
//		logService.saveLog(request, "【设备管理】--【设备信息管理】", "更改“"+ deviceForm.getDeviceName()+"”设备");
		return "success";
	}
	
	/**
	 * 使用ajax删除设备信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String deviceDelete() throws UnsupportedEncodingException
	{
//		String deviceTypeUuid = (String) request.getAttribute("deviceTypeUuid");
//		try {
//			deviceService.deviceDeleteByUuid(deviceTypeUuid);
//			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
//			e.printStackTrace();
//		}
//		logService.saveLog(request, "【设备管理】--【设备信息管理】", "删除“"+ deviceForm.getDeviceName()+"”设备");
		return "ajax-success";
	}
	
	public String deviceDeletes()
	{
//		String[] ids = deviceForm.getIds();
//		if(ids != null && ids.length > 0){
//			for(int i = 0; i < ids.length; i++){
//				DeviceInfoForm deviceForm = deviceService.getfindDeviceByUuid(ids[i]); 
//				logService.saveLog(request, "【设备管理】--【设备信息管理】", "批量删除“"+ deviceForm.getDeviceName()+"”设备");
//			}
//			deviceService.deviceDeleteByIds(ids);
//		}
		return "success";
	}
}
