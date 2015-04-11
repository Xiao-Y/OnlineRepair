<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="x" uri="http://www.xiaoy.com/pageTag/core"%>
<jsp:include page="/pub.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息列表</title>

<script type="text/javascript">
	/**
	//超链接在新窗口显示 
	$(document).ready(function(){
		$("a").attr("target", "_blank");
	});
	*/
	//清除查询条件
	$().ready(function(){
		$("#BT_Reset").click(function(){
			$("#loginName").val("");
			$("#name").val("");
			$("#sexCode").val("");
			$("#maintainTypeCode").val("");
			
			$("#form1").submit();
		});
	});
	
	//链接跳转
	function link(href){
		window.location.href=href;
	}
</script> 

</head>
<body>
	<!-- 查询输入start -->
	<form action="" id="form1" name="form1" method="post">
		<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
			<tr>
				<td class="ta_01" colspan=6 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
					<font face="宋体" size="2"><strong>用户信息管理</strong></font>
				</td>
			</tr>
			<tr height=10>
				<td></td>
			</tr>
			<tr>
				<td class="ta_01" align="right" bgcolor="#f5fafe" height="22">
				登陆名：</td>
				<td class="ta_01" >
					<s:textfield name="loginName" id="loginName" size="18" maxlength="21"/>
				</td>
				<td class="ta_01" align="right" bgcolor="#f5fafe" height="22">
				姓名：</td>
				<td class="ta_01" >
					<s:textfield name="name" id="name" size="18" maxLength="21"/>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="right" bgcolor="#f5fafe" height="22">
				性别：</td>
				<td class="ta_01" >
					<s:select list="%{#request.sex}" id="sexCode" name="sexCode"
						  listKey="ddlCode" listValue="ddlName"
						  headerKey="" headerValue="------请选择------"
						  cssStyle="width:140px"
					/>
				</td>
				<td class="ta_01" align="right" bgcolor="#f5fafe" height="22">
				维护类别：</td>
				<td class="ta_01" >
					<s:select list="%{#request.maintainType}" id="maintainTypeCode" name="maintainTypeCode"
						  listKey="ddlCode" listValue="ddlName"
						  headerKey="" headerValue="------请选择------"
						  cssStyle="width:140px"
					/>
				</td>
			</tr>
	    </table>	
	<!-- 查询输入end -->
	
	<!-- 执行查询begin -->
		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<tr height=10><td></td></TR>			
			<tr>
			  	<td>
	                <table style="width: 105px; height: 20px" border="0">
						<tr>
							<td align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
							<td class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">用户信息管理</TD>
						</tr>
		             </table>
	                 </td>
				<td class="ta_01" align="right">
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Find" onclick="userFind();" type="button" value="查询" name="BT_Find" >&nbsp;&nbsp;
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Reset" type="button" value="清除" name="BT_Reset" >&nbsp;&nbsp;
				    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Del" onclick="userDel();" type="button" value="批量删除" name="BT_Del" >&nbsp;&nbsp;
					<input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="添加用户信息" name="BT_Add" onclick="link('${pageContext.request.contextPath }/UserMag/userAction_toUserAdd.action')">
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan="5">			
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
						style="border-right:gray 1px solid; border-top:gray 1px solid; border-left:gray 1px solid; width:100%; word-break:break-all; border-bottom:gray 1px solid; border-collapse:collapse; background-color:#f5fafe; word-wrap:break-word">
						<!-- 列表标题 begin -->
						<tr style="font-weight:bold;font-size:12pt;height:25px;background-color:#afd1f3">
							<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
							<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">
								<input type="checkbox" id="checkbox" name="checkbox" onclick="quanxuan();">
							</td>
							<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">登陆名</td>
						    <td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">姓名</td>
							<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">性别</td>
							<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">联系方式</td>
							<td align="center" width="10%"  height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
							<td align="center" width="10%"  height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>
						</tr>
						<!-- 列表标题 end -->
						
						<!-- 列表数据 begin -->
						<s:if test="%{#request.users != null && #request.users.size() > 0}">
							<s:iterator value="%{#request.users}" var="user" status="u">
								<tr id="<s:property value="%{#user.userUuid}"/>" onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="HEIGHT:22px" align="center" width="5%">
										<s:property value="%{#u.getIndex() + 1}"/>
									</td>
									<td style="HEIGHT:22px" align="center" width="5%">
										<input type="checkbox" id="${user.userUuid }" name="ids" class="ids" value="${user.userUuid }">
									</td>
									<td style="height:22px" align="center" width="20%">
										<a href="${pageContext.request.contextPath }/UserMag/userAction_userView.action?userUuid=<s:property value="%{#user.userUuid}"/>">
											<s:property value="%{#user.loginName}"/>
										</a>
									</td>
									<td style="height:22px" align="center" width="20%">
										<a href="${pageContext.request.contextPath }/UserMag/userAction_userView.action?userUuid=<s:property value="%{#user.userUuid}"/>">
											<s:property value="%{#user.name}"/>
										</a>
									</td>
									<td style="height:22px" align="center" width="5%">
										<s:property value="%{#user.sex}"/>
									</td>									
									<td style="height:22px" align="center" width="20%">
										<s:property value="%{#user.phone}"/>
									</td>
									<td align="center" style="HEIGHT: 22px" align="center" width="10%">																	
									   <a href="${pageContext.request.contextPath }/UserMag/userAction_userEdit.action?userUuid=<s:property value="%{#user.userUuid}"/>">
									   <img src="${pageContext.request.contextPath }/images/edit.gif" border="0" style="cursor:hand"></a>													
									</td>
									<td align="center" style="HEIGHT: 22px" align="center" width="10%">
										<a href="javascript:deleteUser('${user.name }','${user.userUuid }'); ">
										<img src="${pageContext.request.contextPath }/images/delete.gif" width="16" height="16" border="0" style="cursor:hand"></a>												
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td colspan=6 style="HEIGHT:22px" align="center" width="100%">
									<font color="#FF0000">没有更多数据...</font>
								</td>
							</tr>
						</s:else>
						<!-- 列表数据 end -->
					</table>		
				</td>
			</tr>
		</table>
	</form>
	<x:pager pageNo="${pageNo}" recordCount="${recordCount}" pageSize="${pageSize}" url="${pageContext.request.contextPath}/UserMag/userAction_userIndex.action"/>
	<!-- 执行查询end -->
</body>
</html>