<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pub.jsp"/>
<html>
<head>

<title>编辑审核通过故障信息</title>
<script>
	$(function(){
		$('#tr_failAccount').hide();
		$('#maintainStatCode1').hide();
		$('label[for=maintainStatCode1]').hide();
	});
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
<form name="Form1" id="Form1" method="post" class="form-validate" action="">
	<br>
    <table cellSpacing="1" cellPadding="5" width="880" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>编辑审核通过故障信息</strong></font>
			</td>
		</tr>
	     <tr>
	       <td align="right"  width="20%" class="ta_01">区域：</td>
	       <td class="ta_01" bgColor="#ffffff"  width="30%">
				<s:textfield id="areaName" name="areaName" size="20" readonly="true"/>
			</td>
			<td align="right"  width="20%" class="ta_01">安装位置：</td>
			<td class="ta_01" bgColor="#ffffff"  width="30%">
				<s:textfield id="installationSiteName" name="installationSiteName" size="20" readonly="true"/>
			</td>
	    </tr>
	    
		<tr>
			<td align="right"  width="20%" class="ta_01">设备名：</td>
	        <td class="ta_01" bgColor="#ffffff"  width="30%">
	        	<s:textfield id="deviceName" name="deviceName" size="20" readonly="true"/>
			</td>
			
			 <td align="right"  width="20%" class="ta_01">型号：</td>
			 <td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="version" name="version" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
		    <td align="right"  width="20%" class="ta_01">申报人：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="name" name="name" size="20" readonly="true"/>
			</td>
		    <td align="right"  width="20%" class="ta_01">申报人手机号：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="reportingPhone" name="reportingPhone" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right"  width="20%" class="ta_01">申报日期：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="reportingTime" name="reportingTime" size="20" readonly="true"/>
			</td>
			<td align="right"  width="20%" class="ta_01">预约日期：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="orderTime" name="orderTime" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<!-- 
			<td align="right"  width="20%" class="ta_01">优先级别：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="priorName" name="priorName" size="20" readonly="true"/>
			</td>
			 -->
			<td align="right"  width="20%" class="ta_01">审核状态：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:if test="%{#request.auditStat != null && #request.auditStat.size() > 0}">
					<s:radio list="%{#request.auditStat}" id="auditStatCode" name="auditStatCode" 
					listKey="ddlCode" listValue="ddlName" onchange="auditStateChange();" />
				</s:if>
			</td>
			<td></td>
			<td></td>
		</tr>
		<tr id="tr_MaintainType">
			<td align="right"  width="20%" class="ta_01">维护类别：<font color="#FF0000">*</font></td>
			<td class="ta_01" bgColor="#ffffff" width="30%" height="21">
			<div id="maintainTypeDIV" style="">
				<s:if test="%{#request.diList != null && #request.diList.size() > 0}">
		       		<s:select list="%{#request.diList}" id="maintainTypeCode" name="maintainTypeCode"
					  listKey="ddlCode" listValue="ddlName" onchange="auditInfoWaitMaintainType();"
					  headerKey="" headerValue="---请选择---"
					  cssStyle="width:153px" data-rule-required="true"
					/>
				</s:if>
				<s:else>
					<select id="" name="" style="width:153px"></select>
				</s:else>
			</div>
			</td>
			
			<td align="right"  width="20%" class="ta_01">维护人员：<font color="#FF0000">*</font></td>
			<td class="ta_01" bgColor="#ffffff" width="30%" height="22px">
				<div id="auditInfoWaitMaintainUserDiv">
					<s:textfield id="maintainName" name="maintainName" size="20" readonly="true"/>
				</div>
			</td>
		</tr>
		
		<tr>
			<td align="right" class="ta_01">审核通过日期：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield id="auditTime" name="auditTime" size="20" readonly="true"/>
			</td>
			<td align="right" class="ta_01">维护完成时间：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:if test="%{finishTime == ''}">
					<s:textfield id="finishTime" name="finishTime" value="暂无" size="20" readonly="true"/>
				</s:if>
				<s:else>
					<s:textfield id="finishTime" name="finishTime" size="20" readonly="true"/>
				</s:else>
			</td>
		</tr>
		<tr id="tr_pass_maintainStat">
			<td align="right" class="ta_01">维护状态：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:if test="%{#request.maintainStat != null && #request.maintainStat.size() > 0}">
					<s:radio list="%{#request.maintainStat}" id="maintainStatCode" name="maintainStatCode" 
					listKey="ddlCode" listValue="ddlName"/>
				</s:if>
			</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="ta_01" align="right" width="20%" >上传的图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<img alt="设备图片" src="${pageContext.request.contextPath }${devicePicUrl}" width="500px" height="300px">
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" width="20%" >故障原因：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="account" id="account" style="width:80%" rows="4" cols="52" readonly="true"/>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" width="20%" >备注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="remark" id="remark" style="width:80%" rows="4" cols="52" readonly="true"/>
			</td>
		</tr>
		
		<tr id="tr_failAccount">
			<td class="ta_01" align="right" width="20%" >驳回原因：<font color="#FF0000">*</font></td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="failAccount" id="failAccount" style="width:80%" rows="4" cols="52" data-rule-required="true" />
				<br>
				<font color="#FF0000">当审核不通过时必须填写驳回原因</font>
			</td>
		</tr>
		
		<TR>
			<td  align="center"  colSpan="4"  class="sep1"></td>
			<!-- 设备状态的uuid -->
			<s:hidden id="deviceStateUuid" name="deviceStateUuid"/>
			<s:hidden id="auditUuid" name="auditUuid"/>
			<s:hidden id="evaluateUuid" name="evaluateUuid"/>
			<s:hidden id="deviceTypeUuid" name="deviceTypeUuid"/>
		</TR>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" colSpan="4">
				<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55" onclick="auditInfoPassButton()">
				<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
				<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="history.back()">
			</td>
		</tr>
	</table>　
</form>
</body>
</html>