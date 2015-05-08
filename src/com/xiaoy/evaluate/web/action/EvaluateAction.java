package com.xiaoy.evaluate.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.device.servic.DeviceInfoService;
import com.xiaoy.device.web.form.DeviceInfoForm;
import com.xiaoy.evaluate.service.EvaluateService;
import com.xiaoy.evaluate.web.form.EvaluateForm;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.web.form.UserForm;

@SuppressWarnings("serial")
public class EvaluateAction extends BaseAction implements ModelDriven<EvaluateForm>
{
	// 评论
	@Resource
	private EvaluateService evaluateService;

	// 日志管理
	@Resource
	private LogService logService;

	// 数据字典
	@Resource
	private DictionaryService dictionaryService;

	// 获取输入流，用于ajax的删除
	private InputStream inputStream;

	public InputStream getInputStream()
	{
		return inputStream;
	}

	/**
	 * 设备信息
	 */
	@Resource
	private DeviceInfoService deviceInfoService;

	EvaluateForm evaluateForm = new EvaluateForm();

	@Override
	public EvaluateForm getModel()
	{
		return evaluateForm;
	}

	/**
	 * 评价列表
	 * 
	 * @return
	 */
	public String evaluateList()
	{
		Map<String, String> map = new HashMap<String, String>();
		// 查询区域
		map.put("area", DictionaryForm.AREA_NAME);
		// 安装位置
		map.put("installationSite", DictionaryForm.INSTALLATION_SITE_NAME);
		// 评价状态
		map.put("evaluateSata", DictionaryForm.EVALUATE_STAT);

		Map<String, List<DictionaryForm>> ddlList = dictionaryService.findDictionaryMapKeyWord(map);
		for (Entry<String, List<DictionaryForm>> ddl : ddlList.entrySet())
		{
			request.setAttribute(ddl.getKey(), ddl.getValue());
		}
		// 获取所有设备的名称
		List<DeviceInfoForm> deviceName = deviceInfoService.findDeviceName();
		request.setAttribute("deviceName", deviceName);

		//获取用户的信息
		HttpSession session = request.getSession();
		UserForm userInfo = (UserForm) session.getAttribute("userInfo");
		evaluateForm.setUserUuid(userInfo.getUserUuid());
		
		List<EvaluateForm> list = evaluateService.findEvaluateList(evaluateForm);
		request.setAttribute("list", list);

		int recordCount = evaluateService.countEvaluate(evaluateForm);
		evaluateForm.setRecordCount(recordCount);

		logService.saveLog(request, EvaluateForm.MODEL_NAME, "进入评价列表页面");
		return "evaluateList";
	}

	public String evaluateInfoEdit()
	{
		// 满意度
		List<DictionaryForm> list = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.RANK);
		request.setAttribute("ranks", list);

		evaluateForm = evaluateService.findEvaluateByUuid(evaluateForm);
		ActionContext.getContext().getValueStack().push(evaluateForm);

		logService.saveLog(request, EvaluateForm.MODEL_NAME, "进入评价编辑页面");
		return "evaluateInfoEdit";
	}

	/**
	 * 保存评价信息
	 * 
	 * @return
	 */
	public String evaluateInfoSave()
	{
		evaluateService.evaluateInfoSave(evaluateForm);
		
		logService.saveLog(request, EvaluateForm.MODEL_NAME, "保存评价信息");
		return "success";
	}

	public String evaluateInfoView()
	{
		// 满意度
		List<DictionaryForm> list = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.RANK);
		request.setAttribute("ranks", list);

		evaluateForm = evaluateService.findEvaluateByUuid(evaluateForm);
		ActionContext.getContext().getValueStack().push(evaluateForm);

		logService.saveLog(request, EvaluateForm.MODEL_NAME, "查看评价信息");
		return "evaluateInfoView";
	}

	/**
	 * 删除单个评价 1。将评价设备为不可以看见
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String deleteEvaluateInfo() throws UnsupportedEncodingException
	{
		try
		{
			String evaluateUuid = request.getParameter("evaluateUuid");
			evaluateService.updateEvaluateInfo(evaluateUuid);

			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e)
		{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			e.printStackTrace();
		}

		logService.saveLog(request, EvaluateForm.MODEL_NAME, "删除评价信息");
		return "ajax-success";
	}

	/**
	 * 批量删除评论
	 * 
	 * @return
	 */
	public String deletesEvaluateInfo()
	{
		String[] ids = evaluateForm.getIds();
		evaluateService.deletesEvaluateInfo(ids);
		
		logService.saveLog(request, EvaluateForm.MODEL_NAME, "批量删除评价信息");
		return "success";
	}
}
