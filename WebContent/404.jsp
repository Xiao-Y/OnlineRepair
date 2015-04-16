<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404错误-Page Not Found</title>
<link href="${pageContext.request.contextPath }/css/errStyle.css" rel="stylesheet">
</head>
<body>
	<div class="error_wrap">
	<h1>此路不通 -_- !</h1>
	<p>抱歉，您访问的页面地址有误，或者该页面不存在。</p>
	<p>请检查输入的网址是否正确。</p>
	<p>您可以：<a href="${pageContext.request.contextPath }/MenuMag/menuAction_home.action" target="_top">返回首页</a></p>
</div>
</body>
</html>