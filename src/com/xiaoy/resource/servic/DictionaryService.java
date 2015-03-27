package com.xiaoy.resource.servic;

import java.util.List;

import com.xiaoy.resource.web.form.DictionaryForm;

public interface DictionaryService
{
	/**
	 * 查询数据类型，且去掉重复的关键字(接口)
	 * @return	List&ltDictionaryFormm&gt
	 */
	public List<DictionaryForm> findKeyWord();

	/**
	 * 通过数据项名称查询数据字典的集合
	 * @param keyWord	关键字
	 * @return	List&ltDictionaryForm&gt
	 */
	public List<DictionaryForm> findDictionaryListByKeyWord(String keyWord);

	/**
	 * 保存数据字典（数据类型、数据项编号、数据项名称）
	 * @param dictionaryForm vo对象 保存页面传递的表单值
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年11月12日 下午10:15:20
	 */
	public void saveDictionary(DictionaryForm dictionaryForm);

	/**
	 * 根据主键删除数据项
	 * @param seqID		主键
	 */
	public void removeDictionary(Integer seqID);
	
	/**
	 * 通过主键id查询数据字典
	 * @param id	主键id
	 */
	public DictionaryForm findDictionaryById(Integer id);
}
