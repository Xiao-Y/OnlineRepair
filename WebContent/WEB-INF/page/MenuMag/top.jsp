<%@page import="com.xiaoy.user.web.form.UserForm"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%> <%-- 获取系统时间必须导入的--%> 
<%@ page import="java.text.*"%> <%-- 获取系统时间必须导入的--%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>top</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.0.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/pub.css" />
</head>
<body>
	<form id=Form1 name=Form1 method=post>
		<table border="0" width="100%" height="9" cellspacing="0" cellpadding="0">
			<tr>
				<td width="100%" colspan="4"
					background="${pageContext.request.contextPath }/images/title.jpg"
					height="58"></td>
			</tr>
			<tr>
				<td width="100%" height="1" bgcolor="#66C89C" colspan="4"><img
					border="0"
					src="${pageContext.request.contextPath }/images/titleline.jpg"
					width="100%" height="3"></td>
			</tr>
			<tr>
				<td width="20%" height="19" bgcolor="#0965CA">
					<table border="0" width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td width="10%" align="center"><font color="#FFFFFF"><b>|</b></font></td>
							<td width="85%" align="center">
								<font color=#FFFFFF> 
									<!-- 显示当前时间 -->
									<b id="time">
									<script>
										document.getElementById('time').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());
										setInterval("document.getElementById('time').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
									</script>
									</b>
								</font>
							</td>
							<td width="15%" align="center"><font color="#FFFFFF"><b>|</b></font></td>
						</tr>
					</table>
				<td width="30%" height="19" bgcolor="#0965CA">
					<table cellSpacing="2" height="19" cellPadding="0" width="100%" border="0">
						<tr>
							<td valign="top"><b><font color="#FFFFFF">&nbsp;&nbsp;${userInfo.name }&nbsp;欢迎您!&nbsp;&nbsp; </font></b>
							</td>
							<td width="5%" align="center"><font color="#FFFFFF"><b>|</b></font></td>
							<td valign="top"><b><font color="#FFFFFF">角色：&nbsp;${roleStr } </font></b>
							</td>
						</tr>
					</table>
				</td>
				<td width="30%" height="19" bgcolor="#0965CA"></td>
				<td width="18%" height="19" bgcolor="#0965CA" align="center">
					<table border="0" width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td width="100%" align="center">
								<a href="${pageContext.request.contextPath }/MenuMag/menuAction_loading.action" target="main">
									<font color="#FFFFFF"><b>返回首页</b></font>
								</a>
								<font color="#FFFFFF"><b>|</b></font>
								<a href="${pageContext.request.contextPath }/login.jsp" target="_top"> 
									<font color="#FFFFFF"><b>重新登录</b></font>
								</a> 
								<font color="#FFFFFF"><b>|</b></font>
								<a href="${pageContext.request.contextPath }/login.jsp" target="_top">
									<font color="#FFFFFF"><b>退出系统</b></font>
								</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
