<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pub.jsp"/>
<html>
<head>
<title>资源管理</title>

</head>

<body style="background-color:#F5FAFE;">
	<s:form name="Form1" id="Form1" method="post" cssStyle="margin: 0px;">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0" id="tb">
			<tbody>
				<tr>
					<td class="ta_01" colspan=3 align="center"background="${pageContext.request.contextPath }/images/b-info.gif">
						<font face="宋体" size="2"><strong>数据字典维护</strong></font>
					</td>
				</tr>
				<tr height=10>
					<td colspan=3></td>
				</tr>
				<tr>
					<td class="ta_01" align="right" width="35%">类型列表：</td>
					<td class="ta_01" align="left" width="30%">
					<s:select list="%{#request.systemList}" id="keyWord" name="keyWord" 
								cssClass="bg" cssStyle="width: 176px" onchange="changetype()"
								listKey="%{keyWord}" listValue="%{keyWord}"
								headerKey="jerrynew" headerValue="添加新类型名称"
						>
					</s:select>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="right" width="35%" id="newtypename">类型名称：</td>
					<td class="ta_01" align="left" width="30%" height=22 id="newddlText">
						<s:textfield name="keywordname" maxlength="25" size="23"/>
					</td>
					<td class="ta_01" align="right" width="35%"></td>
				</tr>

				<tr height=10>
					<td colspan=3 align="right"><input type="button" name="saveitem" value="添加选项" style="font-size: 12px; color: black;" onClick="addTr()"></td>
				</tr>
			</tbody>
		</table>
	</s:form>

	<s:form name="Form2" id="Form2" method="post" cssStyle="margin: 0px;">
		<table cellSpacing="1" id="tableOld" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<tr>
				<td>
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="dictTbl" style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td class="ta_01" align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编号</td>
							<td class="ta_01" align="center" width="60%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">名称</td>
							<td class="ta_01" align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>
						</tr>
						<tr>
							<td class="ta_01" align="center" width="20%">1</td>
							<td class="ta_01" align="center" width="60%"><s:textfield name="itemname" size="45" maxlength="25"/></td>
							<td class="ta_01" align="center" width="20%"></td>
						</tr>
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
			<s:hidden name="keywordname" id="keywordname"/>
			<s:hidden name="typeflag" id="typeflag"/>
		</table>
	</s:form>
</body>
</html>


