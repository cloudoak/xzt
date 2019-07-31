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
         <div class="lfztop">量表添加</div>
         <div class="liangbao">
         	<a href="#">
            	<em class="em1"></em>
                基本信息
            </a>
            <a href="#">
            	<em class="em2"></em>
                条目设置
            </a>
            <a href="#">
            	<em class="em3"></em>
                因子设置
            </a>
            <a href="#">
            	<em class="em4"></em>
                因子解释
            </a>
            <a href="#">
            	<em class="em5"></em>
                总解释
            </a>
            <br>
            <input type="button" value="进入下一步" class="button1">
            <input type="button" value="返回" class="button2">
         </div>
         <ul class="liangbaobut">
         	<li><p>量表类型</p><select class="text"><option>请选择</option></select></li>
            <li><p>量表名称</p><input type="text" class="text" placeholder="请输入"></li>
            <li><p>量表介绍</p><textarea placeholder="请输入"></textarea></li>
            <li><p>量表指导语</p><textarea placeholder="请输入"></textarea></li>
            <li><p>设置教师必需项</p><div class="p_r"><input type="checkbox"> 性别&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 职务&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 职称&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 出生年月</div></li>
            <li><p>设置来访者必需项</p><div class="p_r"><input type="checkbox"> 性别&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 民族&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 出生年月&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 籍贯&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 是否城镇&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 是否住宿&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 是否干部&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 是否单亲&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 是否与父母同住&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 父亲受教育水平&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 父亲职业&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 父亲职务&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 父亲职业&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 母亲受教育水平&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 母亲职业&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 母亲职务&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 直系亲属是否有病史&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 是否贫困&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 家庭排行</div></li>
            <li><p>家属必需项</p><div class="p_r"><input type="checkbox"> 性别&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"> 出生年月</div></li>
            <li><p>查看规则</p><div class="p_r"><input type="radio"> 允许用户查看&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"> 不允许用户查看&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"> 正常允许异常不允许用户查看</div></li>
            <li><p>规则解释</p><textarea placeholder="请输入"></textarea></li>
            <li><p>允许家长查看</p><div class="p_r"><input type="radio"> 允许&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"> 不允许</div></li>
            <li><p>所需积分</p><input type="text" class="text" placeholder="请输入"></li>
            <li><p>最少显示时间</p><input type="text" class="text" placeholder="请输入"></li>
            <li><p>最大答题时间</p><input type="text" class="text" placeholder="请输入"></li>
            <li><p>年龄限制</p><input type="text" class="text" placeholder="10" style="width:70px;"> 到 <input type="text" placeholder="10" class="text" style="width:70px;"></li>
            <li><p>是否共享量表</p><div class="p_r"><input type="radio"> 共享&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"> 不共享</div></li>
            <div class="clear"></div>
         </ul>
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