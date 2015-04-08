<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
		<tr>
 			<td>
  				<fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;color:#023726;font-size: 12px;">
  					<legend align="left">权限分配</legend>
    				<table cellspacing="0" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">			 
		  				<tr>
			 				<s:set value="%{''}" scope="request" var="parentCode"/>
							<s:if test="%{#request.xmlList != null}">
								<s:iterator value="%{#request.xmlList}" var="xml">
									<s:if test="%{#request.parentCode == #xml.parentCode}">
										<input type="checkbox"  name="selectoper" value='<s:property value="%{#xml.code}"/>' >
										<s:property value="%{#xml.name}"/>
									</s:if>
									<s:else>
										<s:set value="%{#xml.parentCode}" scope="request" var="parentCode"/>
										<br>
										<s:iterator begin="0" end="%{8 - #request.parentName.length()}" step="1">
											&nbsp;&nbsp;&nbsp;
										</s:iterator>
										<s:property value="%{#xml.parentName}"/> :
										<input type="checkbox"  name="selectoper" value='<s:property value="%{#xml.code}"/>' >
										<s:property value="%{#xml.name}"/>
									</s:else>
								</s:iterator>
							</s:if>
						</tr>
						<s:hidden name="roleStr" id="roleStr"/>
						<s:hidden name="roleid" id="roleId"/>						
	 				</table>	
       			</fieldset>
  			</td>
 		</tr>					
	</table>
