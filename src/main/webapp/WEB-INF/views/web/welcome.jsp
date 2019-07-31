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
	<br /> 
    <div class="index-container" id="container">
    <div class="wrapper clearfix">
    <div class="consultant box row1 fl-left">
       <div class="clearfix headline">
           <div class="subject fl-left">
               <img src="${serverUrl }images/family.png" alt="咨询中心介绍" class="fl-left">
               <span class="ch text fl-left">咨询中心介绍</span>
               <span class="en text fl-left">Consultant</span>
           </div>
       </div>
        <ul class="list clearfix">
         <li>
           <div class="info">
               <p>咨询中心介绍：${counselCenter.intro }</p>
               <p>咨询中心制度：${counselCenter.institution }</p>
               <p>工作时间：${counselCenter.workHour }</p>
               <p>咨询中心地址：${counselCenter.address }</p>
               <p>联系方式：${counselCenter.contactWay }</p>
          </div>
         </li>
        </ul>
     </div>
     </div>
    </div>
    <script type="text/javascript">
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
            if (isPc()) {
                if ($(window).width() > 640) {
                    containerHeight = winHeight - $('#header').innerHeight() - $('#footer').innerHeight();
                    $('#container .portalPanel').height(containerHeight);
                }
            } 
        }
        adjustScreen();
        $(window).resize(function () {
            adjustScreen();
        });
    </script>
</body>
</html>