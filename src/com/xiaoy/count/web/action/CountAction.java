package com.xiaoy.count.web.action;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.xiaoy.base.reports.Reports;
import com.xiaoy.base.web.action.BaseAction;
import com.xiaoy.count.sevice.CountService;
import com.xiaoy.resource.servic.LogService;

@SuppressWarnings("serial")
public class CountAction extends BaseAction
{
	public final static String MENU_MODE = "【统计管理】";
	@Resource
	private CountService countService;

	// 日志
	@Resource
	private LogService logService;

	/**
	 * 用户性别统计
	 * 
	 * @return
	 * @throws IOException
	 */
	public String userSexCount() throws IOException
	{
		String flag = request.getParameter("flag");

		Map<String, Double> map = countService.userSexCount();
		Reports rp = new Reports();
		String title = "用户性别比例";
		String picName = "";
		if ("1".equals(flag))
		{
			DefaultPieDataset dataset = new DefaultPieDataset();
			if (map != null && map.size() > 0)
			{
				for (Entry<String, Double> entry : map.entrySet())
				{
					dataset.setValue(entry.getKey(), entry.getValue());
				}
			}

			picName = "chartPicUserSexCount.jpeg";

			rp.pie(dataset, title, request, picName);
		} else
		{
			String categoryAxisLabel = "用户性别";
			String valueAxisLabel = "数量";
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			// value 值, rowKey 行值, columnKey 列值
			if (map != null && map.size() > 0)
			{
				for (Entry<String, Double> entry : map.entrySet())
				{
					dataset.setValue(entry.getValue(), "用户性别", entry.getKey());
				}
			}

			picName = "chartBarUserSexCount.jpeg";

			rp.bar(dataset, title, categoryAxisLabel, valueAxisLabel, request, picName);
		}

		request.setAttribute("picName", picName);
		request.setAttribute("path", "/CountMag/countAction_userSexCount.action");
		request.setAttribute("flag", flag);
		
		logService.saveLog(request, MENU_MODE, "用户性别统计");
		return "success";
	}

	/**
	 * 用户的维护类型
	 * 
	 * @return
	 * @throws IOException
	 */
	public String userTypeCount() throws IOException
	{
		String flag = request.getParameter("flag");

		Map<String, Double> map = countService.userTypeCount();
		Reports rp = new Reports();
		String title = "用户维护类型";
		String picName = "";
		if ("1".equals(flag))
		{
			DefaultPieDataset dataset = new DefaultPieDataset();
			if (map != null && map.size() > 0)
			{
				for (Entry<String, Double> entry : map.entrySet())
				{
					dataset.setValue(entry.getKey(), entry.getValue());
				}
			}

			picName = "chartPicUserTypeCount.jpeg";

			rp.pie(dataset, title, request, picName);
		} else
		{
			String categoryAxisLabel = "维护类型";
			String valueAxisLabel = "数量";
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			// value 值, rowKey 行值, columnKey 列值
			if (map != null && map.size() > 0)
			{
				for (Entry<String, Double> entry : map.entrySet())
				{
					dataset.setValue(entry.getValue(), "维护类型", entry.getKey());
				}
			}

			picName = "chartBarUserTypeCount.jpeg";

			rp.bar(dataset, title, categoryAxisLabel, valueAxisLabel, request, picName);
		}

		request.setAttribute("picName", picName);
		request.setAttribute("path", "/CountMag/countAction_userTypeCount.action");
		request.setAttribute("flag", flag);
		
		logService.saveLog(request, MENU_MODE, "用户的维护类型");
		return "success";
	}

	/**
	 * 设备故障次数统计
	 * 
	 * @return
	 * @throws IOException
	 */
	public String deviceBreakdownCount() throws IOException
	{
		String flag = request.getParameter("flag");

		Map<String, Double> map = countService.deviceBreakdownCount();
		Reports rp = new Reports();
		String title = "设备故障次数统计";
		String picName = "";
		if ("1".equals(flag))
		{
			DefaultPieDataset dataset = new DefaultPieDataset();
			if (map != null && map.size() > 0)
			{
				for (Entry<String, Double> entry : map.entrySet())
				{
					dataset.setValue(entry.getKey(), entry.getValue());
				}
			}

			picName = "chartPicAkdownCount.jpeg";

			rp.pie(dataset, title, request, picName);
		} else
		{
			String categoryAxisLabel = "设备故障次数";
			String valueAxisLabel = "次数";
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			// value 值, rowKey 行值, columnKey 列值
			if (map != null && map.size() > 0)
			{
				for (Entry<String, Double> entry : map.entrySet())
				{
					dataset.setValue(entry.getValue(), "设备故障次数", entry.getKey());
				}
			}

			picName = "deviceBreakdownCount.jpeg";

			rp.bar(dataset, title, categoryAxisLabel, valueAxisLabel, request, picName);
		}
		request.setAttribute("picName", picName);
		request.setAttribute("path", "/CountMag/countAction_deviceBreakdownCount.action");
		request.setAttribute("flag", flag);
		
		logService.saveLog(request, MENU_MODE, "设备故障次数统计");
		return "success";
	}

	/**
	 * 设备数量统计
	 * 
	 * @return
	 * @throws IOException
	 */
	public String deviceSum() throws IOException
	{
		String flag = request.getParameter("flag");

		Map<String, Double> map = countService.deviceSum();
		Reports rp = new Reports();
		String title = "设备数量统计";
		String picName = "";
		if ("1".equals(flag))
		{
			DefaultPieDataset dataset = new DefaultPieDataset();
			if (map != null && map.size() > 0)
			{
				for (Entry<String, Double> entry : map.entrySet())
				{
					dataset.setValue(entry.getKey(), entry.getValue());
				}
			}

			picName = "chartPicDeviceSum.jpeg";

			rp.pie(dataset, title, request, picName);
		} else
		{
			String categoryAxisLabel = "设备数量";
			String valueAxisLabel = "数量";
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			// value 值, rowKey 行值, columnKey 列值
			if (map != null && map.size() > 0)
			{
				for (Entry<String, Double> entry : map.entrySet())
				{
					dataset.setValue(entry.getValue(), "设备数量", entry.getKey());
				}
			}

			picName = "chartBerDeviceSum.jpeg";

			rp.bar(dataset, title, categoryAxisLabel, valueAxisLabel, request, picName);
		}
		
		request.setAttribute("picName", picName);
		request.setAttribute("path", "/CountMag/countAction_deviceSum.action");
		request.setAttribute("flag", flag);
		
		logService.saveLog(request, MENU_MODE, "设备数量统计");
		return "success";
	}
}
