<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/web/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>首页</title>
    <link href="${serverUrl }css/global.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/reset.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/style.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/measure.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/index.css" type="text/css" rel="stylesheet" />
	<link href="${serverUrl }css/header.css" type="text/css" rel="stylesheet" />
	<script src="${serverUrl }js/jquery.js" type="text/javascript"></script>
	<script src="${serverUrl }js/header.js" type="text/javascript"></script>
</head>
<body>
	<!-- 头部 -->
    <%@ include file="/WEB-INF/views/web/common/header.jsp"%>
    <div class="index-container" id="container">
        <div class="wrapper clearfix">
	            <div class="happiness-mirror box row1 fl-left" onclick="rediretUrl('幸福镜子', '/xzt/a/jzmk/scale/quiz?id=2');">
		                <div class="happiness-number">200</div>
		                <div class="happiness-desc">幸福指数平均值</div>
		                <div class="happiness-chart"><img src="${serverUrl }images/chart.png"  alt="" height="100%"></div>
		                <div class="bg-img"><img src="${serverUrl }images/happiness-mirror.png" alt="" height="100%"></div>
	            </div>
            <div class="consultant box row1 fl-left">
                <div class="clearfix headline">
                    <div class="subject fl-left">
                        <img src="${serverUrl }images/consultant.png" onclick="rediretUrl('咨询师管理', '/xzt/a/counsel/counselor/list?indexFlag=1');" alt="咨询师管理" class="fl-left">
                        <span class="ch text fl-left">咨询师</span>
                        <span class="en text fl-left">Consultant</span>
                    </div>
                    <a href="javascript:rediretUrl('咨询师管理', '/xzt/a/counsel/counselor/list?indexFlag=1');" class="pc-more more fl-right">查看更多>></a>
                    <a href="javascript:rediretUrl('咨询师管理', '/xzt/a/counsel/counselor/list?indexFlag=1');" class="mobile-more more fl-right">更多>></a>
                </div>
                <ul class="list clearfix">
                <c:forEach items="${counselors}" var="counselor">
                 <li>
                 	<a href="javascript:rediretUrl('咨询师管理详情', 'counsel/counselor/form?id=${counselor.id}');" title="${counselor.instro }">
                     <div class="frame">
                         <img src="${serverUrl }images/human.png" onclick="rediretUrl('咨询师管理详情', 'counsel/counselor/form?id=${counselor.id}');" alt="${counselor.instro }">
                     </div>
                     <div class="info">
                         <p>${ counselor.user.name }</p>
                         <p>${fns:abbr(counselor.instro,30)} </p>
                     </div>
                    </a>
                 </li>
                </c:forEach>
                </ul>
            </div>
            <div class="announcement box row1 fl-left">
                <div class="clearfix headline">
	                    <div class="subject">
	                        <img src="${serverUrl }images/announcement.png" onclick="javascript:gotoPage('afficheList');" alt="公告管理" class="fl-left">
	                        <span class="ch text fl-left">公告</span>
	                        <span class="en text fl-left">Announcement</span>
	                    </div>
                </div>
                <ul class="list">
                <c:forEach items="${sysAffiches}" var="sysAffiche">
                    <li>
                        <a href="afficheView?id=${sysAffiche.id}">
                            <div class="title">${sysAffiche.afficheTitle}</div>
                            <div class="date"><format:formatDate value="${sysAffiche.createDate}"/></div>
                        </a>
                    </li>
                </c:forEach>
                </ul>
                <div onclick="javascript:gotoPage('afficheList');"  class="pc-more more">查看更多>></div>
            </div>
            <div class="activities box row2 fl-left">
                <div class="clearfix headline">
                    <div class="subject fl-left">
                        <img src="${serverUrl }images/activities.png" href="javascript:rediretUrl('查看公益活动', '/xzt/a/ante/publicActivity/list');" alt="公益活动" class="fl-left">
                        <span class="ch text fl-left">公益活动</span>
                        <span class="en text fl-left">activities</span>
                    </div>
                    <a href="javascript:rediretUrl('查看公益活动', '/xzt/a/ante/publicActivity/list');" class="pc-more more fl-right">查看更多>></a>
                    <a href="javascript:rediretUrl('查看公益活动', '/xzt/a/ante/publicActivity/list');" class="mobile-more more fl-right">更多>></a>
                </div>
                <ul class="list clearfix">
                	<c:forEach items="${publicActivitys}" var="publicActivity">
                	<li>
                        <a href="javascript:rediretUrl('查看公益活动详情', '/xzt/a/ante/publicActivity/form?id=${publicActivity.id}');" title="${publicActivity.title}">
                            <div class="frame">
                                <img src="${serverUrl }images/flower.png" onclick="rediretUrl('查看公益活动详情', '/xzt/a/ante/publicActivity/form?id=${publicActivity.id}');" alt="${publicActivity.title}" >
                            </div>
                            <div class="info">
                                <div class="title">
                                    <p>${publicActivity.title}</p>
                                    <p>${publicActivity.content}</p>
                                </div>
                                <div class="date"><format:formatDate value="${publicActivity.createDate}"/></div>
                            </div>
                        </a>
                    </li>
                	</c:forEach>
                </ul>
            </div>
            <div class="interflow box row2 fl-left">
                 <div class="counsel" data-toggle="tab" onclick="rediretUrl('预约咨询管理','/xzt/a/counsel/counselorSchedule/list');">
                    	预约咨询
                </div>
                <div class="case" onclick="rediretUrl('成功安例管理','/xzt/a/relax/successCase');">
                    	成功安例
                </div>
            </div>
            <div class="scan row2 box fl-left">
                <div class="qrcode">
                    <img src="${serverUrl }images/qrcode.png" onclick="rediretUrl('心知堂APP', '/xzt/a/ante/publicActivity/form?id=${publicActivity.id}');" alt="心知堂APP下载" class="block">
                    	心知堂APP下载
                </div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <%@ include file="/WEB-INF/views/web/common/footer.jsp" %>
    <script type="text/javascript">
    var userType = '${currentUser.userType}';
   	function rediretUrl(title, url){
   		if(userType){
   			location.href="${ctx}/backstage?rediretUrl=" + url + "&title="+ title;
   		}else{
   			location.href="${ctx}/web/index";
   		}
    }
   	function gotoPage(url){
   			location.href=url;
    }
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
    </script>
</body>
</html>