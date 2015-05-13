package com.xiaoy.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author XiaoY
 * @explain 时间类型的帮助类
 *          <p>
 * 
 * @date: 2014年10月19日 上午11:31:38
 */
public class DateHelper
{

	/**
	 * String 类型的时间转换成 Date类型的<br>
	 * 格式：yyyy-MM--dd<br>
	 * 
	 * @param textDate
	 *            要转换的时间
	 * @return Date
	 */
	public static Date stringConverDate(String textDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try
		{
			if(!textDate.isEmpty() && textDate.length() != 0)
			{
				date = sdf.parse(textDate);
			}
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * String 类型的时间转换成 Date类型的<br>
	 * 格式：yyyy-MM-dd hh:mm:ss<br>
	 * 
	 * @param textDateTime
	 *            要转换的时间
	 * @return Date
	 */
	public static Date stringConverDateTime(String textDateTime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try
		{
			if(!textDateTime.isEmpty() && textDateTime.length() != 0)
			{
				date = sdf.parse(textDateTime);
			}
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将时间类型的转换成字符串类型
	 * @param date 时间类型
	 * @return 字符串类型的时间 格式为"yyyy-MM-dd"
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年10月25日 上午12:21:49
	 */
	public static String dateConverString(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = null;
		strdate = date != null ? sdf.format(date) : ""; 
		return strdate;
	}
	
	/**
	 * 将时间类型的转换成字符串类型
	 * @param dateTime 时间类型
	 * @return 字符串类型的时间 格式为"yyyy-MM-dd hh:mm:ss"
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年10月25日 上午12:21:49
	 */
	public static String dateTimeConverString(Date dateTime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strdate = null;
		strdate = dateTime != null ? sdf.format(dateTime) : ""; 
		return strdate;
	}
	
	/**
	 * 将时间类型的转换成指定格式的字符串类型
	 * @param dateTime 时间类型
	 * @param format 指定的格式
	 * @return 字符串类型的时间
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年10月25日 上午12:21:49
	 */
	public static String dateTimeConverString(Date dateTime, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String strdate = null;
		strdate = dateTime != null ? sdf.format(dateTime) : ""; 
		return strdate;
	}
}
