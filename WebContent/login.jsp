<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/login.js"></script>
</head>
<body class="bg_f5"><!--body背景色与默认不一样，为f5f5f5-->
<!--登录页简单的头部-->
<div class="w_990">
	<h1 class="y_logo">
		设备故障申报系统
	</h1>
</div>
<!--登录-->
<div class="y_loginbox">
	<div class="y_loginad"><a href="javascript:void(0);"><img src="images/login1.png"></a></div>	
		<div class="y_loginrt">
		<div class="y_logbox">
			<div class="y_logintab">
				<h2>用户登录</h2>
			</div>
			<div class="y_suslgbx">
				<div class="form-group">
					<label class="control-label" for="sususername">用户名</label>
					<input type="text" required class="form-control" id="sususername" name="username">
				</div>
				<div class="form-group">
					<label class="control-label" for="sususerpasd">密码</label>
					<input type="password" required class="form-control" id="sususerpasd" name="password">
				</div>
				<button id="y_loadlogin" class="btn btn-custom btn-lg btn-block y_mt20" onclick="login();">登 录</button>
			</div>
			<div align="center" id="err"></div>
		</div>
	</div>
</div>
<!-- END -->
</body>
</html>