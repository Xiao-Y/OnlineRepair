<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/pub.jsp"/>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/loading.css" />
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
</head>

<body style="background-color: #F5FAFE;">
	<div style="height: 98%; width: 99.8%; overflow: hidden">
		<div>
			<div class="ta_01" align="center"
				style="background-image: url('../images/b-info.gif')">
				<strong>系统首页</strong>
			</div>
		</div>
		<div>
			<fieldset style="width: 100%; padding: 1">
				<legend>
					<font> <img border="0"
						src="${pageContext.request.contextPath }/images/zoom.gif">
						系统公告
					</font>
				</legend>
				<div class="ti">
					<div>公告时间</div>
					<div>标题</div>
					<div>公告内容</div>
					<div>公告人</div>
				</div>
				<br> <br>
				<div class="con">
					<%
						for (int i = 0; i < 100; i++)
						{
					%>
					<div>2015-03-23</div>
					<div>&nbsp;明天</div>
					<div>
						&nbsp;<a href="">明天停水</a>
					</div>
					<div>&nbsp;&nbsp;xiaoy</div>
					<br>
					<%
						}
					%>
				</div>
			</fieldset>
		</div>
		<br>
		<div>
			<fieldset style="width: 100%; padding: 1">
				<legend>
					<font> <img border="0"
						src="${pageContext.request.contextPath }/images/zoom.gif">
						最新申报故障
					</font>
				</legend>
				<div class="ti">
					<div>区域</div>
					<div>位置</div>
					<div>设备名</div>
					<div>型号</div>
				</div>
				<br> <br>
				<div class="con">
					<%
						for (int i = 0; i < 100; i++)
						{
					%>
					<div>教室</div>
					<div>&nbsp;A4049</div>
					<div>
						&nbsp;<a href="">空调</a>
					</div>
					<div>&nbsp;&nbsp;小天鹅</div>
					<br>
					<%
						}
					%>
				</div>
			</fieldset>
		</div>
		<br>
		<div>
			<fieldset style="width: 100%; padding: 1">
				<legend>
					<font> <img border="0"
						src="${pageContext.request.contextPath }/images/zoom.gif">
						用户最新留言
					</font>
				</legend>
				<div class="ti">
					<div>留言时间</div>
					<div>用户名</div>
					<div>留言标题</div>
					<div>回复状态</div>
				</div>
				<br> <br>
				<div class="con">
					<%
						for (int i = 0; i < 100; i++)
						{
					%>
					<div>&nbsp;2015-03-23</div>
					<div>XiaoY</div>
					<div>&nbsp;没电了</div>
					<div>
						&nbsp;&nbsp;<a href="">未回复</a>
					</div>
					<br>
					<%
						}
					%>
				</div>
			</fieldset>
		</div>
	</div>
</body>

</html>
