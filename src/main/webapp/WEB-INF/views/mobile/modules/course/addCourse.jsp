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
  	<div class="main">
		<div class="Headerbg"></div>
		<ul class="registerList">
			<li class="jiantou">
				<div class="left">课件分类</div>
				<input type="text" placeholder="请选择课件分类" id="trigger1">
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">课件名称</div>
				<input type="text" placeholder="请输入课件名称">
				<div class="clear"></div>
			</li>
			<li>
				<div class="left">课件描述</div>
				<textarea placeholder="请输入课件描述"></textarea>
				<div class="clear"></div>
			</li>
			<li class="jiantou">
				<div class="left">上传附件</div>
				<dl>
					<dd>请选择文件</dd>
					<dd class="dd1">（限doc.html.htm.swf.ppt.txt.xls）</dd>	
					<input type="file" class="file">
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
			<input type="button" value="确定" class="buttoninput"  />
		</div>
	</div>
<script type="text/javascript">
	
	 //弹出选择层
	 
	var nation=['课件分类1','课件分类2','课件分类3','课件分类4','课件分类5','课件分类6','课件分类7'];					
	
	
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
			console.log(data);
		}
	});
	
</script>
</body>
</html>