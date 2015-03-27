//在新窗口打开
function openWindow(link) {
	another = open(link, 'NewWindow');
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

	$("#Form1").attr("action", "resource/noticeAction_saveNotice");
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
	
	if(flag == false){
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
					}else{
						alert("删除失败");
					}
				});
			}
		});
	}
}
// 公告删除操作的Ajax========end

