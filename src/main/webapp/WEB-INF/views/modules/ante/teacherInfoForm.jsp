<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
	</style>
	<script type="text/javascript">
		$(function(){
			/* $("#officeContent").document.body.scrollTop = "500"; */
			$("#officeContent").document.body.scrollTop = "500";
		});
	</script>
</head>
<body>
	<sys:message content="${message}"/>
	<div id="content" class="row-fluid">
		<div id="left" class="accordion-group">
			<div class="accordion-heading">
		    	<a class="accordion-toggle">组织机构<i class="icon-refresh pull-right" onclick="refreshTree();"></i></a>
		    </div>
			<div id="ztree" class="ztree"></div>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<c:choose>
  				<c:when test="${counselor.id==''}">
					<iframe id="officeContent" name="officeContentName" src="${ctx}/ante/teacherInfo/toTeacherInfoRight" width="100%" height="91%" frameborder="0"></iframe>
  				</c:when>
  				<c:otherwise>
					<iframe id="officeContent" name="officeContentName" src="${ctx}/ante/teacherInfo/toTeacherInfoRight?id=${counselor.id}" width="100%" height="91%" frameborder="0"></iframe>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript">
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
					var id = treeNode.pId == '0' ? '' :treeNode.pId;
					/* $('#officeContent').attr("src","${ctx}/sys/office/list?id="+id+"&parentIds="+treeNode.pIds); */
					/* $('#officeIdInput').attr("value",id); */
					window.frames["officeContentName"].document.getElementById("officeIdInput").value=id;
				}
			}
		};
		
		function refreshTree(){
			$.getJSON("${ctx}/sys/office/treeData",function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
			});
		}
		refreshTree();
		 
		var leftWidth = 180; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe"); 
		/* var frameObj = $("#left"); */
		function wSize(){
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":"hidden", "overflow-y":"hidden"});
			mainObj.css("width","auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			$(".ztree").width(leftWidth - 10).height(frameObj.height() - 46);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>