<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/pub.jsp"/>
<html>
<head>
<title>公告信息编辑</title>
</head>

<body style="background-color:#F5FAFE;">
<s:form name="Form1" id="Form1" method="post" cssClass="form-validate" action="ResourceMa/noticeAction_saveNotice.action">
	<table id="opperate1" cellspacing="1" cellpadding="5" width="90%" align="center" style="border:1px solid #8ba7e3" border="0">
        <tr>
			<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>公告信息编辑</strong></font>
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="right" width="30%">公告标题：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
				<input id="noticeTit" name="noticeTit" data-rule-required="true" style="width: 500px; height: 25px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" maxlength="20">
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="right" width="30%">公告内容：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
			<textarea id="notice" name="notice" data-rule-required="true" style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('notice');"></textarea>
			</td>
		</tr>
        <tr>
        	<td></td>
			<td class="ta_01" style="width: 100%" align="center">
				<input type="button" name="BT_Submit" value="保存" onclick="checkchar()" id="BT_Submit" style="font-size:12px; color:black; height=20;width=50">
			</td>
		</tr>
	</table>
	
	<!-- 进度条start -->
	<table id="load" width="700" border="0" align="center" bgcolor="#FAFAFA" cellpadding="0" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;display:none ">
	  <tr>
	    <td><br><br>
		    <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#287BCE" style="border-collapse:collapse ">
		        <tr bgcolor="#F7F7F6">
		          <td width="20%" height="100" valign="middle">
				    <table align='center' width='500'>
				      <tr>
				       <td colspan='2' align='center' >
				    	<font size="2">正在进行保存，用时较长，请稍后...</font>
				       </td>
				      </tr>
				      <tr>
				        <td id='tdOne' height='25' width=1 bgcolor="blue">&nbsp;</td>
				        <td id='tdTwo' height='25' width=500 bgColor='#999999'>&nbsp;</td>
				      </tr>
				    </table>
		          </td>
		        </tr>
		    </table>
	  	</td>
	  </tr>
	</table>
	<!-- 进度条end -->
</s:form>

<s:form name="Form2" id="Form2"  method="post">
	<table id="opperate2" cellSpacing="1" cellPadding="0" width="90%" align="center" border="0">
		<TBODY>
			<TR height=10><td></td></TR>			
			<tr>
			  	<td>
	            	<TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
						<TR>
							<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
							<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">公告信息</TD>
						</TR>
		            </TABLE>
                </td>
                <td align="right">
					<input style="font-size:12px; color:black; height=20;width=80" id="BT_Del" onclick="deletes();" type="button" value="删除" name="BT_Reset" >
                </td>
			</tr>
			<tr>
				<td class="ta_01" align="center" colspan=3>			
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
								<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
								<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">
									<input type="checkbox" id="checkbox" name="checkbox" onclick="quanxuan();">
								</td>
								<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">公告标题</td>
								<td align="center" width="50%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">公告内容</td>
								<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">创建日期</td>
								<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">公告人</td>
							</tr>
							<s:if test="#request.commonList != null">
								<s:iterator value="%{#request.commonList}" var="common" status="st">
									<tr id="noticeIndex" onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="HEIGHT:22px" align="center" width="5%">
											<s:property value="%{#st.getIndex() + 1}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="5%">
											<input type="checkbox" id="${common.noticeUuid}" name="ids" class="ids">
										</td>
										<td style="HEIGHT:22px" align="center" width="15%">
											<s:property value="%{#common.noticeTit}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="50%">
											<s:property value="%{#common.notice}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="15%">
											<s:property value="%{#common.noticeTime}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="10%">
											<s:property value="%{#common.noticeName}"/>
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr id="noticeIndex" onmouseover="this.style.backgroundColor = '#d4e3e5'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td colspan=5 style="HEIGHT:22px" align="center" width="100%">
										<font color="#FF0000">没有更多数据...</font>
									</td>
								</tr>
							</s:else>
						</table>
					</td>
				</tr>        
		</TBODY>
	</table>
</s:form>
</body>
</html>