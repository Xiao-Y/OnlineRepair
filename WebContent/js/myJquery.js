//在新窗口打开
function openWindow(link) {
	another = open(link,'NewWindow');
}

//打开添加页面
function openAdd(url){
	window.location.href = url;
}

// 关闭当前页面
function closeWindow() {
	window.close();
}

//链接跳转
function link(href){
	window.location.href=href;
}

// 带提示的关闭当前页面
function custom_close() {
	if (confirm("您确定要关闭本页吗？")) {
		window.opener = null;
		window.open('', '_self');
		window.close();
	} else {
	}
}

// 全选、全不选(第一个复选框name为checkbox,其它都要为ids)==========start
function quanxuan() {
	var checkbox = document.getElementsByName("checkbox")[0];
	var ids = document.getElementsByName("ids");
	if (checkbox.checked) {
		for (var i = 0; i < ids.length; i++) {
			ids[i].checked = true;
		}
	} else {
		for (var i = 0; i < ids.length; i++) {
			ids[i].checked = false;
		}
	}
}
// 全选、全不选(第一个复选框name为checkbox,其它都要为ids)==========end

//资源管理====================start
// 检查添加公告时公告标题和公告内容是否合法
function checkchar() {

	if ($("#noticeTit").val().length > 20) {

		alert("公告标题字数不能超过20字");
		return;
	}

	if ($("#notice").val().length > 200) {

		alert("公告内容字数不能超过200字");
		return;
	}
	if($("#Form1").valid()){
		$("#Form1").submit();
		loading();
	}
}

// 为换行添加换行符
function addEnter(element) {
	$("#" + element).val($("#" + element).val() + "<br/>");
}

// 添加保存时显示的进度条
var len = 500;
var add = 0;
function openContenFrame() {
	var td1 = document.getElementById('tdOne');
	var td2 = document.getElementById('tdTwo');
	add = add + 10;
	td1.width = add;
	if (len - add <= 0) {
		td2.width = 1;
	} else {
		td2.width = len - add;
	}
	if (add <= len) {
		;
	} else {
		td1.width = 1;
		td2.width = 500;
		add = 0;
	}
	setTimeout('openContenFrame()', 100);
}

//加载进度条
function loading() {
	document.getElementById("load").style.display = "";
	document.getElementById("opperate1").style.display = "none";
	document.getElementById("opperate2").style.display = "none";
	openContenFrame();
}

// 公告删除操作的Ajax
function deletes() {
	var url = "${pageContext.request.contextPath }/ResourceMag/noticeAction_deleteNotice.action";
	var flag = false;
	$.each($("input:checkbox"), function(i, val) {
		if (val.checked == true) {
			flag = confirm("确定要删除所选公告？");
			return false;
		}
	});

	if (flag == false) {
		alert("请选择需要删除的公告");
		return false;
	}

	if (flag) {
		$.each($("input:checkbox"), function(i, val) {
			if (val.checked == true && i != 0) {
				var $tr = $(this).parent().parent();
				var id = $(this).attr("id");
				var args = {
					"time" : new Date,
					"id" : id
				};
				$.post(url, args, function(data) {
					if (data == "1") {
						$tr.remove();
					} else {
						alert("删除失败");
					}
				});
			}
		});
	}
}

//当类型发生改变的时候
function changetype() {
	
	if($("#keyWord").val() == "jerrynew"){
		var textStr = "<input type=\"text\" name=\"keywordname\" height=20 maxlength=\"50\" size=\"23\"> ";
		$("#newtypename").html("类型名称：");
		$("#newddlText").html(textStr);
		
		findDDl();
	}else{
		
		var textStr = "";
		$("#newtypename").html("");
		$("#newddlText").html(textStr);
		
		findDDl();
	}
}

//删除一行属性
function remove(ddlCode,seqID){
	var url = "${pageContext.request.contextPath }/ResourceMag/dictionaryAction_dictionaryRemove.action";
	var args = {
			"seqID": seqID,
			"time" : new Date
		};
	$.post(url,args,function(data){
		if (data == "1") {
			delTableRow(ddlCode);
		} else {
			alert("删除失败！");
		}
	});
}

