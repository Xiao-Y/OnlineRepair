package com.xiaoy.resource.servic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.Dictionary;
import com.xiaoy.resource.dao.DictionaryDao;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.web.form.DictionaryForm;

@Service
@Transactional(readOnly = true)
public class DictionaryServiceImpl implements DictionaryService
{
	@Resource
	private DictionaryDao dictionaryDao;

	@Override
	public List<DictionaryForm> findKeyWord()
	{
		List<Object> list = dictionaryDao.findKeyWord();
		List<DictionaryForm> formList = this.dictionaryObjectToVO(list);
		return formList;
	}
	
	@Override
	public List<DictionaryForm> findDictionaryListByKeyWord(String keyWord)
	{
		//根据数据类型查询对应的数据项
		List<Dictionary> list = this.findDictionaryByCondition(keyWord);
		List<DictionaryForm> listForm = this.dictionaryPOListToVOList(list);
		
		return listForm;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void saveDictionary(DictionaryForm dictionaryForm)
	{
		//获取页面类型
		String keyword = dictionaryForm.getKeywordname();
		
		//获取判断是新增还是修改的标识量
		String typeflag= dictionaryForm.getTypeflag();
		
		//获取新增数据项的名称
		String[] itemname = dictionaryForm.getItemname();
		
		//如果typeflag==new表示是新增的数据，如果typeflag==add表示是在原有的数据项上修改或编辑
		if(typeflag != null && typeflag.equals("new"))
		{
			//保存数据字典
			this.saveDictionaryWithParams(keyword, itemname);
		}else
		{
			//分两步：1.先删除原来的数据项。2.添加新的数据项
			//根据数据类型查询对应的数据项
			List<Dictionary> list = this.findDictionaryByCondition(keyword);
			List<Integer> str = new ArrayList<Integer>();
			for(Dictionary d : list)
			{
				str.add(d.getSeqID());
			}
			dictionaryDao.deleteObjectByCollectionIds(str);
			//保存数据字典
			this.saveDictionaryWithParams(keyword, itemname);
		}
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void removeDictionary(Integer seqID)
	{
		dictionaryDao.deleteObjectByid(seqID);
	}

	@Override
	public DictionaryForm findDictionaryById(Integer id)
	{
		Dictionary dictionary = dictionaryDao.findObjectById(id);
		DictionaryForm dictionaryForm = new DictionaryForm();
		dictionaryForm.setKeyWord(dictionary.getKeyWord());
		dictionaryForm.setDdlName(dictionary.getDdlName());
		dictionaryForm.setSeqID(dictionary.getSeqID().toString());
		return dictionaryForm;
	}
	
	/**
	 * 将Object 转换为 VO 对象
	 * @param list	Object
	 * @return	List&ltDictionaryForm&gt
	 */
	private List<DictionaryForm> dictionaryObjectToVO(List<Object> list)
	{
		if(list != null)
		{
			List<DictionaryForm> listForm = new ArrayList<DictionaryForm>();
			for(Object o : list)
			{
				DictionaryForm form = new DictionaryForm();
				form.setKeyWord(o.toString());
				listForm.add(form);
			}
			return listForm;
		}
		return null;
	}
	
	/**
	 * 根据数据类型查询对应的数据项，获取数据项列表
	 * @param keyword	数据类型
	 * @return	List &ltDictionary&gt
	 */
	private List<Dictionary> findDictionaryByCondition(String keyword)
	{
		List<Dictionary> list = dictionaryDao.findCollectionByConditionNoPage(keyword);
		return list;
	}
	
	/**
	 * 数据集合将po对象转换成vo对象
	 * @param list	po对象 
	 * @return	List &ltDictionaryForm&gt
	 */
	private List<DictionaryForm> dictionaryPOListToVOList(List<Dictionary> list)
	{
		if(!list.isEmpty()){
			List<DictionaryForm> listForm = new ArrayList<DictionaryForm>();
			for(Dictionary s : list)
			{
				DictionaryForm form = new DictionaryForm();
				form.setDdlCode(s.getDdlCode().toString());
				form.setDdlName(s.getDdlName());
				form.setKeyWord(s.getKeyWord());
				form.setSeqID(s.getSeqID().toString());
				listForm.add(form);
			}
			return listForm;
		}
		return null;
	}
	
	/**
	 * 保存数据字典
	 * @param keyword	页面类型
	 * @param itemname	数据项（数组）
	 */
	private void saveDictionaryWithParams(String keyword, String[] itemname)
	{
		List<Dictionary> list = new ArrayList<Dictionary>();
		for(int i = 0; itemname != null && i < itemname.length; i++)
		{
			Dictionary dictionary = new Dictionary();
			dictionary.setKeyWord(keyword);
			dictionary.setDdlName(itemname[i]);
			dictionary.setDdlCode(new Integer(i + 1));
			list.add(dictionary);
		}
		dictionaryDao.saveObjectCollection(list);
	}

	@Override
	public Map<String, List<DictionaryForm>> findDictionaryMapKeyWord(Map<String, String> keyWords)
	{
		Map<String, List<DictionaryForm>> map = null;
		if(keyWords != null && keyWords.size() > 0)
		{
			map = new HashMap<String, List<DictionaryForm>>();
			
			for(Entry<String, String> entry : keyWords.entrySet())
			{
				List<DictionaryForm> list = this.findDictionaryListByKeyWord(entry.getValue());
				map.put(entry.getKey(), list);
			}
		}
		return map;
	}
}
