<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/pub.jsp"/>
		<title>角色权限管理</title>
				
		<script language="javascript">
		  
		 function saveRole(){
			var roleid = $("#role").val(); 		 
           $("#roleid").val(roleid);
           alert(roleid);
		   //document.Form2.action="saveRole.do";
		   //document.Form2.submit();
		}
		
       
       function selectRole(){
          
          if($("#Form1 :input[name=roleId]").val()=="0"){
         	 $("#Form1").attr("action","${pageContext.request.contextPath }/AuthorityMag/roleAction_home.action");
             $("#Form1").submit();            
          }else{
        	  $("#Form2").text("");
              $("#Form2").load("${pageContext.request.contextPath }/AuthorityMag/roleAction_edit.action");
          }
       }
		
		</script>
	</HEAD>
		
	<body>
		<s:form name="Form1" id="Form1"  method="post" cssStyle="margin:0px;">
			<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
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
							<s:select list="#request.systemList" name="roleId" 
							  listKey="ddlCode" listValue="ddlName" id="roleId"
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
													&nbsp;
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
		</s:form>
	</body>
</html>
