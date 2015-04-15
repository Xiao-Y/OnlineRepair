package com.xiaoy.base.web.action;

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
	
	/**
	 * 执行成功，1
	 */
	protected static final String SUCCESS = "1";
	
	/**
	 * 执行失败，0
	 */
	protected static final String FAIL = "0";
	
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
