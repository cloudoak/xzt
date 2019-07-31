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
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>${fns:getConfig('productName')} 班级</title>
</head>
<body>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.inactive').click(function(){
			if($(this).siblings('ul').css('display')=='none'){
				$(this).parent('li').siblings('li').removeClass('inactives');
				$(this).addClass('inactives');
				$(this).siblings('ul').slideDown(100).children('li');
				if($(this).parents('li').siblings('li').children('ul').css('display')=='block'){
					$(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
					$(this).parents('li').siblings('li').children('ul').slideUp(100);

				}
			}else{
				//控制自身变成+号
				$(this).removeClass('inactives');
				//控制自身菜单下子菜单隐藏
				$(this).siblings('ul').slideUp(100);
				//控制自身子菜单变成+号
				$(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
				//控制自身菜单下子菜单隐藏
				$(this).siblings('ul').children('li').children('ul').slideUp(100);

				//控制同级菜单只保持一个是展开的（-号显示）
				$(this).siblings('ul').children('li').children('a').removeClass('inactives');
			}
		})
	});
	</script>
</head>
<body>
	<div class="Header">
		<a href="#" class="Return"><i></i></a>
		<h1>选择班级</h1>
	</div>
  	<div class="main">
		<div class="Headerbg"></div>
			<div class="list">
				<div class="biaoti">清华大学</div>
				<ul class="yiji">
					<li><a href="#" class="inactive"><em></em>大学一年级</a>
						<ul style="display: none">
							<li><a href="#" class="inactive active"><em></em>物理系</a>
								<ul>
									<li><a href="#"><label class="labelinput"><input type="radio" name="1"><i></i></label> 某某班级</a></li>
									<li><a href="#"><label class="labelinput"><input type="radio" name="1"><i></i></label> 某某班级</a></li>
									<li><a href="#"><label class="labelinput"><input type="radio" name="1"><i></i></label> 某某班级</a></li>
								</ul>
						</li> 
					</ul>
				</li>
				<li><a href="#" class="inactive"><em></em>大学二年级</a>
						<ul style="display: none">
							<li><a href="#" class="inactive active"><em></em>物理系</a>
								<ul>
									<li><a href="#"><label class="labelinput"><input type="radio" name="1"><i></i></label> 某某班级</a></li>
									<li><a href="#"><label class="labelinput"><input type="radio" name="1"><i></i></label> 某某班级</a></li>
									<li><a href="#"><label class="labelinput"><input type="radio" name="1"><i></i></label> 某某班级</a></li>
								</ul>
						</li> 
					</ul>
				</li>
			</ul>
			
		</div>
	</div>
</body>
</html>