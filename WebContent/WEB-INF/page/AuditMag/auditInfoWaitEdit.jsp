<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pub.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>审核故障信息</title>
<script>
   window.onload = function () { 
        new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
    }
   
   //通过维护类别异步加载用户名
   function auditInfoWaitMaintainType(){
		$("#deviceTypeUuid").remove();
		
		var maintainTypeCode = $('#maintainTypeCode').val();
		var url = "${pageContext.request.contextPath}/AuditMag/auditInfoAction_auditInfoWaitList.action";
		var dataType = "JSON";
		var data = {"date":new Date,"maintainTypeCode":maintainTypeCode};
		
		$.post(url,data,function(data){
			var html = '<select id="deviceTypeUuid" name="deviceTypeUuid" style="width: 140px" data-rule-required="true">';
			
			$.each(data,function(i){
				html = html + '<option value="'+ data[i].deviceTypeUuid +'" >'+ data[i].version + '</option>';
			});
			
			html = html + "</select>";
			
			$("#auditInfoWaitMaintainType").html(html);
		},dataType);
   }
   
</script>
<style type="text/css">
body {
	background-color:#F5FAFE; 	
}
td {
	background-color: #F5FAFE;
}
</style>
</head>
<body>
<form name="Form1" method="post">
	<br>
    <table cellSpacing="1" class="form-validate" cellPadding="5" width="880" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>编辑故障信息</strong></font>
			</td>
		</tr>
	    <tr>
	       <td align="right" bgColor="#f5fafe" width="20%" class="ta_01">区域：</td>
	       <td class="ta_01" bgColor="#ffffff"  width="30%">
				<s:textfield id="areaName" name="areaName" size="20" readonly="true"/>
			</td>
			<td align="right" bgColor="#f5fafe" width="20%" class="ta_01">安装位置：</td>
			<td class="ta_01" bgColor="#ffffff"  width="30%">
				<s:textfield id="installationSiteName" name="installationSiteName" size="20" readonly="true"/>
			</td>
	    </tr>
	    
		<tr>
			<td align="right" bgColor="#f5fafe" width="20%" class="ta_01">设备名：</td>
	        <td class="ta_01" bgColor="#ffffff"  width="30%">
	        	<s:textfield id="deviceName" name="deviceName" size="20" readonly="true"/>
			</td>
			
			 <td align="right" bgColor="#f5fafe" width="20%" class="ta_01">型号：</td>
			 <td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="version" name="version" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
		    <td align="right" bgColor="#f5fafe" width="20%" class="ta_01">申报人：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="name" name="name" size="20" readonly="true"/>
			</td>
		    <td align="right" bgColor="#f5fafe" width="20%" class="ta_01">申报人手机号：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="reportingPhone" name="reportingPhone" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right" bgColor="#f5fafe" width="20%" class="ta_01">申报日期：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="reportingTime" name="reportingTime" size="20" readonly="true"/>
			</td>
			<td align="right" bgColor="#f5fafe" width="20%" class="ta_01">审核状态：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<input type="radio" name="test" value="通过" checked="checked">通过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="test" value="驳回">驳回&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td align="right" bgColor="#f5fafe" width="20%" class="ta_01">维护类别：<font color="#FF0000">*</font></td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:if test="%{#request.diList != null && #request.diList.size() > 0}">
		       		<s:select list="%{#request.diList}" id="maintainTypeCode" name="maintainTypeCode"
					  listKey="ddlCode" listValue="ddlName" onchange="auditInfoWaitMaintainType();"
					  headerKey="" headerValue="---请选择---"
					  cssStyle="width:140px" data-rule-required="true"
					/>
				</s:if>
				<s:else>
					<select id="" name="" style="width:140px"></select>
				</s:else>
			</td>
			
			<td align="right" bgColor="#f5fafe" width="20%" class="ta_01">维护人员：<font color="#FF0000">*</font></td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<div id="auditInfoWaitMaintainType"></div>
			</td>
		</tr>
		
		<tr>
			<!-- 
			<td align="right" bgColor="#f5fafe" class="ta_01">维护人员联系方式：</td>
			<td class="ta_01" bgColor="#ffffff">
				<input type="text" name="test" value="13888277788" disabled="disabled">
				<s:textfield id="reportingPhone" name="reportingPhone" size="20" readonly="true"/>
			</td>
			 -->
		</tr>
		
		<tr>
			<td class="ta_01" align="right" width="20%" bgcolor="#f5fafe">上传的图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<img alt="设备图片" src="${pageContext.request.contextPath }${devicePicUrl}" width="500px" height="300px">
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" width="20%" bgcolor="#f5fafe">故障原因：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="account" id="account" style="width:95%" rows="4" cols="52" readonly="true"/>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" width="20%" bgcolor="#f5fafe">备注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="remark" id="remark" style="width:95%" rows="4" cols="52" readonly="true"/>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" width="20%" bgcolor="#f5fafe">驳回原因：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="failAccount" id="failAccount" style="width:95%" rows="4" cols="52"/>
				<br>
				<font color="#FF0000">当审核不通过时必须填写驳回原因</font>
			</td>
		</tr>
		
		<tr>
			<td  align="center"  colspan="4"  class="sep1"></td>
		</tr>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
				<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"   onClick="check_null()">
				<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
				<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="history.back()">
			</td>
		</tr>
	</table>　
</form>
</body>
</html>