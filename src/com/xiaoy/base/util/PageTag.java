package com.xiaoy.base.util;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * @author XiaoY
 * @explain 自定义分页标签 <br/>
 *          使用方式: &lt x:pager pageSize="10" pageNo="1" recordCount="100" url="index.jsp" / &gt
 * 
 * @date: 2015年3月15日 下午8:58:29
 */
@SuppressWarnings("serial")
public class PageTag extends TagSupport
{
	private int pageSize = 10; // 每页要显示的记录数
	private int pageNo = 1; // 页号
	private int recordCount; // 总记录数
	private String url; // 目的地URL

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public void setRecordCount(int recordCount)
	{
		this.recordCount = recordCount;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	// 主要的逻辑
	@Override
	public int doStartTag() throws JspException
	{
		if (recordCount == 0)
		{
			return super.doStartTag();
		}

		// 总页数
		int pageCount = (recordCount + pageSize - 1) / pageSize;
		// 页号越界处理
		if (pageNo > pageCount)
		{
			pageNo = pageCount;
		}
		if (pageNo < 1)
		{
			pageNo = 1;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("\r\n<div class='div_page'>\r\n");
		sb.append("<form name='pageController' id='pageController' action='' method='post'> &nbsp&nbsp;");
		sb.append("<table class='table_page'>");
		sb.append("<tr class='tr_page'>");
		sb.append("<input type='hidden' id='pageNo' name='pageNo' value='" + pageNo + "' />&nbsp;&nbsp;");

		// ------------------------------------ 获取所有請求中的参数
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Enumeration<String> enumeration = request.getParameterNames();
		String name = null;
		String value = null;
		// 把请求中的所有参数当作隐藏表单域在页面中写出)
		while (enumeration.hasMoreElements())
		{
			name = enumeration.nextElement();
			value = request.getParameter(name);
			// 去除页号
			if (name.equals("pageNo"))
			{
				if (null != value && !"".equals(value))
				{
					pageNo = Integer.parseInt(value);
				}
				continue;
			}
			sb.append("<input type='hidden' name='").append(name).append("' value='").append(value).append("'/>&nbsp;");
		}
		// ----------------------------------------------------
		sb.append("<td class='td_span'>总共<span>" + recordCount + "</span>条记录&nbsp;&nbsp;&nbsp;分<span>" + pageNo + "/" + pageCount + "</span>页</td>");
		sb.append("<td width='35%'></td>");
		if (pageNo == 1)
		{
			sb.append("<td>首页</td>");
			sb.append("<td>上一页</td>");
		} else
		{
			sb.append("<td><a href='javascript:void(0);' onclick='turnOverPage(1)'>首页</a></td>");
			sb.append("<td><a href='javascript:void(0);' onclick='turnOverPage(").append((pageNo - 1)).append(")'>上一页</a></td>");
		}
		sb.append(" ");
		if (pageNo == pageCount)
		{
			sb.append("<td>下一页</td>");
			sb.append("<td>尾页</td>");
		} else
		{
			sb.append("<td><a href='javascript:void(0);' onclick='turnOverPage(").append((pageNo + 1)).append(")'>下一页</a></td>");
			sb.append("<td><a href='javascript:void(0);' onclick='turnOverPage(").append(pageCount).append(")'>尾页</a></td>");
		}

		sb.append("<td>跳到&nbsp;&nbsp;&nbsp;<select style='width: 100px' onChange='turnOverPage(this.value)'>");
		for (int i = 1; i <= pageCount; i++)
		{
			if (i == pageNo)
			{
				sb.append("  <option value='").append(i).append("' selected='selected'>第").append(i).append("页</option>&nbsp;");
			} else
			{
				sb.append("  <option value='").append(i).append("'>第").append(i).append("页</option>&nbsp;");
			}
		}
		sb.append("</select></td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</form>");

		// 生成提交表单的JS

		sb.append("<script type='text/javascript'>");
		sb.append("  function turnOverPage(no){");
		sb.append("    var form = document.pageController;");
		sb.append("    if(no").append(">").append(pageCount).append(") {");
		sb.append("        no=").append(pageCount).append(";");
		sb.append("    }");
		sb.append("    if(no").append("< 1){");
		sb.append("        no=1;");
		sb.append("    }");
		sb.append("    form.").append("pageNo").append(".value=no;");
		sb.append("    form.action='").append(url).append("';");
		sb.append("    form.submit();");
		sb.append("  }");
		sb.append("</script>");

		sb.append("</div>");
		try
		{
			pageContext.getOut().println(sb.toString());
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return super.doStartTag();
	}
}
