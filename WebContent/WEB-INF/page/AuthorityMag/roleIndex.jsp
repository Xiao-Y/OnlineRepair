<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pub.jsp"/>
<title>角色权限管理</title>
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
		<s:form name="Form1" id="Form1"  method="post" cssStyle="margin:0px;">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" border="0">
				<tbody>
					<tr>
						<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
							<font face="宋体" size="2"><strong>角色管理</strong></font>
						</td>
					</tr>	
					<tr>
					   <td class="ta_01" colspan=2 align="right" width="100%"  height=10>
					   </td>
					</tr>		
					<tr>
						<td class="ta_01" align="right" width="35%" >角色类型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="ta_01" align="left"  width="65%" >
							<s:select list="%{#request.roleList}" name="roleCode" 
							  listKey="ddlCode" listValue="ddlName" id="roleCode"
							  headerKey="0" headerValue="请选择"
							  cssClass="bg" cssStyle="width:180px" onchange="selectRole()"
							/> 
						 </td>				
					</tr>
				    <tr>
					   <td class="ta_01" align="right" colspan=2 align="right" width="100%"  height=10></td>
					</tr>
				</tbody>
			</table>
		</s:form>

		<s:form  name="Form2" id="Form2"  method="post" cssStyle="margin:0px;">
			<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
		 		<tr>
		  			<td>
		   				<fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;color:#023726;font-size: 12px;">
		   					<legend align="left">权限分配</legend>
		     				<table cellspacing="0" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">			 
					  			<tr>
									<s:if test="%{#request.xmlList != null}">
										<s:iterator value="%{#request.xmlList}" var="me">
												<s:iterator begin="0" end="%{8 - #request.codeName.length()}" step="1">
													&nbsp;
												</s:iterator>
												<s:property value="%{#me.codeName}"/>
												<input type="checkbox" style="display: none;" class="popedomCode${me.code }" name="popedomCode" id='<s:property value="%{#me.code}"/>' value='<s:property value="%{#me.code}"/>'>
												：&nbsp;&nbsp;
												<s:iterator value="%{#me.menus}" var="m">
													<input type="checkbox" class="selectoper${me.code }" name="popedomCode" onclick="selectChild('${me.code }')" value='<s:property value="%{#m.code}"/>' >
													<s:property value="%{#m.codeName}"/>
												</s:iterator>
												<br/>
										</s:iterator>
									</s:if>
								</tr>
				 			</table>	
		        		</fieldset>
			  		</td>
			 	</tr>					
			</table>
		</s:form>
	</body>
</html>
