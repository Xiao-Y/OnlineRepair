<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="x" uri="http://www.xiaoy.com/pageTag/core"%>
<jsp:include page="/pub.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	       <td align="right" width="20%" bgColor="#f5fafe" class="ta_01">区域：<font color="#FF0000">*</font></td>
	       <td class="ta_01" width="30%" bgColor="#ffffff">
	       		<s:select list="%{#request.area}" id="areaCode" name="areaCode"
					  listKey="ddlCode" listValue="ddlName"
					  headerKey="" headerValue="---请选择---"
					  cssStyle="width:140px"  data-rule-required="true"
				/>
	       </td>
	       <td align="right" width="20%" bgColor="#f5fafe" class="ta_01">安装位置：<font color="#FF0000">*</font></td>
	       <td class="ta_01" width="30%" bgColor="#ffffff">
	      		<s:select list="%{#request.installationSite}" id="installationSiteCode" name="installationSiteCode"
				  listKey="ddlCode" listValue="ddlName"
				  headerKey="" headerValue="---请选择---"
				  cssStyle="width:140px"  data-rule-required="true"
				/>
	       </td>
	       
	    </tr>
	    
		<tr>
			<td align="right" width="20%" bgColor="#f5fafe" class="ta_01">设备名：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" width="30%" bgColor="#ffffff">
	       		<s:select list="%{#request.list}" id="deviceName" name="deviceName"
				  listKey="deviceName" listValue="deviceName"
				  headerKey="" headerValue="---请选择---"
				  cssStyle="width:140px" onchange="changeDevice();" data-rule-required="true"
				/>
	       	</td>
			<td align="right" width="20%" bgColor="#f5fafe" class="ta_01">型号：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" width="30%" bgColor="#ffffff">
	       		<select id="version" name="version" style="width: 140px"></select>
	      	</td>
			
		</tr>
		<tr>
			<td align="right" width="20%" bgColor="#f5fafe" class="ta_01">上次检修日期：</td>
	       	<td class="ta_01" width="35%" bgColor="#ffffff">
				<input id="lastTime" name="lastTime" class="Wdate" type="text" size="20"  data-rule-required="true" style="width: 137px" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})">
	       	</td>
		    <td align="right" width="20%" bgColor="#f5fafe" class="ta_01">下次检修日期：</td>
			<td class="ta_01" width="30%" bgColor="#ffffff">
				<input id="nextTime" name="nextTime" class="Wdate" type="text" size="20"  data-rule-required="true" style="width: 137px" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})">
			</td>
		</tr>
		
		<tr>
			<td align="right" width="20%" bgColor="#f5fafe" class="ta_01">安装日期：</td>
			<td class="ta_01" width="30%" bgColor="#ffffff">
				<input id="installationTime" name="installationTime" class="Wdate" type="text" size="20" data-rule-required="true" style="width: 137px" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})">
			</td>
		   <td align="right" width="20%" bgColor="#f5fafe" class="ta_01">运行状态：</td>
			<td class="ta_01" width="30%" bgColor="#ffffff">
				<s:select list="%{#request.state}" id="stateCode" name="stateCode"
				  listKey="ddlCode" listValue="ddlName"
				  cssStyle="width:140px"
				/>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" bgcolor="#f5fafe">设备图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<input type="file" id="image" />
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
				<textarea name="remark" id="remark" style="width:95%" rows="4" cols="52"></textarea>
			</td>
		</tr>
		
		<TR>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</TR>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
			<input type="submit" name="BT_Submit" value="保存" style="font-size:12px; color:black; height=22;width=55">
			 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
			<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="返回"  name="Reset1"  onClick="history.back()">
				
			</td>
		</tr>
		<tr align="left">
			<td colspan="4">温馨提示： <font color="#FF0000">* 为必填项</font></td>
		</tr>
	</table>　
</form>
</body>
</html>