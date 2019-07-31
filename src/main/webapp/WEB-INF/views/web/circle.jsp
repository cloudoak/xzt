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
		<div class="quanzhi">
            <div class="quanzhi_left">
                <div class="top">选择机构</div>
                <dl>
                    <dt class="on">一级机构</dt>
                    <dd>
                        <a href="#">二级机构</a>
                        <a href="#">二级机构</a>
                        <a href="#">二级机构</a>
                    </dd>
                </dl>
            </div>
            <div class="quanzhi_right">
            	<p>地区</p>
                <input type="text" class="text" placeholder="省一级"><span></span><input type="text" class="text" placeholder="省一级"><span></span><input type="text" class="text" placeholder="省一级"><input type="text" class="text" placeholder="时间控件"><span>至</span><input type="text" class="text" placeholder="时间控件">
            </div>
            <div class="clear"></div>
            <div class="quanzhi_xian">
            	<div class="left"><span class="span1">当前选择组织：</span><span class="span2">二级组织机构</span><img src="images/ico15.png"><span class="span3">我的健康指数</span><span class="span4">10分</span></div>
                <div class="right"><a href="#"><img src="images/sct.png"></a></div>
            </div>
            <ul class="quanzhi_ul">
            	<li><em class="em1"></em>对比图</li>
                <li class="on" style="border-right:0px; border-left:0px;"><em class="em2"></em>圈子</li>
            	<li><em class="em3"></em>我发布的</li>
            </ul>
            <div class="quanzhi_but">
            	<dl>
                	<dt><p><img src="images/ico16.png">&nbsp;王百万</p><span>2017-01-11   19:24</span></dt>
                    <dd class="dd1">薛庆文针对中小汽车修理企业“自动变速箱维修保养”业务缺口的保养培训。 培训目的和目标---- 一、理解和掌握新型自动变速箱保养常识 二、规避自动变速箱保养后带来的风</dd>
					<dd class="dd2"><p>李四：<span>如何掌握新型自动变速箱程和方法。</span></p><a><img src="images/ico17.png"></a></dd>
					<dd class="dd2"><p>李四：<span>如何掌握新型变速箱流程和方法。</span></p><a><img src="images/ico17.png"></a></dd>
                    <dd class="dd2"><p>李四：<span>变速箱养护作业流程和方法。</span></p><a><img src="images/ico17.png"></a></dd>
                    <dd class="dd3">
                    	<textarea></textarea> <p>收起回复 <img src="images/jianx_3.png"></p>
                    </dd>
                    <dd class="dd4"><input type="button" value="确定" class="ys1"> <input type="button" value="取消" class="ys2"></dd>
                </dl>
                <dl>
                	<dt><p><img src="images/ico16.png">&nbsp;王百万</p><span>2017-01-11   19:24</span></dt>
                    <dd class="dd1">薛庆文针对中小汽车修理企业“自动变速箱维修保养”业务缺口的保养培训。 培训目的和目标---- 一、理解和掌握新型自动变速箱保养常识 二、规避自动变速箱保养后带来的风</dd>
                </dl>
                <dl>
                	<dt><p><img src="images/ico16.png">&nbsp;王百万</p><span>2017-01-11   19:24</span></dt>
                    <dd class="dd1">薛庆文针对中小汽车修理企业“自动变速箱维修保养”业务缺口的保养培训。 培训目的和目标---- 一、理解和掌握新型自动变速箱保养常识 二、规避自动变速箱保养后带来的风</dd>
                </dl>
                <a href="#" class="jzgd">加载更多...</a>
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