package com.xiaoy.reporting.web.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.device.servic.DeviceStateService;
import com.xiaoy.device.web.action.DeviceStateAction;
import com.xiaoy.device.web.form.DeviceStateForm;
import com.xiaoy.reporting.service.ReportingService;
import com.xiaoy.reporting.web.form.ReportingForm;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.service.UserService;
import com.xiaoy.user.web.form.UserForm;

@Controller
@SuppressWarnings("serial")
public class ReportingAction extends BaseAction implements ModelDriven<ReportingForm>
{
	
	private final static String MENU_MODEL = "【设备管理】--【申报故障设备】";
	
	//设备状态信息
	@Resource
	private DeviceStateService deviceStateService;
	
	//用户
	@Resource
	private UserService userService;
	
	//数据字典
	@Resource
	private DictionaryService dictionaryService;
	
	//日志
	@Resource
	private LogService logService;
	
	@Resource
	private ReportingService reportingService;
	
	//获取输入流
	private InputStream inputStream;

	public InputStream getInputStream()
	{
		return inputStream;
	}

	private ReportingForm reportingForm = new ReportingForm();
	
	@Override
	public ReportingForm getModel()
	{
		return reportingForm;
	}

	/**
	 * 申报故障列表页面
	 * @return
	 */
	public String reportingBugInfoList()
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
		//维护状态
		List<DictionaryForm> maintainStat = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.MAINTAIN_STAT);
		request.setAttribute("maintainStat", maintainStat);
//		//评论状态
//		List<DictionaryForm> evaluateStat = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.EVALUATE_STAT);
//		request.setAttribute("evaluateStat", evaluateStat);
		//审核状态
		List<DictionaryForm> auditStat = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.AUDIT_STAT);
		request.setAttribute("auditStat", auditStat);
