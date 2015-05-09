<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">  
function exitsys(){
    window.close();   
}
</script>

<title>设备故障申报系统</title>
</head>
	<frameset border=0 frameSpacing="0" rows="85,*" frameborder="no" id="mainparent">
		<frame name=topFrame src="${pageContext.request.contextPath }/MenuMag/menuAction_top.action" noResize scrolling=no>
		<frameset border=0 frameSpacing=0 frameBorder="no" cols=168,*>
			<frame name="leftFrame" src="${pageContext.request.contextPath }/MenuMag/menuAction_left.action" noResize scrolling="auto">
			<frame name="main" scrolling="auto" src="${pageContext.request.contextPath }/MenuMag/menuAction_loading.action">
			<!-- 
			<frame name="main" scrolling="auto" src="${pageContext.request.contextPath }/MenuMag/menuAction_loading.action">
			 -->
		</frameset>
		<!-- 
		<frame name=topFrame src="${pageContext.request.contextPath }/page/MenuMag/buttom.jsp" noResize scrolling=no>
		 -->
	</frameset>
		
</html>