//保存修改项
function saveDict() {

	if ($("#keyWord").val()== "jerrynew") {
		if ($.trim($("input[name=keywordname]").val()) == "") {
			alert("请输入类型名称");
			return false;
		}

		var allkeywords = document.Form1.keyWord;
		for (var i = 0; i < allkeywords.length; i++) {
			if (allkeywords[i].value == $.trim($("input[name=keywordname]").val())) {

				alert("已存在此类型名称,请重新输入");
				return false;
			}
		}
		$("#keywordname").val($.trim($("input[name=keywordname]").val()));
		$("#typeflag").val("new");
	} else {
		$("#keywordname").val($("#keyWord").val());
		$("#typeflag").val("add");
	}
	var tbl = document.getElementById("dictTbl");
	for (i = 1; i < tbl.rows.length; i++) {
		var name = tbl.rows[i].cells[1].children.item(0).value;
		if ($.trim(name) == "") {
			alert("名称不能为空！");
			return false;
		}
	}
	for (var k = 1; k <= tbl.rows.length - 2; k++) {
		for (var m = k + 1; m <= tbl.rows.length - 1; m++) {
			var name1 = tbl.rows[k].cells[1].children.item(0).value;
			var name2 = tbl.rows[m].cells[1].children.item(0).value;
			if (name1 == name2) {
				alert("名称不能相同！");
				return false;
			}
		}
	}
	$("#Form2").attr("action", "${pageContext.request.contextPath }/ResourceMag/dictionaryAction_dictionarySave.action");
	$("#Form2").submit();
}

/**
 * 为table指定行添加一行 
 * tab 表id 
 * row 行数，
 * 如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行 
 * trHtml 添加行的html代码
 */
function addTr() {
	// 获取table最后一行 $("#tab tr:last")
	// 获取table第一行 $("#tab tr").eq(0)
	// 获取table倒数第二行 $("#tab tr").eq(-2)
	var $tr = $("#dictTbl tr").eq(-1);
	var length = $("#dictTbl").find("tr").length;
	var trHtml = 
		"<tr>" +
			"<td class=\"ta_01\" align=\"center\"  width=\"15%\">"+ length + "</td>"+
			"\<td class=\"ta_01\" align=\"center\"  width=\"60%\">" +
				"<input name=\"itemname\" type=\"text\" id=\""+length+"\" size=\"45\" maxlength=25>" +
			"</td>"+
			"<td class=\"ta_01\" align=\"center\"  width=\"25%\">" +
				"<a href='javascript:delTableRow(\""+length +"\")'>" +
					"<img src=../images/delete.gif width=15 height=14 border=0 style=CURSOR:hand>" +
				"</a>" +
			"</td>" +
		"</tr>";
	
	$tr.after(trHtml);
}

/**
 * 删除一行
 * @param rowNum	当前行的编号
 */
function delTableRow(rowNum) {
	var tbl = document.getElementById("dictTbl");
	if (tbl.rows.length > rowNum) {
		tbl.deleteRow(rowNum);
		for (var i = rowNum; i < tbl.rows.length; i++) {
			tbl.rows[i].cells[0].innerText = i;
			tbl.rows[i].cells[2].innerHTML = "<a href='javascript:delTableRow(\"" + i + "\")'><img src=../images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>";
		}
	}
}

//异步加载查询出来的数据
function findDDl(){
	var keyWord = $("#keyWord").val();
	var url = "${pageContext.request.contextPath }/ResourceMag/dictionaryAction_dictionaryEdit.action";
	var args = {"date" : new Date, "keyWord" : keyWord};
	var dataType = "HTML";
	$.post(url, args,function(data) {
		$("#tableOld").attr("");
		$("#Form2").html(data);
	},dataType);
}

