<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/pub.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>查看设备信息</title>

</head>
<body>
<form name="Form1" method="post">
	<br>
    <table cellSpacing="1" cellPadding="5" width="600" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>查看设备信息</strong></font>
			</td>
		</tr>
		
	    <tr>
	       <td align="right" bgColor="#f5fafe" class="ta_01" width="20%">设备名：</td>
	       <td class="ta_01" bgColor="#ffffff" width="30%">
	       		<input name="deviceName" id="deviceName" maxlength="25" size="20" value="${device.deviceName }" readonly ="true">
	       </td>
	       <td width="18%" align="right" bgColor="#f5fafe" class="ta_01" width="20%">型号：</td>
	       <td class="ta_01" bgColor="#ffffff" width="30%">
	       		<input name="version" id="version" maxlength="25" size="20" value="${device.version}" readonly ="true">
	       </td>
	    </tr>
	    
		<tr>
			<td align="right" bgColor="#f5fafe" class="ta_01" width="20%">生产商：</td>
	       	<td class="ta_01" bgColor="#ffffff" width="30%">
	       		<input name="producerName" id="producerName" maxlength="25" size="20" value="${device.producerName }" readonly ="true">
	       	</td>
	       	<td align="right" bgColor="#f5fafe" class="ta_01" width="20%">生产商联系方式：</td>
	       	<td class="ta_01" bgColor="#ffffff" width="30%">
	       		<input name="producerPhone" id="producerPhone" maxlength="25" size="20" value="${device.producerPhone}" readonly ="true">
	       	</td>
		</tr>
		<tr>
			<td align="right" bgColor="#f5fafe" class="ta_01" width="20%">设备价格：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<input name="devicePrice" id="devicePrice" maxlength="25" size="20" value="${device.devicePrice }" readonly ="true">
			</td>
			<td align="right" bgColor="#f5fafe" class="ta_01" width="20%">设备数量：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<input name="deviceAmount" id="deviceAmount" maxlength="25" size="20" value="${device.deviceAmount }" readonly ="true">
			</td>
		</tr>

		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">设备图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<img alt="设备图片" src="${pageContext.request.contextPath }${device.devicePicUrl}" width="500px" height="300px">
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">备注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="remark" id="remark" style="width:95%" rows="4" cols="52" readonly ="true">${device.remark }</textarea>
			</td>
		</tr>
		
		<tr>
			<td  align="center"  colspan="4"  class="sep1"></td>
		</tr>
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