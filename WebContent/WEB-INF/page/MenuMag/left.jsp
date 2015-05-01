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
.qx_list span {
	width: 100%; 
	height: 100%;
	margin-top: 6px; 
	margin-left: 10px;
	cursor:pointer;
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
		<tr height="1"><td></td></tr>
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
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/DeviceMag/deviceAction_deviceInfoList.action' target="main">
									<span>设备信息管理</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceStateList.action' target="main">
									<span>查询设备状态</span>
								</a>
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
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/UserMag/userAction_userInfo.action' target="main">
									<span>个人信息</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/UserMag/userAction_userIndex.action' target="main">
									<span>用户信息</span>
								</a>
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
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/ReportingMag/reportingAction_reportingBugInfoAdd.action' target="main">
									<span>申报故障</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/ReportingMag/reportingAction_reportingBugInfoList.action' target="main">
									<span>故障信息列表</span>
								</a>
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
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/AuditMag/auditInfoAction_auditInfoWaitList.action' target="main">
									<span>待审核</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/AuditMag/auditInfoAction_auditInfoPassList' target="main">
									<span>审核通过</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/AuditMag/auditInfoAction_auditInfoRefuseList' target="main">
									<span>审核未通过</span>
								</a>
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
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/EvaluateMag/evaluateInfoList.jsp' target="main">
									<span>我的评价</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/EvaluateMag/evaluateInfo' target="main">
									<span>留言管理</span>
								</a>
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
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/AuthorityMag/roleAction_home.action' target="main">
									<span>角色权限管理</span>
								</a>
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
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/ResourceMag/noticeAction_noticeIndex.action' target="main">
									<span>发布公告</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/ResourceMag/dictionaryAction_dictionaryIndex.action' target="main">
									<span>数据字典</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/ResourceMag/logAction_logIndex.action' target="main">
									<span>日志管理</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/druid/index.html' target="main">
									<span>数据库连接管理</span>
								</a>
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
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/CountMag/userSexCount.jsp' target="main">
									<span>用户性别统计</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/CountMag/userTypeCount.jsp' target="main">
									<span>维护人员类别统计</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/CountMag/deviceBreakdownCount.jsp' target="main">
									<span>设备故障次数统计</span>
								</a>
							</td>
						</tr>
						<tr height="25">
							<td class="box05" onmouseover="backgroundColorChange(this,'#AAAAAA');" onmouseout="backgroundColorChange(this,'');">
								<a class="cl_01" onclick="linkcolorchange(this)" href='${pageContext.request.contextPath }/page/CountMag/userEvaluateCount.jsp' target="main">
									<span>各种评价数量统计</span>
								</a>
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