//		//维护类型
//		List<DictionaryForm> maintainType = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.MAINTAIN_TYPE_NAME);
//		request.setAttribute("maintainType", maintainType);
		
		//查询出现所有是维护人员的信息
		List<UserForm> users = userService.findUserCoditionMaintain();
		request.setAttribute("users", users);
		
		//获取当前用户的uuid
		HttpSession session = request.getSession();
		UserForm userInfo = (UserForm) session.getAttribute("userInfo");
		reportingForm.setUserUuid(userInfo.getUserUuid());
		
		//当是管理员时，不添加用户uuid。否则添加用户uuid，查询出现当前用户的申报信息
		List<ReportingForm> list = reportingService.findReportingBugInfoList(reportingForm);
		
		//添加分页的总记录数
		int recordCount = reportingService.countReportingBugInfo(reportingForm);
		reportingForm.setRecordCount(recordCount);
		
		request.setAttribute("reportingBugInfoList", list);
		return "reportingBugInfoList";
	}
	
	/**
	 * 申报故障添加页面
	 * @return
	 */
	public String reportingBugInfoAdd()
	{
		//发送数据到页面
		this.sendDataPage();
		logService.saveLog(request, MENU_MODEL, "进入添加申报故障");
		return "reportingBugInfoAdd";
	}
	
	/**
	 * 发送数据到页面
	 */
	private void sendDataPage()
	{
		//在设备状态表中查询出设备的区域
		List<DeviceStateForm> area = deviceStateService.findDeviceArea();
		request.setAttribute("area", area);
		//在数据字典中查询出优先级别
		List<DictionaryForm> priorCodes = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.PRIOR);
		request.setAttribute("priorCodes", priorCodes);
	}

	/**
	 * 用户异步请求显示设备安装位置<br/>
	 * 使用了谷歌的集合转json的Gson.jar
	 */
	public void deviceInstallationSiteByArea()
	{
		String areaCode = request.getParameter("areaCode");
		if(!StringUtils.isEmpty(areaCode))
		{
			List<DeviceStateForm> InstallationSite = deviceStateService.findInstallationSiteByArea(areaCode);
			Gson gson = new Gson();
			String json = gson.toJson(InstallationSite);
			try {
				response.getOutputStream().print(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	/**
	 * 用户异步请求显示设备名<br/>
	 * 通过区域和安置位置在设备状态信息表中查询出现所有的设备名
	 */
	public String deviceNameByinstallationSite()
	{
		//区域Code
		String areaCode = request.getParameter("areaCode");
		//安装位置Code
		String installationSiteCode = request.getParameter("installationSiteCode");
		if(!StringUtils.isEmpty(areaCode) && !StringUtils.isEmpty(installationSiteCode))
		{
			List<DeviceStateForm> deviceName = deviceStateService.findDeviceNameByinstallationSite(areaCode,installationSiteCode);
			Gson gson = new Gson();
			String json = gson.toJson(deviceName);
			try
			{
				//带有中文
				inputStream = new ByteArrayInputStream(json.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	/**
	 * 用户异步请求显示设备型号<br/>
	 * 通过区域和安置位置和设备名，在设备状态信息表中查询出现所有的设备型号
	 * @return
	 */
	public String versionBydeviceName()
	{
		//区域Code
		String areaCode = request.getParameter("areaCode");
		//安装位置Code
		String installationSiteCode = request.getParameter("installationSiteCode");
		//设备名
		String deviceName = request.getParameter("deviceName");
		
		if(!StringUtils.isEmpty(areaCode) && !StringUtils.isEmpty(installationSiteCode))
		{
			List<DeviceStateForm> versions = deviceStateService.findVersionBydeviceNamee(areaCode,installationSiteCode,deviceName);
			Gson gson = new Gson();
			String json = gson.toJson(versions);
			try
			{
				//带有中文
				inputStream = new ByteArrayInputStream(json.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
//	/**
//	 * 申报故障编辑页面
//	 * @return
//	 */
//	public String reportingBugInfoEdit()
//	{
//		//发送数据到页面,区域和优先级
//		this.sendDataPage();
//		//发送安装位置到页面
//		List<DeviceStateForm> installationSite = deviceStateService.findInstallationSiteByArea("");
//		request.setAttribute("installationSite",installationSite);
//		List<DeviceStateForm> deviceName = deviceStateService.findDeviceNameByinstallationSite("", "");
//		request.setAttribute("deviceName", deviceName);
//		List<DeviceStateForm> version = deviceStateService.findVersionBydeviceNamee("", "", "");
//		request.setAttribute("version", version);
//		
//		logService.saveLog(request, MENU_MODEL, "进入编辑申报故障");
//		return "reportingBugInfoEdit";
//	}
	
	public String reportingBugInfoView()
	{
		reportingForm = reportingService.findReportingBugInfoByRrUuid(reportingForm.getReportingUuid());
		ActionContext.getContext().getValueStack().push(reportingForm);
		return "reportingBugInfoView";
	}
	
	/**
	 * 保存设备申报信息
	 * 1.保存设备申报信息
	 * 2.添加申报故障审核信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String reportingBugInfoSave() throws UnsupportedEncodingException
	{
		HttpSession session = request.getSession();
		UserForm userInfo = (UserForm) session.getAttribute("userInfo");
		if(userInfo != null)
		{
			reportingForm.setUserUuid(userInfo.getUserUuid());
		}
		String flag = reportingForm.getFlag();
		reportingService.reportingBugInfoSave(reportingForm, request);
		logService.saveLog(request, MENU_MODEL, "保存申报故障信息");
		//继续添加申报信息
		if(ReportingAction.SUCCESS.equals(flag))
		{
			return "successSave";
		}
		return "success";
	}
	
	/**
	 * 删除故障申报信息<br/>
	 * 1、待审核的。只用删除申报信息。<br/>
	 * 2、审核通过。删除审核通过的申报信息，将会删除审核信息和评价信息<br/>
	 * 3、审核未通过。删除审核未通过的申报信息，将会删除审核信息<br/>
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String deleteReportingBugInfo() throws UnsupportedEncodingException
	{
		String reportingUuid = request.getParameter("reportingUuid");
		String auditStatCode = request.getParameter("auditStatCode");
		String auditUuid = request.getParameter("auditUuid");
		try
		{
			reportingService.deleteReportingBugInfo(reportingUuid,auditStatCode,auditUuid);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e)
		{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			e.printStackTrace();
		}
		return "ajax-success";
	}
	

	/**
	 * 通过设备状态信息查询出设备是否异常
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String deviceStateInfoDeviceStatUuid() throws UnsupportedEncodingException
	{
		String deviceStateUuid = request.getParameter("deviceStateUuid");
		String deviceStateCode =  deviceStateService.findDeviceStateInfoDeviceStatUuid(deviceStateUuid);
		if(!StringUtils.isEmpty(deviceStateCode) && DictionaryForm.DEVICE_STAT_EXCEPTION.equals(deviceStateCode))
		{
			inputStream = new ByteArrayInputStream(DeviceStateAction.FAIL.getBytes("UTF-8"));
		}else
		{
			inputStream = new ByteArrayInputStream(DeviceStateAction.SUCCESS.getBytes("UTF-8"));
		}
		
		return "ajax-success";
	}
}
