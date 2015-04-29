<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/pub.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body {
	background-color:#F5FAFE; 	
}
td {
	background-color: #F5FAFE;
}
</style>
<script type="text/javascript">
	$(function(){
		$('#tr_failAccount').hide();
		var auditStatCode = '${auditStatCode}';
		if(auditStatCode == '3'){
			$('#tr_failAccount').show();
		}
	});
</script>
<title>故障详细信息</title>

</head>
<body>
<form name="Form1" method="post">
	<br>
    <table cellSpacing="1" cellPadding="5" width="700px" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>故障详细信息</strong></font>
			</td>
		</tr>
	    <tr>
	       <td align="right" width="20%" class="ta_01">区域：</td>
	       <td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="areaName" name="areaName" size="20" readonly="true"/>
			</td>
			<td align="right" width="20%" class="ta_01">安装位置：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="installationSiteName" name="installationSiteName" size="20" readonly="true"/>
			</td>
	    </tr>
	    
		<tr>
			<td align="right" width="20%" class="ta_01">设备名：</td>
	        <td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="deviceName" name="deviceName" size="20" readonly="true"/>
			</td>
			<td align="right" width="20%" class="ta_01">型号：</td>
			 <td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="version" name="version" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right" width="20%" class="ta_01">申报人：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="name" name="name" size="20" readonly="true"/>
			</td>
		    <td align="right" width="20%" class="ta_01">申报人手机号：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="reportingPhone" name="reportingPhone" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td align="right" width="20%" class="ta_01">申报日期：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:textfield id="reportingTime" name="reportingTime" size="20" readonly="true"/>
			</td>
			<td align="right" width="20%" class="ta_01">预约日期：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:if test="%{orderTime == ''}">
					<s:textfield id="orderTime" name="orderTime" value="暂无" size="20" readonly="true"/>
				</s:if>
				<s:else>
					<s:textfield id="orderTime" name="orderTime" size="20" readonly="true"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<td align="right" width="20%" class="ta_01">审核日期：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:if test="%{auditTime == ''}">
					<s:textfield id="auditTime" name="auditTime" value="暂无" size="20" readonly="true"/>
				</s:if>
				<s:else>
					<s:textfield id="auditTime" name="auditTime" size="20" readonly="true"/>
				</s:else>
			</td>
			<td align="right" width="20%" class="ta_01">审核状态：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:if test="%{auditStatName == ''}">
					<s:textfield id="auditStatName" name="auditStatName" value="暂无" size="20" readonly="true"/>
				</s:if>
				<s:else>
					<s:textfield id="auditStatName" name="auditStatName" size="20" readonly="true"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<td align="right" width="20%" class="ta_01">维护人员：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:if test="%{maintainUserName == ''}">
					<s:textfield id="maintainUserName" name="maintainUserName" value="暂无" size="20" readonly="true"/>
				</s:if>
				<s:else>
					<s:textfield id="maintainUserName" name="maintainUserName" size="20" readonly="true"/>
				</s:else>
			</td>
	        <td align="right" width="20%" class="ta_01">维护人手机号：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:if test="%{maintainPhone == ''}">
					<s:textfield id="maintainPhone" name="maintainPhone" value="暂无" size="20" readonly="true"/>
				</s:if>
				<s:else>
					<s:textfield id="maintainPhone" name="maintainPhone" size="20" readonly="true"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<td align="right" width="20%" class="ta_01">维护类型：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:if test="%{maintainTypeName == ''}">
					<s:textfield id="maintainTypeName" name="maintainTypeName" value="暂无" size="20" readonly="true"/>
				</s:if>
				<s:else>
					<s:textfield id="maintainTypeName" name="maintainTypeName" size="20" readonly="true"/>
				</s:else>
			</td>
			<td align="right" width="20%" class="ta_01">维护状态：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:if test="%{maintainStatName == ''}">
					<s:textfield id="maintainStatName" name="maintainStatName" value="暂无" size="20" readonly="true"/>
				</s:if>
				<s:else>
					<s:textfield id="maintainStatName" name="maintainStatName" size="20" readonly="true"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<td align="right" width="20%" class="ta_01">维护日期：</td>
			<td class="ta_01" bgColor="#ffffff" width="30%">
				<s:if test="%{finishTime == ''}">
					<s:textfield id="finishTime" name="finishTime" value="暂无" size="20" readonly="true"/>
				</s:if>
				<s:else>
					<s:textfield id="finishTime" name="finishTime" size="20" readonly="true"/>
				</s:else>
			</td>
			<td align="right" width="20%" class="ta_01"></td>
			<td class="ta_01" bgColor="#ffffff" width="30%"></td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" width="20%" bgcolor="#f5fafe">上传的图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:if test="%{ #request.devicePicUrl != '' || %{#request.devicePicUrl != null}">
					<img alt="设备图片" src="${pageContext.request.contextPath }${devicePicUrl}" width="500px" height="300px">
				</s:if>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" width="20%">故障原因：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="account" id="account" style="width:90%" rows="4" cols="52" readonly="true"/>
			</td>
		</tr>
		
		<tr>
			<td class="ta_01" align="right" width="20%">备注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="remark" id="remark" style="width:90%" rows="4" cols="52" readonly="true"/>
			</td>
		</tr>
		<tr id="tr_failAccount">
			<td class="ta_01" align="right" width="20%" bgcolor="#f5fafe">驳回原因：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<s:textarea name="failAccount" id="failAccount" style="width:90%" rows="4" cols="52" />
			</td>
		</tr>
		
		<!-- 如果审核不通过、未处理状态、没有评价这几种情况应该不显示  start-->
		<%--
		<tr>
			<td class="ta_01" align="right">满意度：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<input type="radio" name="rank" value="A" checked="checked">非常满意&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="radio" name="rank" value="B">满意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="radio" name="rank" value="C">不满意&nbsp;&nbsp;&nbsp;&nbsp;
	       		<input type="radio" name="rank" value="D">非常不满意
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="right">我的评价：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="evaluate" id="evaluate" style="width:95%" rows="4" cols="52" disabled="disabled">asasas</textarea>
			</td>
		</tr>
		 --%>
		<!-- 如果审核不通过、未处理状态、没有评价这几种情况应该不显示  end-->
		
		
		<tr>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</tr>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" colSpan="4">
				<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="返回"  name="Reset1"  onClick="history.back()">
			</td>
		</tr>
	</table>　
</form>
</body>
</html>