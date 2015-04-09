<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pub.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>编辑设备信息</title>
<script>
   window.onload = function () { 
        new uploadPreview({ UpBtn: "image", DivShow: "imgdiv", ImgShow: "imgShow" });
    }
   
   function update(){
   		if($("#image").val() == ""){
   			$("#Form1").attr("enctype","");
   		}
   		$("#Form1").submit();
   }
</script>


</head>
<body>
<form enctype="multipart/form-data" name="Form1" method="post" id="Form1" class="form-validate" action="${pageContext.request.contextPath }/DeviceMag/deviceAction_deviceUpdate.action">
	<br>
    <table cellSpacing="1" cellPadding="5" width="880" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>编辑设备信息</strong></font>
			</td>
		</tr>
		
	    <tr>
	       <td align="right" bgColor="#f5fafe" class="ta_01" width="20%">设备名：<font color="#FF0000">*</font></td>
	       <td class="ta_01" bgColor="#ffffff" width="30%">
	       		<input type="hidden" name="deviceTypeUuid" id="deviceTypeUuid" value="${device.deviceTypeUuid }">
	       		<input name="deviceName" id="deviceName" maxlength="25" size="20" value="${device.deviceName }"  data-rule-required="true">
	       </td>
	       <td width="18%" align="right" bgColor="#f5fafe" class="ta_01" width="20%">型号：<font color="#FF0000">*</font></td>
	       <td class="ta_01" bgColor="#ffffff" width="30%">
	       		<input name="version" id="version" maxlength="25" size="20" value="${device.version}"  data-rule-storeDomain="true" data-rule-required="true">
	       </td>
	    </tr>
	    
		<tr>
			<td align="right" bgColor="#f5fafe" class="ta_01" width="20%">生产商：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff" width="30%">
	       		<input name="producerName" id="producerName" maxlength="25" size="20" value="${device.producerName }"  data-rule-required="true"> 
	       	</td>
	       	<td align="right" bgColor="#f5fafe" class="ta_01" width="20%">生产商联系方式：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff" width="30%">
	       		<input name="producerPhone" id="producerPhone" maxlength="25" size="20" value="${device.producerPhone}"  data-rule-required="true">
	       	</td>
		</tr>
		<tr>
			<td align="right" bgColor="#f5fafe" class="ta_01" width="20%">设备价格：<font color="#FF0000">*</font></td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<input name="devicePrice" id="devicePrice" maxlength="25" size="20" value="${device.devicePrice }"  data-rule-required="true">
			</td>
			<td align="right" bgColor="#f5fafe" class="ta_01" width="20%">设备数量：<font color="#FF0000">*</font></td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<input name="deviceAmount" id="deviceAmount" maxlength="25" size="20" value="${device.deviceAmount }" data-rule-required="true">
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">设备图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<img alt="设备图片" src="${pageContext.request.contextPath }${device.devicePicUrl}" width="500px" height="300px">
				<input type="hidden" value="${device.devicePicUrl}" id="oldUrl" name="oldUrl"/>
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">修改故障图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:file id="image" name="image"/>
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">修改后故障图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<div id="imgdiv"><img id="imgShow" width="500px" height="300px"/></div>
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="remark" id="remark" style="width:95%" rows="4" cols="52">${device.remark }</textarea>
			</td>
		</tr>
		
		<tr>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</tr>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
			<input type="button" id="BT_Submit" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55" onclick="update()">
			 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
			<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="返回"  name="Reset1"  onClick="history.back()">
			</td>
		</tr>
		<tr align="right">
			<td>温馨提示： <font color="#FF0000">* 为必填项</font></td>
		</tr>
	</table>　
</form>
</body>
</html>