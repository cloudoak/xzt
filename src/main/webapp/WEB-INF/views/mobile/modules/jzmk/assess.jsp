<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/Assessment.css" /> 
<link rel="stylesheet" href="${ctxStatic}/mobile-css/style.css">
<link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css">  
<script type="text/javascript"  src="${ctxStatic }/mobile-js/jquery-min.js"></script>
 <script type="text/javascript"  src="${ctxStatic }/mobile-js/mobileSelect.js"></script>
    <title>${fns:getConfig('productName')} 酣眠评估</title>
</head>
<body>
		<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>睡眠评估</h1>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="smpgbox">
			<ul>
				<li class="ys1"><a href="/xzt/a/jzmk/scaleCheckTask/list"><img src="${ctxStatic}/mobile-images/smpgbox1.png"><br>测评任务管理</a></li>
				<li class="ys2"><a href="${ctx }/jzmk/assessResult"><img src="${ctxStatic}/mobile-images/smpgbox2.png"><br>测评结果</a></li>
				<li class="ys3"><a href="${ctx }/jzmk/mentality"><img src="${ctxStatic}/mobile-images/smpgbox3.png"><br>心理异常筛选</a></li>
				<li class="ys4"><a href="#"><img src="${ctxStatic}/mobile-images/smpgbox4.png"><br>测评统计</a></li>
			</ul>
		</div>
		<div class="clear"></div>
		<dl class="smpgboxdl">
			<dt>
				<div class="left">待测量表</div>
				<a href="#">更多></a>
			</dt>
			<dd>
				<p><a href="#">症状自评量表（SCL-90）</a></p>
				<p><a href="#">幸福度测试量表</a></p>
				<p><a href="#">症状自评量表（SCL-90）</a></p>
				<p><a href="#">幸福度测试量表</a></p>
			</dd>
		</dl>
		<dl class="smpgboxdl">
			<dt>
				<div class="left">漏测量表</div>
				<a href="#">更多></a>
			</dt>
			<dd>
				<p class="certs">您现在没有漏测的量表！</p>
			</dd>
		</dl>
	</div>
</body>
</html>