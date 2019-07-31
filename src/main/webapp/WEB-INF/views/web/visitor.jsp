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
         <div class="lfztop">来访者信息查询/维护</div>
         <div class="lfztopbox">
         	<div class="lfztopbox_left">
            	<div class="top">选择机构</div>
                <dl>
                	<dt class="on">一级机构</dt>
                    <dd>
                    	<a href="#">二级机构</a>
                        <a href="#">二级机构</a>
                        <a href="#">二级机构</a>
                    </dd>
                </dl>
                <dl>
                	<dt>一级机构</dt>
                    <dd style="display:none;">
                    	<a href="#">二级机构</a>
                        <a href="#">二级机构</a>
                        <a href="#">二级机构</a>
                    </dd>
                </dl>
            </div>
            <ul>
            	<li><p>编号</p><input type="text" class="text" placeholder="请输入您的编号"></li>
                <li><p>姓名</p><input type="text" class="text" placeholder="请输入您的姓名"></li>
                <li><p>性别</p><input type="radio"> 男&nbsp;&nbsp;&nbsp;<input type="radio"> 女</li>
                <li><p>年龄</p><input type="text" class="text" placeholder="10" style="width:20%;"> 到 <input type="text" placeholder="10" class="text" style="width:20%;"></li>
                <li><p>电话</p><input type="text" class="text" placeholder="请输入您的联系方式"></li>
                <li><p>民族</p><select class="text"><option>请选择</option></select></li>
                <li><p>昵称</p><input type="text" class="text" placeholder="请输入您的联系方式"></li>
            </ul>
         </div>
         <div class="lfztopbut">
         	<a href="#" class="but1"></a>
            <a href="#" class="but2"></a>
            <a href="#" class="but3"></a>
            <a href="#" class="but4"></a>
            <a href="#" class="but5"></a>
         </div>
         <div class="lfztablebox">
             <table class="lfztable">
                  <tr>
                    <th class="th1"><input type="checkbox"> 编号</th>
                    <th class="th2">姓名</th>                                                                                   
                    <th class="th3">性别</th>
                    <th class="th4">机构</th>
                    <th class="th5">年龄</th>
                    <th class="th6">操作</th>
                  </tr>
                  <tr>
                    <td class="th1"><input type="checkbox"> <span>1125554771</span></td>
                    <td class="th2">张三</td>
                    <td class="th3">男</td>
                    <td class="th4">心知堂 > 2014级 > 大一 > 1班</td>
                    <td class="th5">21</td>
                    <td class="th6"><a href="#"><img src="images/ico12.png"></a>&nbsp;<a href="#"><img src="images/ico13.png"></a>&nbsp;<a href="#"><img src="images/ico14.png"></a></td>
                  </tr>
                  <tr>
                    <td class="th1"><input type="checkbox"> <span>1125554771</span></td>
                    <td class="th2">张三</td>
                    <td class="th3">男</td>
                    <td class="th4">心知堂 > 2014级 > 大一 > 1班</td>
                    <td class="th5">21</td>
                    <td class="th6"><a href="#"><img src="images/ico12.png"></a>&nbsp;<a href="#"><img src="images/ico13.png"></a>&nbsp;<a href="#"><img src="images/ico14.png"></a></td>
                  </tr>
                  <tr>
                    <td class="th1"><input type="checkbox"> <span>1125554771</span></td>
                    <td class="th2">张三</td>
                    <td class="th3">男</td>
                    <td class="th4">心知堂 > 2014级 > 大一 > 1班</td>
                    <td class="th5">21</td>
                    <td class="th6"><a href="#"><img src="images/ico12.png"></a>&nbsp;<a href="#"><img src="images/ico13.png"></a>&nbsp;<a href="#"><img src="images/ico14.png"></a></td>
                  </tr>
                  <tr>
                    <td class="th1"><input type="checkbox"> <span>1125554771</span></td>
                    <td class="th2">张三</td>
                    <td class="th3">男</td>
                    <td class="th4">心知堂 > 2014级 > 大一 > 1班</td>
                    <td class="th5">21</td>
                    <td class="th6"><a href="#"><img src="images/ico12.png"></a>&nbsp;<a href="#"><img src="images/ico13.png"></a>&nbsp;<a href="#"><img src="images/ico14.png"></a></td>
                  </tr>
                  <tr>
                    <td class="th1"><input type="checkbox"> <span>1125554771</span></td>
                    <td class="th2">张三</td>
                    <td class="th3">男</td>
                    <td class="th4">心知堂 > 2014级 > 大一 > 1班</td>
                    <td class="th5">21</td>
                    <td class="th6"><a href="#"><img src="images/ico12.png"></a>&nbsp;<a href="#"><img src="images/ico13.png"></a>&nbsp;<a href="#"><img src="images/ico14.png"></a></td>
                  </tr>
                  <tr>
                    <td class="th1"><input type="checkbox"> <span>1125554771</span></td>
                    <td class="th2">张三</td>
                    <td class="th3">男</td>
                    <td class="th4">心知堂 > 2014级 > 大一 > 1班</td>
                    <td class="th5">21</td>
                    <td class="th6"><a href="#"><img src="images/ico12.png"></a>&nbsp;<a href="#"><img src="images/ico13.png"></a>&nbsp;<a href="#"><img src="images/ico14.png"></a></td>
                  </tr>
              </table>
          </div>
          <div class="fenye">
          	<a href="#"><</a>
            <a href="#" class="on">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">6</a>
            <a href="#">7</a>
            <a href="#">~</a>
            <a href="#">99</a>
            <a href="#">100</a>
            <a href="#">></a>
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