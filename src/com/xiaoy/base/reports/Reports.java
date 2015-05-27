package com.xiaoy.base.reports;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 饼状图
 * 
 * @author XiaoY
 * @date: 2015年5月3日 上午12:26:43
 */
public class Reports
{
	/**
	 * 饼状态图
	 * 
	 * @param dataset
	 *            数据集<br/>
	 *            DefaultPieDataset dataset = new DefaultPieDataset();<br/>
	 *            dataset.setValue("农业", 26.32);<br/>
	 * @param title
	 *            主标题
	 * @throws IOException
	 */
	public void pie(DefaultPieDataset dataset, String title, HttpServletRequest request, String picName) throws IOException
	{
		JFreeChart chart = ChartFactory.createPieChart3D(title, // 主标题
				dataset, // 数据集
				true, // 是否显示图例
				true, // 是否显示工具
				true// 是否生成url
				);
		// 处理中文
		// 处理主标题
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
		// 处理图例
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
		// 获取图表区域对象
		PiePlot3D piePlot = (PiePlot3D) chart.getPlot();
		piePlot.setLabelFont(new Font("宋体", Font.BOLD, 15));

		// 格式：“上海 32 （34%）”
		String format = "{0} {1} ({2})";
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(format));

		// 生成图片
		String path = request.getSession().getServletContext().getRealPath("/chart/" + picName);
		File file = new File(path);
		ChartUtilities.saveChartAsJPEG(file, chart, 700, 500);
		System.out.println("饼状图片生成完毕...");
	}

	/**
	 * 条形图 <br/>
	 * 1.JFreeChart对象 <br/>
	 * 处理主标题、子标题 2.CategoryPlot图表区域对象 <br/>
	 * 获得x、y对象和绘图区域对象<br/>
	 * 2.1 CategoryAxis3D获取种类轴（x轴）对象 <br/>
	 * 2.2 NumberAxis3D获取值轴（y轴）对象 <br/>
	 * 2.3.BarRenderer3D获取绘图区域对象 <br/>
	 * 
	 * @param dataset	数据集<br/>
	 * DefaultCategoryDataset dataset = new DefaultCategoryDataset();<br/>
	 * value 值, rowKey 行值, columnKey 列值<br/>
	 * dataset.addValue(14, "所属单位", "北京");<br/>
	 * @param title
	 *            主标题，不能为空
	 * @param categoryAxisLabel
	 *            种类轴（x轴）
	 * @param valueAxisLabel
	 *            值轴（y轴）
	 * @throws IOException
	 */
	public void bar(DefaultCategoryDataset dataset, String title, String categoryAxisLabel, String valueAxisLabel, HttpServletRequest request, String picName) throws IOException
	{
		// DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// //value 值, rowKey 行值, columnKey 列值
		// dataset.addValue(14, "所属单位", "北京");
		// dataset.addValue(6, "所属单位2", "北京");
		// dataset.addValue(10, "所属单位", "上海");
		// dataset.addValue(8, "所属单位2", "上海");
		// dataset.addValue(12, "所属单位", "深圳");
		// dataset.addValue(11, "所属单位2", "深圳");

		/**
		 * title //主标题，不能为空 
		 * categoryAxisLabel //种类轴（x轴） 
		 * valueAxisLabel //值轴（y轴） 
		 * dataset //图表显示的数据集 orientation 
		 * //图形显示的定向（水平horizontal、垂直vertical） legend 
		 * //是否显示子标题（图例） tooltips 
		 * //是否显示提示工具 urls 
		 * //是否生成URL
		 */
		JFreeChart chart = ChartFactory.createBarChart3D(title, // 主标题，不能为空
				categoryAxisLabel, // 种类轴（x轴）
				valueAxisLabel, // 值轴（y轴）
				dataset, // 图表显示的数据集
				PlotOrientation.VERTICAL, // 图形显示的定向（水平horizontal、垂直vertical）
				true, // 是否显示子标题（图例）
				true, // 是否显示提示工具
				true // 是否生成URL
				);
		// 1、处理乱码
		// 处理主标题
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
		// 处理子标题
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 13));

		// 2、获取图表区域对象(每个种类都不一样，可以输出获取chart.getPlot()的返回值)
		CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();

		// 2.1、获取种类轴（x轴）对象(每个种类都不一样，可以输出，获取categoryPlot.getDomainAxis()的返回值)
		CategoryAxis3D categoryAxis3D = (CategoryAxis3D) categoryPlot.getDomainAxis();
		// 处理x轴上的乱码
		categoryAxis3D.setTickLabelFont(new Font("宋体", Font.BOLD, 13));
		// 处理x轴外的乱码
		categoryAxis3D.setLabelFont(new Font("宋体", Font.BOLD, 13));

		// 2.2、获取值轴（y轴）对象(每个种类都不一样，可以输出，获取categoryPlot.getRangeAxis()的返回值)
		NumberAxis3D numberAxis3D = (NumberAxis3D) categoryPlot.getRangeAxis();
		// 处理y轴上的乱码
		numberAxis3D.setTickLabelFont(new Font("宋体", Font.BOLD, 13));
		// 处理y轴外的乱码
		numberAxis3D.setLabelFont(new Font("宋体", Font.BOLD, 13));

		// 处理y轴上显示的刻度
		// 不自动分格
		numberAxis3D.setAutoTickUnitSelection(true);
		NumberTickUnit unit = new NumberTickUnit(1);
		numberAxis3D.setTickUnit(unit);

		// 2.3、获取绘图区域对象(每个种类都不一样，可以输出，获取categoryPlot.getRenderer()的返回值)
		BarRenderer3D barRenderer3D = (BarRenderer3D) categoryPlot.getRenderer();
		// 设置柱形图的宽度
		barRenderer3D.setMaximumBarWidth(0.07);
		// 在图形上显示数字
		barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		// 设置可显
		barRenderer3D.setBaseItemLabelsVisible(true);
		// 设置字体
		barRenderer3D.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 13));

		// 生成图片
		String path = request.getSession().getServletContext().getRealPath("/chart/" + picName);
		File file = new File(path);
		ChartUtilities.saveChartAsJPEG(file, chart, 700, 500);
		System.out.println("图片生成完毕...");
	}
}
