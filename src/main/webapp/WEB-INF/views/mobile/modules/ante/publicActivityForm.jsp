<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/commonweal.css" />  
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/style.css" />  
<link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/LCalendar.css" />
<script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
<script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>

    <title>${fns:getConfig('productName')} 发布公益活动</title>
</head>
<body>
	<div class="Header">
		<a href="${ctx}/ante/publicActivity/list" class="Return"><i></i></a>
		<h1>发布活动</h1>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<form:form id="inputForm" modelAttribute="publicActivity" action="${ctx}/ante/publicActivity/save?myActivity=${myActivity}" method="post" class="form-horizontal m-t">
		<ul class="registerList">
			<li>
				<div class="left">活动名称</div>
				<input type="text" placeholder="请输入活动名称" name="title"  value="${publicActivity.title}" id="title" required="true">
				<div class="clear"></div>
			</li>
			<!-- <li>
				<div class="left">活动时间</div>
				<input type="text" placeholder="请输入活动时间" name="startTime">
				<div class="clear"></div>
			</li> -->
			<li>
				<div class="left">活动时间</div>
				<input type="text" id="demo1" readonly="" name="startTime" placeholder="请输入日期" value="${publicActivity.startTime }" id="startTime" required="required"/>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">联系人</div>
				<input type="text" placeholder="请输入联系人" name="createName" value="${publicActivity.createName }" id="createName">
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">联系电话</div>
				<input type="text" placeholder="请输入联系电话">
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">活动简介</div>
				<textarea placeholder="请输入活动简介" name="content" id="content" required="required"></textarea>
				<div class="clear"></div>
			</li>
		</ul>
		<div class="button_2">
			<input type="submit" value="发布" class="buttoninput"  />
		</div>
		</form:form>
	</div>
</body>
<script type="text/javascript" src="${ctxStatic }/mobile-js/jquery-min.js" ></script>
<script type="text/javascript" src="${ctxStatic }/mobile-js/LCalendar.js" ></script>
<script>
	var calendar = new LCalendar();
	calendar.init({
	    'trigger': '#demo1',//标签id
	    'type': 'date',//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择
	    'minDate':'1900-1-1',//最小日期 注意：该值会覆盖标签内定义的日期范围
	    'maxDate':'2216-3-18'//最大日期 注意：该值会覆盖标签内定义的日期范围
	});
</script>

<script type="text/javascript">
		jQuery.validator.addMethod("compareDate", function(value, element, param) {
				var startDate = $(param).val();
				var date1 = new Date(Date.parse(startDate.substring(0,10).replace(/-/g, "/")));
		        var date2 = new Date(Date.parse(value.substring(0,10).replace(/-/g, "/")));
	        return this.optional(element) || (date1 < date2);
		}, "结束日期必须大于开始日期!");
		
		$(function() {
			$("#inputForm").validate({
				rules: {
					"content":{
	                    required: true
	                },
					"startTime":{
	                    required: true
	                },
	                
	                "chk": {
	                    required: true
	                },
	                "title":{
	                	required: true
	                }
				},
				messages: {
					title :{required : "请输入活动标题!"},
					content: {required: "请输入活动简介！"},
					startTime: {required: "请输入开始时间！"},
					endTime: {required: "请输入结束时间！"},
					chk: {required: "请选择免责声明！"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>



</html>