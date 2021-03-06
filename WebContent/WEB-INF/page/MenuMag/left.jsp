<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/menu.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.0.min.js"></script>

<title>菜单</title>

<!-- 切换折叠 -->
<script type="text/javascript">
	$().ready(function() {
		//左侧菜单栏初始状为关闭
		var open = "${pageContext.request.contextPath }/images/open.gif";
		var add = "${pageContext.request.contextPath }/images/add.gif";
		$(".qx_list").show();
		
		$(".qx").click(function() {
			var id = $(this).attr("id");
			$(this).next().toggle(0,
				function(){
					var src = $("img[name="+ id +"]").attr("src");
					if(src == open){
						src = add;
					}else{
						src = open;
					}
					$("img[name="+ id +"]").attr("src", src);
				}		
			);
		});
	});
	
	function linkcolorchange(objLink)
	{
		for(var i=0;i<document.links.length;i++)
		{
			document.links[i].style.color = "" ;
		}
			objLink.style.color = "red" ;
	}
	
	function backgroundColorChange(objLink,strColor)
	{
		objLink.style.backgroundColor = strColor ;
	}
</script>
</head>

<body>
	<table height="100%" width="150" border="0" cellSpacing="0" cellPadding="0">
		<tr height="1"><td></td></tr>
		<tr>
			<td vAlign="top" height="100%">
				
				<s:iterator value="%{#request.list}" var="me">
					<s:if test="%{#session.popedom.indexOf(#me.code) >= 0}">
					<!-- 父级菜单 -->
					<div class="qx" id='${me.code}Parent' style="cursor:pointer;">
						<table cellSpacing="0" cellPadding="0" width="100%" border="0">
							<tr height=25>
								<td align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
									<div class="img">
										&nbsp;<img src="${pageContext.request.contextPath }/images/open.gif" name="${me.code}Parent" width="8" height="9" alt="" border="0" />
										<s:property value="%{#me.codeName}"/>
									</div>
								</td>
							</tr>
						</table>
					</div>
					
					<!-- 子级菜单 -->
					<div class="qx_list" id="${me.code}Child">
						<table cellSpacing="0" cellPadding="0" width="99%" border="0">
							<s:iterator value="%{#me.menus}" var="m">
								<s:if test="%{#session.popedom.indexOf(#m.code) >= 0}">
								<tr height="25">
									<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
										<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }${m.url}' target="main">
											<span><s:property value="%{#m.codeName}"/></span>
										</a>
									</td>
								</tr>
								</s:if>
							</s:iterator>
						</table>
					</div>
					</s:if>
				</s:iterator>
				
			</td>
		</tr>
		<tr height="60"><td></td></tr>
	</table>
</body>
</html>