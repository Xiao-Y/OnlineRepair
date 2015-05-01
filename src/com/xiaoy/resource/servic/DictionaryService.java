package com.xiaoy.resource.servic;

import java.util.List;
import java.util.Map;

import com.xiaoy.resource.web.form.DictionaryForm;

public interface DictionaryService
{
	/**
	 * 查询数据类型，且去掉重复的关键字(接口)
	 * 
	 * @return List&ltDictionaryFormm&gt
	 */
	public List<DictionaryForm> findKeyWord();

	/**
	 * 通过数据项名称查询数据字典的集合
	 * 
	 * @param keyWord
	 *            关键字
	 * @return List&ltDictionaryForm&gt
	 */
	public List<DictionaryForm> findDictionaryListByKeyWord(String keyWord);

	/**
	 * 保存数据字典（数据类型、数据项编号、数据项名称）
	 * 
	 * @param dictionaryForm
	 *            vo对象 保存页面传递的表单值
	 * 
	 * @author XiaoY
	 * @date: 2014年11月12日 下午10:15:20
	 */
	public void saveDictionary(DictionaryForm dictionaryForm);

	/**
	 * 根据主键删除数据项
	 * 
	 * @param seqID
	 *            主键
	 */
	public void removeDictionary(Integer seqID);

	/**
	 * 通过主键id查询数据字典
	 * 
	 * @param id
	 *            主键id
	 */
	public DictionaryForm findDictionaryById(Integer id);

	/**
	 * 通过一组关键字Map<String, String>，查询出数据字典的集合放入到Map<String, List<DictionaryForm>>集合中。
	 * <p/>
	 * Map<String, List<DictionaryForm>>对象的key为Map<String, String>中的key，value为数据字典对象集合
	 * <p/>
	 * 如：Map<String,String> map = new HashMap<String,String>(); <br/>
	 * map.put("maintainTypeName",DictionaryForm.MAINTAIN_TYPE_NAME); <br/>
	 * map.put("auditStat",DictionaryForm.AUDIT_STAT);
	 * <p/>
	 * 
	 * 查询后的Map<String, List<DictionaryForm>>对象为:<br/>
	 * { " maintainTypeName " : 数据字典对象集合1, " auditStat " : 数据字典对象集合2}<br/>
	 * 
	 * @param keyWords
	 *            关键字Map&ltString, String&gt
	 * @return Map&ltString, List&ltDictionaryForm&gt&gt
	 */
	public Map<String, List<DictionaryForm>> findDictionaryMapKeyWord(Map<String, String> keyWords);
}
