package com.xiaoy.count.dao;

import java.util.List;

public interface CountDao
{

	/**
	 * 统计用户的性别
	 * @return
	 */
	List<Object[]> userSexCount();

	/**
	 * 用户的维护类型
	 * @return
	 */
	List<Object[]> userTypeCount();

	/**
	 * 设备故障次数统计 
	 * @return
	 */
	List<Object[]> deviceBreakdownCount();

	/**
	 * 设备数量统计
	 * @return
	 */
	List<Object[]> deviceSum();

}
