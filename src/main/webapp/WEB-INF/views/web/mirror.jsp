<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/web/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>首页</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/web/common/header.jsp"%>
    <div class="Maskbox" onClick="hidekuang()"></div>
    <div class="public_left">
    	<ul>
        	<li id="li1">
            	<p onClick="xianshion(1)"><em class="em1"></em>系统常规设置<span></span></p>
            	<div class="erji">
                	<a href="#">系统参数设置</a>
                    <a href="#">系统公告管理</a>
                    <a href="#">系统积分配置</a>
                </div>
            </li>
            <li id="li2">
            	<p onClick="xianshion(2)"><em class="em2"></em>公益活动<span></span></p>
                <div class="erji">
                	<a href="#">公益活动查询/维护</a>
                </div>
            </li>
            <li id="li3" class="on">
            	<p onClick="xianshion(3)"><em class="em3"></em>家属管理<span></span></p>
                <div class="erji">
                	<a href="#">家属信息查询/维护</a>
                    <a href="#">家属自助注册审核</a>
                </div>
            </li>
            <li id="li4">
            	<p onClick="xianshion(4)"><em class="em4"></em>即时信息管理<span></span></p>
                <div class="erji">
                	<a href="#">收件箱</a>
                    <a href="#">发件箱</a>
                </div>
            </li>
            <li id="li5">
            	<p onClick="xianshion(5)"><em class="em5"></em>个人信息管理<span></span></p>
                <div class="erji">
                	<a href="#">修改个人信息</a>
                    <a href="#">密码管理</a>
                    <a href="#">给我的评价</a>
                    <a href="#">评价他人</a>
                    <a href="#">给出的评语记录</a>
                </div>
            </li>
            <li id="li6">
            	<p onClick="xianshion(6)"><em class="em6"></em>教师信息管理<span></span></p>
                <div class="erji">
                	<a href="#">部门管理</a>
                    <a href="#">教师类型管理</a>
                    <a href="#">教师信息查询/维护</a>
                    <a href="#">教师自助注册审核</a>
                    <a href="#">咨询师资格认证</a>
                    <a href="#">评价管理</a>
                    <a href="#">幸福镜子</a>
                    <a href="#">圈子</a> 
                </div>
            </li>
        </ul>
        <script>
			function xianshion(num){
				$(".public_left li").removeClass("on");
				var display =$("#li"+num+" .erji").css('display');
				if(display == 'none'){
				   $("#li"+num).addClass("on");
				}else{
					$("#li"+num).removeClass("on");
				}
				$("#li"+num+" .erji").toggle();
			}
        	 
        </script>
    </div>
    <div class="public_right">
         <div class="public_right_top"><em onClick="showkuang()"></em>当前位置：<a href="#">首页</a> -> <a href="#">频道</a> - > <a href="#">下级页面</a> -> <span>所在页面</span></div>
		<div class="xfzzbox">
			<div class="xfzzboxtop">幸福镜子</div>
			<div class="xfzzbox1">
            	<p>指导语：</p>
                <div class="p_r">我想了一会儿，然后领着男友来到玻璃窗正中间的位置，这个位置正对着飞机驾驶员座舱。我们站在那儿，我全神贯注地注视着飞机驾驶员，希望引起他们的注意。
一名飞机驾驶员抬起了头，他看到我们可怜兮兮地站在玻璃窗前。<br>我直视着他的眼睛，眼里充满了悲伤和哀求。那一刻，时间仿佛都凝滞了。最后，那名飞机驾驶员的嘴唇动了几下，另一名驾驶员也抬起了头。我又紧盯着他的眼睛，只见他点了点头。</div>
				<div class="clear"></div>
                <input type="button" class="button" value="开始评估">
            </div>
            <div class="xfzzboxtop1">上次指数统计</div>
            <div class="xfzzbox2"><span class="span1">我的健康指数</span> <span class="span2">10</span>分</div>
            <div class="xfzzbox3">
            	<p>结果解释：</p>
                <div class="p_r">如果没有人相信你,那就自己相信自己;如果没人欣赏你,那就自己欣赏自己;如果没人祝福你,那就自己祝福自己。自信是成功的源泉,...如果没有人相信你,那就自己相信自己</div>
                <div class="xfzzbox3_but">
                	<a href="#">最近30天</a>
                    <a href="#" class="on">最近60天</a>
                    <a href="#">最近90天</a>
                    <input type="text" class="text"> 至 <input type="text" class="text">
                </div>
                <img src="images/tu1.png" class="tuuu">
				<div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    <!-- 底部 -->
    <%@ include file="/WEB-INF/views/web/common/footer.jsp" %>
    <script>
        function isPc() {
            var userAgentInfo = navigator.userAgent;
            var agents = ['Android', 'iPhone', 'SymbianOS', 'Windows Phone', 'iPad', 'iPod'];
            var flag = true;
            for (var i = 0; i < agents.length; i ++) {
                if (userAgentInfo.indexOf(agents[i]) > 0) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
        function adjustScreen() {
            var winHeight = $(window).height();
            var containerHeight = winHeight - $('#header').innerHeight();
            var formHeight = $('.login-form').innerHeight();
            if (isPc()) {
                if ($(window).width() > 640) {
                    containerHeight = winHeight - $('#header').innerHeight() - $('#footer').innerHeight();
                    $('#container .wrapper').height(containerHeight);
                }
                $('.index-container .announcement').removeClass('m-announcement');
            } else {
                $('.index-container .announcement').addClass('m-announcement');
            }
            $('.consultant .frame').css({
                'height': $('.consultant .frame').width()
            });
            var frame = $('.consultant .frame').innerHeight();
            $('.consultant .frame img').css({
                'height': $('.consultant .frame img').width(),
                'margin-top': (frame - $('.consultant .frame img').width()) / 2,
                'padding-top': 0
            });
            $('.qrcode').css({
                'margin-top': ($('.scan').height() - $('.qrcode').height()) / 2
            });
        }
        adjustScreen();
        $(window).resize(function () {
            adjustScreen();
        });
		
		
		function hidekuang(){
			$('.Maskbox').removeClass("Maskbox_hide");
			$('.public_left').removeClass("public_left_hide");
		}
		function showkuang(){
			$('.Maskbox').addClass("Maskbox_hide");
			$('.public_left').addClass("public_left_hide");
		}
    </script>
</body>
</html>