<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="x" uri="http://www.xiaoy.com/pageTag/core"%>
<jsp:include page="/pub.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未通过审核故障列表</title>

<script type="text/javascript">
	//清除查询条件
	$().ready(function(){
		$("#BT_Reset").click(function(){
			$("#deviceName").val("");
			$("#version").val("");
			$("#producer").val("");
			$("#rank").val("");
		});
	});
	
</script> 

</head>
<body style="background-color:#F5FAFE;">
	<!-- 查询输入start -->
	<form id="form1" name="form1" action="" method="post">
		<table cellspacing="1" cellpadding="0" width="100%" align="center" border="0">
			<tr>
				<td class="ta_01" colspan=9 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>审核未通过故障列表</strong></font>
				</td>
			</tr>
			<tr height=10>
				<td></td>
			</tr>
			<tr>
				<td class="ta_01" align="center" height="22">
				区域：</td>
				<td class="ta_01" >
					<select id="role" name="role" style="width: 140px">
						<option>------请选择------</option>				
						<option>教室</option>				
						<option>寝室</option>				
						<option>机房</option>				
					</select>
				</td>
				<td class="ta_01" align="center" height="22">
				设备名：</td>
				<td class="ta_01" >
					<input name="loginName" id="loginName" size="21">
				</td>
				<td class="ta_01" align="center" height="22">
				安装位置：</td>
				<td class="ta_01" >
					<select id="role" name="role" style="width: 140px">
						<option>------请选择------</option>				
						<option>F7650</option>				
						<option>T4325</option>				
						<option>Y5435</option>				
					</select>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" height="22">
				申报人：</td>
				<td class="ta_01" >
					<input name="loginName" id="loginName" size="21">
				</td>
				<td class="ta_01" align="center" height="22">
				申报时间：</td>
				<td class="ta_01" >
					<input class="Wdate" type="text" size="21" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})">
				</td>
				<td class="ta_01" align="center" height="22">
				审核未通过时间：</td>
				<td class="ta_01" >
					<input class="Wdate" type="text" size="21" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})">
				</td>
			</tr>
	    </table>	
	</form>
	<!-- 查询输入end -->
	
	<!-- 执行查询begin -->
	<form action="">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" border="0">
			<tr height=10><td></td></TR>			
			<tr>
			  	<td>
	                <table style="width: 145px; height: 20px" border="0">
						<tr>
							<td align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
							<td class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">审核未通过故障列表</TD>
						</tr>
		             </table>
	            </td>
				<td class="ta_01" align="right">
					
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Find" type="button" value="查询" name="BT_Find" >&nbsp;&nbsp;
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Reset" type="button" value="清除" name="BT_Reset" >&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" colspan="5">			
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
						style="border-right:gray 1px solid; border-top:gray 1px solid; border-left:gray 1px solid; width:100%; word-break:break-all; border-bottom:gray 1px solid; border-collapse:collapse; background-color:#f5fafe; word-wrap:break-word">
						<!-- 列表标题 begin -->
						<tr style="font-weight:bold;font-size:12pt;height:25px;background-color:#afd1f3">
							<th align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</th>
						    <th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">区域</th>
							<th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">安装位置</th>
						    <th align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备名</th>
							<th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">申报人</th>
							<th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">申报人联系方式</th>
							<th align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">申报时间</th>
							<th align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">审核未通过时间</th>
							<th align="center" width="5%"  height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</th>
							<th align="center" width="5%"  height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</th>
						</tr>
						<!-- 列表标题 end -->
						
						<!-- 列表数据 begin -->
						<% 
							for(int i = 0; i < 10; i++){
						%>
						<tr onmouseover="this.style.backgroundColor = '#D4E3E5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
							<td style="HEIGHT:22px" align="center">
								<%=i+1 %>
							</td>
							<td style="height:22px" align="center">
								教室
							</td>
							<td style="height:22px" align="center">
								A4059
							</td>
							<td style="height:22px" align="center">
								<input type="hidden" id="123">
								<a href="${pageContext.request.contextPath }/page/AuditMag/auditInfoRefuseView.jsp">多媒体</a>
							</td>
							<td style="height:22px" align="center">
								<a href="${pageContext.request.contextPath }/page/UserMag/userInfoView.jsp">XiaoY</a>
							</td>									
							<td style="height:22px" align="center">
								12345678907
							</td>									
							<td style="height:22px" align="center">
								2015-02-25
							</td>
							<td style="height:22px" align="center">
								2015-03-09
							</td>										
							<td align="center" style="HEIGHT: 22px" align="center">																	
							   <a href="${pageContext.request.contextPath }/page/AuditMag/auditInfoRefuseEdit.jsp">
							   <img src="${pageContext.request.contextPath }/images/edit.gif" border="0" style="cursor:hand"></a>													
							</td>
							<td align="center" style="HEIGHT: 22px" align="center">
								<a href="system/elecUserAction_delete.do?userID=" onclick="return confirm('你确定要删除  XiaoY ？')">
								<img src="${pageContext.request.contextPath }/images/delete.gif" width="16" height="16" border="0" style="cursor:hand"></a>												
							</td>
						</tr>
						<%} %>
						<!-- 列表数据 end -->
					</table>		
				</td>
			</tr>
		</table>
	</form>
	<!-- 执行查询end -->
</body>
</html>