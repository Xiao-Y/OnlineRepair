package com.xiaoy.base.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xiaoy.base.entites.Menu;

public class ReadMenuXml
{
	
	Boolean flag = true;
	
	@SuppressWarnings("unchecked")
	public List<Menu> readXML()
	{
		ServletContext servletContext = ServletActionContext.getServletContext();
		String realPath = servletContext.getRealPath("/WEB-INF/classes/menu.xml");
		File file = new File(realPath);
		
		// 使用dom4j开始解析xml文件
		SAXReader read = new SAXReader();
		List<Menu> list = new ArrayList<Menu>();
		try
		{
			Document d = read.read(file);
			Element element = d.getRootElement();
			Menu menu = null;
			Iterator<Element> iter = element.elementIterator("menu");
			while (iter.hasNext())
			{
				menu = new Menu();
				Element e = iter.next();
				
				String code = e.elementTextTrim("code");
				String parentCode = e.elementTextTrim("parentCode");
				
				//可以在系统管理的的update配置来true和false来决定是否更新系统数据
				if(code.equals("systemMag"))
				{
					flag = Boolean.parseBoolean(e.elementTextTrim("update"));
				}
				
				if(code.equals("systemMag") || parentCode.equals("systemMag"))
				{
					if(!flag)
					{
						continue;
					}
				}
				
				menu.setCode(code);
				menu.setCodeName(e.elementTextTrim("codeName"));
				menu.setOrderMenu(e.elementTextTrim("order"));
				menu.setUrl(e.elementTextTrim("url"));
				
				if(!StringUtils.isEmpty(parentCode))
				{
					Menu m = new Menu();
					m.setCode(e.elementTextTrim("parentCode"));
					menu.setMenu(m);
				}
				
				list.add(menu);
			}
		} catch (DocumentException e)
		{
			e.printStackTrace();
		}

		return list;
	}
}
