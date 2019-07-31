<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
	<link rel="stylesheet"  type="text/css" href="${ctxStatic}/mobile-css/circle.css" /> 
	<link rel="stylesheet"  href="${ctxStatic}/mobile-css/style.css">
    <link rel="stylesheet"  href="${ctxStatic}/mobile-css/mobileSelect.css"> 
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
    <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>
    <title>${fns:getConfig('productName')} 圈子</title>
    <style>
		.anniu{  position:absolute; bottom:1rem;  width:100%; z-index: 11;}
		.anniu input{ width:41%; margin-left:6%; float:right;}
		.main>div:nth-last-child(1){margin-bottom:4rem}
</style>

	</style>
</head>
<body bgcolor="#f6f6f6" style="padding-bottom:1rem;">

<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>幸福镜子</h1>
		<a href="#" class="HeaderTxt" onclick="graph();">我的对比图</a>
		<div class="HeaderRegion">
			<input readonly  type="text" value="山东" id="trigger1">
		</div>
	</div>
	
		<div class="main" >
		<div class="Headerbg"></div>
		<c:forEach items="${page.list}" var="circle">
		<div class="circle">
			<dl>
				<dt><img src="${ctxStatic}/mobile-images/touxiang.png" width="100%" height="100%"></dt>
				<dd>
					<h2>天使的翅膀</h2>
					<p>今天 15:14   来自iphone客户端</p>
				</dd>
			</dl>
			<div class="text">
				<a href="#">#幸福的镜子#</a>${circle.content}
			</div>
			<img src="${ctxStatic}/mobile-imagess/tu2.png" width="100%">
			<div class="circlebibu">
				<div class="left">评论(4)</div>
				<div class="right"><a href="#">查看详情</a></div>
			</div>
			<div class="clear"></div>
		</div>
		</c:forEach>
		
	</div>
			<div  style="position:fixed;  bottom:0.5rem;width:30%; margin-left:35%;padding:1rem  0; z-index:1000">
					<input  type="submit"  value="发布" class="buttoninput"  style="padding:0 "/>
			</div>
</body>

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
	
	function graph()
	{
		location.href='${ctx}/jzmk/circle/graph?id=${circle.id}';
	}
	
	
</script>
</html>