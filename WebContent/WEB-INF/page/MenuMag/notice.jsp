<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页公告</title>
</head>
<body  style="background-color:#F5FAFE;">
	<div
		style="width: 750px; margin: auto; position: relative; z-index: 999; font-size: 12px; font-weight: normal; text-align: left; background: #fff;">
		<div
			style="height: 50px; line-height: 50px; background: #ff5f5f; overflow: hidden;">
			<div
				style="height: 48px; border-top: 1px #fff dashed; border-bottom: 1px #fff dashed; padding: 0 10px;">
				<h3 style="color: #fff; font-weight: normal; line-height: 20px;">
					<span
						style="padding-right: 8px; float: left; font-family: 'impact'; font-size: 32px;">NOTICE</span>
					<span style="float: left; font-size: 29px;">系统公告</span>
				</h3>
			</div>
		</div>
		<div
			style="padding: 20px; border: 1px solid #d7d7d7; border-top: 0; overflow: hidden;">
			<div
				style="line-height: 22px; float: left; color: #6a6a6a;">
				<s:if test="%{#request.list != null && #request.list.size() > 0 }">
				<s:iterator value="%{#request.list}" var="n" status="u">
					<div style="color: #ff5f5f; font-size: 14px; font-weight: bold;">
						${n.noticeTime }【${n.noticeTit }】
					</div>
					<div style="padding-bottom: 15px;">
						&nbsp;&nbsp;${n.notice }
					</div>
				</s:iterator>
				</s:if>
				<s:else>
					<div style="padding-bottom: 15px;">
						暂无公告
					</div>
				</s:else>
			</div>
		</div>
	</div>
</body>
</html>