<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="查询" name="BT_find"  onclick="$('#Form1').submit()"/>&nbsp;&nbsp;
				<input style="font-size:12px; color:black; height=20;width=80" id="BT_Delete" type="button" value="删除所有日志" name="BT_Delete"  onclick="conDelete()" />
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
					<s:if test="%{#request.formList != null}">
						<s:iterator value="%{#request.formList}" var="list">
							<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<s:hidden name="logid" id="logid" value="%{#list.logId}"/>
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
				</table>					
			</td>
		</tr>
	</TBODY>
</table>