//删除日志
function logDelete(meg){
	if(meg == "page"){
		var flag = window.confirm('你确定要删除当前面的日志');
		if(!flag){
			return;
		}else{
			$("#Form1").attr("action","${pageContext.request.contextPath }/ResourceMag/logAction_deleteLog.action");
			$("#Form1").submit();
		}
	}else if(meg == "all"){
		var flag = window.confirm('你确定要删除所有的日志');
		if(!flag){
			return;
		}else{
			$("#Form1").attr("action","${pageContext.request.contextPath }/ResourceMag/logAction_deleteLogAll.action");
			$("#Form1").submit();
		}
	}
}
//资源管理====================start

//设备管理=====================start
//ajax删除设备信息
function deletesDevice(deviceUuid,deviceName){
	var f = confirm('你确定要删除 ' + deviceName + ' ？');
	if(f){
		var $tr = $("#"+deviceUuid);
		var url = "${pageContext.request.contextPath }/DeviceMag/deviceAction_deviceDelete.action";
		var args = {"date":new Date,"deviceTypeUuid":deviceUuid};
		$.post(url,args,function(data){
			if(data == "1"){
				$tr.remove();
			}else if(data == "0"){
				alert("删除失败！");
			}else{
				alert("服务器错误，稍后再试！");
			}
		});
	}
}

//批量删除设备
function deviceDelete(){
	var url = "${pageContext.request.contextPath }/DeviceMag/deviceAction_deviceDeletes.action";
	var flag = false;
	$.each($("input:checkbox"), function(i, val) {
		if (val.checked == true) {
			flag = confirm("确定要删除设备信息？");
			return false;
		}
	});

	if (flag == false) {
		alert("请选择需要删除的设备信息");
		return false;
	}

	if (flag) {
		$("#form1").attr("action", url);
		$("#form1").submit();
	}
}

//异步加载设备型号
function changeDevice(){
	
	$("#deviceTypeUuid").remove();
	
	var deviceName = $("#deviceName").val();
	var url = "${pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceVersion.action";
	var dataType = "JSON";
	var data = {"date":new Date,"deviceName":deviceName};
	
	$.post(url,data,function(data){
		var html = '<select id="deviceTypeUuid" name="deviceTypeUuid" style="width: 140px" data-rule-required="true">';
		
		$.each(data,function(i){
			html = html + '<option value="'+ data[i].deviceTypeUuid +'" >'+ data[i].version + '</option>';
		});
		
		html = html + "</select>";
		
		$("#versionDiv").html(html);
	},dataType);
}

//查看设备状态--条件查询
function findDeviceSatae(){
	$("#form1").attr("action","${pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceStateList.action");
	$("#form1").submit();
}


//异步单个删除设备状态信息
function deviceStateDelete(deviceStateUuid,deviceName){
	var flag = confirm('你确定要删除 “'+ deviceName +'”？');
	if(flag){
		var url = "{pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceStateDelete.action";
		var args = {"date":new Date,"deviceStateUuid":deviceStateUuid};
		$.post(url,args,function(data){
			if(data == "1"){
				$("#" + deviceStateUuid).remove();
			}else if(data == "0"){
				alert("删除失败！");
			}else{
				alert("服务器错误，稍后再试！");
			}
		});
	}
	return;
}

//批量删除设备状态信息
function diviceStateDeletes(){
	var url = "{pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceStateDeletes.action";
	var flag = false;
	$.each($("input:checkbox"), function(i, val) {
		if (val.checked == true) {
			flag = confirm("确定要删除设备状态信息？");
			return false;
		}
	});

	if (flag == false) {
		alert("请选择需要删除的设备状态信息");
		return false;
	}

	if (flag) {
		$("#form1").attr("action", url);
		$("#form1").submit();
	}
}

//检查设备状态添加和修改时的时间并提交表单
function checkTime(){
	var lastTime = $("#lastTime").val();
	var nextTime = $("#nextTime").val();
	var installationTime = $("#installationTime").val();
	
	if(lastTime != "" && nextTime != "" && installationTime != ""){
		if(nextTime <= lastTime){
			alert("下次检修时间要在上次检修时间之后");
			return;
		}
		
		if(installationTime > lastTime){
			alert("安装时间要在检修时间之前");
			return;
		}
		
		Form1.submit();
	}
}

