<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/Classroom.css" /> 
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/style.css" />  
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/mobileSelect.css" />
<script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>
    <title>${fns:getConfig('productName')} 新增课件</title>
</head>
<body>
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>新增课件</h1>
	</div>
	<form:form action="${ctx}/course/course/save" id="inputForm" method="post">
  	<div class="main">
		<div class="Headerbg"></div>
		
		<ul class="registerList">
			<li class="jiantou">
				<div class="left">课件分类 </div>
				<input type="text" readonly="readonly" placeholder="请选择课件分类" id="trigger1" required="required">
				<input type="hidden" name="courseCatalogId"  id="courseCatalogId"> 
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">课件名称</div>
				<input type="text"  name="name" id="name" placeholder="请输入课件名称" required="required">
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">课件描述</div>
				<textarea placeholder="请输入课件描述" name="intro" required="required" id="intro"></textarea>
				<div class="clear"></div>
			</li>
			<li class="jiantou">
				<div class="left">上传附件</div>
				<dl>
					<dd>请选择文件</dd>
					<dd class="dd1">（限doc.html.htm.swf.ppt.txt.xls）</dd>	
					<input type="file" class="file" name="path" id="filePath" required="required" id="path">
				</dl>
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">浏览权限</div>
				<dl>
					<dt>
						<label class="labelinput">
							<input type="radio" name="1"><i></i>
						</label>
						公开&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label class="labelinput">
							<input type="radio" name="1"><i></i>
						</label>
						部分组织
					</dt>
					<dd class="dd1">
						（选择部分组织需设置可浏览的班级）
					</dd>	
				</dl>
				<div class="clear"></div>
			</li>
		</ul>
		
		<div class="button_2">
			<input type="submit" value="确定" class="buttoninput"  />
		</div>
	
	</div>
	</form:form>
<script type="text/javascript" src="${ctxStatic }/mobile-js/jquery-min.js" ></script>
<script type="text/javascript" src="${ctxStatic }/mobile-js/LCalendar.js" ></script>
<script type="text/javascript">
	 //弹出选择层
	 
	/* var nation=['课件分类1','课件分类2','课件分类3','课件分类4','课件分类5','课件分类6','课件分类7'];					 */
	/* var nation = "${courseCatelogList}".replace('[','').replace(']','').split(','); */
    var nation = [<c:forEach items="${courseCatelogList}" var="item1">'${item1.name}',</c:forEach>];  
	var nationVal = [<c:forEach items="${courseCatelogList}" var="item2">${item2.id},</c:forEach>]; 
	var mobileSelect1 = new MobileSelect({
		trigger: '#trigger1', 
		title: '课件分类',  
		wheels: [
					{data: nation}
				],
		position:[0], //初始化定位 打开时默认选中的哪个 如果不填默认为0
		transitionEnd:function(indexArr, data){
			console.log(data);
		},
		callback:function(indexArr, data){
			$("#courseCatalogId").val(nationVal[indexArr]);
		}

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
					"trigger1":{
	                    required: true
	                },
					"name":{
	                    required: true
	                },
	                
	                "intro": {
	                    required: true
	                },
	                "path":{
	                	required: true
	                }
				},
				messages: {
					trigger1 :{required : "请选择课件分类!"},
					name: {required: "请输入课件名称！"},
					intro: {required: "请输入课件描述！"},
					path: {required: "请选择课件附件！"},
					
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

</body>
</html>