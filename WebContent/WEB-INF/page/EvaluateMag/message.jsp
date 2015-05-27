<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/pub.jsp"/>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.2/jquery.easyui.min.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/pub.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.2/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.2/themes/icon.css">

<title>Insert title here</title>
</head>
<script type="text/javascript">
function reply(messageUuid){
	$("#dia").show();
	$("#div_text").text("");
	
	$("#dia").dialog({
	     resizable: false,
	     modal:true,
	     title:'回复',
	     fit : false,//自动变化 
		//按钮
	     buttons: [{
	    	 text: '确定',
                id: 'OK',
                iconCls: 'icon-ok',
                handler: function () {
                	var replyMes = $("#div_text").val();
                    $.ajax({
                       type: "POST",
                       url: "${pageContext.request.contextPath }/EvaluateMag/messageAction_messageSave.action",
                       async: false,
                       data: {"date":new Date,"messageUuid":messageUuid,"replyMes":replyMes,"flag":"1"},
                       success: function (data) {
                    	   location.href="${pageContext.request.contextPath }/EvaluateMag/messageAction_message.action";
		                   $("#dia").dialog('close');
                       }
                    });
                },
                onClose: function () {
                    $("#dia").dialog('destroy');
                }
	     },{
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
               	 $("#dia").dialog('close');
                },
                onClose: function () {
                    $("#dia").dialog('destroy');
                }
            }]
	 });
}

function replyOk(messageUuid){
	$("#dia").show();
	
	$.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath }/EvaluateMag/messageAction_findMessageByMessageUuid.action",
        async: false,
        data: {"messageUuid":messageUuid,"date":new Date},
        dataTpye:'JSON',
        success: function (data) {
        	var obj = $.parseJSON(data);
			$("#div_text").text(obj.replyMes);
        }
     });
	
	$("#dia").dialog({
	     resizable: false,
	     modal:true,
	     title:'回复',
	     fit : false,//自动变化 
		//按钮
	     buttons: [{
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
           	 $("#dia").dialog('close');
            },
            onClose: function () {
                $("#dia").dialog('destroy');
            }
        }]
	 });
}

</script>

<body  style="background-color:#F5FAFE;">
	<!-- 回复弹出框 -->
	<div id="dia" style="display:none;">
		<textarea style="width: 350px;height: 150px;" id="div_text" rows="5" cols="50" class="easyui-validatebox" data-options="required:true,missingMessage:'回复不能为空'"></textarea>
	</div>
<br/>
<br/>
	<div align="center">
		<table align="center" style="background-image:url(../images/message_title.png);width: 78%">
			<tr >
				<td colspan="2">留言板</td>
			</tr>
		</table>
		<form action="${pageContext.request.contextPath }/EvaluateMag/messageAction_messageSave.action" method="post" id="form1" name="form1" class="form-validate">
			<table style="background-image:url(../images/message_bg.png); width: 78%">
				<tr style="width: 100%">
					<td align="left">标题：<font color="#FF0000">*</font></td>
					<td align="left" width="90%" ><input name="titleMes" id="titleMes" size="30" data-rule-required="true"></td>
				</tr>
				<tr style="width: 100%">
					<td align="left">内容：<font color="#FF0000">*</font></td>
					<td align="left" width="90%"><textarea id="messageMes" name="messageMes" rows="11" cols="50" style="background-image:url(../images/message_me.png);resize: none;" data-rule-required="true"></textarea></td>
				</tr>
				<tr style="width: 100%">
					<td></td>
					<td align="left"><input id="" name="" value="提交" type="submit"></td>
				</tr>
			</table>
		</form>
		<br/>
		<table cellspacing="0" cellpadding="1" rules="all" border="1" style="width: 78%">
			<tr style="background-image:url(../images/message_title.png);" height="26px">
				<td colspan="7" align="center">所有留言</td>
			</tr>
			<tr style="background-image:url(../images/message_title.png);">
				<th width="17%">留言时间</th>
				<th width="20%">标题</th>
				<th width="53%">内容</th>
				<th width="5%">回复</th>
				<th width="5%">操作</th>
			</tr>
			<s:if test="%{#request.list != null && #request.list.size() > 0}">
				<s:iterator value="%{#request.list}" var="mess">
				<tr id="tr${mess.messageUuid}" style="background-image:url(../images/message_title.png);">
					<td><s:date name="%{#mess.messageTime}" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:property value="%{#mess.titleMes}"/></td>
					<td><s:property value="%{#mess.messageMes}"/></td>
					<td class="<s:property value="%{#mess.messageUuid}"/>" align="center">
						<s:if test="%{#mess.replySata == 1}">
							<input type="button" id="${mess.messageUuid}" onclick="replyOk('${mess.messageUuid}')" name="reply" value="${mess.replySateName}"/>
						</s:if>
						<s:else>
							<s:if test="%{#session.roleCode.indexOf('1') >= 0 || #session.roleCode.indexOf('2') >= 0}">
								<input type="button" id="${mess.messageUuid}" onclick="reply('${mess.messageUuid}')" name="reply" value="${mess.replySateName}"/>
							</s:if>
							<s:else>
								${mess.replySateName}
							</s:else>
						</s:else>
					</td>
					<td><input type="button" id="deleteMessae" name="deleteMessae" onclick="deleteMessageById('${mess.messageUuid}');" value="删除"></td>
				</tr>
				</s:iterator>
			</s:if>
			<s:else>
				<tr style="background-image:url(../images/message_title.png);">
					<td colspan="7" align="center"><font color="#FF0000">没有发表留言</font></td>
				</tr>
			</s:else>
		</table>
	</div>
</body>
</html>