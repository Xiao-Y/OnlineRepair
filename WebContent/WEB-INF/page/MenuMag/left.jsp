<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/pub.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
a {
	color: #0A0A0A;
	 text-decoration:none;
}
body {
	background-color:#F5FAFE; 	
}
td {
	background-color: #F5FAFE;
}
</style>
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
		<tr height="6"><td></td></tr>
		<tr>
			<td vAlign="top" height="100%">
				<!-- 设备管理 begin -->
				<div class="qx" id="deviceMagParent" style="cursor:pointer;">
					<table cellSpacing="0" cellPadding="0" width="100%" border="0">
						<tr height=25>
							<td align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
								<div class="img">
									&nbsp;<img src="${pageContext.request.contextPath }/images/open.gif" name="deviceMagParent" width="8" height="9" alt="" border="0" />
									设备管理
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="qx_list" id="deviceMagChild">
					<table cellSpacing="0" cellPadding="0" width="99%" border="0">
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/DeviceMag/deviceAction_deviceInfoList.action' target="main">设备信息管理</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceStateList.action' target="main">查询设备状态</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- 设备管理 end -->
				
				<!-- 用户管理 begin -->
				<div class="qx" id="userMagParent" style="cursor:pointer;">
					<table cellSpacing="0" cellPadding="0" width="100%" border="0">
						<tr height=25>
							<td align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
								<div class="img">
									&nbsp;<img src="${pageContext.request.contextPath }/images/open.gif" name="userMagParent" width="8" height="9" alt="" border="0" />
									用户信息管理
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="qx_list" id="userMagChild">
					<table cellSpacing="0" cellPadding="0" width="99%" border="0">
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/UserMag/userAction_userInfo.action' target="main">个人信息</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/UserMag/userAction_userIndex.action' target="main">用户信息</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- 用户管理 end -->
				
				<!-- 申报故障管理 begin-->
				<div class="qx" id="reportingMagParent" style="cursor:pointer;">
					<table cellSpacing="0" cellPadding="0" width="100%" border="0">
						<tr height=25>
							<td align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
								<div class="img">
									&nbsp;<img src="${pageContext.request.contextPath }/images/open.gif" name="reportingMagParent" width="8" height="9" alt="" border="0" />
									申报故障管理
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="qx_list" id="reportingMagChild">
					<table cellSpacing="0" cellPadding="0" width="99%" border="0">
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/ReportingMag/reportingAction_reportingBugInfoAdd.action' target="main">申报故障</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/ReportingMag/reportingAction_reportingBugInfoList.action' target="main">故障信息列表</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- 申报故障管理 end-->
				
				<!-- 故障申报审核 begin -->
				<div class="qx" id="auditMagParent" style="cursor:pointer;">
					<table cellSpacing="0" cellPadding="0" width="100%" border="0">
						<tr height=25>
							<td align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
								<div class="img">
									&nbsp;<img src="${pageContext.request.contextPath }/images/open.gif" name="auditMagParent" width="8" height="9" alt="" border="0" />
									故障申报审核
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="qx_list" id="auditMagChild">
					<table cellSpacing="0" cellPadding="0" width="99%" border="0">
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/auditMag/AuditInfoAction_auditInfoWaitList.action' target="main">待审核</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/AuditMag/auditInfoPassList.jsp' target="main">审核通过</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/AuditMag/auditInfoRefuseList.jsp' target="main">审核未通过</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- 故障申报审核 end  -->
				
				<!-- 评价留言管理 begin -->
				<div class="qx" id="evaluateMagParent" style="cursor:pointer;">
					<table cellSpacing="0" cellPadding="0" width="100%" border="0">
						<tr height=25>
							<td align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
								<div class="img">
									&nbsp;<img src="${pageContext.request.contextPath }/images/open.gif" name="evaluateMagParent" width="8" height="9" alt="" border="0" />
									评价留言管理
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="qx_list" id="evaluaterMagChild">
					<table cellSpacing="0" cellPadding="0" width="99%" border="0">
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/EvaluateMag/evaluateInfoList.jsp' target="main">我的评价</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/EvaluateMag/evaluateInfo' target="main">留言管理</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- 评价留言管理 end -->
				
				<!-- 权限管理 begin -->
				<div class="qx" id="authorityMagParent" style="cursor:pointer;">
					<table cellSpacing="0" cellPadding="0" width="100%" border="0">
						<tr height=25>
							<td align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
								<div class="img">
									&nbsp;<img src="${pageContext.request.contextPath }/images/open.gif" name="authorityMagParent" width="8" height="9" alt="" border="0" />
									权限管理
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="qx_list" id="authorityMagChild">
					<table cellSpacing="0" cellPadding="0" width="99%" border="0">
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/AuthorityMag/roleAction_home.action' target="main">角色权限管理</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- 权限管理 end -->
				
				<!-- 资源管理 begin -->
				<div class="qx" id="resourceMagParent" style="cursor:pointer;">
					<table cellSpacing="0" cellPadding="0" width="100%" border="0">
						<tr height=25>
							<td align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
								<div class="img">
									&nbsp;<img src="${pageContext.request.contextPath }/images/open.gif" name="resourceMagParent" width="8" height="9" alt="" border="0" />
									资源管理
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="qx_list" id="resourceMagChild">
					<table cellSpacing="0" cellPadding="0" width="99%" border="0">
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/ResourceMag/noticeAction_noticeIndex.action' target="main">发布公告</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/ResourceMag/dictionaryAction_dictionaryIndex.action' target="main">数据字典</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/ResourceMag/logAction_logIndex.action' target="main">日志管理</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- 资源管理end -->
				
				<!-- 统计管理 beging -->
				<div class="qx" id="countMagParent" style="cursor:pointer;">
					<table cellSpacing="0" cellPadding="0" width="100%" border="0">
						<tr height=25>
							<td align="left" background="${pageContext.request.contextPath }/images/b-info.gif"  vAlign="middle">
								<div class="img">
									&nbsp;<img src="${pageContext.request.contextPath }/images/open.gif" name="countMagParent" width="8" height="9" alt="" border="0" />
									统计管理
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="qx_list" id="countMagChild">
					<table cellSpacing="0" cellPadding="0" width="99%" border="0">
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/CountMag/userSexCount.jsp' target="main">用户性别统计</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/CountMag/userTypeCount.jsp' target="main">维护人员类别统计</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/CountMag/deviceBreakdownCount.jsp' target="main">设备故障次数统计</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/CountMag/userEvaluateCount.jsp' target="main">各种评价数量统计</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- 统计管理 end -->
				
			</td>
		</tr>
		<tr height="60"><td></td></tr>
	</table>
</body>
</html>