<%@ page language="java" pageEncoding="UTF-8"%>
<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
	<tr>
		<td>
			<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="dictTbl" style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
				<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
					<td class="ta_01" align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编号</td>
					<td class="ta_01" align="center" width="60%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">名称</td>
					<td class="ta_01" align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>
				</tr>
				<s:if test="#request.systemList != null && #request.systemList.size() > 0">
					<s:iterator value="%{#request.systemList}">
						<tr>
							<td class="ta_01" align="center" width="20%"><s:property value="%{ddlCode}"/> </td>
							<td class="ta_01" align="center" width="60%"><s:textfield name="itemname" size="45" maxlength="25" value="%{ddlName}"/></td>
							<td class="ta_01" align="center" width="20%">
								<a href="#"  onclick="remove('<s:property value="%{ddlCode}"/>','<s:property value="%{seqID}"/>')" id="${seqID }">
									<img src="../images/delete.gif" width="16" height="16" border="0" style="CURSOR:hand">
								</a>
							</td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr>
						<td class="ta_01" align="center" width="20%">1</td>
						<td class="ta_01" align="center" width="60%"><s:textfield name="itemname" size="45" maxlength="25" value="%{ddlName}"/></td>
						<td class="ta_01" align="center" width="20%"></td>
					</tr>
				</s:else>
			</table>
		</td>
	</tr>
	<tr>
		<td></td>
	</tr>
	<tr height=10>
		<td colspan=3></td>
	</tr>
	<tr>
		<td align="center" colspan=3><input type="button" name="saveitem" value="保存" style="font-size: 12px; color: black;" onClick="return saveDict();"></td>
	</tr>
	<s:hidden name="keywordname"/>
	<s:hidden name="typeflag"/>
</table>