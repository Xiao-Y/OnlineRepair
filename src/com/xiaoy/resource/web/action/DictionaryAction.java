package com.xiaoy.resource.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.action.BaseAction;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.web.form.DictionaryForm;

@SuppressWarnings("serial")
@Controller
public class DictionaryAction extends BaseAction implements ModelDriven<DictionaryForm>
{

	@Resource
	private DictionaryService dictionaryService;

	private DictionaryForm dictionaryForm = new DictionaryForm();

	@Override
	public DictionaryForm getModel()
	{
		return dictionaryForm;
	}

	//获得输入流
	private InputStream inputStream;
	
	public InputStream getInputStream()
	{
		return inputStream;
	}
	
	/**
	 * 查询数据类型关键字，且去掉重复的关键字
	 * @return	home	跳转到dictionaryIndex.jsp
	 */
	public String dictionaryIndex()
	{
		List<DictionaryForm> list = dictionaryService.findKeyWord();
		request.setAttribute("systemList", list);
		return "dictionaryIndex";
	}
	
	/**
	 * 根据选中的数据类型，跳转到相应的编辑页面上
	 * @return	edit	跳转到dictionaryEdit.jsp
	 */
	public String dictionaryEdit()
	{
		String keyWord = dictionaryForm.getKeyWord();
		List<DictionaryForm> list = dictionaryService.findDictionaryListByKeyWord(keyWord);
		request.setAttribute("systemList", list);
		return "dictionaryEdit";
	}

	/**
	 * 保存数据字典
	 * @return save 重定向home
	 */
	public String dictionarySave()
	{
		dictionaryService.saveDictionary(dictionaryForm);
		return "saveDictionary";
	}
	
	/**
	 * 根据主键删除数据项
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String remove()
	{
		String seqID = dictionaryForm.getSeqID();
		Integer i = Integer.parseInt(seqID);
		//DictionaryForm elecSystemDDLForm = dictionaryService.findDictionaryById(i);
		dictionaryService.removeDictionary(i);
		try
		{
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return "ajax-success";
	}
}