//检查设备是否已经安装
function checkDevice(){
	var areaCode = $("#areaCode").val();
	var installationSiteCode = $("#installationSiteCode").val();
	var deviceTypeUuid = $("#deviceTypeUuid").val();
	var deviceStateUuid = $("input[name='deviceStateUuid']").val();
	 
	var url = "{pageContext.request.contextPath }/DeviceMag/deviceStateAction_checkDevice";
	var args = {"date":new Date,"areaCode":areaCode,"installationSiteCode":installationSiteCode,"deviceTypeUuid":deviceTypeUuid,"deviceStateUuid":deviceStateUuid};
	$.ajax({
		url:url,
		data:args,
		success:function(data){
			if(data == "1"){
				checkTime();
			}else if(data == "0"){
				alert("该设备状态已存在！");
			}else{
				alert("服务器错误，稍后再试！");
			}
		}
	});
}
//设备管理=====================end

//用户信息管理操作===============start

//添加用户时，验证登陆名是否重复
function checkLoginName(){
	var loginName = $("#loginName").val();
	if(loginName != ''){
		var url = "${pageContext.request.contextPath }/UserMag/userAction_userCheckLoginName.action";
		var args = {"date":new Date, "loginName":loginName};
		$.ajax({
			url:url,
			data:args,
			success:function(data){
				if(data == '1'){
					$("#loginNameErr").html("<font id='err' color='#FF0000'>用户名重复</font>");
					$("#BT_Submit").attr("disabled",true);
				}else if(data == '0'){
					$("#err").remove();
					$("#BT_Submit").attr("disabled",false);
				}else{
					alert("服务器错误，稍后再试！");
				}
			}
		});
	}
}

//删除用户信息
function deleteUser(name,userUuid){
	var f = confirm('你确定要删除 ' + name + ' ？');
	if(f){
		var $tr = $("#"+userUuid);
		var url = "${pageContext.request.contextPath }/UserMag/userAction_userDelete.action";
		var args = {"date":new Date,"userUuid":userUuid};
		$.post(url,args,function(data){
			if(data == "1"){
				$tr.remove();
			}else if(data == "0"){
				alert("删除失败！");
			}else{
				alert("服务器错误，稍后再试！");
			}
		});
	}
}

//批量删除用户信息 
function userDel() {
	var url = "${pageContext.request.contextPath }/UserMag/userAction_userDeletes.action";
	var flag = false;
	$.each($("input:checkbox"), function(i, val) {
		if (val.checked == true) {
			flag = confirm("确定要删除用户信息？");
			return false;
		}
	});

	if (flag == false) {
		alert("请选择需要删除的用户");
		return false;
	}

	if (flag) {
		$("#form1").attr("action", url);
		$("#form1").submit();
	}
}

//查询用户信息
function userFind(){
	$("#form1").attr("action", "${pageContext.request.contextPath}/UserMag/userAction_userIndex.action");
	$("#form1").submit();
}

//用户信息管理操作===============end

//申报管理======================start
//当区域改变时，安装位置动态加载   
function changeDeviceStateArea(){

	$("#installationSiteCode").remove();
	$("#deviceName").remove();
	$("#deviceStateUuid").remove();
	
	var areaCode = $("#areaCode").val();
	if(areaCode != ""){
		var url = "${pageContext.request.contextPath }/ReportingMag/reportingAction_deviceInstallationSiteByArea.action";
		var dataType = "JSON";
		var args = {"date":new Date,"areaCode":areaCode};
		
		$.post(url,args,function(data){
			var html = '<select id="installationSiteCode" name="installationSiteCode" onchange="changeDeviceStateDeviceName();" style="width: 140px" data-rule-required="true">';
			html = html + "<option value=''>------请选择------</option>";
			$.each(data,function(i){
				html = html + '<option value="'+ data[i].installationSiteCode +'" >'+ data[i].installationSiteName + '</option>';
			});
			
			html = html + "</select>";
			
			$("#installationSiteDiv").html(html);
		},dataType);
	}
}

	//当安置改变时，动态加载设备名
