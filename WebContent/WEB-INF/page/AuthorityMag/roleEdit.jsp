<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
	<tr>
		<td>
		   <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;">
		   		<legend align="left">权限分配</legend><br/>
		   		<table cellSpacing="0" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">	
		   			<tr height="30">
		   				<td> 全选/全不选<input type="checkbox" name="selectoperAll" onClick="checkAllOper(this)"/></td>
		   			</tr>		 
					  <tr>
					 	<s:if test="%{#request.xmlList != null}">
							<s:iterator value="%{#request.xmlList}" var="me">
								<s:iterator begin="0" end="%{8 - #request.codeName.length()}" step="1">
									&nbsp;
								</s:iterator>
								<s:property value="%{#me.codeName}"/>
								<s:if test="%{#request.popedomCode != null && #request.popedomCode.indexOf(#me.code) >= 0}">
									<input type="checkbox" style="display: none;" class="popedomCode${me.code }" name="popedomCode" id='<s:property value="%{#me.code}"/>' value='<s:property value="%{#me.code}"/>' checked>
								</s:if>
								<s:else>
									<input type="checkbox" style="display: none;" class="popedomCode${me.code }" name="popedomCode" id='<s:property value="%{#me.code}"/>' value='<s:property value="%{#me.code}"/>'>
								</s:else>
								：&nbsp;&nbsp;
								<s:iterator value="%{#me.menus}" var="m">
									<s:if test="%{#request.popedomCode != null && #request.popedomCode.indexOf(#m.code) >= 0}">
										<input type="checkbox" class="selectoper${me.code }" name="popedomCode" onclick="selectChild('${me.code }')" id='<s:property value="%{#m.code}"/>' value='<s:property value="%{#m.code}"/>' checked>
									</s:if>
									<s:else>
										<input type="checkbox" class="selectoper${me.code }"  name="popedomCode" onclick="selectChild('${me.code }')" id='<s:property value="%{#m.code}"/>' value='<s:property value="%{#m.code}"/>' >
									</s:else>
									<s:property value="%{#m.codeName}"/>
								</s:iterator>
								<br/>
							</s:iterator>
						</s:if>
					  </tr>						
				 </table><br/>	
		     </fieldset>
	  	</td>
	</tr>
	<tr>
		<td height=10></td>
	</tr>
	<tr>
		<td>
			用户分配角色
			<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
						style="border-right:gray 1px solid; border-top:gray 1px solid; border-left:gray 1px solid; 
						width:100%; word-break:break-all; border-bottom:gray 1px solid; border-collapse:collapse; 
						background-color:#f5fafe; word-wrap:break-word">  
				<tr style="font-weight:bold;font-size:12pt;height:25px;background-color:#afd1f3">
				   <th align="center" width="20%" height=22 background="../images/tablehead.jpg">
				   		<input type="checkbox" id="checkbox" name="checkbox" onclick="checkAllUser(this);">
				   </th>
				   <th align="center" width="40%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">登录名</th>
				   <th align="center" width="40%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">用户姓名</th>
				</tr>
				<s:if test="%{#request.userList != null}">
					<s:iterator value="%{#request.userList}" var="user">
						<tr onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
							<td style="height: 22px" align="center" width="20%">
								<s:if test="%{#user.flag == 0}">
									<input type="checkbox" name="userIds" value="${user.userUuid }">
								</s:if>
								<s:else>
									<input type="checkbox" name="userIds" value="${user.userUuid }" checked>
								</s:else>
							</td>
							<td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
								${user.loginName }
							</td>
							<td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
								${user.name }
							</td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
						<td colspan=4 style="HEIGHT:22px" align="center" width="100%">
							<font color="#FF0000">没有更多数据...</font>
						</td>
					</tr>
				</s:else>
			</table>
		</td>
	</tr>
	<tr>
		<td class="ta_01" align="center" colspan=3 width="100%"  height=20>
			<s:hidden name="roleid" id="roleid"/>
			<input type="button" name="saverole" onclick="saveRole()" style="font-size:12px; color:black; height=20;width=50" value="保存">
		</td>
	</tr>
</table>