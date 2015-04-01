<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="x" uri="http://www.xiaoy.com/pageTag/core"%>

<html>
	<head>
		<title>用户管理</title>		
		<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath }/css/pageTag.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/myJquery.js"></script>
	</head>
		
	<body >
		<s:form id="Form1" name="Form1" action="" method="post" cssStyle="margin:0px;"> 
			<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
				<tr height=10><td></td></tr>
				<tr>
					<td class="ta_01" colspan=2 align="center" background="../images/b-info.gif">
						<font face="宋体" size="2"><strong>日志信息管理</strong></font>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
						操作人：<s:textfield name="opeName" value="%{#list.opeName}" id="opeName" cssStyle="width:140px" />
					</td>
				</tr>
		    </table>	
		</s:form>
		<s:form id="Form2" name="Form2" action="" method="post">
			<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<TR height=10><td></td></TR>			
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
							<input style="font-size:12px; color:black; height=20;width=80" id="BT_Delete" type="button" value="删除所有日志" name="BT_Delete"  onClick="logDelete()" />
						</td>
					</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>			
							<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
								<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
									<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">操作人</td>
									<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">ip地址</td>
									<td align="center" width="25%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">操作时间</td>
									<td align="center" width="45%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">操作情况</td>
								</tr>
								<s:if test="%{#request.formList != null && #request.formList.size() > 0}">
									<s:iterator value="%{#request.formList}" var="list">
										<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<s:hidden name="logId" id="logId" value="%{#list.logID}"/>
											<td style="HEIGHT:22px" align="center" width="10%">
												<s:property value="%{#list.opeName}"/>
											</td>
											<td style="HEIGHT:22px" align="center" width="20%">
												<s:property value="%{#list.ipAddress}"/>
											</td>
											<td style="HEIGHT:22px" align="center" width="25%">
												<s:property value="%{#list.opeTime}"/>
											</td>									
											<td style="HEIGHT:22px" align="center" width="45%">
												<s:property value="%{#list.details}"/>
											</td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td colspan=4 style="HEIGHT:22px" align="center" width="100%">
											没有更多数据...
										</td>
									</tr>
								</s:else>
							</table>					
						</td>
					</tr>
				</TBODY>
			</table>
		</s:form>
		<x:pager pageNo="${pageNo}" recordCount="${recordCount}" pageSize="${pageSize}" url="${pageContext.request.contextPath }/ResourceMag/logAction_logIndex.action"/>
	</body>
</HTML>
