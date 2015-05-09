package com.xiaoy.count.sevice;

import java.util.Map;

public interface CountService
{

	/**
	 * 统计用户的性别
	 * @return	Map&ltString, Double&gt String 性别类型，Double 数量
	 */
	Map<String, Double> userSexCount();

	/**
	 * 用户的维护类型
	 * @return	Map&ltString, Double&gt String 维护类型，Double 数量
	 */
	Map<String, Double> userTypeCount();

	/**
	 * 设备故障次数统计
	 * @return
	 */
	Map<String, Double> deviceBreakdownCount();

	/**
	 * 设备数量统计
	 * @return
	 */
	Map<String, Double> deviceSum();

	/**
	 * 统计维护人员的各种评价
	 * @param userUuid	维护人员的uuid
	 * @return
	 */
	Map<String, Double> evaluateCount(String userUuid);

}