function changeDeviceStateDeviceName(){
   $("#deviceName").remove();
   $("#deviceStateUuid").remove();
   
	var areaCode = $("#areaCode").val();
	var installationSiteCode = $("#installationSiteCode").val();
	
	if(areaCode != "" && installationSiteCode != ""){
		var url = "${pageContext.request.contextPath }/ReportingMag/reportingAction_deviceNameByinstallationSite.action";
		var dataType = "JSON";
		var args = {"date":new Date,"areaCode":areaCode,"installationSiteCode":installationSiteCode};
		
		$.post(url,args,function(data){
			var html = '<select id="deviceName" name="deviceName" onchange="changeDeviceStateVersion();" style="width: 140px" data-rule-required="true">';
			html = html + "<option value=''>------请选择------</option>";
			$.each(data,function(i){
				html = html + '<option value="'+ data[i].deviceName +'" >'+ data[i].deviceName + '</option>';
			});
			
			html = html + "</select>";
			
			$("#deviceNameDiv").html(html);
		},dataType);
	}
}
	
//当设备改变时，动态加载设备型号
function changeDeviceStateVersion(){
   $("#deviceStateUuid").remove();
	
	var areaCode = $("#areaCode").val();
	var installationSiteCode = $("#installationSiteCode").val();
	var deviceName = $("#deviceName").val();
	
	if(areaCode != "" && installationSiteCode != "" && deviceName != ""){
		var url = "${pageContext.request.contextPath }/ReportingMag/reportingAction_versionBydeviceName.action";
		var dataType = "JSON";
		var args = {"date":new Date,"areaCode":areaCode,"installationSiteCode":installationSiteCode,"deviceName":deviceName};
		
		$.post(url,args,function(data){
			var html = '<select id="deviceStateUuid" name="deviceStateUuid" style="width: 140px" data-rule-required="true">';
			html = html + "<option value=''>------请选择------</option>";
			$.each(data,function(i){
				html = html + '<option value="'+ data[i].deviceStateUuid +'" >'+ data[i].version + '</option>';
			});
			
			html = html + "</select>";
			
			$("#versionDiv").html(html);
		},dataType);
	}
}

//检查设备是否正常运行
function ChackReportingBugInfoByDeviceStatUuid(){
	var deviceStateUuid = $("#deviceStateUuid").val();
	var url = "${pageContext.request.contextPath}/ReportingMag/reportingAction_deviceStateInfoDeviceStatUuid.action";
	var args = {"date":new Date,"deviceStateUuid":deviceStateUuid};
	$.ajax({
		url:url,
		data:args,
		success:function(data){
			if(data == "1"){
				var f = confirm("保存成功，等待审核。是否继续添加申报信息？");
				if(f){
					//继续添加
					$("#flag").val("1");
				}
				Form1.submit();
			}else if(data == "0"){
				alert("该设备故障申报信息已存在！");
			}else{
				alert("服务器错误，稍后再试！");
			}
		}
	});
}

//保存申报信息
function reportingBugInfoSave(){
	
	$("#Form1").validate();
	
	if($("#Form1").valid()){
		//检查设备是否正常运行
		ChackReportingBugInfoByDeviceStatUuid();
	}
}

//申报列表查询
//查询用户信息
function reportingBugInfoFind(){
	$("#form1").attr("action", "${pageContext.request.contextPath}/ReportingMag/reportingAction_reportingBugInfoList.action");
	$("#form1").submit();
}

//异步删除申报信息
function deleteReportingBugInfo(deviceName, reportingUuid, auditStatCode, auditUuid){
	var flag = confirm("确定删除“" + deviceName + "”申报信息？");
	if(flag){
		//审核通过。删除审核通过的申报信息，将会删除审核信息和评价信息
		if(auditStatCode == "2"){
			var reFlag = confirm("删除审核通过的申报信息，将会删除审核信息和评价信息。是否删除？");
			if(!reFlag){
				return;
			}else{
				deleteReportingInfo(reportingUuid, auditStatCode, auditUuid);
			}
		//审核未通过。删除审核未通过的申报信息，将会删除审核信息
		}else if(auditStatCode == "3"){
			var fg = confirm("删除审核未通过的申报信息，将会删除审核信息。是否删除？");
			if(!fg){
				return;
			}else{
				deleteReportingInfo(reportingUuid, auditStatCode, auditUuid);
			}
		//待审核的
		}else if(auditStatCode == "1"){
			deleteReportingInfo(reportingUuid, auditStatCode, auditUuid);
		}
	}
}

