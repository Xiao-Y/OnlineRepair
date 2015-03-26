package com.xiaoy.base.util;

import java.lang.reflect.ParameterizedType;

/**
 * @author XiaoY
 * 
 * @explain 类型转换器
 * @date: 2014年11月5日 下午9:45:44
 */
public class GenericSuperclass
{
	/**
	 * 泛型类型转换器
	 * 
	 * @param tClass
	 *            要转换的泛型
	 * @return 实体对象
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClass(Class tClass)
	{
		// 两种方法的一样的
		// System.out.println("开始泛型类型转换");
		// ParameterizedType pt =
		// (ParameterizedType)tClass.getGenericSuperclass();
		// Type[] type = pt.getActualTypeArguments();
		// return (Class) type[0];

		System.out.println("开始泛型类型转换");
		ParameterizedType pt = (ParameterizedType) tClass
				.getGenericSuperclass();
		Class entity = (Class) pt.getActualTypeArguments()[0];
		System.out.println("实际类型-------->" + entity);
		return entity;
	}
}
