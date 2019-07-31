<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/circle.css" /> 
<link rel="stylesheet" href="${ctxStatic}/mobile-css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css"> 
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>
<%--     <%@ include file="/WEB-INF/views/include/head.jsp" %> --%>
    <title>${fns:getConfig('productName')} 评论详情</title>
</head>
<body>
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>评论详情</h1>
		<a href="${ctx }/jzmk/myCircle" class="HeaderTxt">我的对比图</a>
		<div class="HeaderRegion">
			<input type="text" value="山东" id="trigger1">
		</div>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="circle">
			<dl>
				<dt><img src="${ctxStatic }/mobile-images/touxiang.png" width="100%" height="100%"></dt>
				<dd>
					<h2>天使的翅膀</h2>
					<p>今天 15:14   来自iphone客户端</p>
				</dd>
			</dl>
			<div class="text">
				<a href="#">#幸福的镜子#</a>我的健康指数达到了10分，快来膜拜我吧！
			</div>
			<img src="${ctxStatic}/mobile-images/tu2.png" width="100%">
			<div class="clear"></div>
		</div>
		<div class="pinglunxq">
			<p><span>张三：</span>怎么体验啊？把车开到家里使用一个月么？</p>
			<p><span>张三：</span>怎么体验啊？把车开到家里使用一个月么？</p>
			<p><span>张三：</span>怎么体验啊？把车开到家里使用一个月么？</p>
			<p><span>张三：</span>怎么体验啊？把车开到家里使用一个月么？</p>
			<p><span>张三：</span>怎么体验啊？把车开到家里使用一个月么？</p>
		</div>
		<div class="bottomNavbg"></div>
	</div>
	<div class="pingluntiao">
		<input type="text" placeholder="评论" class="text">
		<input type="button" value="发送" class="button">
	</div>
	
	<script type="text/javascript">
	
	 //弹出选择层
	
	var province =['河北','河南','北京','上海','山东'];
	
	var mobileSelect1 = new MobileSelect({
		trigger: '#trigger1', 
		title: '选择地区',  
		wheels: [
					{data: province}
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