<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="x" uri="http://www.xiaoy.com/pageTag/core"%>
<jsp:include page="/pub.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询设备状态</title>
<script type="text/javascript">
	//清除查询条件
	$().ready(function(){
		$("#BT_Reset").click(function(){
			$("#areaCode").val("");
			$("#installationSiteCode").val("");
			$("#deviceName").val("");
			$("#version").val("");
			$("#installationTime").val("");
			$("#stateCode").val("");
			$("#form1").submit();
		});
	});
</script> 

</head>
<body style="background-color: #F5FAFE">
	<!-- 查询输入start -->
	<form action="" id="form1" name="form1" method="post">
		<table cellspacing="1" cellpadding="0" width="100%" align="center" border="0">
			<tr>
				<td class="ta_01" colspan=6 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>查询设备状态</strong></font>
				</td>
			</tr>
			<tr height=10>
				<td></td>
			</tr>
			<tr>
				<td class="ta_01" align="center" height="22">
				区域：</td>
				<td class="ta_01" >
					<s:if test="%{#request.area != null && #request.area.size() > 0}">
						<s:select list="%{#request.area}" id="areaCode" name="areaCode"
							  listKey="ddlCode" listValue="ddlName"
							  headerKey="" headerValue="------请选择------"
							  cssStyle="width:140px"
						/>
					</s:if>
					<s:else>
						<select id="" name="" style="width:140px"></select>
					</s:else>
				</td>
				<td class="ta_01" align="center" height="22">
				安装位置：</td>
				<td class="ta_01" >
					<s:if test="%{#request.installationSite != null && #request.installationSite.size() > 0}">
						<s:select list="%{#request.installationSite}" id="installationSiteCode" name="installationSiteCode"
							  listKey="ddlCode" listValue="ddlName"
							  headerKey="" headerValue="------请选择------"
							  cssStyle="width:140px"
						/>
					</s:if>
					<s:else>
						<select id="" name="" style="width:140px"></select>
					</s:else>
				</td>
				<td class="ta_01" align="center" height="22">
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
						<select id="" name="" style="width:140px"></select>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" height="22">
				型号：</td>
				<td class="ta_01" >
					<s:textfield id="version" name="version" size="18"/>
				</td>
				<td class="ta_01" align="center" height="21">
				安装日期：</td>
				<td class="ta_01" >
					<s:textfield cssClass="Wdate" id="installationTime" name="installationTime" size="18" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})"/>
				</td>
				<td class="ta_01" align="center" height="22">
				运行状态：</td>
				<td class="ta_01" >
					<s:if test="%{#request.state != null && #request.state.size() > 0}">
						<s:select list="%{#request.state}" id="stateCode" name="stateCode"
							  listKey="ddlCode" listValue="ddlName"
							  headerKey="" headerValue="------请选择------"
							  cssStyle="width:140px"
						/>
					</s:if>
					<s:else>
						<select id="" name="" style="width:140px"></select>
					</s:else>
				</td>
			</tr>
	    </table>	
	<!-- 查询输入end -->
	
	<!-- 执行查询begin -->
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" border="0">
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
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Find" type="button" value="查询" name="BT_Find" onclick="findDeviceSatae();">&nbsp;&nbsp;
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Reset" type="button" value="清除" name="BT_Reset" >&nbsp;&nbsp;
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Del" type="button" onclick="diviceStateDeletes();" value="批量删除" name="BT_Del" >&nbsp;&nbsp;
					<input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="添加设备" name="BT_Add" onclick="link('${pageContext.request.contextPath }/DeviceMag/deviceStateAction_toDeviceStateAdd.action')">
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" colspan="5">			
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
						style="border-right:gray 1px solid; border-top:gray 1px solid; border-left:gray 1px solid; width:100%; word-break:break-all; border-bottom:gray 1px solid; border-collapse:collapse; background-color:#f5fafe; word-wrap:break-word">
						<!-- 列表标题 begin -->
						<tr style="font-weight:bold;font-size:12pt;height:25px;background-color:#afd1f3">
							<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">
								<input type="checkbox" id="checkbox" name="checkbox" onclick="quanxuan();">
							</td>
						  	<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
						  	<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">区域</td>
						  	<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">安装位置</td>
						  	<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备名</td>
							<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">型号</td>
							<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">安装日期</td>
							<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">运行状态</td>
							<td width="5%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
							<td width="5%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>
						</tr>
						<!-- 列表标题 end -->
						
						<!-- 列表数据 begin -->
						<s:if test="%{#request.formList != null && #request.formList.size() > 0}">
							<s:iterator value="%{#request.formList}" var="list" status="st">
								<tr id=${list.deviceStateUuid } onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="HEIGHT:22px" align="center" width="5%">
										<input type="checkbox" id="${list.deviceStateUuid }" name="ids" class="ids" value="${list.deviceStateUuid}">
									</td>
									<td style="HEIGHT:22px" align="center" width="5%">
										<s:property value="%{#st.getIndex() + 1}"/>
									</td>
									<td style="height:22px" align="center" width="10%">
										<s:property value="%{#list.areaName}"/>
									</td>
									<td style="height:22px" align="center" width="10%">
										<s:property value="%{#list.installationSiteName}"/>
									</td>
									<td style="height:22px" align="center" width="15%">
										<input type="hidden" id="123">
										<a href="${pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceStateView.action?deviceStateUuid=${list.deviceStateUuid}">
											<s:property value="%{#list.deviceName}"/>
										</a>
									</td>
									<td style="height:22px" align="center" width="15%">
										<s:property value="%{#list.version}"/>
									</td>
									<td style="height:22px" align="center" width="20%">
										<s:property value="%{#list.installationTime}"/>
									</td>									
									<td style="height:22px" align="center" width="10%">
										<s:if test="%{#list.stateCode == 1}">
											<s:property value="%{#list.stateName}"/>
										</s:if>
										<s:else><!-- 运行异常，红色显示。可以查看异常情况 -->
											<font color="#FF0000">
												<s:property value="%{#list.stateName}"/>
											</font>
										</s:else>
									</td>
									<td align="center" style="HEIGHT: 22px" align="center" width="5%">																	
									   <a href="${pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceStateEdit.action?deviceStateUuid=${list.deviceStateUuid}">
									   <img src="${pageContext.request.contextPath }/images/edit.gif" border="0" style="cursor:hand"></a>													
									</td>
									<td align="center" style="HEIGHT: 22px" align="center" width="5%">
										<a href="javascript:deviceStateDelete('${list.deviceStateUuid}','${list.deviceName}')">
										<img src="${pageContext.request.contextPath }/images/delete.gif" width="16" height="16" border="0" style="cursor:hand"></a>												
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td colspan=10 style="HEIGHT:22px" align="center" width="100%">
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
	<x:pager pageNo="${pageNo}" recordCount="${recordCount}" pageSize="${pageSize}" url="${pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceStateList.action"/>
	<!-- 执行查询end -->
</body>
</html>