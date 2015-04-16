<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/pub.css" />

<title>故障详细信息</title>

</head>
<body>
<form name="Form1" method="post">
	<br>
    <table cellSpacing="1" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>故障详细信息</strong></font>
			</td>
		</tr>
	    <tr>
	       <td align="right" bgColor="#f5fafe" class="ta_01">区域：</td>
	       <td class="ta_01" bgColor="#ffffff">
				<s:textfield id="areaName" name="areaName" size="20" readonly="true"/>
			</td>
			
	       <td align="right" bgColor="#f5fafe" class="ta_01">设备名：</td>
	        <td class="ta_01" bgColor="#ffffff">
				<s:textfield id="deviceName" name="deviceName" size="20" readonly="true"/>
			</td>
	    </tr>
	    
		<tr>
			<td align="right" bgColor="#f5fafe" class="ta_01">型号：</td>
			 <td class="ta_01" bgColor="#ffffff">
				<s:textfield id="version" name="version" size="20" readonly="true"/>
			</td>
			
			<td align="right" bgColor="#f5fafe" class="ta_01">安装位置：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="installationSiteName" name="installationSiteName" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right" bgColor="#f5fafe" class="ta_01">维护类别：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="maintainTypeName" name="maintainTypeName" size="20" readonly="true"/>
			</td>
		    <td align="right" bgColor="#f5fafe" class="ta_01">申报人手机号：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="reportingPhone" name="reportingPhone" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right" bgColor="#f5fafe" class="ta_01">维护人员：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="maintainName" name="maintainName" size="20" readonly="true"/>
			</td>
	        <td align="right" bgColor="#f5fafe" class="ta_01">维护人手机号：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="maintainPhone" name="maintainPhone" size="20" readonly="true"/>
			</td>
		</tr>
		
		<tr>
			<td align="right" bgColor="#f5fafe" class="ta_01">申报日期：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="reportingTime" name="reportingTime" size="20" readonly="true"/>
			</td>
			<td align="right" bgColor="#f5fafe" class="ta_01">审核日期：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="auditTime" name="auditTime" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right" bgColor="#f5fafe" class="ta_01">维护日期：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="finishTime" name="finishTime" size="20" readonly="true"/>
			</td>
			<td align="right" bgColor="#f5fafe" class="ta_01">维护状态：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="maintainStatName" name="maintainStatName" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right" bgColor="#f5fafe" class="ta_01">审核状态：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="auditStatName" name="auditStatName" size="20" readonly="true"/>
			</td>
			<td align="right" bgColor="#f5fafe" class="ta_01">评价状态：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="evaluateStatName" name="evaluateStatName" size="20" readonly="true"/>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">上传的图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<div id="imgdiv"><img id="imgShow" width="500px" height="300px" /></div>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">故障原因：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="account" id="account" style="width:95%" rows="4" cols="52" readonly="readonly">rerggdrgrgd</textarea>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">备注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="remark" id="remark" style="width:95%" rows="4" cols="52" disabled="disabled">asdawdawdawdad</textarea>
			</td>
		</tr>
		
		<!-- 如果审核不通过、未处理状态、没有评价这几种情况应该不显示  start-->
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">满意度：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<input type="radio" name="rank" value="A" checked="checked">非常满意&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="radio" name="rank" value="B">满意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="radio" name="rank" value="C">不满意&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="radio" name="rank" value="D">非常不满意
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">我的评价：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="evaluate" id="evaluate" style="width:95%" rows="4" cols="52" disabled="disabled">asasas</textarea>
			</td>
		</tr>
		<!-- 如果审核不通过、未处理状态、没有评价这几种情况应该不显示  end-->
		
		
		<tr>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</tr>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
				<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="返回"  name="Reset1"  onClick="history.back()">
			</td>
		</tr>
	</table>　
</form>
</body>
</html>