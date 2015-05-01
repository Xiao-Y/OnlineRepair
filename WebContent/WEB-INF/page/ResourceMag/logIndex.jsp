<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="x" uri="http://www.xiaoy.com/pageTag/core"%>
<jsp:include page="/pub.jsp"/>
<html>
<head>
<title>日志管理</title>		
</head>
		
<body style="background-color:#F5FAFE;">
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath }/ResourceMag/logAction_logIndex.action" method="post"> 
			<table cellspacing="1" cellpadding="0" width="100%" align="center" border="0">
				<tr>
					<td class="ta_01" colspan=2 align="center" background="../images/b-info.gif">
						<font face="宋体" size="2"><strong>日志信息管理</strong></font>
					</td>
				</tr>
				<tr height=10><td></td></tr>
				<tr>
					<td class="ta_01" align="center" height="22">
						操作人：<s:textfield name="opeName" value="%{#list.opeName}" id="opeName" cssStyle="width:140px" />
					</td>
				</tr>
		    </table>	
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" border="0">
				<TBODY>
					<tr height=10><td></td></tr>			
					<tr>
					  	<td>
			                <TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
								<TR>
									<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
									<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">日志列表</TD>
								</TR>
				             </TABLE>
	                   </td>
						<td class="ta_01" align="right">
						    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="查询" name="BT_find"  onclick="gotoquery('system/elecLogAction_home.do')"/>&nbsp;&nbsp;
							<input style="font-size:12px; color:black; height=20;width=80" id="BT_Delete" type="button" value="删除当前页日志" name="BT_Delete"  onClick="logDelete('page')" />
							<input style="font-size:12px; color:black; height=20;width=80" id="BT_Delete" type="button" value="删除所有日志" name="BT_Delete"  onClick="logDelete('all')" />
						</td>
					</tr>
				<tr>
					<td class="ta_01" align="center" colspan=3>			
							<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
								<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
									<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
									<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">操作人</td>
									<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">ip地址</td>
									<td align="center" width="25%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">操作时间</td>
									<td align="center" width="45%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">操作情况</td>
								</tr>
								<s:if test="%{#request.formList != null && #request.formList.size() > 0}">
									<s:iterator value="%{#request.formList}" var="list" status="st">
										<tr onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="HEIGHT:22px" align="center" width="5%">
												<s:property value="%{#st.getIndex() + 1}"/>
											</td>
											<s:hidden name="logId" id="logId" value="%{#list.logID}"/>
											<td style="HEIGHT:22px" align="center" width="10%">
												<s:property value="%{#list.opeName}"/>
											</td>
											<td style="HEIGHT:22px" align="center" width="15%">
												<s:property value="%{#list.ipAddress}"/>
											</td>
											<td style="HEIGHT:22px" align="center" width="25%">
												<s:property value="%{#list.opeTime}"/>
											</td>									
											<td style="HEIGHT:22px" align="left" width="45%">
												<s:property value="%{#list.details}"/>
											</td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<tr onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td colspan=4 style="HEIGHT:22px" align="center" width="100%">
											<font color="#FF0000">没有更多数据...</font>
										</td>
									</tr>
								</s:else>
							</table>					
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
		<x:pager pageNo="${pageNo}" recordCount="${recordCount}" pageSize="${pageSize}" url="${pageContext.request.contextPath }/ResourceMag/logAction_logIndex.action"/>
	</body>
</HTML>
