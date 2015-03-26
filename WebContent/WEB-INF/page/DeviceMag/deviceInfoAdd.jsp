<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/myJquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/uploadPreview/uploadPreview.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/pub.css" />

<title>添加设备信息</title>
<script>
   window.onload = function () { 
        new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
    }
   
</script>


</head>
<body>
<form name="Form1" method="post">
	<br>
    <table cellSpacing="1" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
				<font face="宋体" size="2"><strong>添加设备</strong></font>
			</td>
		</tr>
		
	    <tr>
	       <td align="center" bgColor="#f5fafe" class="ta_01">设&nbsp;&nbsp;备&nbsp;&nbsp;名：<font color="#FF0000">*</font></td>
	       <td class="ta_01" bgColor="#ffffff">
	       		<input name="" id="" maxlength="25" size="20">
	       </td>
	       <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：<font color="#FF0000">*</font></td>
	       <td class="ta_01" bgColor="#ffffff">
	       		<input name="" id="" maxlength="25" size="20">
	       </td>
	    </tr>
	    
		<tr>
		    <td align="center" bgColor="#f5fafe" class="ta_01">设备负责人：</td>
			<td class="ta_01" bgColor="#ffffff">
				<input name="" id="" maxlength="25" size="20">
			</td>
			<td align="center" bgColor="#f5fafe" class="ta_01">生&nbsp;&nbsp;产&nbsp;&nbsp;商：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff">
	       		<input name="" id="" maxlength="25" size="20">
	       	</td>
		</tr>
		<tr>
	       	<td align="center" bgColor="#f5fafe" class="ta_01">负责人联系方式：</td>
			<td class="ta_01" bgColor="#ffffff">
				<input name="" id="" maxlength="25" size="20">
			</td>
	       	<td align="center" bgColor="#f5fafe" class="ta_01">生产商联系方式：<font color="#FF0000">*</font></td>
	       	<td class="ta_01" bgColor="#ffffff">
	       		<input name="" id="" maxlength="25" size="20">
	       	</td>
		</tr>
		
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">设备数量：</td>
			<td class="ta_01" bgColor="#ffffff">
				<input name="" id="" maxlength="25" size="20">
			</td>
			
			<td align="center" bgColor="#f5fafe" class="ta_01">设备单价：</td>
			<td class="ta_01" bgColor="#ffffff">
				<input name="" id="" maxlength="25" size="20">
			</td>
		</tr>

		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">设备图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<input type="file" id="up_img" />
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">上传的图片：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<div id="imgdiv"><img id="imgShow" width="500px" height="300px"/></div>
			</td>
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
			<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="remark" id="remark" style="width:95%" rows="4" cols="52"></textarea>
			</td>
		</tr>
		
		<tr>
			<td  align="center"  colSpan="4"  class="sep1"></td>
		</tr>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
			<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"   onClick="check_null()">
			 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
			<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="custom_close()">
			</td>
		</tr>
	</table>　
</form>
</body>
</html>