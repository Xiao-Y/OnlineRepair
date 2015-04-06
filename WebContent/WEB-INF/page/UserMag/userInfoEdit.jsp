<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/pub.jsp"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>

<title>编辑用户信息</title>

</head>
<body>
	<s:form name="Form1" id="Form1" method="post" cssClass="form-validate" action="UserMag/userAction_userUpdate">
		<br>
	    <table cellSpacing="1" cellPadding="5" width="880" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>编辑用户信息</strong></font>
				</td>
			</tr>
		    <tr>
		       <td align="right" bgColor="#f5fafe" class="ta_01" width="15%">登陆名：<font color="#FF0000">*</font></td>
		       <td class="ta_01" bgColor="#ffffff" width="35%">
		       		<s:hidden id="userUuid" name="userUuid"/>
		       		<s:textfield name="loginName" id="loginName" maxlength="25" size="20" cssStyle="width: 134px" data-rule-required="true"/>
		       </td>
		       <td width="18%" align="right" bgColor="#f5fafe" class="ta_01" width="15%">姓名：<font color="#FF0000">*</font></td>
		       <td class="ta_01" bgColor="#ffffff" width="35%">
		       		<s:textfield name="name" id="name" maxlength="20" size="20" cssStyle="width: 134px" data-rule-required="true"/>
		       </td>
		    </tr>
		    
			<tr>
				<td align="right" bgColor="#f5fafe" class="ta_01" width="15%">性别：<font color="#FF0000">*</font></td>
		       	<td class="ta_01" bgColor="#ffffff" width="35%">
		       		<s:select list="%{#request.sex}" id="sexCode" name="sexCode"
						  listKey="ddlCode" listValue="ddlName"
						  cssStyle="width:140px"
					/>
		       	</td>
		       	<td align="right" bgColor="#f5fafe" class="ta_01" width="15%">维护类别：<font color="#FF0000">*</font></td>
		       	<td class="ta_01" bgColor="#ffffff" width="35%">
					<s:select list="%{#request.maintainType}" id="maintainTypeCode" name="maintainTypeCode"
						  listKey="ddlCode" listValue="ddlName"
						  cssStyle="width:140px"
					/>
		       	</td>
			</tr>
			<tr>
				
			    <td align="right" bgColor="#f5fafe" class="ta_01" width="15%">联系方式：<font color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff" width="35%">
					<s:textfield name="phone" id="phone" maxlength="11" size="11" cssStyle="width: 134px" data-rule-required="true" data-rule-mobilezh="true"/>
				</td>
				<td align="right" bgColor="#f5fafe" class="ta_01" width="15%">密码：<font color="#FF0000">*</font></td>
				<td class="ta_01" bgColor="#ffffff" width="35%">
					<s:password name="password" id="password" maxlength="20" size="20" cssStyle="width: 134px" showPassword="true" data-rule-required="true"/>
				</td>
			</tr>
			
			<tr>
			    <td align="right" bgColor="#f5fafe" class="ta_01" width="15%">地址：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3" width="85%">
					<s:textarea name="address" id="address" cssStyle="width:95%" rows="3" cols="52"/>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="right" bgcolor="#f5fafe" width="15%">备注：</td>
				<td class="ta_01" bgcolor="#ffffff" colspan="3" width="85%">
					<s:textarea name="remark" id="remark" cssStyle="width:95%" rows="4" cols="52"/>
				</td>
			</tr>
			
			<TR>
				<td  align="right"  colSpan="4"  class="sep1"></td>
			</TR>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
					<input type="submit" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55">
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="返回"  name="Reset1" onClick="history.back()">
				</td>
			</tr>
		</table>　
	</s:form>
</body>
</html>