//删除申报信息
function deleteReportingInfo(reportingUuid, auditStatCode, auditUuid){
	var url = "${pageContext.request.contextPath }/ReportingMag/reportingAction_deleteReportingBugInfo.action";
	var args = {"date":new Date,"reportingUuid":reportingUuid,"auditStatCode":auditStatCode,"auditUuid":auditUuid};
	$.post(url,args,function(data){
		if(data == "1"){
			$("#" + reportingUuid).remove();
		}else if(data == "0"){
			alert("删除失败");
		}else{
			alert("服务器错误，稍后再试！");
		}
	});
}
//申报管理======================end

//审核管理====================start
//待审核申报查询
function auditInfoWaitSearch(){
	$("#form1").attr("action","${pageContext.request.contextPath}/AuditMag/auditInfoAction_auditInfoWaitList.action");
	$("#form1").submit();
}

//通过维护类别异步加载用户名
function auditInfoWaitMaintainType(){
	$("#maintainUuid").remove();
	
	var maintainTypeCode = $("#maintainTypeCode").val();
	if(maintainTypeCode != ""){
		var url = "${pageContext.request.contextPath}/AuditMag/auditInfoAction_auditInfoWaitUser.action";
		var dataType = "JSON";
		var data = {"date":new Date,"maintainTypeCode":maintainTypeCode};
		
		$.post(url,data,function(data){
			var html = '<select id="maintainUuid" name="maintainUuid" style="width: 140px" data-rule-required="true">';
			
			$.each(data,function(i){
				html = html + '<option value="'+ data[i].userUuid +'" >'+ data[i].name + '</option>';
			});
			
			html = html + "</select>";
			
			$("#auditInfoWaitMaintainUserDiv").html(html);
		},dataType);
	}
}

//当审核状态为通过的时候显示出维护人员
function auditStateChange(){
	
	 var auditStatCode = $('input[name=auditStatCode]:checked').val();
	 if(auditStatCode == '3'){//驳回
		$('#maintainTypeDIV').hide();
	 	$('#tr_failAccount').show();
	 	$('#tr_MaintainType').hide();
	 	$('#tr_pass_maintainStat').hide();
	 }else if(auditStatCode == '2'){//通过
		$('#maintainTypeDIV').show();
		$('#tr_failAccount').hide();
		$('#tr_MaintainType').show();
		$('#tr_pass_maintainStat').show();
		$('#maintainStatCode1').hide();
	 }else if(auditStatCode == '1'){//待审核
		 $('#maintainTypeDIV').hide();
		 $('#tr_failAccount').hide();
		 $('#tr_MaintainType').hide();
		 $('#tr_pass_maintainStat').hide();
	 }
}

//保存待审核时验证
function auditInfoButton(){
   $("#Form1").validate();
	
	if($("#Form1").valid()){
		$("#Form1").attr('action','${pageContext.request.contextPath}/AuditMag/auditInfoAction_auditInfoWaitSave.action');
		Form1.submit();
	}
}

//审核通过的条件查询
function auditInfoPassFind(){
	$('#form1').attr('action','${pageContext.request.contextPath}/AuditMag/auditInfoAction_auditInfoPassList.action');
	$("#form1").submit();
}

//保存审核通过时验证
function auditInfoPassButton(){
   $("#Form1").validate();
	
	if($("#Form1").valid()){
		$("#Form1").attr('action','${pageContext.request.contextPath}/AuditMag/auditInfoAction_auditInfoPassSave.action');
		Form1.submit();
	}
}

//审核未通过的条件查询
function auditInfoRefuseFind(){
	$('#form1').attr('action','${pageContext.request.contextPath}/AuditMag/auditInfoAction_auditInfoRefuseList.action');
	$("#form1").submit();
}

