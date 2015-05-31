访问路径：
http://localhost:8090/OnlineRepair

初始化菜单：
http://localhost:8090/OnlieRepair/ResourceMag/menuInitAction_menuInit.action

初始化数据字典：
http://localhost:8090/OnlieRepair/ResourceMag/dictionaryAction_dictionaryInit.action


js文件：
My97DatePicker			日历
uploadPreview			上传图片预览
myJquery.js				自己的js操作
jquery-easyui-1.4.2		EaseUi用于添加留言
login.js				用于登陆
jqueryValidate			用于表单的验证


css文件：
pub.css		公有的样式

一、MenuMag		菜单管理
	home.jsp	主页面
	left.jsp	左边菜单
	top.jsp		头文件

二、DeviceMag	设备管理
	1、主要管理的是设备
	deviceInfoList.jsp		设备信息管理
	deviceInfoAdd.jsp		添加设备信息
	deviceInfoAdd.jsp		编辑设备信息
	deviceInfoView.jsp		查看设备详细信息
	
	2、主要管理设备的运行状态
	deviceStateList.jsp		查看设备状态
	deviceStateAdd.jsp		添加设备状态信息
	deviceStateEdit.jsp		编辑设备状态信息
	deviceStateView.jsp		查看设备详细状态信息
	
三、UserMag		用户信息管理
	userInfoList.jsp		用户信息列表
	userInfoAdd.jsp			添加用户信息
	userInfoEdit.jsp		编辑用户信息
	userInfoView.jsp		查看用户的详细信息
	
四、ReportingMag	申报管理
	reportingBugInfo.jsp 				申报故障信息
	reportingBugInfoList.jsp			所有故障信息列表
	//reportingBugInfoEdit.jsp.jsp		编辑故障信息
	reportingBugInfoView.jsp.jsp		查看故障信息

五、AuditMag		审核管理
	1.通过审核的
	auditInfoPassEdit.jsp			编辑通过审核故障信息
	auditInfoPassList.jsp			通过审核故障信息列表
	//auditInfoPassView.jsp			查看通过审核故障信息
	
	2.未通过审核的
	auditInfoRefuseEdit.jsp			编辑未通过审核故障信息
	auditInfoRefuseList.jsp			未通过审核故障信息列表
	auditInfoRefuseView.jsp			查看未通过审核故障信息
	
	
	3.待审核的
	auditInfoWaitEdit.jsp			编辑待审核故障信息
	auditInfoWaitList.jsp			待审核故障信息列表
	auditInfoWaitView.jsp			查看待审核故障信息
	
六、EvaluateMag	评价管理
	evaluateInfoList.jsp			评价列表
	evaluateInfoEdit.jsp			去评价

六、AuthorityMag 权限管理
	roleIndex.jsp					权限列表
	roleEdit.jsp					编辑用户权限
	
七、CountMag		统计管理
	deviceBreakdownCount.jsp		设备种类出现故障报表统计
	userEvaluateCount.jsp			用户各种评价报表统计
	userSexCount.jsp				用户性别报表统计
	userTypeCount.jsp				用户类型报表统计
	
八、ResourceMag  资源管理
	dictionaryEdit.jsp				编辑数据字典
	dictionaryIndex.jsp				数据字典列表
	actingIndex.jsp					公告
	logList.jsp						日志列表
	logIndex.jsp					日志初始列表
	
