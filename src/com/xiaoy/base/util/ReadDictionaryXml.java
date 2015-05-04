package com.xiaoy.base.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xiaoy.base.entites.Dictionary;

public class ReadDictionaryXml
{
	@SuppressWarnings("unchecked")
	public List<Dictionary> readXML()
	{
		ServletContext servletContext = ServletActionContext.getServletContext();
		String realPath = servletContext.getRealPath("/WEB-INF/classes/dictionary.xml");
		File file = new File(realPath);
		
		// 使用dom4j开始解析xml文件
		SAXReader read = new SAXReader();
		List<Dictionary> list = new ArrayList<Dictionary>();
		try
		{
			Document d = read.read(file);
			Element element = d.getRootElement();
			Iterator<Element> iter = element.elementIterator("root");
			
			Dictionary dictionary = null;
			while (iter.hasNext())
			{
				dictionary = new Dictionary();
				Element e = iter.next();
				
				dictionary.setKeyWord(e.elementTextTrim("keyword"));
				dictionary.setDdlCode(Integer.parseInt(e.elementTextTrim("ddlcode")));
				dictionary.setDdlName(e.elementTextTrim("ddlname"));
				
				list.add(dictionary);
			}
		} catch (DocumentException e)
		{
			e.printStackTrace();
		}

		return list;
	}
}
