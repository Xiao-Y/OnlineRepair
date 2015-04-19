<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pub.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body {
	background-color:#F5FAFE; 	
}
td {
	background-color: #F5FAFE;
}
</style>
<title>查看用户信息</title>

</head>
<body>
	<form name="Form1" method="post">
		<br>
	    <table cellSpacing="1" cellPadding="5" width="700px" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>查看用户信息</strong></font>
				</td>
			</tr>
		    <tr>
		       <td align="right" class="ta_01">登陆名：</td>
		       <td class="ta_01" bgColor="#ffffff">
		       		<s:textfield name="loginName" id="loginName" maxlength="25" size="20" cssStyle="width: 134px" readonly="true"/>
		       </td>
		       <td width="18%" align="right" class="ta_01">姓名：</td>
		       <td class="ta_01" bgColor="#ffffff">
		       		<s:textfield name="name" id="name" maxlength="20" size="20" cssStyle="width: 134px" readonly="true"/>
		       </td>
		    </tr>
		    
			<tr>
				<td align="right" class="ta_01">性别：</td>
		       	<td class="ta_01" bgColor="#ffffff">
		       		<s:textfield name="sex" id="sex" maxlength="20" size="20" cssStyle="width: 134px" readonly="true"/>
		       	</td>
		       	<td align="right" class="ta_01">注册日期：</td>
				<td class="ta_01" bgColor="#ffffff">
					<s:textfield name="registerTime" id="registerTime" maxlength="20" size="20" cssStyle="width: 134px" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td align="right" class="ta_01">维护类别：</td>
		       	<td class="ta_01" bgColor="#ffffff">
		       		<s:textfield name="maintainType" id="maintainType" maxlength="20" size="20" cssStyle="width: 134px" readonly="true"/>
		       	</td>
			    <td align="right" class="ta_01">联系方式：</td>
				<td class="ta_01" bgColor="#ffffff">
					<s:textfield name="phone" id="phone" maxlength="20" size="20" cssStyle="width: 134px" readonly="true"/>
				</td>
			</tr>
			<tr>
			    <td align="right" class="ta_01">地址：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
					<s:textarea name="address" id="address" cssStyle="width:85%" rows="3" cols="52"/>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="right">备注：</td>
				<td class="ta_01" bgcolor="#ffffff" colspan="3">
					<s:textarea name="remark" id="remark" cssStyle="width:85%" rows="4" cols="52"/>
				</td>
			</tr>
			
			<TR>
				<td  align="center"  colSpan="4"  class="sep1"></td>
			</TR>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center" colSpan="4">
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="返回"  name="Reset1"  onClick="history.back()">
				</td>
			</tr>
		</table>　
	</form>
</body>
</html>