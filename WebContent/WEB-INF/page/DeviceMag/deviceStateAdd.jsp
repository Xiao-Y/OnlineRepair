<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pub.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>添加设备状态信息</title>
<script>
   window.onload = function () { 
        new uploadPreview({ UpBtn: "image", DivShow: "imgdiv", ImgShow: "imgShow" });
    }
</script>

</head>
<body>
<form enctype="multipart/form-data" action="${pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceStateSave.action" name="Form1" method="post" id="Form1" class="form-validate">
	<br>
    <table cellSpacing="1" cellPadding="5" width="880" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>添加设备状态信息</strong></font>
			</td>
		</tr>
	    <tr>
	       <td align="right" style="width: 20%" bgColor="#f5fafe" class="ta_01">区域：<font color="#FF0000">*</font></td>
	       <td class="ta_01" style="width: 30%" bgColor="#ffffff">
	       		<s:select list="%{#request.area}" id="areaCode" name="areaCode"
					  listKey="ddlCode" listValue="ddlName"
					  headerKey="" headerValue="---请选择---"
					  cssStyle="width:140px"  data-rule-required="true"
				/>
	       </td>
	       <td align="right" style="width: 20%" bgColor="#f5fafe" class="ta_01">安装位置：<font color="#FF0000">*</font></td>
	       <td class="ta_01" style="width: 30%" bgColor="#ffffff">
	      		<s:select list="%{#request.installationSite}" id="installationSiteCode" name="installationSiteCode"
				  listKey="ddlCode" listValue="ddlName"
				  headerKey="" headerValue="---请选择---"
				  cssStyle="width:140px"  data-rule-required="true"
				/>
	       </td>
	       
	    </tr>
	    
		<tr>
			<td align="right" style="width: 20%" bgColor="#f5fafe" class="ta_01">设备名：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" style="width: 30%" bgColor="#ffffff">
	       		<s:select list="%{#request.deviceName}" id="deviceName" name="deviceName"
				  listKey="deviceName" listValue="deviceName"
				  headerKey="" headerValue="---请选择---"
				  cssStyle="width:140px" onchange="changeDevice();" data-rule-required="true"
				/>
	       	</td>
			<td align="right" style="width: 20%" bgColor="#f5fafe" class="ta_01">型号：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" style="width: 30%" bgColor="#ffffff">
	       		<!-- 查看的是型号，但保存的却是设备的uuid -->
	       		<div id="v"></div>
	      	</td>
			
		</tr>
		<tr>
			<td align="right" style="width: 20%" bgColor="#f5fafe" class="ta_01">上次检修日期：</td>
	       	<td class="ta_01" style="width: 30%" bgColor="#ffffff">
				<s:textfield id="lastTime" name="lastTime" cssClass="Wdate" size="20"  data-rule-required="true" style="width: 137px" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})"/>
	       	</td>
		    <td align="right" style="width: 20%" bgColor="#f5fafe" class="ta_01">下次检修日期：</td>
			<td class="ta_01" style="width: 30%" bgColor="#ffffff">
				<s:textfield id="nextTime" name="nextTime" cssClass="Wdate" size="20"  data-rule-required="true" style="width: 137px" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})"/>
			</td>
		</tr>
		
		<tr>
			<td align="right" style="width: 20%" bgColor="#f5fafe" class="ta_01">安装日期：</td>
			<td class="ta_01" style="width: 30%" bgColor="#ffffff">
				<s:textfield id="installationTime" name="installationTime" cssClass="Wdate" size="20"  data-rule-required="true" style="width: 137px" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})"/>
			</td>
		   <td align="right" style="width: 20%" bgColor="#f5fafe" class="ta_01">运行状态：</td>
			<td class="ta_01" style="width: 30%" bgColor="#ffffff">
				<s:select list="%{#request.state}" id="stateCode" name="stateCode"
				  listKey="ddlCode" listValue="ddlName"
				  cssStyle="width:140px"
				/>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">设备图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:file id="image" name="image"/>
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">上传的图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<div id="imgdiv"><img id="imgShow" width="500px" height="300px"/></div>
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">备注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="remark" id="remark" style="width:95%" rows="4" cols="52"/>
			</td>
		</tr>
		
		<TR>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</TR>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
			<input type="button" name="BT_Submit" value="保存" onclick="checkTime();" style="font-size:12px; color:black; height=22;width=55">
			<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
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