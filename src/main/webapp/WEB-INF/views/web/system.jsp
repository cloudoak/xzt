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
         <div class="pingtaigltop" style="float:left;">
         	<dl>
            	<dt>
                	<div class="left">最新公告</div>
                    <a href="#">查看更多>></a>
                </dt>
                <dd><a href="#">睡眠、睡眠科学与睡眠医</a><span class="span1">2017-01-01  19:30</span></dd>
                <dd><a href="#">张日昇教授在区劳动教养戒毒所开展的“</a><span class="span1">2017-01-01  19:30</span></dd>
                <dd><a href="#">莞城为未成年人设网上心理咨询</a><span class="span1">2017-01-01  19:30</span></dd>
                <dd><a href="#">睡眠、睡眠科学与睡眠医学</a><span class="span1">2017-01-01  19:30</span></dd>
                <dd><a href="#">张日昇教授在区劳动教养戒毒所开</a><span class="span1">2017-01-01  19:30</span></dd>
            </dl>
         </div>
         <div class="pingtaigltop" style="float:right;">
         		<dt>
                	<div class="left">公益活动</div>
                    <a href="#">查看更多>></a>
                </dt>
                <dd><a href="#">睡眠、睡眠科学与睡眠医</a><span class="span2">报名中</span></dd>
                <dd><a href="#">张日昇教授在区劳动教养戒毒所开展的“</a><span class="span2">报名中</span></dd>
                <dd><a href="#">莞城为未成年人设网上心理咨询</a><span class="span2">报名中</span></dd>
                <dd><a href="#">睡眠、睡眠科学与睡眠医学</a><span class="span2">报名中</span></dd>
                <dd><a href="#">张日昇教授在区劳动教养戒毒所开</a><span class="span2">报名中</span></dd>
         </div>
         <div class="pingtaiglbot">
         	<div class="pingtaiglbot_top">
            	<p class="on">待审核教师信息</p>
                <p style="border-left:0px;border-right:0px;">待审核来访者信息</p>
                <p>待审核家属信息</p>
            </div>
            <div class="pingtaiglbot_bot">
            	<table class="table">
                      <tr>
                        <th>用户名</th>
                        <th>姓名</th>                                                                                   
                        <th>性别</th>
                        <th>年龄</th>
                        <th>类型</th>
                        <th style="border-right:0px;">部门</th>
                      </tr>
                      <tr>
                        <td>我是用户名</td>
                        <td>张三</td>
                        <td>男</td>
                        <td>35</td>
                        <td>咨询师</td>
                        <td style="border-right:0px;">心理咨询</td>
                      </tr>
                      <tr bgcolor="#eee">
                        <td>我是用户名</td>
                        <td>张三</td>
                        <td>男</td>
                        <td>35</td>
                        <td>咨询师</td>
                        <td style="border-right:0px;">心理咨询</td>
                      </tr>
                     <tr>
                        <td>我是用户名</td>
                        <td>张三</td>
                        <td>男</td>
                        <td>35</td>
                        <td>咨询师</td>
                        <td style="border-right:0px;">心理咨询</td>
                      </tr>
                      <tr bgcolor="#eee">
                        <td>我是用户名</td>
                        <td>张三</td>
                        <td>男</td>
                        <td>35</td>
                        <td>咨询师</td>
                        <td style="border-right:0px;">心理咨询</td>
                      </tr>
                      <tr>
                        <td>我是用户名</td>
                        <td>张三</td>
                        <td>男</td>
                        <td>35</td>
                        <td>咨询师</td>
                        <td style="border-right:0px;">心理咨询</td>
                      </tr>
                      <tr bgcolor="#eee">
                        <td>我是用户名</td>
                        <td>张三</td>
                        <td>男</td>
                        <td>35</td>
                        <td>咨询师</td>
                        <td style="border-right:0px;">心理咨询</td>
                      </tr>
                  </table>
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