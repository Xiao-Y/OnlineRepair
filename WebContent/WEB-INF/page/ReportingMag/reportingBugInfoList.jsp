<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="x" uri="http://www.xiaoy.com/pageTag/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>故障信息列表</title>
<jsp:include page="/pub.jsp"/>
<script type="text/javascript">
	//清除查询条件
	$().ready(function(){
		$("#BT_Reset").click(function(){
			$("#areaCode").val("");
			$("#installationSiteCode").val("");
			$("#deviceName").val("");
			$("#maintainStatCode").val("");
			$("#name").val("");
			$("#auditStatCode").val("");
			$("#maintainTypeCode").val("");
			$("#maintainUuid").val("");
			
			$("#form1").submit();
		});
	});
	
	//链接跳转
	function link(href){
		window.location.href=href;
	}
</script> 

</head>
<body style="background-color:#F5FAFE;"> 
	<!-- 查询输入start -->
	<form action="" id="form1" name="form1" method="post">
		<table cellspacing="1" cellpadding="0" width="100%" align="center"  border="0">
			<tr>
				<td class="ta_01" colspan=9 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>所有故障信息列表</strong></font>
				</td>
			</tr>
			<tr height=10>
				<td></td>
			</tr>
			<tr>
				<td class="ta_01" align="right"  height="22">区域：</td>
	       		<td class="ta_01">
	       			<s:if test="%{#request.area != null && #request.area.size() > 0}">
			       		<s:select list="%{#request.area}" id="areaCode" name="areaCode"
							  listKey="areaCode" listValue="areaName"
							  headerKey="" headerValue="---请选择---"
							  cssStyle="width:140px"
						/>
	       			</s:if>
	       			<s:else>
						<select id="" name="" style="width:140px"></select>
					</s:else>
				</td>
				<td class="ta_01" align="right"  height="22">安装位置：</td>
				<td class="ta_01" align="left"  height="22">
					<s:if test="%{#request.installationSite != null && #request.installationSite.size() > 0}">
						<s:select list="%{#request.installationSite}" id="installationSiteCode" name="installationSiteCode"
						  listKey="installationSiteCode" listValue="installationSiteName"
						  headerKey="" headerValue="---请选择---"
						  cssStyle="width:140px"
						/>
					</s:if>
					<s:else>
						<select id="" name="" style="width:140px"></select>
					</s:else>
				</td>
				<td class="ta_01" align="right"  height="22">设备名：</td>
				<td class="ta_01" align="left"  height="22">
					<s:if test="%{#request.deviceName != null && #request.deviceName.size() > 0}">
						<s:select list="%{#request.deviceName}" id="deviceName" name="deviceName"
						  listKey="deviceUuid" listValue="deviceName"
						  headerKey="" headerValue="---请选择---"
						  cssStyle="width:140px"
						/>
					</s:if>
				</td>
				
				<td class="ta_01" align="right"  height="22">维护状态：</td>
				<td class="ta_01" align="left"  height="22">
					<s:if test="%{#request.maintainStat != null && #request.maintainStat.size() > 0}">
						<s:select list="%{#request.maintainStat}" id="maintainStatCode" name="maintainStatCode"
						  listKey="ddlCode" listValue="ddlName"
						  headerKey="" headerValue="---请选择---"
						  cssStyle="width:140px"
						/>
					</s:if>
					<s:else>
						<select id="" name="" style="width:140px"></select>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="right"  height="22">维护人员：</td>
				<td class="ta_01" align="left"  height="22">
					<s:if test="%{#request.users != null && #request.users.size() > 0}">
						<s:select list="%{#request.users}" id="maintainUuid" name="maintainUuid"
						  listKey="userUuid" listValue="name"
						  headerKey="" headerValue="---请选择---"
						  cssStyle="width:140px"
						/>
					</s:if>
					<s:else>
						<select id="" name="" style="width:140px"></select>
					</s:else>
				</td>
				
				<td class="ta_01" align="right"  height="22">申报人：</td>
				<td class="ta_01" align="left"  height="22">
					<s:textfield name="name" id="name" size="21" cssStyle="width:134px"/>
				</td>
				<td class="ta_01" align="right"  height="22">审核状态：</td>
				<td class="ta_01" align="left"  height="22">
					<s:if test="%{#request.auditStat != null && #request.auditStat.size() > 0}">
						<s:select list="%{#request.auditStat}" id="auditStatCode" name="auditStatCode"
						  listKey="ddlCode" listValue="ddlName"
						  headerKey="" headerValue="---请选择---"
						  cssStyle="width:140px"
						/>
					</s:if>
					<s:else>
						<select id="" name="" style="width:140px"></select>
					</s:else>
				</td>
				<%--
				<td class="ta_01" align="right"  height="22">维护类别：</td>
				<td class="ta_01" align="left"  height="22">
					<s:if test="%{#request.maintainType != null && #request.maintainType.size() > 0}">
						<s:select list="%{#request.maintainType}" id="maintainTypeCode" name="maintainTypeCode"
						  listKey="ddlCode" listValue="ddlName"
						  headerKey="" headerValue="---请选择---"
						  cssStyle="width:140px"
						/>
					</s:if>
					<s:else>
						<select id="" name="" style="width:140px"></select>
					</s:else>
				</td>
				 --%>
			</tr>
	    </table>	
	<!-- 查询输入end -->
	
	<!-- 执行查询begin -->
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"  border="0">
			<tr height=10><td></td></tr>			
			<tr>
			  	<td>
	                <table style="width: 105px; height: 20px" border="0">
						<tr>
							<td align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
							<td class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">所有故障信息</TD>
						</tr>
		             </table>
	            </td>
				<td class="ta_01" align="right">
					
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Find" type="button" onclick="reportingBugInfoFind();" value="查询" name="BT_Find" >&nbsp;&nbsp;
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Reset" type="button" value="清除" name="BT_Reset" >&nbsp;&nbsp;
					<input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="添加申报信息" name="BT_Add" onclick="link('${pageContext.request.contextPath }/ReportingMag/reportingAction_reportingBugInfoAdd.action')">
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center"  colspan="5">			
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
						style="border-right:gray 1px solid; border-top:gray 1px solid; border-left:gray 1px solid; width:100%; word-break:break-all; border-bottom:gray 1px solid; border-collapse:collapse; background-color:#f5fafe; word-wrap:break-word">
						<!-- 列表标题 begin -->
						<tr style="font-weight:bold;font-size:12pt;height:25px;background-color:#afd1f3">
						    <th align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</th>
						    <th align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">区域</th>
							<th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">安装位置</th>
						    <th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备名</th>
							<th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">申报人</th>
							<th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">申报人手机号</th>
							<th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">申报时间</th>
							<th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">维护状态</th>
							<th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">审核状态</th>
							<th align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">维护人员</th>
							<th align="center" width="5%"  height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">查看</th>
							<th align="center" width="5%"  height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</th>
						</tr>
						<!-- 列表标题 end -->
						
						<!-- 列表数据 begin -->
						<s:if test="%{#request.reportingBugInfoList != null && #request.reportingBugInfoList.size() > 0}">
							<s:iterator value="%{#request.reportingBugInfoList}" var="reportingBugInfo" status="u">
								<tr id='<s:property value="%{#reportingBugInfo.reportingUuid}"/>' onmouseover="this.style.backgroundColor = '#D4E3E5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="HEIGHT:22px" align="center">
										<s:property value="%{#u.getIndex() + 1}"/>
									</td>
							       	<td style="height:22px" align="center">
							       		<s:property value="%{#reportingBugInfo.areaName}"/>
									</td>
									<td style="height:22px" align="center">
										<s:property value="%{#reportingBugInfo.installationSiteName}"/>
									</td>
									<td style="height:22px" align="center">
										<a href="${pageContext.request.contextPath }/DeviceMag/deviceAction_deviceView.action?deviceTypeUuid=${reportingBugInfo.deviceTypeUuid}">
											<s:property value="%{#reportingBugInfo.deviceName}"/>
										</a>
									</td>
									<td style="height:22px" align="center">
										<a href="${pageContext.request.contextPath }/UserMag/userAction_userView.action?userUuid=<s:property value="%{#reportingBugInfo.userUuid}"/>">
											<s:property value="%{#reportingBugInfo.name}"/>
										</a>
									</td>									
									<td style="height:22px" align="center">
										<s:property value="%{#reportingBugInfo.reportingPhone}"/>
									</td>
									<td style="height:22px" align="center">
										<s:property value="%{#reportingBugInfo.reportingTime}"/>
									</td>									
									<td style="height:22px" align="center">
										<s:property value="%{#reportingBugInfo.maintainStatName}"/>
									</td>
																	
									<td style="height:22px" align="center">
										<s:property value="%{#reportingBugInfo.auditStatName}"/>
									</td>
										
									<td style="height:22px" align="center">
										<s:if test="%{#reportingBugInfo.maintainName != '' && #reportingBugInfo.maintainName != null}">
											<a href="${pageContext.request.contextPath }/UserMag/userAction_userView.action?userUuid=<s:property value="%{#reportingBugInfo.maintainUuid}"/>">
												<s:property value="%{#reportingBugInfo.maintainName}"/>
											</a>
										</s:if>
										<s:else>
											<s:property value="暂无"/>暂无
										</s:else>
									</td>
									
									<td align="center" style="HEIGHT: 22px" align="center">																	
									   <a href="${pageContext.request.contextPath }/ReportingMag/reportingAction_reportingBugInfoView.action?reportingUuid=<s:property value="%{#reportingBugInfo.reportingUuid}"/>">
									   <img src="${pageContext.request.contextPath }/images/view.png" width="20px" height="20px" border="0" style="cursor:hand"></a>													
									</td>
									<td align="center" style="HEIGHT: 22px" align="center">
										<a href="javascript:deleteReportingBugInfo('${reportingBugInfo.deviceName }','${reportingBugInfo.reportingUuid }','${reportingBugInfo.auditStatCode }','${reportingBugInfo.auditUuid }');">
										<img src="${pageContext.request.contextPath }/images/delete.gif" width="16" height="16" border="0" style="cursor:hand"></a>												
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td colspan=12 style="HEIGHT:22px" align="center" width="100%">
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
	<x:pager pageNo="${pageNo}" recordCount="${recordCount}" pageSize="${pageSize}" url="${pageContext.request.contextPath}/ReportingMag/reportingAction_reportingBugInfoList.action"/>
	<!-- 执行查询end -->
</body>
</html>