//保存审核未通过时验证
function auditInfoRefuseButton(){
   $("#Form1").validate();
	
	if($("#Form1").valid()){
		$("#Form1").attr('action','${pageContext.request.contextPath}/AuditMag/auditInfoAction_auditRefuseSave.action');
		Form1.submit();
	}
}
//审核管理====================end

//评价留言=====================start
//删除留言
function deleteMessageById(messageUuid){
	$.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath }/EvaluateMag/messageAction_deleteMessageById.action",
        async: false,
        data: {"date":new Date,"messageUuid":messageUuid,},
        success: function (data) {
        	if(data == '1'){
        		$("#tr"+messageUuid).remove();
        	}else if(data == '0'){
        		alert("删除失败！！");
        	}else{
        		alert("服务器错误，稍后再试！！");
        	}
        }
     });
}

//提交条件查询的评价搜索
function findEvaluateInfo(){
	$("#form1").attr("action","${pageContext.request.contextPath}/EvaluateMag/evaluateAction_evaluateList.action");
	$("#form1").submit();
}

//单个删除评价信息
function deleteEvaluateInfo(evaluateUuid){
	var f = confirm('你确定要删除该条评价？');
	if(f){
		var $tr = $("#"+evaluateUuid);
		var url = "${pageContext.request.contextPath }/EvaluateMag/evaluateAction_deleteEvaluateInfo.action";
		var args = {"date":new Date,"evaluateUuid":evaluateUuid};
		$.post(url,args,function(data){
			if(data == "1"){
				$tr.remove();
			}else if(data == "0"){
				alert("删除失败！");
			}else{
				alert("服务器错误，稍后再试！");
			}
		});
	}
}

//批量删除评价信息
function deletesEvaluateInfo(){
	var url = "${pageContext.request.contextPath }/EvaluateMag/evaluateAction_deletesEvaluateInfo.action";
	var flag = false;
	$.each($("input:checkbox"), function(i, val) {
		if (val.checked == true) {
			flag = confirm("确定要删除设备信息？");
			return false;
		}
	});

	if (flag == false) {
		alert("请选择需要删除的设备信息");
		return false;
	}

	if (flag) {
		$("#form1").attr("action", url);
		$("#form1").submit();
	}
}
//评价留言=====================end


//权限管理=====================start
//用于保存用户角色和权限
function saveRole(){
	var roleid = $("#roleCode").val(); 		 
     $("#roleid").val(roleid);
     $("#Form2").attr("action","${pageContext.request.contextPath }/AuthorityMag/roleAction_save.action");
     $("#Form2").submit();
}

//用于当编辑用户的角色和权限的时候load加载编辑页面
function selectRole(){
   
   if($("#Form1 :input[name=roleCode]").val()=="0"){
  	 	$("#Form1").attr("action","${pageContext.request.contextPath }/AuthorityMag/roleAction_home.action");
      	$("#Form1").submit();            
   }else{
   	var roleCode = $("#Form1 :input[name=roleCode]").val();
 	  	$("#Form2").text("");
       $("#Form2").load("AuthorityMag/roleAction_edit.action",{"roleCode":roleCode});
   }
}

// 权限的全选/全不选
function checkAllOper(oper){
	 var selectoper = $(":input[name=popedomCode]");
	 for(var i = 0; i < selectoper.length; i++){
		 selectoper[i].checked = oper.checked;
	 }
}

	//用户全选/全不选
function checkAllUser(user){
	 var selectoper = $(":input[name=userIds]");
	 for(var i = 0; i < selectoper.length; i++){
		 selectoper[i].checked = user.checked;
	 }
}
	
//当一个权限被选种后，其隐藏的复选框会被选种
function selectChild(code){
	var f = false;
	
	$(".selectoper"+ code).each(function(){
		if($(this).attr("checked")){
			f = true;
			return;
		}
	});
	
	if(f){
		$(".popedomCode"+ code).attr("checked",true);
	}else{
		$(".popedomCode"+ code).attr("checked",false);
	}
}
//权限管理=====================end