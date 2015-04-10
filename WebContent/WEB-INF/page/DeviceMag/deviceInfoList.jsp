<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="x" uri="http://www.xiaoy.com/pageTag/core"%>
<jsp:include page="/pub.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备信息管理</title>
<script type="text/javascript">
	/**
	//超链接在新窗口显示 
	$(document).ready(function(){
		$("a").attr("target", "_blank");
	});
	*/
	//清除查询条件
	$().ready(function(){
		$("#BT_Reset").click(function(){
			$("#deviceName").val("");
			$("#version").val("");
			$("#producerName").val("");
			
			$("#form1").submit();
		});
	});
	
	//链接跳转
	function link(href){
		window.location.href=href;
	}
</script> 

</head>
<body>
	<!-- 查询输入start -->
	<form action="${pageContext.request.contextPath }/DeviceMag/deviceAction_deviceInfoList.action" id="form1" name="form1" method="post">
		<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
			<tr>
				<td class="ta_01" colspan=6 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>设备信息管理</strong></font>
				</td>
			</tr>
			<tr height=10>
				<td></td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
				设备名：</td>
				<td class="ta_01" >
					<s:if test="%{#request.deviceName != null && #request.deviceName.size() > 0}">
					<s:select list="%{#request.deviceName}" id="deviceName" name="deviceName"
					  listKey="deviceName" listValue="deviceName"
					  headerKey="" headerValue="------请选择------"
					  cssStyle="width:140px" onchange="changeDevice();" data-rule-required="true"
					/>
					</s:if>
					<s:else>
						<select id="deviceName" name="deviceName"></select>
					</s:else>
				</td>
				<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
				型号：</td>
				<td class="ta_01" >
					<s:textfield name="version" id="version" size="21"/>
				</td>
				<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
				生产商：</td>
				<td class="ta_01" >
					<s:textfield name="producerName" id="producerName" size="21"/>
				</td>
			</tr>
	    </table>	
	<!-- 查询输入end -->
	
	<!-- 执行查询begin -->
		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<tr height=10><td></td></TR>			
			<tr>
			  	<td>
	                <table style="width: 105px; height: 20px" border="0">
						<tr>
							<td align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
							<td class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">设备列表</TD>
						</tr>
		             </table>
	                 </td>
				<td class="ta_01" align="right">
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Find" type="submit" value="查询" name="BT_Find" >&nbsp;&nbsp;
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Reset" type="button" value="清除" name="BT_Reset" >&nbsp;&nbsp;
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_delet" onclick="deviceDelete();" type="button" value="批量删除" name="BT_delet" >&nbsp;&nbsp;
					<input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="添加设备" name="BT_Add" onclick="link('${pageContext.request.contextPath }/DeviceMag/deviceAction_toDeviceAdd.action')">
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan="5">			
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
						style="border-right:gray 1px solid; border-top:gray 1px solid; border-left:gray 1px solid; width:100%; word-break:break-all; border-bottom:gray 1px solid; border-collapse:collapse; background-color:#f5fafe; word-wrap:break-word">
						<!-- 列表标题 begin -->
						<tr style="font-weight:bold;font-size:12pt;height:25px;background-color:#afd1f3">
							<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">
								<input type="checkbox" id="checkbox" name="checkbox" onclick="quanxuan();">
							</td>
						  	<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备名</td>
							<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">型号</td>
							<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备数量(件)</td>
							<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备价格</td>
							<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
							<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>
						</tr>
						<!-- 列表标题 end -->
						
						<!-- 列表数据 begin -->
						<s:if test="%{#request.deviceList != null && #request.deviceList.size() > 0}">
						<s:iterator value="%{#request.deviceList}" var="device">
						<tr id="${deviceTypeUuid}" onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
							<td style="HEIGHT:22px" align="center" width="5%">
								<input type="checkbox" id="${deviceTypeUuid }" name="ids" class="ids" value="${deviceTypeUuid }">
							</td>
							<td style="height:22px" align="center" width="20%">
								<a href="${pageContext.request.contextPath }/DeviceMag/deviceAction_deviceView.action?deviceTypeUuid=${deviceTypeUuid}">
									<s:property value="%{#device.deviceName}"/>
								</a>
							</td>
							<td style="height:22px" align="center" width="20%">
								<s:property value="%{#device.version}"/>
							</td>
							<td style="height:22px" align="center" width="10%">
								<s:property value="%{#device.deviceAmount}"/>
							</td>									
							<td style="height:22px" align="center" width="10%">
								<s:property value="%{#device.devicePrice}"/>
							</td>									
							<td align="center" style="HEIGHT: 22px" align="center" width="10%">																	
							   <a href="${pageContext.request.contextPath }/DeviceMag/deviceAction_deviceEdit.action?deviceTypeUuid=${deviceTypeUuid}">
							   <img src="${pageContext.request.contextPath }/images/edit.gif" border="0" style="cursor:hand"></a>													
							</td>
							<td align="center" style="HEIGHT: 22px" align="center" width="10%">
								<a href="javascript:deletesDevice('${device.deviceTypeUuid}','${device.deviceName}')">
								<img src="${pageContext.request.contextPath }/images/delete.gif" width="16" height="16" border="0" style="cursor:hand"></a>												
							</td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
							<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td colspan=6 style="HEIGHT:22px" align="center" width="100%">
									<font color="#FF0000">没有更多数据...</font>
								</td>
							</tr>
						</s:else>
						<!-- 列表数据 end -->
					</table>		
				</td>
			</tr>
		</table>
	</form>
	<x:pager pageNo="${pageNo}" recordCount="${recordCount}" pageSize="${pageSize}" url="${pageContext.request.contextPath }/DeviceMag/deviceAction_deviceInfoList.action"/>
	<!-- 执行查询end -->
</body>
</html>