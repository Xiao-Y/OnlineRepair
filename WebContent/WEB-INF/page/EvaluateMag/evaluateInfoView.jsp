<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"/>
<style type="text/css">
body {
	background-color:#F5FAFE; 	
}
td {
	background-color: #F5FAFE;
}
</style>

<title>用户评价</title>

</head>
<body>
<form name="Form1" id="Form1" method="post">
	<br>
    <table cellSpacing="1" cellPadding="5" width="700" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>用户评价</strong></font>
			</td>
		</tr>
	    <tr>
	       <td align="right" class="ta_01">区域：</td>
	       	<td class="ta_01">
				<s:textfield name="areaName" id="areaName" readonly="true"/>
			</td>
			
	       <td align="right" class="ta_01">设备名：</td>
	       <td class="ta_01">
	       		<s:textfield name="installationSiteName" id="installationSiteName" readonly="true"/>
	       </td>
	    </tr>
	    
		<tr>
			<td align="right" class="ta_01">型号：</td>
	       	<td class="ta_01">
	       		<s:textfield name="version" id="version" readonly="true"/>
	      	</td>
	      	
			<td align="right" class="ta_01">安装位置：</td>
	       	<td class="ta_01">
	       		<s:textfield name="installationSiteName" id="installationSiteName" readonly="true"/>
	       	</td>
		</tr>
		<tr>
			<td align="right" class="ta_01">维护人员：</td>
	       	<td class="ta_01">
	       		<s:textfield name="maintainName" id="maintainName" readonly="true"/>
	       	</td>
	       	<td align="right" class="ta_01">维护人员手机号：</td>
			<td class="ta_01">
				<s:textfield name="maintainTypePhone" id="maintainTypePhone" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right" class="ta_01">维护类别：</td>
	       	<td class="ta_01">
	       		<s:textfield name="maintainTypeName" id="maintainTypeName" readonly="true"/>
	       	</td>
		    <td align="right" class="ta_01">申报人手机号：</td>
			<td class="ta_01">
				<s:textfield name="reportingPhone" id="reportingPhone" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right" class="ta_01">申报日期：</td>
			<td class="ta_01">
				<s:textfield name="reportingTime" id="reportingTime" readonly="true"/>
			</td>
			<td align="right" class="ta_01">预约日期：</td>
			<td class="ta_01">
				<s:textfield name="orderTime" id="orderTime" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right" class="ta_01">审核日期：</td>
			<td class="ta_01">
				<s:textfield name="auditTime" id="auditTime" readonly="true"/>
			</td>
			<td align="right" class="ta_01">维护日期：</td>
			<td class="ta_01">
				<s:textfield name="finishTime" id="finishTime" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">满意度：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textfield name="rankName" id="rankName" readonly="true"/>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">上传的图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<div id="imgdiv"><img id="" width="500px" height="300px" src="${pageContext.request.contextPath }${devicePicUrl }" /></div>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">故障原因：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="account" id="account" style="width:95%" rows="4" cols="52" readonly="readonly">${account }</textarea>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">备注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="remark" id="remark" style="width:95%" rows="4" cols="52" readonly="readonly">${remark }</textarea>
			</td>
		</tr>
		<!-- 
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">我的评价：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="evaluate" id="evaluate" style="width:95%" rows="4" cols="52"></textarea>
			</td>
		</tr>
		 -->
		
		<tr>
			<td  align="right"  colspan="4"  class="sep1"></td>
			<s:hidden name="evaluateUuid" id="evaluateUuid"/>
		</tr>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" colSpan="4">
				<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
				<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="history.back();">
			</td>
		</tr>
	</table>　
</form>
</body>
</html>