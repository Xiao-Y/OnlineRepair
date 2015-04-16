<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css" />
<title>登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/login.js"></script>
</head>
<body>
	<div class="login">
		<h1>设备故障申报系统</h1>
		<img src="images/loginbg.jpg" />
		<form action="" method="post" id="form1" name="form1">
			<div class="loginbox">
				<p>
					<label>用户名：</label> 
					<input type="text" name="username" id="username"/>
				</p>
				<p>
					<label>密 &nbsp;码：</label> 
					<input type="password" name="password" id="password"/>
				</p>
				<p class="center">
					<a href="javascript:void(0)" class="loginbtn" onclick="login();">登录</a>
				</p>
				<br>
				<div align="center" id="err"></div>
			</div>
		</form>
	</div>
</body>
</html>