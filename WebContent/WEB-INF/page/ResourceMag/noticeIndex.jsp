<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>公告信息编辑</title>
<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.0.min.js"></script>

<script type="text/javascript"> 
  function checkchar(){
  
	  if($("#noticeTit").val().length>20){
	  
	     alert("公告标题字数不能超过20字");
	     return;
	  }
	  
	  if($("#notice").val().length>200){
	  
	     alert("公告内容字数不能超过200字");
	     return;
	  }
	  
	  $("#Form1").attr("action", "resource/noticeAction_saveNotice");
	  $("#Form1").submit();
	  
	  loading();
	  alert(" 公告保存成功!");
  }
  
  function addEnter(element){
  	$("#" + element).val($("#" + element).val() + "<br/>");
  }
  
  //保存时显示的进度条
  	var len = 500 ;
	var add = 0 ;
	function openContenFrame(){
	    var td1 = document.getElementById('tdOne') ;
	    var td2 = document.getElementById('tdTwo') ;
	    add = add+10 ;
	    td1.width = add ;
	    if(len - add <= 0){
	       td2.width = 1 ;
	    }else{
	       td2.width = len - add ;
	    }
	    if(add<=len) {
		   ;
	    }else{
	       td1.width = 1 ;
	       td2.width = 500 ;
	       add = 0 ;
	    }
	    setTimeout('openContenFrame()',100) ;
	}
	
	function loading(){
	    document.getElementById("load").style.display="";
	    document.getElementById("opperate1").style.display="none";
	    document.getElementById("opperate2").style.display="none";
	    openContenFrame();
	}
</script>

</head>

<body>
<s:form name="Form1" id="Form1" method="post">
	<table id="opperate1" cellspacing="1" cellpadding="5" width="90%" align="center" bgcolor="#f5fafe" style="border:1px solid #8ba7e3" border="0">
        <tr>
			<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
			<font face="宋体" size="2"><strong>公告信息编辑</strong></font>
			</td>
		</tr>
		<TR height=10><td></td><td></td></TR>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">公告标题：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
			<input id="noticeTit" name="noticeTit" style="width: 500px; height: 25px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" maxlength="20">
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">公告内容：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
			<textarea id="notice" name="notice" style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('notice');"></textarea>
			</td>
		</tr>
        <tr>
			<td class="ta_01" style="width: 100%" align="center" bgcolor="#f5fafe" colspan="2">
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
	<table id="opperate2" cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
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
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>			
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">公告标题</td>
								<td align="center" width="40%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">公告内容</td>
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">创建日期</td>
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">公告人</td>
							</tr>
							<s:if test="#request.commonList != null">
								<s:iterator value="%{#request.commonList}" var="common">
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="HEIGHT:22px" align="center" width="20%">
											<s:property value="%{#common.noticeTit}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="40%">
											<s:property value="%{#common.notice}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="20%">
											<s:property value="%{#common.noticeTime}"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="20%">
											<s:property value="%{#common.noticeName}"/>
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</table>
					</td>
				</tr>        
		</TBODY>
	</table>
</s:form>
</body>
</html>