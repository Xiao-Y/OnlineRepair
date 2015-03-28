package com.xiaoy.resource.dao;

import java.util.List;

import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.Dictionary;

/**
 * 数据字典的接口 
 * @author XiaoY
 * @explain 
 * 
 * @date: 
 * 2015年3月27日 下午9:49:34
 */
public interface DictionaryDao extends Common<Dictionary>
{
	/**
	 * 查询数据类型关键字，且去掉重复的关键字
	 * @return	List &lt Notice &gt
	 */
	public List<Object> findKeyWord();

	/**
	 * 通过数据类型和数据项查询出数据项名称
	 * @param ddlCode	数据项编号
	 * @param keyword	数据类型
	 * @return	String 数据项名称
	 */
	public String findDDLName(String ddlCode, String keyword);

	/**
	 * 根据数据类型查询对应的数据项，获取数据项列表
	 * @param keyword	数据类型
	 * @return	List &ltDictionary&gt
	 */
	public List<Dictionary> findCollectionByConditionNoPage(String keyword);
	
	/**
	 * 通过主键SeqId来删除
	 * @param ids	主键集合
	 */
	public void deleteObjectByCollectionIds(List<Integer> ids);
}
