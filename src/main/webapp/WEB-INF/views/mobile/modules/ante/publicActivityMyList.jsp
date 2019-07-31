<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/style.css" />  
<link rel="stylesheet" href="${ctxStatic}/mobile-css/mobileSelect.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile-css/commonweal.css" />  
<script type="text/javascript"  src="${ctxStatic}/mobile-js/jquery-min.js"></script>
<script type="text/javascript"  src="${ctxStatic}/mobile-js/mobileSelect.js"></script>  
    <%-- <%@ include file="/WEB-INF/views/include/head.jsp" %> --%>
    <title>${fns:getConfig('productName')} 我的公益</title>
    <style>
		.rwglul{padding-bottom: 4rem;}
	</style>

</head>
<body>
	<div class="Header">
		<a href="${ctx}/ante/publicActivity/list" class="Return"><i></i></a>
		<h1>公益活动</h1>
		<a href="#" onclick="delOne('${publicActivity.id}')" class="HeaderTxt">删除</a>
	</div>
	<form action="${ctx}/ante/publicActivity/form?myActivity=true" method="post">
  	<div class="main">
		<div class="Headerbg"></div>
		<div class="commonboxbanner"><a href="#"><img src="${ctxStatic }/mobile-images/tu1.png" width="100%"></a></div>
		<div class="clear"></div>
		<ul class="SupportNav">
			<li><a href="${ctx}/ante/publicActivity/list" >全部公益</a></li>
			<li><a href="" class="on">我的公益</a></li>
		</ul>
		<div class="conBox">
			<ul class="rwglul">
				<c:forEach items="${page.list}" var="publicActivityMy">
					<li>
						<a href="${ctx}/ante/publicActivity/view?id=${publicActivityMy.id}">
						<p class="p1"><span class="left">公益活动标题${publicActivityMy.title}</span><span class="right ys">${fns:getDictLabel(publicActivityMy.activityStatus, 'public_activity_status', '')}</span></p>
						<p class="p3"><span class="left">参与人数：xxx</span><span class="right ys1"><fmt:formatDate value="${publicActivityMy.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
						</a>
						<div class="clear"></div>
					</li>
					</c:forEach>
				</ul>
		</div>
	</div>
	<div class="buttonbot">
		<input type="submit" value="发布公益活动" class="buttoninput">
	</div>
	</form>
</body>
<script>
	$(function(){
		$(".SupportNav li").click(function(){
			$(".SupportNav li a").removeClass("on");
			$(this).children("a").addClass("on");
			/* var oIndex = $(this).index();
			$(".conBox>ul").eq(oIndex).show().siblings().hide(); */
		});
		
	})
</script>
<script type="text/javascript">
		/**
		 * 报名
		 */
		function enroll(id){
			layer.confirm('是否报名活动？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/ante/publicActivity/enroll?id="+id;
			    layer.msg('报名成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
		
		/**
		 * 单个删除
		 */
		function delOne(id){
			layer.confirm('是否删除记录？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				document.location.href="${ctx}/ante/publicActivity/delete?id="+id+"&flag=1";
			    layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
		
		/**
		 * 批量删除
		 */
		function delAll(){
			var sumChecked=$("input:checked").length;
			if(sumChecked<1){
				layer.msg('请选择要删除的记录！', {icon: 6});
				return false;
			}
			var a = $.map($("#publicActivityMyTable").bootstrapTable('getSelections'), function (row) {
		        return row.id;
		    });
			var ids=JSON.stringify(a);
			layer.confirm('是否删除选中的记录？', {
			    btn: ['确认','取消'], //按钮
			    title:'提示',
			    shade: false //不显示遮罩
			}, function(){
				window.location.href="${ctx}/ante/publicActivity/deleteAll?ids="+ids+"&myActivity="+true;
			    //layer.msg('删除成功！', {icon: 6});
			}, function(){
			    layer.msg('已取消', {icon: 1});
			});
		}
	</script>
</html>