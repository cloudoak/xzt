<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>音乐分类列表</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}<%--
		.ztree li span.button.level0, .ztree li a.level0 {display:none;height:0;}
		.ztree li ul.level0 {padding:0;background:none;}--%>
		.accordion-inner{padding:2px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			var setting = {view:{selectedMulti:false},data:{simpleData:{enable:true}}};
			var zNodes=[
		            <c:forEach items="${musicCatelogList}" var="tpl">{id:'${tpl.id}', pId:'${not empty tpl.parent?tpl.parent.id:0}', name:"${tpl.name}", url:"${ctx}/relax/music/list?musicCatalogParentids=${tpl.parentIds}&musicCatalogId=${tpl.id}", target:"musicMainFrame"},
		            </c:forEach>];
			// 初始化树结构
			var tree = $.fn.zTree.init($("#tree"), setting, zNodes);
			// 展开第一级节点
			var nodes = tree.getNodesByParam("level", 0);
			for(var i=0; i<nodes.length; i++) {
				tree.expandNode(nodes[i], true, true, false);
			}
			// 展开第二级节点
			nodes = tree.getNodesByParam("level", 1);
			for(var i=0; i<nodes.length; i++) {
				tree.expandNode(nodes[i], true, true, false);
			}
			wSize();
		});
		$(window).resize(function(){
			wSize();
		});
		function wSize(){
			$(".ztree").width($(window).width()-16).height($(window).height()-62);
			$(".ztree").css({"overflow":"auto","overflow-x":"auto","overflow-y":"auto"});
			$("html,body").css({"overflow":"hidden","overflow-x":"hidden","overflow-y":"hidden"});
		}
	</script>
</head>
<body>
	<div class="accordion-group">
	    <div class="accordion-heading">
	    	<a class="accordion-toggle">音乐分类列表</a>
	    </div>
	    <div class="accordion-body">
			<div class="accordion-inner">
				<div id="tree" class="ztree"></div>
			</div>
	    </div>
	</div>
</body>
</html>