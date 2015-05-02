package com.xiaoy.base.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaoy.user.web.form.UserForm;

/**
 * 过滤器
 * @author XiaoY
 * @explain 
 * 过滤 *.do 和	*.jsp的请求
 *
 * @date: 
 * 2014年12月28日 下午3:22:54
 */
public class LoginFilter implements Filter
{

	List<String> list = new ArrayList<String>();

	/**
	 * 1）初始化不能被过滤的连接（存放到List对象中）
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		list.add("/js/jquery-1.8.0.min.js");
		list.add("/css/login.css");
		list.add("/images/login1.png");
		list.add("/images/password.png");
		list.add("/images/user.png");
		list.add("/js/login.js");
		list.add("/login.jsp");
		list.add("/MenuMag/menuAction_login.action");
	}

	/**
	 * 1）获取当前操作的所有连接，与初始化中的不能被过滤的连接进行对比，<br/>
	 * 		如果不包含不被过滤的连接，则不能放行，跳转到首页面<br/>
     * 2）从request中获取session，从session中再获取当前登录的用户信息，<br/>
     * 		判断用户是否为空，如果为空，跳转到首页面<br/>
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		//访问路径
		String path = request.getServletPath();
		if(list != null && list.size() > 0 && list.contains(path))
		{
			chain.doFilter(request, response);
			return;
		}
		
		//从session中获取当前登陆信息
		UserForm userInfo = (UserForm) request.getSession().getAttribute("userInfo");
		
		//不为空，放行
		if(userInfo != null)
		{
			chain.doFilter(request, response);
			return;
		}
		//所有不满足的返回到登陆页面
		response.sendRedirect(request.getContextPath() + "/");
	}

	@Override
	public void destroy()
	{

	}
}
