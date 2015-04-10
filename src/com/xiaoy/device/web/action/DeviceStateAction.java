package com.xiaoy.device.web.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.action.BaseAction;
import com.xiaoy.device.servic.DeviceInfoService;
import com.xiaoy.device.servic.DeviceStateService;
import com.xiaoy.device.web.form.DeviceInfoForm;
import com.xiaoy.device.web.form.DeviceStateForm;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.DictionaryForm;

/**
 * 设备状态信息处理类
 * @author XiaoY
 * @date 2015年4月10日
 */
@SuppressWarnings("serial")
@Controller
public class DeviceStateAction extends BaseAction implements ModelDriven<DeviceStateForm>
{
	private final static String MENU_MODEL = "【设备管理】--【查询设备状态】";
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
		logService.saveLog(request, MENU_MODEL, "查看状态列表");
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
		logService.saveLog(request, MENU_MODEL, "进入设备状态添加页面");
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
		//区域
		List<DictionaryForm> area = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.AREA_NAME);
		request.setAttribute("area", area);
		//安装位置
		List<DictionaryForm> installationSite = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.INSTALLATION_SITE_NAME);
		request.setAttribute("installationSite", installationSite);
		//运行状态
		List<DictionaryForm> state = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.STATE_NAME);
		request.setAttribute("state", state);
		//获取所有设备的名称
		List<DeviceInfoForm> deviceName = deviceInfoService.findDeviceName();
		request.setAttribute("deviceName", deviceName);
		
	}
	
	/**
	 * 保存设备状态信息
	 * @return
	 */
	public String deviceStateSave()
	{
        deviceStateService.deviceStateSave(deviceStateForm, request);
		logService.saveLog(request, MENU_MODEL, "完成添加设备状态");
		return "success";
	}
	
	/**
	 * 浏览设备信息
	 * @return
	 */
	public String deviceStateView()
	{
		deviceStateForm = deviceStateService.findDeviceStateByUuid(deviceStateForm.getDeviceStateUuid());
		ActionContext.getContext().getValueStack().push(deviceStateForm);
		logService.saveLog(request, MENU_MODEL, "查看“"+ deviceStateForm.getDeviceName()+"”设备状态");
		return "deviceStateView";
	}

	/**
	 * 编辑设备信息
	 * @return
	 */
	public String deviceStateEdit()
	{
		deviceStateForm = deviceStateService.findDeviceStateByUuid(deviceStateForm.getDeviceStateUuid());
		ActionContext.getContext().getValueStack().push(deviceStateForm);
		//向页面上发送类型数据
		this.sendPageData();
		logService.saveLog(request, MENU_MODEL, "进入“"+ deviceStateForm.getDeviceName()+"”编辑");
		return "deviceStateEdit";
	}
	
	/**
	 * 更新设备信息
	 * @return
	 */
	public String deviceStateUpdate()
	{
		deviceStateService.deviceStateUpdate(deviceStateForm,request);
		logService.saveLog(request, MENU_MODEL, "更改“"+ deviceStateForm.getDeviceName()+"”设备");
		return "success";
	}
	
	/**
	 * 使用ajax删除设备状态信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String deviceStateDelete() throws UnsupportedEncodingException
	{
		String deviceStateUuid = (String) request.getAttribute("deviceStateUuid");
		
		try {
			if(!StringUtils.isEmpty(deviceStateUuid)){
				deviceStateForm = deviceStateService.findDeviceStateByUuid(deviceStateUuid);
			}
			logService.saveLog(request, MENU_MODEL, "删除“"+ deviceStateForm.getDeviceName()+"”设备");
			deviceStateService.deviceStateDeleteByUuid(deviceStateUuid);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			e.printStackTrace();
		}
		return "ajax-success";
	}
	
	/**
	 * 批量删除设备状态信息
	 * @return
	 */
	public String deviceStateDeletes()
	{
		String[] ids = deviceStateForm.getIds();
		if(ids != null && ids.length > 0){
			for(int i = 0; i < ids.length; i++){
				DeviceStateForm deviceStateForm = deviceStateService.findDeviceStateByUuid(ids[i]); 
				logService.saveLog(request, MENU_MODEL, "批量删除“"+ deviceStateForm.getDeviceName()+"”设备");
			}
			deviceStateService.deviceStateDeleteByIds(ids);
		}
		return "success";
	}
}