一、使用技术：
	①分页的使用方法：
		1.添加PageTag.java文件。
		2.添加pgaeTag.tld文件到WEB-INF下
		3.添加分页对象BaseForm,需要分页的页面都要继承BaseForm.java
		
		4.Action、service中传入父类Form,dao中调用父类的findCollectionByConditionWithPage和countByCollection。
			一个是查询分页的数据，一个是查询总记录数的
			Action中将分页后的数据放入到request中，将总记录数放入到当前父类的Form的setRecordCount中。
		
		5.页面上使用：
			<x:pager pageNo="${pageNo}" recordCount="${recordCount}" pageSize="${pageSize}" url="${pageContext.request.contextPath }/ResourceMag/logAction_logIndex.action"/>
		
		注意：可以在继承BaseForm的文件中添加private Integer pageSize = 15;自定义分页大小
	
	②jqueryValidate使用方法：
		1.引入js及css
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jqueryValidate/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jqueryValidate/jquery.validate.message_cn.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jqueryValidate/com.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jqueryValidate/com.validate.expand.js"></script>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/pub.css" />
		2.要验证的表单中添加class="form-validate"，必须要有id和name
		3.需要验证的输入项中添加data-rule-required="true"
		注意：
			com.validate.expand.js中可以添加自定义的方法，使用请参照其它方法;
			com.validate.js用于显示错误信息
			jquery.validate.message_cn.js将英文信息修改为中文
			
		现已修改为公有的。导入<jsp:include page="/pub.jsp"/>就行了
		
	③My97DatePicker	日历使用方法：
		1.引入<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
		2.输入项中onclick="WdatePicker({readOnly:true,highLineWeekDay:false})" 
			readOnly为true表示不允许手动写入，highLineWeekDay为false表示不显示周
			
	④uploadPreview	上传图片预览使用方法：
		1.添加js代码：
			<script>
			   window.onload = function () { 
			        new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
			    }
			</script>
		2.
			上传图上：<input type="file" id="up_img" />
			显示图片：<div id="imgdiv"><img id="imgShow" width="500px" height="300px"/></div>
		注意：
			UpBtn中的名字要与上传图片的id相同，
			DivShow中的名字要与显示图片中的div的id相同
			ImgShow中的名字要与显示图片中的img中的id相同
			
	⑤使用Struts2标签：
		当使用strus2标签显示数据时，可以使用ActionContext.getContext().getValueStack().push(userForm);进行页面数据的显示
		
	⑥上传图片方法的使用：
		1.xxForm需要继承BaseForm 上传的文件id和name为image不能改变。
		2.程序中调用com.xiaoy.base.util.UploadImageHelper中的uploadImage(BaseForm)方法
		3.Action中UploadImageHelper.uploadImage(xxForm);
		4.设备图片保存路径UploadImageHelper.PICURL
		
		注意：在更新的时候需要删除原来的图片。
			//删除原来的图片
			UploadImageHelper.deleteImage(request, devicePicUrl);
			request:当前的request，为了取得服务器上的路径。
			devicePiceUrl:为数据库中保存的图片路径。格式：/deviceStateUploadImages/20150410104208.jpg
	
	⑦使用谷歌的Gson.jar将集合转换成json：
		1.Gson gson = new Gson();
		2.String json = gson.toJson(list);
		3.发送数据到页面
			try {
				response.getOutputStream().print(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		4.页面上：
			var deviceName = $("#deviceName").val();
		   	var url = "${pageContext.request.contextPath }/DeviceMag/deviceStateAction_deviceVersion.action";
		   	var dataType = "JSON";
		   	var data = {"date":new Date,"deviceName":deviceName};
		   
			$.post(url,data,function(data){
			   var html = '';
			   $.each(data,function(i){
				   html = html + '<option value="'+ data[i].deviceTypeUuid +'" >'+ data[i].version + '</option>';
			   });
			   $("#version").html(html);
		   	},dataType);
		注意：
			方法无返回值，页面使用ajax接收。dataType = "JSON";
		
	⑧由于审核状态和维护状态都是由数据字典提供，初始化时（保存申报信息时，添加审核信息）故不能确定。
		数据字典中：
		固定系统审核状为1时表示待审核。
		固定系统维护状态为1时表示未处理。

	⑨由于在审核操作的时候，保存了操作人也就是审核人。所以在以后的维护人员在对设备维护状态修改的时候只能修改维护状态。
	
	⑩初始化菜单时，资源管理下的初始化菜单链接强制不初始化

二、页面修改
	①去掉设备信息管理中的“设备负责人”及“设备负责人联系方式”
	②修改页面加载js、css方式为添加公有的pub.jsp
	③去掉Function.js
	④去掉Style.css
	⑤修改页面分页样式，使用pageTag2.css,PageTag2.java
	⑥抽取左侧菜单样式为单独的menu.css
	⑦使用EaseUi完成添加留言
	

三、错误记录：
	①Object转Integer时报错：
		解决方法：
		Integer.parseInt(count.toString())
		
	②添加设备信息后保存不上
		解决方法：
		没有在service层添加事务
		@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
		
	③上传图片的时候出现错误：
		解决方法：
		上传图片的时候主要是由于修改时出现问题，修改设备信息的时候可能没有修改图片信息。
		所有要做一个判断，是否有新图片上传。如果有新图片上传，设置form的enctype="multipart/form-data"，
		直接更新设备对象。如果没有修改图片。使用jquery设置form中enctype的属性为""，使用隐藏域将以前的
		图片路径再传到Action中，放入路径属性中再更新。更新、保存都要做上传图片处理。
		
	④分页标签不显示：
		解决方法：
		检查是否引用分面标签及css样式。在引用的公用pub.jsp中不知道是什么原因在请求Action时出现错误。
		删除所有的html元素后只保留<%@ page language="java" pageEncoding="UTF-8"%>就可以请求到。
	
	⑤删除所有日志是出现错误：
		解决方法：
		没有添加事务注解。删除条件上要用""参数上用null。条件上不能用null，会被当做条件执行，结果就是报错。
	
	⑥没有实例化的错误：
		解决方法：
		主要是没有在applicationContext.xml中添加自动扫描。
		修改：扫描路径为com.xiaoy
	
	⑦出现乱码：
		解决方法：
		修改form的提交方式为method="post"
		
	⑧时间查询出现错误：
		解决方法：
		and e.installationTime = timestamp(:installationTime)加上timestamp格式化。
		
		select timestamp('2008-08-08');                         -- 2008-08-08 00:00:00
		select timestamp('2008-08-08 08:00:00', '01:01:01');    -- 2008-08-08 09:01:01
		select timestamp('2008-08-08 08:00:00', '10 01:01:01'); -- 2008-08-18 09:01:01 
	
	⑨设备状态在做更新的时候出现错误：
		可能是一对多的时候有什么问题。
		解决方法：
		暂时未解决。
		已解决：
		deviceState.setDeviceStateUuid(deviceStateForm.getDeviceTypeUuid());
		写错了应该为:
		deviceState.setDeviceStateUuid(deviceStateForm.getDeviceStateUuid());
	
	⑩radio使用change事件首次触发不起作用：
		改变选项不能使用change事件而要使用click事件
		
	11、由于事物是控制在Service中的，故所有的除查询外，其它的都要使用dao注入。
	
	12、自表连接的时候要设定<set name="menus" table="MENU" inverse="true" lazy="false">
		不然在更新的时候，连接字就更新为NULL
		
	13、建立菜单表出错：
		使用了表中的关键字order，更改为orderMenu
		
	14、查询出来的set集合排序：<set name="menus" table="MENU" inverse="true" lazy="false" order-by="orderMenu">
	
	15、添加设备故障次数字段：deviceNum
		用于记录设备的故障次数。首次添加设备是，故障次数为0、修改设备信息后，故障设备次数为0.
		
	16、修改时间的存储格式为：yyyy-MM-dd hh:ss:mm
		查询的时候修改为：
		and e.installationTime >= timestamp(:installationTime,'00 00:00:00');
		and e.installationTime < timestamp(:installationTime,'01 00:00:00');
		map.put("installationTime",installationTime);
	
申报业务流：
用户填写申报信息，后台添加审核记录-->

	|--->管理员在待审核列表中进行审核--------->驳回：填写填写驳回原因。	
	|								|
	|								|------>审核通过：分配维护类型和维护人员，修改维护状态为“待维护”,后台添加评价记录
	|								|
	|								|------>待审核：不作处理
	|								
	|--->管理员在通过审核列中进行修改--------->待审核：删除评价记录、清空审核表中维护人员uuid、清空完成时间、清空驳回原因，维护状态设为“未处理”，修改审核状态为“待审核”
	|								|			
	|								|----->审核通过（不修改审核状态）：------>已处理：修改维护人员uuid、添加新的完成时间、清空驳回原因、维护状态设置为“已处理”，修改审核状态为“审核通过”			
	|								|								|
	|								|								|----->待处理：修改维护人员uuid、清空完成时间、清空驳回原因、维护状态设为“待处理”，修改审核状态为“审核通过”
	|								|
	|								|----->驳回：删除评价记录、清空审核表中维护人员uuid、清空完成时间、填写驳回原因，维护状态设为“未处理”	，修改审核状态为“驳回”		
	|										
	|--->管理员在未通过审核列表中进行修改------>待审核：修改审核状态为“待审核”、修改审核日期										
										|	
										|-->审核通过：添加评价记录、清空驳回原因、修改审核日期、维护状态设为“待维护”，添加维护人员uuid，添加审核通过时间，添加审核人uuid、更改设备状态	
										|
										|-->驳回：不作处理、修改驳回原因、修改审核日期	
											
											
											
											
											
											