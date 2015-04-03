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

// 检查添加公告时公告标题和公告内容是否合法=====start
function checkchar() {

	if ($("#noticeTit").val().length > 20) {

		alert("公告标题字数不能超过20字");
		return;
	}

	if ($("#notice").val().length > 200) {

		alert("公告内容字数不能超过200字");
		return;
	}

	$("#Form1").attr("action", "${pageContext.request.contextPath }/ResourceMa/noticeAction_saveNotice.action");
	$("#Form1").submit();

	loading();
}
// 检查添加公告时公告标题和公告内容是否合法=====end

// 为换行添加换行符========start
function addEnter(element) {
	$("#" + element).val($("#" + element).val() + "<br/>");
}
// 为换行添加换行符========end

// 添加保存时显示的进度条=======start
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

function loading() {
	document.getElementById("load").style.display = "";
	document.getElementById("opperate1").style.display = "none";
	document.getElementById("opperate2").style.display = "none";
	openContenFrame();
}
// 添加保存时显示的进度条=======end

// 公告删除操作的Ajax========start
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
// 公告删除操作的Ajax========end

// 数据字典的操作================start

//当类型发生改变的时候
function changetype() {
	
	if($("#keyWord").val() == "jerrynew"){
		var textStr = "<input type=\"text\" name=\"keywordname\" maxlength=\"50\" size=\"24\"> ";
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
// 数据字典的操作================end

//日志管理=====================start
function logDelete(meg){
	if(meg == "page"){
		var flag = window.confirm('你确定要删除当前面的日志');
		if(!flag){
			return;
		}else{
			$("#Form2").attr("action","${pageContext.request.contextPath }/ResourceMag/logAction_deleteLog.action");
			$("#Form2").submit();
		}
	}else if(meg == "all"){
		var flag = window.confirm('你确定要删除所有的日志');
		if(!flag){
			return;
		}else{
			$("#Form2").attr("action","${pageContext.request.contextPath }/ResourceMag/logAction_deleteLogAll.action");
			$("#Form2").submit();
		}
	}
}
//日志管理=====================end

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
			}else{
				alert("删除失败！");
			}
		});
	}
}
//设备管理=====================end


