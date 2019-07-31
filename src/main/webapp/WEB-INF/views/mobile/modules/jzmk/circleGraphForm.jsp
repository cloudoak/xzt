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
    <script src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
   <%--  <%@ include file="/WEB-INF/views/include/head.jsp" %> --%>
    <title>${fns:getConfig('productName')} 我的对比图</title>
</head>
<body>
	<div class="Header">
		<a href="${ctx }/circle" class="Return"><i></i></a>
		<h1>我的对比图</h1>
		<a href="${ctx }/jmzk/publish" class="Headerbut">发布</a>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="duibitu_top">
			<div class="left">
				<p>选择地区</p>
				<!-- <input type="text" value="山东" class="text"  id="trigger1"/> -->
				<select id="province" class="form-control" name="province">
						<option value="">请选择你所在的城市/省</option>
						<c:forEach items="${provinces}" var="province">
							<option value="${province.code}">${province.name}</option>
						</c:forEach>
				</select>
			</div>
			<div class="right">
				当前位置
			</div>
		</div>
		<ul class="duibitu_cer">
			<li>
				<input type="button" value="2017-10-27" id="trigger2">
				<span>选择开始时间</span>
			</li>
			<li>
				<input type="button" value="2017-10-27" id="trigger3">
				<span>选择结束时间</span>
			</li>
			<div class="xian"></div>
		</ul>
		<div class="button_3">
			<input type="button" value="确定" class="buttoninput2">
		</div>
		<div class="duibitu_button">
			<ul>
				<li class="left">
					<p>9.2</p>
					<span>我的得分</span>
				</li>
				<li class="right">
					<p>8.2</p>
					<span>系统平均分</span>
				</li>
			</ul>
			<img src="${ctxStatic}/mobile-images/tu2.png" width="100%">
		</div>
		<div class="button_3">
			<input type="button" value="分享" class="buttoninput" id="fx">
		</div>
	</div>
	
	<div class="fenxiangbg"></div>
	<div class="fenxiang">
		<div class="top">分享至</div>
		<ul>
			<li><a href="#"  title="分享到QQ"><img src="${ctxStatic}/images/qq.png" width="100%">QQ</a></li>
			<li><a href="#"  title="分享到QQ空间"><img src="${ctxStatic}/images/qqkj.png" width="100%">QQ空间</a></li>
			<li><a href="#"><img src="${ctxStatic}/images/wx.png" width="100%">微信</a></li>
			<li><a href="#"><img src="${ctxStatic}/images/pyq.png" width="100%">朋友圈</a></li>
		</ul>
		<div class="guanbi"></div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
	
			$(document).on('click','#fx', function(event) {
				$('.fenxiangbg').show();
				$('.fenxiang').show();
			});
			
			$(document).on('click','.guanbi', function(event) {
				$('.fenxiangbg').hide();
				$('.fenxiang').hide();
			});
			
			
		});
		
		//弹出选择层
		var year=['2017','2018','2019','2020','2021'];
		var month=['12','11','10','9','8','7','6','5','4','3','2','1'];
		var day=['31','30','29','28','27','26','25','24','23','22','21','20','19','18','17','16','15','14','13','12','11','10','9','8','7','6','5','4','3','2','1'];
		
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
		 
		var mobileSelect2 = new MobileSelect({
			trigger: '#trigger2',
			title: '选择日期',
			wheels: [
						{data: year},
						{data: month},
						{data: day}
					],
			position:[0, 4, 1], 
			transitionEnd:function(indexArr, data){
				console.log(data);
			},
			callback:function(indexArr, data){
				console.log(data);
			}
		});
		
		var mobileSelect3 = new MobileSelect({
			trigger: '#trigger3',
			title: '选择日期',
			wheels: [
						{data: year},
						{data: month},
						{data: day}
					],
			position:[0, 4, 1], 
			transitionEnd:function(indexArr, data){
				console.log(data);
			},
			callback:function(indexArr, data){
				console.log(data);
			}
		});
		window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"image":{"viewList":["qzone","tsina","tqq","weixin","renren"],"viewText":"分享到：","viewSize":"16"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
		
	</script>
</body>
</html>