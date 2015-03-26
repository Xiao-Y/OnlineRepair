package com.xiaoy.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author XiaoY
 * @explain 
 * 公用类<p>
 * 继承 ActionSupport<br>
 * 实现 ServletRequestAware, ServletResponseAware<br>
 * 
 * @date: 
 * 2014年10月19日 上午11:03:10
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware
{
	protected HttpServletRequest request = null;
	
	protected HttpServletResponse response = null;
	
	@Override
	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}
}
