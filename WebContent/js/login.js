//登陆处理===================start
function login(){
		$("#msg").remove();
		
		var username = $("#sususername").val();
		var password = $("#sususerpasd").val();
		
		var userEmpty = '<font id="msg" color="red" size="2px"><strong>用户名不能为空</strong></font>';
		var passEmpty = '<font id="msg" color="red" size="2px"><strong>密码不能为空</strong></font>';
		var loginErr = '<font id="msg" color="red" size="2px"><strong>用户名或密码错误</strong></font>';
		
		if(username == ""){
			$("#err").html(userEmpty);
			return;
		}
		
		if(password == ""){
			$("#err").html(passEmpty);
			return;
		}
		
		$("#y_loadlogin").text("登 录 中...");
		
		var url = "MenuMag/menuAction_login.action";
		var args = {"date":new Date,"logingName":username,"password":password};
		$.post(url,args,function(data){
			if(data == "1"){
				location.href="MenuMag/menuAction_home.action";
			}else if(data == "0"){
				$("#err").html(loginErr);
				$("#y_loadlogin").text("登 录");
				return;
			}else{
				$("#y_loadlogin").text("登 录");
				alert("服务器错误");
			}
		});
	}
//登陆处理===================end