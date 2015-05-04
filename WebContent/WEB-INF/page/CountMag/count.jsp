<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>统计报表</title>
<script type="text/javascript">
//链接跳转
function link(href){
	window.location.href=href;
}
</script>
</head>

<body  style="background-color:#F5FAFE;">
	<s:if test="%{#request.flag == 1 || #request.flag == ''}">
		<input style="font-size:12px; color:black; height=20;width=80" type="button" value="切换条形图" onclick="link('${pageContext.request.contextPath }${path}?flag=0')">
	</s:if>
	<s:else>
		<input style="font-size:12px; color:black; height=20;width=80" type="button" value="切换饼状图" onclick="link('${pageContext.request.contextPath }${path}?flag=1')">
	</s:else>
	<div align="center">
		<img style="width: 700px; height: 500px" src="${pageContext.request.contextPath}/chart/${picName}" border="0">
	</div>
</body>
</html>