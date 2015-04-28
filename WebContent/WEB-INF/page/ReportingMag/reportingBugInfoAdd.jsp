<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"/>

<title>申报故障信息</title>
<style type="text/css">
body {
	background-color:#F5FAFE; 	
}
td {
	background-color: #F5FAFE;
}
</style>
<script type="text/javascript">
   window.onload = function () { 
        new uploadPreview({ UpBtn: "image", DivShow: "imgdiv", ImgShow: "imgShow" });
    }
</script>

</head>
<body> 
<form name="Form1" enctype="multipart/form-data" method="post" id="Form1" name="Form1" class="form-validate" action="${pageContext.request.contextPath }/ReportingMag/reportingAction_reportingBugInfoSave.action">
	<br>
    <table cellSpacing="1" cellPadding="5" width="880" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>申报故障信息</strong></font>
			</td>
		</tr>
	    <tr>
	       	<td align="right" width="20%" class="ta_01">区域：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff" width="30%">
	       		<s:if test="%{#request.area != null && #request.area.size() > 0}">
		       		<s:select list="%{#request.area}" id="areaCode" name="areaCode"
						  listKey="areaCode" listValue="areaName"
						  headerKey="" headerValue="---请选择---"
						  cssStyle="width:140px"  data-rule-required="true"
						  onchange="changeDeviceStateArea();"
					/>
	       		</s:if>
				<s:else>
					<select id="" name="" style="width:140px"></select>
				</s:else>
	       	</td>
	       	<td align="right" width="20%" class="ta_01">安装位置：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff">
	       	<div id="installationSiteDiv"></div>
	       	<%-- 
	       		<s:select list="%{#request.installationSite}" id="installationSiteCode" name="installationSiteCode"
				  listKey="ddlCode" listValue="ddlName"
				  headerKey="" headerValue="---请选择---"
				  cssStyle="width:140px"  data-rule-required="true"
				/>
	       	--%> 
	       	</td>
	       
	    </tr>
	    
		<tr>
			<td align="right" width="20%" class="ta_01">设备名：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff" height="21">
	       	<div id="deviceNameDiv"></div>
	       	<%-- 
	       		<s:select list="%{#request.deviceName}" id="deviceName" name="deviceName"
				  listKey="deviceName" listValue="deviceName"
				  headerKey="" headerValue="---请选择---"
				  cssStyle="width:140px" onchange="changeDevice();" data-rule-required="true"
				/>
	       	 --%>
	       	</td>
			<td align="right" width="20%" class="ta_01">型号：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff">
	       		<div id="versionDiv"></div>
	      	</td>
		</tr>
		<tr>
		    <td align="right" width="20%" class="ta_01">申报人手机号：<font color="#FF0000">*</font></td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield maxlength="11" size="18" id="reportingPhone" name="reportingPhone" data-rule-required="true" data-rule-mobilezh="true"/>
			</td>
			<td align="right" class="ta_01">预约日期：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="orderTime" name="orderTime" cssClass="Wdate" size="20" style="width: 137px" onclick="WdatePicker({readOnly:true,highLineWeekDay:false})"/>
			</td>
		</tr>
		
		<tr>
			<td align="right" width="20%" class="ta_01">优先级别：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:if test="%{#request.priorCodes != null && #request.priorCodes.size() > 0}">
		       		<s:select list="%{#request.priorCodes}" id="priorCode" name="priorCode"
					  listKey="ddlCode" listValue="ddlName"
					  headerKey="" headerValue="---请选择---"
					  cssStyle="width:140px" data-rule-required="true"
					/>
				</s:if>
				<s:else>
					<select id="" name="" style="width:140px"></select>
				</s:else>
			</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="ta_01" width="20%" align="right">故障设备图片：</td>
			<td class="ta_01" width="80%" bgcolor="#ffffff" colspan="3">
				<s:file id="image" name="image"/>
			</td>
		</tr>
		<tr>
			<td class="ta_01" width="20%" align="right">上传的图片：</td>
			<td class="ta_01" width="80%" bgcolor="#ffffff" colspan="3">
				<div id="imgdiv"><img id="imgShow" width="500px" height="300px" /></div>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" width="20%" align="right">故障原因：<font color="#FF0000">*</font></td>
			<td class="ta_01" width="80%" bgcolor="#ffffff" colspan="3">
				<s:textarea name="account" id="account" style="width:80%" rows="4" cols="52" data-rule-required="true"/>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" width="20%" align="right">备注：</td>
			<td class="ta_01" width="80%" bgcolor="#ffffff" colspan="3">
				<s:textarea name="remark" id="remark" style="width:80%" rows="4" cols="52"/>
			</td>
		</tr>
		
		<tr>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</tr>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" colSpan="4">
				<input type="button" id="button" onclick="reportingBugInfoSave();" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55">
				<s:hidden id="flag" name="flag"/>
			</td>
		</tr>
	</table>　
</form>
</body>
</html>