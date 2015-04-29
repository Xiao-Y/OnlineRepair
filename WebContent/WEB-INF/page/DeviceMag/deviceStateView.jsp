<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>查看设备状态信息</title>
</head>
<body>
<form name="Form1" method="post">
	<br>
    <table cellSpacing="1" cellPadding="5" width="700px" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>查看设备状态信息</strong></font>
			</td>
		</tr>
	    <tr>
	       <td align="right" bgColor="#f5fafe" width="20%" class="ta_01">区域：</td>
	       <td class="ta_01" bgColor="#ffffff" width="30%">
	       		<s:textfield name="areaName" id="areaName" maxlength="25" size="20" readOnly="false"/>
	       </td>
	       <td align="right" bgColor="#f5fafe" width="20%" class="ta_01">安装位置：</td>
	       <td class="ta_01" bgColor="#ffffff" width="30%">
	       		<s:textfield name="installationSiteName" id="installationSiteName" maxlength="25" size="20" readOnly="false"/>
	       </td>
	    </tr>
	    
		<tr>
			<td align="right" bgColor="#f5fafe" width="20%" class="ta_01">设备名：</td>
	       	<td class="ta_01" bgColor="#ffffff" width="30%">
	       		<s:textfield name="deviceName" id="deviceName" maxlength="25" size="20" readOnly="false"/>
	       	</td>
			<td align="right" bgColor="#f5fafe" width="20%" class="ta_01">型号：</td>
	       	<td class="ta_01" bgColor="#ffffff" width="30%">
	       		<s:textfield name="version" id="version" maxlength="25" size="20" readOnly="false"/>
	      	</td>
		</tr>
		<tr>
			<td align="right" bgColor="#f5fafe" width="20%" class="ta_01">上次检修日期：</td>
	       	<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield name="lastTime" id="lastTime" maxlength="25" size="20" readOnly="false"/>
	       	</td>
		    <td align="right" bgColor="#f5fafe" width="20%" class="ta_01">下次检修日期：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield name="nextTime" id="nextTime" maxlength="25" size="20" readOnly="false"/>
			</td>
		</tr>
		
		<tr>
			<td align="right" bgColor="#f5fafe" width="20%" class="ta_01">安装日期：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield name="installationTime" id="installationTime" maxlength="25" size="20" readOnly="false"/>
			</td>
		    <td align="right" bgColor="#f5fafe" width="20%" class="ta_01">运行状态：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield name="stateName" id="stateName" maxlength="25" size="20" readOnly="false"/>
			</td>
		</tr>

		<tr>
			<td class="ta_01" align="right" width="20%" bgcolor="#f5fafe">设备图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<img alt="设备图片" src="${pageContext.request.contextPath }${devicePicUrl}" width="500px" height="300px">
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="right" width="20%" bgcolor="#f5fafe">备注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="remark" id="remark" style="width:90%" rows="4" cols="52" readOnly="true"/>
			</td>
		</tr>
		
		<TR>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</TR>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
				<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
				<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="返回"  name="Reset1"  onClick="history.back()">
			</td>
		</tr>
	</table>　
</form>
</body>
</html>