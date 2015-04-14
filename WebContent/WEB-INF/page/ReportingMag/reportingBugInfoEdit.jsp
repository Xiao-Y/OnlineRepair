<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"/>

<title>编辑故障信息</title>
<script>
   window.onload = function () { 
        new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
    }
</script>

</head>
<body>
<form name="Form1" method="post">
	<br>
    <table cellSpacing="1" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>编辑故障信息</strong></font>
			</td>
		</tr>
	    <tr>
	       <td align="center" bgColor="#f5fafe" class="ta_01">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff">
				<s:select list="%{#request.area}" id="areaCode" name="areaCode"
					  listKey="areaCode" listValue="areaName"
					  headerKey="" headerValue="---请选择---"
					  cssStyle="width:140px"  data-rule-required="true"
					  onchange="changeDeviceStateArea();"
				/>
			</td>
			<td align="right" width="20%" bgColor="#f5fafe" class="ta_01">安装位置：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff">
	       		<div id="installationSiteDiv">
	       			<s:select list="%{#request.installationSite}" id="installationSiteCode" name="installationSiteCode"
					  listKey="installationSiteCode" listValue="installationSiteName"
					  headerKey="" headerValue="---请选择---"
					  cssStyle="width:140px"  data-rule-required="true"
					  onchange="changeDeviceStateDeviceName();"
					/>
	       		</div>
	       	</td>
	    </tr>
	    
		<tr>
			<td align="right" width="20%" bgColor="#f5fafe" class="ta_01">设备名：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff" height="21">
	       	<div id="deviceNameDiv">
	       		<s:select list="%{#request.deviceName}" id="deviceName" name="deviceName"
					  listKey="deviceName" listValue="deviceName"
					  headerKey="" headerValue="---请选择---"
					  cssStyle="width:140px"  data-rule-required="true"
					  onchange="changeDeviceStateVersion();"
					/>
	       	</div>
	       	</td>
			<td align="right" width="20%" bgColor="#f5fafe" class="ta_01">型号：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff">
	       		<div id="versionDiv">
	       			<s:select list="%{#request.version}" id="deviceStateUuid" name="deviceStateUuid"
					  listKey="deviceStateUuid" listValue="version"
					  headerKey="" headerValue="---请选择---"
					  cssStyle="width:140px"  data-rule-required="true"
					/>
	       		</div>
	      	</td>
		</tr>
		<tr>
		    <td align="center" bgColor="#f5fafe" class="ta_01">申报人联系方式：</td>
			<td class="ta_01" bgColor="#ffffff">
				<input class="" type="text" size="20" id="phone" name="phone" value="1554551655">
			</td>
			
			<td align="center" bgColor="#f5fafe" class="ta_01">希望处理日期：</td>
			<td class="ta_01" bgColor="#ffffff">
				<input class="Wdate" type="text" size="20" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})">
			</td>
		</tr>
		
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">优先级别：</td>
			<td class="ta_01" bgColor="#ffffff">
				<input type="radio" name="rank" value="A">高&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="radio" name="rank" value="B">中&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="radio" name="rank" value="C"  checked="checked">低&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">上传的图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<div id="imgdiv"><img id="show" width="500px" height="300px" /></div>
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">修改故障图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<input type="file" id="up_img" />
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">修改后故障图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<div id="imgdiv"><img id="imgShow" width="500px" height="300px"/></div>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">故障原因：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="account" id="account" style="width:95%" rows="4" cols="52"></textarea>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="remark" id="remark" style="width:95%" rows="4" cols="52"></textarea>
			</td>
		</tr>
		
		<TR>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</TR>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
			<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"   onClick="check_null()">
			 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
			<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="history.back();">
				
			</td>
		</tr>
	</table>　
</form>
</body>
</html>